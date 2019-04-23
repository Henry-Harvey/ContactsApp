package com.example.hhfre.contactsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class ShowOneBusinessContact extends AppCompatActivity {

    Button btn_call, btn_text, btn_email, btn_navigateTo, btn_openURL, btn_edit, btn_delete;
    TextView tv_name, tv_relationship, tv_phone, tv_email, tv_businessHours, tv_website, tv_address;
    ImageView iv_profPic;

    public ArrayList<String> properties;

    AddressBook addressBook;

    static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_contact);

        addressBook = ((MyGlobalApplication) this.getApplication()).getAddressBook();
        properties = new ArrayList<>();

        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_email = findViewById(R.id.btn_email);
        btn_navigateTo = findViewById(R.id.btn_navigateTo);
        btn_openURL = findViewById(R.id.btn_openURL);
        btn_delete = findViewById(R.id.btn_delete);
        tv_name = findViewById(R.id.tv_name);
        tv_relationship = findViewById(R.id.tv_relationship);
        tv_phone = findViewById(R.id.tv_phone);
        tv_email = findViewById(R.id.tv_email);
        tv_businessHours = findViewById(R.id.tv_businessHours);
        tv_website = findViewById(R.id.tv_website);
        tv_address = findViewById(R.id.tv_address);
        iv_profPic = findViewById(R.id.iv_profPic);


        Bundle b_incomingIntent = getIntent().getExtras();
        if (b_incomingIntent != null) {
            String pictureNumber = b_incomingIntent.getString("b_pictureNumber");
            String lastName = b_incomingIntent.getString("b_lastName");
            String firstName = b_incomingIntent.getString("b_firstName");
            String relationship = b_incomingIntent.getString("b_relationship");
            String phoneNumber = b_incomingIntent.getString("b_phoneNumber");
            String email = b_incomingIntent.getString("b_email");
            String street = b_incomingIntent.getString("b_street");
            String city = b_incomingIntent.getString("b_city");
            String state = b_incomingIntent.getString("b_state");
            String zip = b_incomingIntent.getString("b_zip");
            String country = b_incomingIntent.getString("b_country");
            String businessHours = b_incomingIntent.getString("b_businessHours");
            String website = b_incomingIntent.getString("b_website");
            String deletePosition = b_incomingIntent.getString("b_position");

            properties.add(pictureNumber);
            properties.add(lastName);
            properties.add(firstName);
            properties.add(relationship);
            properties.add(phoneNumber);
            properties.add(email);
            properties.add(street);
            properties.add(city);
            properties.add(state);
            properties.add(zip);
            properties.add(country);
            properties.add(businessHours);
            properties.add(website);
            properties.add(deletePosition);

            tv_name.setText(firstName + " " + lastName);
            tv_relationship.setText(relationship);
            tv_phone.setText(phoneNumber);
            tv_email.setText(email);
            tv_businessHours.setText(businessHours);
            tv_website.setText(website);
            tv_address.setText(street + ", " + city + ", " + state + ", " + zip + ", " + country);

            Glide.with(this).load(new File(pictureNumber)).into(iv_profPic);

            btn_edit = findViewById(R.id.btn_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), EditBusinessContact.class);
                    i.putExtra("b_pictureNumber", properties.get(0));
                    i.putExtra("b_lastName", properties.get(1));
                    i.putExtra("b_firstName", properties.get(2));
                    i.putExtra("b_relationship", properties.get(3));
                    i.putExtra("b_phoneNumber", properties.get(4));
                    i.putExtra("b_email", properties.get(5));
                    i.putExtra("b_street", properties.get(6));
                    i.putExtra("b_city", properties.get(7));
                    i.putExtra("b_state", properties.get(8));
                    i.putExtra("b_zip", properties.get(9));
                    i.putExtra("b_country", properties.get(10));
                    i.putExtra("b_businessHours", properties.get(11));
                    i.putExtra("b_website", properties.get(12));
                    i.putExtra("b_position", properties.get(13));

                    startActivity(i);
                }
            });
        }

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Search.class);
                addressBook.deleteOne(addressBook.getList().get(Integer.parseInt(properties.get(13))));
                startActivity(i);
            }
        });

        // Android Services Buttons
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(tv_phone.getText().toString(), "");
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(tv_phone.getText().toString());
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] addresses = new String [1];
                addresses[0] = tv_email.getText().toString();
                composeEmail(addresses, "Hello");
            }
        });

        btn_openURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(tv_website.getText().toString());
            }
        });

        btn_navigateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap(Uri.parse("geo:0,0?q=" + tv_address.getText().toString()));
            }
        });

    }

    // Android Services Methods
    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (ContextCompat.checkSelfPermission(ShowOneBusinessContact.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(ShowOneBusinessContact.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else{
                startActivity(intent);
            }
        }
    }

    //  Request Access to Call
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPhoneNumber(tv_phone.getText().toString());
            }
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        if(!url.startsWith("http://") || !url.startsWith("https://")){
            url = "http://" + url;
        }
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
