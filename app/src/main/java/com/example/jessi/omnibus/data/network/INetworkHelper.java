package com.example.jessi.omnibus.data.network;

import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.BusInfoRequest;
import com.example.jessi.omnibus.data.models.CheckoutRequest;
import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.LoginRequest;
import com.example.jessi.omnibus.data.models.RegistrationRequest;
import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.data.models.SeatInfoRequest;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;

public interface INetworkHelper {

    void networkRetrofitCallRegistration
            (IDataManger.RegistrationOnResponseListener listener, RegistrationRequest registrationRequest);

    void networkRetrofitCallLogin
            (IDataManger.LogInOnResponseListener listener, LoginRequest loginRequest);

    void networkRetrofitCallForgotPW
            (IDataManger.ForgotPWOnResponseListener listener, ForgotPWRequest forgotPWRequest);

    void networkRetrofitCallCities
            (IDataManger.CitiesOnResponseListener listener);

    void networkRetrofitCallRoutes
            (IDataManger.RoutesOnResponseListener listener, RouteRequest routeRequest);

    void networkRetrofitCallBusInfo
            (IDataManger.BusInfoOnResponseListener listener, BusInfoRequest busInfoRequest);

    void networkRetrofitCallSeats
            (IDataManger.SeatInfoOnResponseListener listener, SeatInfoRequest seatInfoRequest);

    void networkRetrofitCallSeatReservation
            (IDataManger.SeatReserveConfirmOnResponseListener listener, SeatReservationRequest seatReservationRequest);

    void networkRetrofitCallCoupons
            (IDataManger.CouponsOnResponseListener listener);

    void networkRetrofitCallCouponValidation
            (IDataManger.CouponValidationOnResponseListener listener, CouponRequest couponRequest);

    void networkRetrofitCallCheckOutInfo
            (IDataManger.CheckoutOnResposneListener listener, CheckoutRequest checkoutRequest);
}
