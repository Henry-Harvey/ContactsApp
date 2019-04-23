package com.example.hhfre.contactsapp;

public class BusinessContact extends BaseContact {
    private String businessHours;
    private String website;

    // super
    public BusinessContact(String pictureNumber, String lastName, String firstName, String relationship, String phoneNumber, String email, String street,
                           String city, String state, String zip, String country, String businessHours,
                           String website) {
        super(pictureNumber, lastName, firstName, relationship, phoneNumber, email, street, city, state, zip, country);
        this.businessHours = businessHours;
        this.website = website;
    }

    // default void constructor
    public BusinessContact() {
        this.pictureNumber = "2";
        this.businessHours = "N/A";
        this.website = "N/A";
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

    public void openURL() {
        System.out.println("Opens Web Browser App");
    }

    //getters & setters
    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String toString() {
        return "\r\n=============================================== \r\n"
                + "Name: " + this.lastName + ", " + this.firstName + "\r\n"
                + "Relationship: " + this.relationship + "\r\n"
                + "Phone Number: " + this.phoneNumber + "\r\n"
                + "Email: " + this.email + "\r\n"
                + "Address: " + this.street + ", " + this.city + ", " + this.state + ", " + this.zip + ", " + this.country + "\r\n"
                + "Business Hours: " + this.businessHours + "\r\n"
                + "Website: " + this.website + "\r\n"
                + "=============================================== \r\n";
    }

}
