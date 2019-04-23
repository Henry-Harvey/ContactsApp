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

public class ShowOnePersonalContact extends AppCompatActivity {

    Button btn_call, btn_text, btn_email, btn_navigateTo, btn_openURL, btn_edit, btn_delete;
    TextView tv_name, tv_relationship, tv_phone, tv_email, tv_nickname, tv_birthday, tv_address;
    ImageView iv_profPic;

    public ArrayList<String> properties;

    AddressBook addressBook;

    static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_contact);

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
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_address = findViewById(R.id.tv_address);
        iv_profPic = findViewById(R.id.iv_profPic);

        Bundle p_incomingIntent = getIntent().getExtras();
        if (p_incomingIntent != null) {
            String pictureNumber = p_incomingIntent.getString("p_pictureNumber");
            String lastName = p_incomingIntent.getString("p_lastName");
            String firstName = p_incomingIntent.getString("p_firstName");
            String relationship = p_incomingIntent.getString("p_relationship");
            String phoneNumber = p_incomingIntent.getString("p_phoneNumber");
            String email = p_incomingIntent.getString("p_email");
            String street = p_incomingIntent.getString("p_street");
            String city = p_incomingIntent.getString("p_city");
            String state = p_incomingIntent.getString("p_state");
            String zip = p_incomingIntent.getString("p_zip");
            String country = p_incomingIntent.getString("p_country");
            String nickname = p_incomingIntent.getString("p_nickname");
            String birthday = p_incomingIntent.getString("p_birthday");
            String deletePosition = p_incomingIntent.getString("p_position");

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
            properties.add(nickname);
            properties.add(birthday);
            properties.add(deletePosition);

            tv_name.setText(firstName + " " + lastName);
            tv_relationship.setText(relationship);
            tv_phone.setText(phoneNumber);
            tv_email.setText(email);
            tv_nickname.setText(nickname);
            tv_birthday.setText(birthday);
            tv_address.setText(street + ", " + city + ", " + state + ", " + zip + ", " + country);

            Glide.with(this).load(new File(pictureNumber)).into(iv_profPic);

            btn_edit = findViewById(R.id.btn_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), EditPersonalContact.class);
                    i.putExtra("p_pictureNumber", properties.get(0));
                    i.putExtra("p_lastName", properties.get(1));
                    i.putExtra("p_firstName", properties.get(2));
                    i.putExtra("p_relationship", properties.get(3));
                    i.putExtra("p_phoneNumber", properties.get(4));
                    i.putExtra("p_email", properties.get(5));
                    i.putExtra("p_street", properties.get(6));
                    i.putExtra("p_city", properties.get(7));
                    i.putExtra("p_state", properties.get(8));
                    i.putExtra("p_zip", properties.get(9));
                    i.putExtra("p_country", properties.get(10));
                    i.putExtra("p_nickname", properties.get(11));
                    i.putExtra("p_birthday", properties.get(12));
                    i.putExtra("p_position", properties.get(13));

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
            if (ContextCompat.checkSelfPermission(ShowOnePersonalContact.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(ShowOnePersonalContact.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
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

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
