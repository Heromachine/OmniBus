package com.example.jessi.omnibus.ui.payment;

import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.CouponValidation;

public interface PaymentContract {
    interface View{
        CouponRequest getCouponValidationRequest();

        void setCouponValidation(CouponValidation couponValidation);
    }
    interface Presenter{

    }
}
