package com.example.jessi.omnibus.data.models;

public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobile;
    private String password;

    boolean bValid = false;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String firstName, String lastName, String address, String email, String mobile, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public boolean isValid() {
        return bValid;
    }

    public void setValid(boolean bValid) {
        this.bValid = bValid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
