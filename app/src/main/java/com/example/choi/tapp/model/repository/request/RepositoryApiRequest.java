package com.example.choi.tapp.model.repository.request;

import com.example.choi.tapp.model.repository.api.RepositoryApi;
import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.HttpService;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by choi on 2017. 6. 22..
 */

public class RepositoryApiRequest implements RepositoryApi {

    private HttpService httpService;

    public RepositoryApiRequest(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public Disposable requestGetUserRepository(String userID, ApiCallback<Response<ArrayList<Repository>>> callback) {
        Observable<Response<ArrayList<Repository>>> repos = httpService.getUserRepository(userID);
        return repos.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
