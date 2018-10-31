package com.example.jessi.omnibus.data.models;

public class LoginRequest {
    private String mobile;
    private String password;
    private boolean bSuccess = false;

    public LoginRequest() {
    }

    public LoginRequest(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public boolean isbSuccess() {
        return bSuccess;
    }

    public void setbSuccess(boolean bSuccess) {
        this.bSuccess = bSuccess;
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
