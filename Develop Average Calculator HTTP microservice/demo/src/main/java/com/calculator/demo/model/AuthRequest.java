package com.calculator.demo.model;


public class AuthRequest {
    private String email;
    private String name;
    private String rollNo;
    private String accessCode;
    private String clientID;
    private String clientSecret;

    public AuthRequest(String email, String name, String rollNo, String accessCode, String clientID, String clientSecret) {
        this.email = email;
        this.name = name;
        this.rollNo = rollNo;
        this.accessCode = accessCode;
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public AuthRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
