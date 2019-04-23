package com.example.hhfre.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Index extends AppCompatActivity {

    Button btn_addNewConatct, btn_searchContact;
    AddressBook addressBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // load
        DataService dataService = new DataService(MyGlobalApplication.getAppContext());
        addressBook = ((MyGlobalApplication) this.getApplication()).getAddressBook();
        addressBook = dataService.readList("stored_address_book.txt");
        ((MyGlobalApplication) this.getApplication()).setAddressBook(addressBook);

        btn_addNewConatct = findViewById(R.id.btn_addNewContact);
        btn_addNewConatct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), NewContact.class);
                startActivity(goToNextPage);
            }
        });

        btn_searchContact = findViewById(R.id.btn_searchContact);
        btn_searchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), Search.class);
                startActivity(goToNextPage);
            }
        });


    }
}
