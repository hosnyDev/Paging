package com.hosnydevtest.paging.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_REL = "https://api.stackexchange.com/2.2/";
    private Retrofit retrofit;
    private static RetrofitClient mInstance;

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_REL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null)
            mInstance = new RetrofitClient();
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }


}
