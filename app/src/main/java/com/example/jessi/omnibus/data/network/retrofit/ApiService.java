package com.example.jessi.omnibus.data.network.retrofit;

import com.example.jessi.omnibus.data.models.BusInfoModel;
import com.example.jessi.omnibus.data.models.CitiesModel;
import com.example.jessi.omnibus.data.models.CouponValidation;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.data.models.ForgotPassword;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.Routes;
import com.example.jessi.omnibus.data.models.SeatInfo;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    //http://rjtmobile.com/aamir/otr/android-app/registration.php?firstname=aamir&lastname=husain&address=India&email=aa@aa.com&mobile=9876543210&password=12345678
    @GET("registration.php")
    Call<RegistrationModel>getRegistration(
            @Query("firstname") String firstname,
            @Query("lastname") String lastname,
            @Query("address") String address,
            @Query("email") String email,
            @Query("mobile") String mobile,
            @Query("password") String password
    );

    //http://rjtmobile.com/aamir/otr/android-app/login.php?mobile=9876543210&password=12345678
    @GET("login.php")
    Call<List<LogIn>> getLogin(
            @Query("mobile") String mobile,
            @Query("password") String password
    );

    //http://rjtmobile.com/aamir/otr/android-app/forgot_pass.php?mobile=9876543210
    @GET("forgot_pass.php")
    Call<ForgotPassword>getForgotPW(
            @Query("mobile") String mobile
    );

    @GET("city.php")
    Call<CitiesModel> getCitiesList();


   //routeinfo.php?route-startpoint-latitude={startlatitude}&route-startpoint-longitude={startlongitude}&route-endpoint-latitude={endlatitude}&route-endpoint-longiude={endlogitude}";
    @GET( "routeinfo.php")
     Call<Routes>getRouteList(
            @Query("route-startpoint-latitude") String startLatitude,
            @Query("route-startpoint-longitude") String startLongitude,
            @Query ("route-endpoint-latitude")  String endLatitude,
            @Query("route-endpoint-longiude") String endLongitude);

    //http://rjtmobile.com/aamir/otr/android-app/businfo.php?routeid=2
    @GET("businfo.php")
    Call<BusInfoModel>getBusInfo(
            @Query("routeid") String routeID
    );

    //http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102
    @GET("seatinfo.php")
    Call<SeatInfo>getSeatInfo(
            @Query("busid") String busID
    );
    //TODO SEAT REQUEST RATHER COMPLICATED

    //http://rjtmobile.com/aamir/otr/android-app/coupon_list.php?
    @GET("coupon_list.php")
    Call<Coupons> getCoupons();


    //http://rjtmobile.com/aamir/otr/android-app/coupon_validation.php?couponno=54316544
    @GET("coupon_validation.php")
    Call<CouponValidation> getCouponValidation(
            @Query("couponno") String couponNo
    );


   // "http://rjtmobile.com/aamir/otr/android-app/chooseseat.php?busid=102&s1=1
   // http://rjtmobile.com/aamir/otr/android-app/chooseseat.php?busid=102&s2=1&s3=1"

    @GET("chooseseat.php")
    Call<SeatRequestResponse> getSeatRequestReponse(
            @Query("busid") String busid,
            @Url String url);

    //"http://rjtmobile.com/aamir/otr/android-app/ticketcheckoutinfo.php?
//    @GET("ticketcheckoutinfo.php")
//    Call<>

}
