package com.example.jessi.omnibus.data.models;

public class RegistrationModel {

    String mobile;
    String password;

    public RegistrationModel() {
    }

    public RegistrationModel(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
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
