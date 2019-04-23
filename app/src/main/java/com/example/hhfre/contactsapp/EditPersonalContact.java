package com.example.hhfre.contactsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditPersonalContact extends AppCompatActivity {

    ContactAdapter adapter;
    AddressBook addressBook;

    Button btn_submit, btn_cancel, btn_takePhoto, btn_loadPhoto;

    EditText et_pictureNumber, et_firstName, et_lastName, et_phone, et_email, et_birthday, et_nickname, et_relationship, et_street, et_city, et_state, et_zip, et_country;

    ImageView iv_profPic;

    String deletePosition;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SELECT_A_PHOTO = 2;

    // File saved by camera
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_contact);

        et_pictureNumber = findViewById(R.id.et_pictureNumber);
        et_firstName = findViewById(R.id.et_name);
        et_lastName = findViewById(R.id.et_lastName);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_birthday = findViewById(R.id.et_businessHours);
        et_nickname = findViewById(R.id.et_nickname);
        et_relationship = findViewById(R.id.et_relationship);
        et_street = findViewById(R.id.et_street);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_zip = findViewById(R.id.et_zip);
        et_country = findViewById(R.id.et_country);
        iv_profPic = findViewById(R.id.iv_profPic);

        addressBook = ((MyGlobalApplication) this.getApplication()).getAddressBook();
        adapter = new ContactAdapter(EditPersonalContact.this, addressBook.getList());

        Bundle p_incomingIntent = getIntent().getExtras();
        if (p_incomingIntent != null) {
            String pictureNumber = p_incomingIntent.getString("p_pictureNumber");
            Glide.with(this).load(new File(pictureNumber)).into(iv_profPic);

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
            deletePosition = p_incomingIntent.getString("p_position");

            et_pictureNumber.setText(pictureNumber);
            et_lastName.setText(lastName);
            et_firstName.setText(firstName);
            et_relationship.setText(relationship);
            et_phone.setText(phoneNumber);
            et_email.setText(email);
            et_street.setText(street);
            et_city.setText(city);
            et_state.setText(state);
            et_zip.setText(zip);
            et_country.setText(country);
            et_nickname.setText(nickname);
            et_birthday.setText(birthday);

        }

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((et_country.getText().toString().equals("") || et_zip.getText().toString().equals("") || et_state.getText().toString().equals("") ||
                        et_city.getText().toString().equals("") || et_street.getText().toString().equals("") || et_relationship.getText().toString().equals("") ||
                        et_birthday.getText().toString().equals("") || et_nickname.getText().toString().equals("") || et_email.getText().toString().equals("") ||
                        et_phone.getText().toString().equals("") || et_lastName.getText().toString().equals("") || et_firstName.getText().toString().equals("") ||
                        et_pictureNumber.getText().toString().equals("")) || (!et_pictureNumber.getText().toString().startsWith("/storage/") &&
                        !et_pictureNumber.getText().toString().startsWith("content://"))){
                    Toast.makeText(EditPersonalContact.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String newPictureNumber = et_pictureNumber.getText().toString();
                    String newFirstName = et_firstName.getText().toString();
                    String newLastName = et_lastName.getText().toString();
                    String newPhone = et_phone.getText().toString();
                    String newEmail = et_email.getText().toString();
                    String newBirthday = et_birthday.getText().toString();
                    String newNickname = et_nickname.getText().toString();
                    String newRelationship = et_relationship.getText().toString();
                    String newStreet = et_street.getText().toString();
                    String newCity = et_city.getText().toString();
                    String newState = et_state.getText().toString();
                    String newZip = et_zip.getText().toString();
                    String newCountry = et_country.getText().toString();

                    PersonalContact p = new PersonalContact(newPictureNumber, newLastName, newFirstName, newRelationship, newPhone, newEmail, newStreet,
                            newCity, newState, newZip, newCountry, newNickname, newBirthday);
                    addressBook.deleteOne(addressBook.getList().get(Integer.parseInt(deletePosition)));
                    addressBook.getList().add(p);
                    adapter.notifyDataSetChanged();

                    Intent goToNextPage = new Intent(v.getContext(), Search.class);
                    startActivity(goToNextPage);
                }
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), Search.class);
                startActivity(goToNextPage);
            }
        });

        btn_takePhoto = findViewById(R.id.btn_takePhoto);
        btn_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btn_loadPhoto = findViewById(R.id.btn_loadPhoto);
        btn_loadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_A_PHOTO);
            }
        });

    }

    // Opens camera app
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.hhfre.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    // Gets image from camera
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Glide.with(this).load(currentPhotoPath).into(iv_profPic);
            et_pictureNumber.setText(currentPhotoPath);
        }
        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK) {
            Uri selectedPhoto = data.getData();
            Glide.with(this).load(selectedPhoto).into(iv_profPic);
            et_pictureNumber.setText(selectedPhoto.toString());
        }
    }
}
