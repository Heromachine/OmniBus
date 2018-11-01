package com.example.jessi.omnibus.ui.chekcout;



import com.example.jessi.omnibus.data.models.CheckoutRequest;

public interface CheckOutContract {
    interface Presenter{
        void onButtonClicked(android.view.View view);
    }
    interface View{
        CheckoutRequest getCheckOutRequest();

        void setCheckoutConfirmation(String string);
    }

}
