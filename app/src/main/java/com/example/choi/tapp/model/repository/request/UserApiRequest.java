package com.example.choi.tapp.model.repository.request;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.repository.api.UserApi;
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
 * Disposable과 Callback Interface를 이용
 */

public class UserApiRequest implements UserApi {

    private HttpService httpService;

    public UserApiRequest(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback) {
        Observable<Response<ArrayList<User>>> users = httpService.getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }

    @Override
    public Disposable requestGetGithubUser(String userID, ApiCallback<Response<User>> callback) {
        Observable<Response<User>> user = httpService.getGithubUser(userID);
        return user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
