package com.example.choi.tapp.network.service;

import com.example.choi.tapp.domain.User;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 8..
 */

public class MainService {

    public Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback) {
        Observable<Response<ArrayList<User>>> users = NetModule.getHttpService().getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, error -> callback.onError(error.getMessage()));
    }

}
