package com.example.finalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://www.breakingbadapi.com/";
    private static Retrofit retrofitClient = null;

    public static URLapi getRetrofitClient(){
        if(retrofitClient ==null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient.create(URLapi.class);
    }
}
