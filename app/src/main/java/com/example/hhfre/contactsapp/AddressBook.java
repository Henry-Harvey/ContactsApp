package com.example.hhfre.contactsapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AddressBook {
    // main storage structure for the application

    private ArrayList<BaseContact> allContactsList;

    // default constructor, creates empty list of contacts
    public AddressBook() {
        this.allContactsList = new ArrayList<BaseContact>();
        /*PersonalContact p = new PersonalContact("2", "Sanchez", "Sylvia", "friend", "(123)456-7890", "ssanchez@email.com", "123 Grove St",
                "Phoenix", "AZ", "85017", "U.S.","Sylv", "01-01-2000");
        PersonalContact p1 = new PersonalContact("3", "Baxter", "Alfred", "friend", "(123)456-7890", "ssanchez@email.com", "123 Grove St",
                "Phoenix", "AZ", "85017", "U.S.","Alfy", "01-01-2000");
        PersonalContact p2 = new PersonalContact("4", "Adrian", "Zachery", "friend", "(123)456-7890", "ssanchez@email.com", "123 Grove St",
                "Phoenix", "AZ", "85017", "U.S.","CantAdd", "01-01-2000");
        BusinessContact b = new BusinessContact("1", "Swanson", "Bob", "friend", "(123)456-7891", "bswanson@email.com", "124 Grove St",
                "Phoenix", "AZ", "85017", "U.S.", "5-9",
                "guns.com");
        BusinessContact b2 = new BusinessContact("5", "Zach", "Adams", "friend", "(123)456-7891", "bswanson@email.com", "124 Grove St",
                "Phoenix", "AZ", "85017", "U.S.", "5-9",
                "guns.com");
        allContactsList.add(p);
        allContactsList.add(p1);
        allContactsList.add(p2);
        allContactsList.add(b);
        allContactsList.add(b2);*/
    }

    public <T extends BaseContact> void addOne(T contact) {
        this.allContactsList.add(contact);
    }

    public <T extends BaseContact> boolean deleteOne(T contact) {
        if (this.allContactsList.contains(contact)) {
            this.allContactsList.remove(contact);
            return true;
        } else {
            return false;
        }
    }

    public Set<BaseContact> searchForAny(String str) {
        Set<BaseContact> Results = new HashSet<BaseContact>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getLastName().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getFirstName().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getRelationship().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getPhoneNumber().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getEmail().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getStreet().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getCity().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getState().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getZip().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getCountry().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            }
        }
        ArrayList<BusinessContact> bList = this.getBusinessList();
        for (int i = 0; i < bList.size(); i++) {
            if (bList.get(i).getWebsite().contains(str)) {
                Results.add(bList.get(i));
            }
            if (bList.get(i).getBusinessHours().contains(str)) {
                Results.add(bList.get(i));
            }
        }
        ArrayList<PersonalContact> pList = this.getPersonalList();
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getNickname().contains(str)) {
                Results.add(pList.get(i));
            }
            if (pList.get(i).getBirthday().contains(str)) {
                Results.add(pList.get(i));
            }
        }
        return Results;
    }

    public ArrayList<BaseContact> searchForName(String str) {
        ArrayList<BaseContact> Results = new ArrayList<BaseContact>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getLastName().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            } else if (allContactsList.get(i).getFirstName().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            }
        }
        return Results;
    }

    public ArrayList<BaseContact> searchForCity(String str) {
        ArrayList<BaseContact> Results = new ArrayList<BaseContact>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getCity().toLowerCase().contains(str.toLowerCase())) {
                Results.add(allContactsList.get(i));
            }
        }
        return Results;
    }

    public ArrayList<BaseContact> searchForNumber(String str) {
        ArrayList<BaseContact> Results = new ArrayList<BaseContact>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getPhoneNumber().contains(str)) {
                Results.add(allContactsList.get(i));
            }
        }
        return Results;
    }

    public ArrayList<BaseContact> getList() {
        return allContactsList;
    }

    // dont need this right now
    /*
     * public ArrayList<BaseContact> getList(ArrayList<BaseContact> listName) {
     * return listName; }
     */

    @JsonIgnore
    public ArrayList<BusinessContact> getBusinessList() {
        ArrayList<BusinessContact> allBusinessList = new ArrayList<>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getClass() == BusinessContact.class) {
                allBusinessList.add((BusinessContact) allContactsList.get(i));
            }
        }
        return allBusinessList;
    }

    @JsonIgnore
    public ArrayList<BaseContact> getBusinessBaseList() {
        ArrayList<BaseContact> allBusinessList = new ArrayList<>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getClass() == BusinessContact.class) {
                allBusinessList.add((BusinessContact) allContactsList.get(i));
            }
        }
        return allBusinessList;
    }

    @JsonIgnore
    public ArrayList<PersonalContact> getPersonalList() {
        ArrayList<PersonalContact> allPersonalList = new ArrayList<>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getClass() == PersonalContact.class) {
                allPersonalList.add((PersonalContact) allContactsList.get(i));
            }
        }
        return allPersonalList;
    }

    @JsonIgnore
    public ArrayList<BaseContact> getPersonalBaseList() {
        ArrayList<BaseContact> allPersonalList = new ArrayList<>();
        for (int i = 0; i < allContactsList.size(); i++) {
            if (allContactsList.get(i).getClass() == PersonalContact.class) {
                allPersonalList.add((PersonalContact) allContactsList.get(i));
            }
        }
        return allPersonalList;
    }

    public String toString() {
        String wholeList = "";
        for (int i = 0; i < allContactsList.size(); i++) {
            wholeList += allContactsList.get(i);
        }
        return wholeList;
    }

    public String toString(ArrayList<BaseContact> listName) {
        String wholeList = "";
        for (int i = 0; i < listName.size(); i++) {
            wholeList += listName.get(i) + "\n";
        }
        return wholeList;
    }

}
