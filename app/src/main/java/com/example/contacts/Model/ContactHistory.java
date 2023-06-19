package com.example.contacts.Model;

public class ContactHistory {
    private String name;
    private String number;
private String timestamp;
private String otp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public ContactHistory(String name, String number, String timestamp, String otp) {
        this.name = name;
        this.number = number;
        this.timestamp = timestamp;
        this.otp = otp;
    }
}
