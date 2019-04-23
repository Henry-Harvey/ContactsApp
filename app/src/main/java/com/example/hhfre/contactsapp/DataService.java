package com.example.hhfre.contactsapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataService {

    ObjectMapper om = new ObjectMapper();

    Context context;

    public DataService(Context context){
        this.context = context;
    }

    public void writeList(AddressBook addressBook, String filename){

        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        try{

            om.writerWithDefaultPrettyPrinter().writeValue(file, addressBook);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public AddressBook readList(String filename){
        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        AddressBook returnList = new AddressBook();
        try{
            returnList = om.readValue(file, AddressBook.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return returnList;
    }
}
