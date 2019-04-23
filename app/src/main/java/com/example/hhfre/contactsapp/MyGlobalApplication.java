package com.example.hhfre.contactsapp;


import android.app.Application;
import android.content.Context;

public class MyGlobalApplication extends Application {

    private AddressBook addressBook = new AddressBook();

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyGlobalApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyGlobalApplication.context;
    }
}
