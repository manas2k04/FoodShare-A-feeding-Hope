package com.manas.mainactivity;

public class Donation {
    private String name;
    private String foodName;
    private String pincode;
    private String address;

    // Empty constructor for Firebase
    public Donation() {
    }

    public Donation(String name, String foodName, String pincode, String address) {
        this.name = name;
        this.foodName = foodName;
        this.pincode = pincode;
        this.address = address;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPincode() {
        return pincode;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

