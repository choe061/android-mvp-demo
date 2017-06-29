package com.example.choi.tapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.choi.tapp.App;
import com.example.choi.tapp.network.HttpService;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by choi on 2017. 6. 29..
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    public HttpService httpService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getAppComponent().inject(this);
        httpService = retrofit.create(HttpService.class);
    }
}
