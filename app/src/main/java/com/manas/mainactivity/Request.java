// Request.java
package com.manas.mainactivity;

public class Request {
    private String name;
    private String description;
    private String members;
    private String address;
    private String phoneNumber;

    // Default constructor required for Firebase
    public Request() {
        // Empty constructor for Firebase
    }

    // Constructor with parameters (you can have other constructors as needed)
    public Request(String name, String description, String members, String address, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters (you can generate them in Android Studio)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
