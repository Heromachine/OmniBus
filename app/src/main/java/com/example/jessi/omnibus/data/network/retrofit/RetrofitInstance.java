package com.example.jessi.omnibus.data.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://rjtmobile.com/aamir/otr/android-app/";

    public static Retrofit getRetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
