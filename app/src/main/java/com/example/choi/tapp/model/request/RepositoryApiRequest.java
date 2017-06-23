package com.example.choi.tapp.model.request;

import com.example.choi.tapp.model.api.RepositoryApi;
import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public class RepositoryApiRequest implements RepositoryApi {

    @Override
    public Disposable requestGetUserRepository(String userID, ApiCallback<Response<ArrayList<Repository>>> callback) {
        Observable<Response<ArrayList<Repository>>> repos = NetModule.getHttpService().getUserRepository(userID);
        return repos.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
