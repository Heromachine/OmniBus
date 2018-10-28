package com.example.jessi.omnibus.data;

import com.example.jessi.omnibus.data.models.BusInfoModel;
import com.example.jessi.omnibus.data.models.CitiesModel;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.data.models.ForgotPassword;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.Routes;
import com.example.jessi.omnibus.data.models.SeatInfo;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.data.network.INetworkHelper;

public interface IDataManger extends INetworkHelper {

    interface RegistrationOnResponseListener{
        void getRegistrationModel(RegistrationModel registrationModel);
    }

    interface LogInOnResponseListener {
        void passLogin(LogIn logIn);
    }

    interface ForgotPWOnResponseListener{
        void getForgotPWModel(ForgotPassword forgotPassword);
    }
    interface CitiesOnResponseListener{
        void setCitiesModel(CitiesModel citiesModel);

    }
    interface RoutesOnResponseListener{
        void getRoutesModel(Routes routes);

    }
    interface BusInfoOnResponseListener{
        void getBusInfoModel(BusInfoModel busInfoModel);

    }
    interface SeatInfoOnResponseListener{
        void getSeatsInfoModel(SeatInfo seatInfo);

    }
    interface SeatReserveConfirmOnResponseListener{
        void getSeatReservConfirm(SeatRequestResponse seatRequestResponse);

    }
    interface CouponsOnResponseListener{
        void getCouponsModel(Coupons coupons);

    }
    interface CouponValidationOnResponseListener{
        void getCouponValidation(String string);
    }
    interface CheckoutOnResposneListener{
        void getCheckoutConfirmation(String string);
    }


}
