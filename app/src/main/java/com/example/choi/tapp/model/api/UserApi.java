package com.example.choi.tapp.model.api;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.HttpService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 18..
 * 1. Observable을 return 하거나
 * 2. Callback Interface를 이용
 */

public class UserApi {

    private HttpService httpService;

    public UserApi(HttpService httpService) {
        this.httpService = httpService;
    }

    public Observable<Response<ArrayList<User>>> requestGetGithubUsers() {
        return httpService.getGithubUsers();
    }

    public Disposable requestGetGithubUser(String userID, ApiCallback<Response<User>> callback) {
        Observable<Response<User>> user = httpService.getGithubUser(userID);
        return user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
