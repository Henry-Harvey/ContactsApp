package com.example.hhfre.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Search extends AppCompatActivity {

    Button btn_add, btn_sortLast, btn_sortFirst, btn_sortBusiness, btn_sortPersonal, btn_all, btn_search;
    EditText et_search;
    ListView lv_allContacts;

    ContactAdapter adapter;
    AddressBook addressBook;

    ArrayList<BaseContact> filteredList;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        addressBook = ((MyGlobalApplication) this.getApplication()).getAddressBook();

        adapter = new ContactAdapter(Search.this, addressBook.getList());

        btn_add = findViewById(R.id.btn_add);
        btn_search = findViewById(R.id.btn_search);
        btn_sortFirst = findViewById(R.id.btn_sortFirst);
        btn_sortLast = findViewById(R.id.btn_sortLast);
        btn_sortBusiness = findViewById(R.id.btn_sortBusiness);
        btn_all = findViewById(R.id.btn_all);
        btn_sortPersonal = findViewById(R.id.btn_sortPersonal);
        et_search = findViewById(R.id.et_search);
        lv_allContacts = findViewById(R.id.lv_allContacts);

        lv_allContacts.setAdapter(adapter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_search.getText().toString().equals("")) {
                    filteredList = addressBook.searchForName(et_search.getText().toString());
                    adapter = new ContactAdapter(Search.this, filteredList);
                    lv_allContacts.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    filteredList = addressBook.getList();
                    adapter = new ContactAdapter(Search.this, filteredList);
                    lv_allContacts.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //button clicks
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), NewContact.class);
                startActivity(goToNextPage);
            }
        });

        btn_sortLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(addressBook.getList(), new Comparator<BaseContact>() {
                    @Override
                    public int compare(BaseContact contact1, BaseContact contact2) {
                        int compareResult = contact1.lastName.compareTo(contact2.lastName);
                        if (compareResult == 0) {
                            return contact1.firstName.compareTo(contact2.firstName);
                        } else {
                            return compareResult;
                        }
                    }
                });
                filteredList = addressBook.getList();
                adapter = new ContactAdapter(Search.this, filteredList);
                lv_allContacts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        btn_sortFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(addressBook.getList(), new Comparator<BaseContact>() {
                    @Override
                    public int compare(BaseContact contact1, BaseContact contact2) {
                        int compareResult = contact1.firstName.compareTo(contact2.firstName);
                        if (compareResult == 0) {
                            return contact1.lastName.compareTo(contact2.lastName);
                        } else {
                            return compareResult;
                        }
                    }
                });
                filteredList = addressBook.getList();
                adapter = new ContactAdapter(Search.this, filteredList);
                lv_allContacts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        btn_sortPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList = addressBook.getPersonalBaseList();
                adapter = new ContactAdapter(Search.this, filteredList);
                lv_allContacts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        btn_sortBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList = addressBook.getBusinessBaseList();
                adapter = new ContactAdapter(Search.this, filteredList);
                lv_allContacts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredList = addressBook.getList();
                adapter = new ContactAdapter(Search.this, filteredList);
                lv_allContacts.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        lv_allContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(addressBook.getList().get(position).getClass() == BusinessContact.class){
                    showOneBusinessContact(position);
                }
                else{
                    showOnePersonalContact(position);
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //save
       DataService dataService = new DataService(MyGlobalApplication.getAppContext());
        dataService.writeList(addressBook, "stored_address_book.txt");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //save
        DataService dataService = new DataService(MyGlobalApplication.getAppContext());
        dataService.writeList(addressBook, "stored_address_book.txt");
    }

    public void showOneBusinessContact(int position){
        Intent i = new Intent(getApplicationContext(), ShowOneBusinessContact.class);
        BusinessContact b = (BusinessContact) addressBook.getList().get(position);
        i.putExtra("b_pictureNumber", b.getPictureNumber());
        i.putExtra("b_lastName", b.getLastName());
        i.putExtra("b_firstName", b.getFirstName());
        i.putExtra("b_relationship", b.getRelationship());
        i.putExtra("b_phoneNumber", b.getPhoneNumber());
        i.putExtra("b_email", b.getEmail());
        i.putExtra("b_street", b.getStreet());
        i.putExtra("b_city", b.getCity());
        i.putExtra("b_state", b.getState());
        i.putExtra("b_zip", b.getZip());
        i.putExtra("b_country", b.getCountry());
        i.putExtra("b_businessHours", b.getBusinessHours());
        i.putExtra("b_website", b.getWebsite());
        i.putExtra("b_position", Integer.toString(addressBook.getList().indexOf(b)));

        startActivity(i);
    }

    public void showOnePersonalContact(int position){
        Intent i = new Intent(getApplicationContext(), ShowOnePersonalContact.class);
        PersonalContact p = (PersonalContact) addressBook.getList().get(position);
        i.putExtra("p_pictureNumber", p.getPictureNumber());
        i.putExtra("p_lastName", p.getLastName());
        i.putExtra("p_firstName", p.getFirstName());
        i.putExtra("p_relationship", p.getRelationship());
        i.putExtra("p_phoneNumber", p.getPhoneNumber());
        i.putExtra("p_email", p.getEmail());
        i.putExtra("p_street", p.getStreet());
        i.putExtra("p_city", p.getCity());
        i.putExtra("p_state", p.getState());
        i.putExtra("p_zip", p.getZip());
        i.putExtra("p_country", p.getCountry());
        i.putExtra("p_nickname", p.getNickname());
        i.putExtra("p_birthday", p.getBirthday());
        i.putExtra("p_position", Integer.toString(addressBook.getList().indexOf(p)));

        startActivity(i);
    }
}
