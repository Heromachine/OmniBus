package com.example.jessi.omnibus.data;

import com.example.jessi.omnibus.data.models.BusInfoRequest;
import com.example.jessi.omnibus.data.models.CheckoutRequest;
import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.LoginRequest;
import com.example.jessi.omnibus.data.models.RegistrationReques;
import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.data.models.SeatInfoRequest;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;
import com.example.jessi.omnibus.data.network.INetworkHelper;
import com.example.jessi.omnibus.data.network.NetworkHelper;

public class DataManager implements IDataManger {

    INetworkHelper networkHelper;

    public DataManager() {
        networkHelper = new NetworkHelper();
    }


    @Override
    public void networkRetrofitCallRegistration(RegistrationOnResponseListener listener, RegistrationReques registrationReques) {
        networkHelper.networkRetrofitCallRegistration(listener, registrationReques);
    }

    @Override
    public void networkRetrofitCallLogin(LogInOnResponseListener listener, LoginRequest loginRequest) {
        networkHelper.networkRetrofitCallLogin(listener, loginRequest);
    }

    @Override
    public void networkRetrofitCallForgotPW(ForgotPWOnResponseListener listener, ForgotPWRequest forgotPWRequest) {
        networkHelper.networkRetrofitCallForgotPW(listener, forgotPWRequest);
    }

    @Override
    public void networkRetrofitCallCities(CitiesOnResponseListener listener) {
        networkHelper.networkRetrofitCallCities(listener);
    }

    @Override
    public void networkRetrofitCallRoutes(RoutesOnResponseListener listener, RouteRequest routeRequest) {
        networkHelper.networkRetrofitCallRoutes(listener, routeRequest);
    }

    @Override
    public void networkRetrofitCallBusInfo(BusInfoOnResponseListener listener, BusInfoRequest busInfoRequest) {
        networkHelper.networkRetrofitCallBusInfo(listener, busInfoRequest);
    }

    @Override
    public void networkRetrofitCallSeats(SeatInfoOnResponseListener listener, SeatInfoRequest seatInfoRequest) {
        networkHelper.networkRetrofitCallSeats(listener, seatInfoRequest);
    }

    @Override
    public void networkRetrofitCallSeatReservation(SeatReserveConfirmOnResponseListener listener,  SeatReservationRequest seatReservationRequest) {
        networkHelper.networkRetrofitCallSeatReservation(listener, seatReservationRequest);
    }

    @Override
    public void networkRetrofitCallCoupons(CouponsOnResponseListener listener) {
        networkHelper.networkRetrofitCallCoupons(listener);
    }

    @Override
    public void networkRetrofitCallCouponValidation(CouponValidationOnResponseListener listener, CouponRequest couponRequest) {
        networkHelper.networkRetrofitCallCouponValidation(listener, couponRequest);
    }

    @Override
    public void networkRetrofitCallCheckOutInfo(CheckoutOnResposneListener listener, CheckoutRequest checkoutRequest) {
        networkHelper.networkRetrofitCallCheckOutInfo(listener, checkoutRequest);
    }


}
