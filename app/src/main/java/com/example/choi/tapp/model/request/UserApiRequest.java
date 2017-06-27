package com.example.choi.tapp.model.request;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.api.UserApi;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 18..
 */

public class UserApiRequest implements UserApi {

    @Override
    public Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback) {
        Observable<Response<ArrayList<User>>> users = NetModule.getHttpService().getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }

    //error 처리를 어디서
    public Observable<ArrayList<User>> requestGetGithubUsers2() {
        Observable<Response<ArrayList<User>>> users = NetModule.getHttpService().getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(Response::body);
    }

    @Override
    public Disposable requestGetGithubUser(String userID, ApiCallback<Response<User>> callback) {
        Observable<Response<User>> user = NetModule.getHttpService().getGithubUser(userID);
        return user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
