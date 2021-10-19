package com.example.Customer.request;



import com.example.Customer.utilits.Credential;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService
{
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Credential.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

}
