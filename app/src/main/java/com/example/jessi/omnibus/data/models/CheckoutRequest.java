package com.example.jessi.omnibus.data.models;

import java.util.Map;

public class CheckoutRequest {

    private String routeName;
    private String busId;
    private String fare;
    private String couponDiscount;
    private String servicetax;
    private String journyDate;
    private String boardingTime;
    private String droppingTime;
    private String duration;
    private String passengerId;
    private String passengereMail;
    private String passengermobile;
    private Map<String,String> seatSelected;
    private Map<String,String> passengerName;
    private Map<String,String> passengerage;
    private Map<String,String> passengergender;

    public CheckoutRequest() {
    }

    public CheckoutRequest(String routeName, String busId, String fare, String couponDiscount, String servicetax, String journyDate, String boardingTime, String droppingTime, String duration, String passengerId, String passengereMail, String passengermobile, Map<String, String> seatSelected, Map<String, String> passengerName, Map<String, String> passengerage, Map<String, String> passengergender) {
        this.routeName = routeName;
        this.busId = busId;
        this.fare = fare;
        this.couponDiscount = couponDiscount;
        this.servicetax = servicetax;
        this.journyDate = journyDate;
        this.boardingTime = boardingTime;
        this.droppingTime = droppingTime;
        this.duration = duration;
        this.passengerId = passengerId;
        this.passengereMail = passengereMail;
        this.passengermobile = passengermobile;
        this.seatSelected = seatSelected;
        this.passengerName = passengerName;
        this.passengerage = passengerage;
        this.passengergender = passengergender;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(String couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public String getServicetax() {
        return servicetax;
    }

    public void setServicetax(String servicetax) {
        this.servicetax = servicetax;
    }

    public String getJournyDate() {
        return journyDate;
    }

    public void setJournyDate(String journyDate) {
        this.journyDate = journyDate;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getDroppingTime() {
        return droppingTime;
    }

    public void setDroppingTime(String droppingTime) {
        this.droppingTime = droppingTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengereMail() {
        return passengereMail;
    }

    public void setPassengereMail(String passengereMail) {
        this.passengereMail = passengereMail;
    }

    public String getPassengermobile() {
        return passengermobile;
    }

    public void setPassengermobile(String passengermobile) {
        this.passengermobile = passengermobile;
    }

    public Map<String, String> getSeatSelected() {
        return seatSelected;
    }

    public void setSeatSelected(Map<String, String> seatSelected) {
        this.seatSelected = seatSelected;
    }

    public Map<String, String> getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(Map<String, String> passengerName) {
        this.passengerName = passengerName;
    }

    public Map<String, String> getPassengerage() {
        return passengerage;
    }

    public void setPassengerage(Map<String, String> passengerage) {
        this.passengerage = passengerage;
    }

    public Map<String, String> getPassengergender() {
        return passengergender;
    }

    public void setPassengergender(Map<String, String> passengergender) {
        this.passengergender = passengergender;
    }
}
