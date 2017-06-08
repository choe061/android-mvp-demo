package com.example.choi.tapp.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by choi on 2017. 5. 20..
 */

public class NetModule {

    private static HttpService httpService;

    public static HttpService getHttpService() {
        httpService = provideRetrofit();
        return httpService;
    }

    private static HttpService provideRetrofit() {
        if (httpService == null) {
            synchronized (NetModule.class) {
                if (httpService == null) {
                    httpService = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(HttpService.class);
                }
            }
        }
        return httpService;
    }
}
