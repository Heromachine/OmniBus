package com.example.jessi.omnibus.data.models;

public class ForgotPWRequest {
    private String mobile;

    public ForgotPWRequest(String mobile) {
        this.mobile = mobile;
    }

    public ForgotPWRequest() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
