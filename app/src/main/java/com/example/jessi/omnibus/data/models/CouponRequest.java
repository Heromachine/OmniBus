package com.example.jessi.omnibus.data.models;

public class CouponRequest {

    private String couponNo;

    public CouponRequest(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }
}
