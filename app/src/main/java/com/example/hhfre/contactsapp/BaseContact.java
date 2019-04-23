package com.example.hhfre.contactsapp;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = BaseContact.class, name = "base"),
        @Type(value = PersonalContact.class, name = "personal"),
        @Type(value = BusinessContact.class, name = "business")})

    public class BaseContact implements Comparable<BaseContact> {
        protected String pictureNumber;
        protected String lastName;
        protected String firstName;
        protected String relationship;
        protected String phoneNumber;
        protected String email;
        protected String street;
        protected String city;
        protected String state;
        protected String zip;
        protected String country;

        //super
        public BaseContact(String pictureNumber, String lastName, String firstName, String relationship, String phoneNumber, String email,
                           String street, String city, String state, String zip, String country) {
            super();
            this.pictureNumber = pictureNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.relationship = relationship;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.country = country;
        }

        //default void constructor
        public BaseContact() {
            this.pictureNumber = "N/A";
            this.firstName = "N/A";
            this.lastName = "N/A";
            this.relationship = "N/A";
            this.phoneNumber = "N/A";
            this.email = "N/A";
            this.street = "N/A";
            this.city = "N/A";
            this.state = "N/A";
            this.zip = "N/A";
            this.country = "N/A";
        }


        public void textContact() {
            System.out.println("Opens Texting App");
        }

        public void callContact() {
            System.out.println("Opens Phone App");
        }

        public void emailContact() {
            System.out.println("Opens Email App");
        }

        public void navigateToContact() {
            System.out.println("Opens Maps App");
        }

        //getters & setters
        public String getPictureNumber() {
            return pictureNumber;
        }

        public void setPictureNumber(String pictureNumber) {
            this.pictureNumber = pictureNumber;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String name) {
            this.lastName = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String name) {
            this.firstName = name;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public int compareTo(BaseContact other) {
            int compareResult = this.lastName.compareTo(other.lastName);

            if (compareResult == 0) {
                return this.firstName.compareTo(other.firstName);
            } else {
                // the last names do not match
                return compareResult;
            }

            // do both last name and first name match? return true

            // if this = other, return 0
            // if this > other, return 1
            // if this < other, return -1
        }

        public String toString() {
            return "\r\n=============================================== \r\n"
                    + "Name: " + this.lastName + ", " + this.firstName + "\r\n"
                    + "Relationship: " + this.relationship + "\r\n"
                    + "Phone Number: " + this.phoneNumber + "\r\n"
                    + "Email: " + this.email + "\r\n"
                    + "Address: " + this.street + ", " + this.city + ", " + this.state + ", " + this.zip + ", " + this.country + "\r\n"
                    + "=============================================== \r\n";
        }

    }

