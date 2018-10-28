package com.example.jessi.omnibus.data.network;

import android.util.Log;

import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.BusInfoModel;
import com.example.jessi.omnibus.data.models.BusInfoRequest;
import com.example.jessi.omnibus.data.models.CheckoutRequest;
import com.example.jessi.omnibus.data.models.CitiesModel;
import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.CouponValidation;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.ForgotPassword;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.LoginRequest;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.RegistrationReques;
import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.data.models.Routes;
import com.example.jessi.omnibus.data.models.SeatInfo;
import com.example.jessi.omnibus.data.models.SeatInfoRequest;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;
import com.example.jessi.omnibus.data.network.retrofit.ApiService;
import com.example.jessi.omnibus.data.network.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHelper implements INetworkHelper {
    private static final String TAG = "NetworkHelper";

    @Override
    public void networkRetrofitCallRegistration
            (final IDataManger.RegistrationOnResponseListener listener, RegistrationReques registrationReques) {
        Log.d(TAG, "networkRetrofitCallRegistration: ");
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<RegistrationModel> registrationModelCall = apiService.getRegistration(
                registrationReques.getFirstName(),
                registrationReques.getLastName(),
                registrationReques.getAddress(),
                registrationReques.getEmail(),
                registrationReques.getMobile(),
                registrationReques.getPassword()
        );
        registrationModelCall.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                Log.d(TAG, "onResponse: Registration passed ");
                RegistrationModel registrationModel = response.body();
                listener.getRegistrationModel(registrationModel);
            }
            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                Log.d(TAG, "onFailure: Registration - " + t);
            }
        });
    }

    @Override
    public void networkRetrofitCallForgotPW
            (final IDataManger.ForgotPWOnResponseListener listener, ForgotPWRequest forgotPWRequest) {
        Log.d(TAG, "networkRetrofitCallForgotPW: ");
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<ForgotPassword> forgotPasswordCall = apiService.getForgotPW(forgotPWRequest.getMobile());
        forgotPasswordCall.enqueue(new Callback<ForgotPassword>() {
            @Override
            public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                ForgotPassword forgotPassword = response.body();
                listener.getForgotPWModel(forgotPassword);
            }
            @Override
            public void onFailure(Call<ForgotPassword> call, Throwable t) {
                Log.d(TAG, "onFailure: Forgot PW - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallLogin
            (final IDataManger.LogInOnResponseListener listener, LoginRequest loginRequest) {
        Log.d(TAG, "networkRetrofitCallLogin: ");
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<List<LogIn>> loginCall = apiService.getLogin(loginRequest.getMobile(), loginRequest.getPassword());
        loginCall.enqueue(new Callback<List<LogIn>>() {
            @Override
            public void onResponse(Call<List<LogIn>> call, Response<List<LogIn>> response) {
                Log.d(TAG, "onResponse: ");
                LogIn logIn = response.body().get(0);
                listener.passLogin(logIn);
            }

            @Override
            public void onFailure(Call<List<LogIn>> call, Throwable t) {
                Log.d(TAG, "onFailure: Forgot PW - " + t.getMessage());
            }
        });
    }
    
    @Override
    public void networkRetrofitCallCities
            (final IDataManger.CitiesOnResponseListener listener) {
        Log.d(TAG, "networkRetrofitCallCities: ");
        ApiService apiService = RetrofitInstance.getRetrofitInstance()
                .create(ApiService.class);
        Call<CitiesModel> citiesModelCall = apiService.getCitiesList();
        citiesModelCall.enqueue(new Callback<CitiesModel>() {
            @Override
            public void onResponse(Call<CitiesModel> call, Response<CitiesModel> response) {
                CitiesModel citiesModel = response.body();
                Log.d(TAG, "onResponse: Body: Cities - " +  citiesModel.toString());
                listener.setCitiesModel(citiesModel);
            }
            @Override
            public void onFailure(Call<CitiesModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallRoutes
            (final IDataManger.RoutesOnResponseListener listener, RouteRequest routeRequest) {
        Log.d(TAG, "networkRetrofitCallRoutes: ");
        ApiService apiService = RetrofitInstance.getRetrofitInstance()
                .create(ApiService.class);
        Call<Routes> routesCall = apiService.getRouteList(
                        routeRequest.getStartLat(),
                        routeRequest.getStartLon(),
                        routeRequest.getEndLat(),
                        routeRequest.getEndLon()
        );
        routesCall.enqueue(new Callback<Routes>() {
            @Override
            public void onResponse(Call<Routes> call, Response<Routes> response) {

                Routes routes = response.body();
                Log.d(TAG, "onResponse: " + routes.toString());
                listener.getRoutesModel(routes);
            }
            @Override
            public void onFailure(Call<Routes> call, Throwable t) {
                Log.d(TAG, "onFailure: Routes - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallBusInfo
            (final IDataManger.BusInfoOnResponseListener listener, BusInfoRequest busInfoRequest) {
        Log.d(TAG, "networkRetrofitCallBusInfo: Route ID:" + busInfoRequest.getRouteID());

        ApiService apiService = RetrofitInstance.getRetrofitInstance()
                .create(ApiService.class);
        Call<BusInfoModel> busInfoModelCall = apiService.getBusInfo(busInfoRequest.getRouteID());
        busInfoModelCall.enqueue(new Callback<BusInfoModel>() {
            @Override
            public void onResponse(Call<BusInfoModel> call, Response<BusInfoModel> response) {

                BusInfoModel busInfoModel = response.body();
                Log.d(TAG, "onResponse: Body : "+ response.body().toString());
                listener.getBusInfoModel(busInfoModel);
            }
            @Override
            public void onFailure(Call<BusInfoModel> call, Throwable t) {
                Log.d(TAG, "onFailure: Bus Info - "+ t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallSeats(
            final IDataManger.SeatInfoOnResponseListener listener, SeatInfoRequest seatInfoRequest){
        Log.d(TAG, "networkRetrofitCallSeats: ");
        List<String> seatinformationItems = new ArrayList<>();
        //seatInfoRequest = new SeatInfoRequest("102");

        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<SeatInfo> seatInfoCall = apiService.getSeatInfo(seatInfoRequest.getBusID());
        seatInfoCall.enqueue(new Callback<SeatInfo>() {
            @Override
            public void onResponse(Call<SeatInfo> call, Response<SeatInfo> response) {

                Log.d(TAG, "onResponse: resposne : "+ response.body());
                SeatInfo seatInfo = response.body();
                listener.getSeatsInfoModel(seatInfo);
            }
            @Override
            public void onFailure(Call<SeatInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: Seats Ino - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallSeatReservation
            (final IDataManger.SeatReserveConfirmOnResponseListener listener,
             SeatReservationRequest seatReservationRequest) {

        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<SeatRequestResponse> seatRequestResponseCall
                = apiService.getSeatRequestReponse(
                    seatReservationRequest.getBusID(),
                    seatReservationRequest.getSeatURL()
                );
        seatRequestResponseCall.enqueue(new Callback<SeatRequestResponse>() {
            @Override
            public void onResponse
                    (Call<SeatRequestResponse> call, Response<SeatRequestResponse> response) {
                SeatRequestResponse seatRequestResponse = response.body();
                listener.getSeatReservConfirm(seatRequestResponse);
            }
            @Override
            public void onFailure(Call<SeatRequestResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Seat Reservation - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallCoupons
            (final IDataManger.CouponsOnResponseListener listener) {
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<Coupons> couponsCall = apiService.getCoupons();
        couponsCall.enqueue(new Callback<Coupons>() {
            @Override
            public void onResponse(Call<Coupons> call, Response<Coupons> response) {
                Coupons coupons = response.body();
                listener.getCouponsModel(coupons);
            }
            @Override
            public void onFailure(Call<Coupons> call, Throwable t) {
                Log.d(TAG, "onFailure: Coupons - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallCouponValidation
            (final IDataManger.CouponValidationOnResponseListener listener, CouponRequest couponRequest) {
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<CouponValidation> couponValidationCall = apiService.getCouponValidation(couponRequest.getCouponNo());
        couponValidationCall.enqueue(new Callback<CouponValidation>() {
            @Override
            public void onResponse(Call<CouponValidation> call, Response<CouponValidation> response) {
                CouponValidation couponValidation = response.body();
                listener.getCouponValidation(couponValidation.toString());
            }
            @Override
            public void onFailure(Call<CouponValidation> call, Throwable t) {
                Log.d(TAG, "onFailure: Coupon validation - " + t.getMessage());
            }
        });
    }

    @Override
    public void networkRetrofitCallCheckOutInfo
            (IDataManger.CheckoutOnResposneListener listener, CheckoutRequest checkoutRequest) {

    }


}
