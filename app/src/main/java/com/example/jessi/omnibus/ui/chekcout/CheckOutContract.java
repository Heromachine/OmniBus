package com.example.jessi.omnibus.ui.chekcout;

import com.example.jessi.omnibus.data.models.CheckoutRequest;

public interface CheckOutContract {
    interface Presenter{}
    interface View{
        CheckoutRequest getCheckOutRequest();
    }

}
