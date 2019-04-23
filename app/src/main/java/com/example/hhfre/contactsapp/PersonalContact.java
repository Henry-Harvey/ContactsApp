package com.example.hhfre.contactsapp;

public class PersonalContact extends BaseContact {
    private String nickname;
    private String birthday;

    //super
    public PersonalContact(String pictureNumber, String lastName, String firstName, String relationship, String phoneNumber, String email, String street,
                           String city, String state, String zip, String country, String nickname, String birthday) {
        super(pictureNumber, lastName, firstName, relationship, phoneNumber, email, street, city, state, zip, country);
        this.nickname = nickname;
        this.birthday = birthday;
    }

    //default void constructor
    public PersonalContact() {
        this.pictureNumber = "1";
        this.nickname = "N/A";
        this.birthday = "N/A";
        this.pictureNumber = "N/A";
        this.lastName = "N/A";
        this.firstName = "N/A";
        this.relationship = "N/A";
        this.phoneNumber = "N/A";
        this.email = "N/A";
        this.street = "N/A";
        this.city = "N/A";
        this.state = "N/A";
        this.zip = "N/A";
        this.country = "N/A";
    }



    //getters & setters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return "\r\n=============================================== \r\n"
                + "Name: " + this.lastName + ", " + this.firstName + "\r\n"
                + "Relationship: " + this.relationship + "\r\n"
                + "Phone Number: " + this.phoneNumber + "\r\n"
                + "Email: " + this.email + "\r\n"
                + "Address: " + this.street + ", " + this.city + ", " + this.state + ", " + this.zip + ", " + this.country + "\r\n"
                + "Nickname: " + this.nickname + "\r\n"
                + "Birthday: " + this.birthday + "\r\n"
                + "=============================================== \r\n";
    }

}

