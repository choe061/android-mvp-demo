package com.example.choi.tapp;

import android.app.Application;

import com.example.choi.tapp.network.Constants;
import com.example.choi.tapp.util.di.AppComponent;
import com.example.choi.tapp.util.di.DaggerAppComponent;
import com.example.choi.tapp.util.di.module.NetModule;

/**
 * Created by choi on 2017. 6. 28..
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .netModule(new NetModule(Constants.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
