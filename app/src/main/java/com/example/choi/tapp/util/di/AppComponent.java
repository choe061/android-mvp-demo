package com.example.choi.tapp.util.di;

import com.example.choi.tapp.util.di.module.NetModule;
import com.example.choi.tapp.view.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by choi on 2017. 6. 28..
 */

@Singleton
@Component(modules = {NetModule.class})
public interface AppComponent {
    void inject(BaseActivity activity);
}
