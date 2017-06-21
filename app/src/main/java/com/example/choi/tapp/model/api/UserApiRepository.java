package com.example.choi.tapp.model.api;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.repository.UserRepository;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.NetModule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 18..
 */

public class UserApiRepository implements UserRepository {
    @Override
    public Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback) {
        Observable<Response<ArrayList<User>>> users = NetModule.getHttpService().getGithubUsers();
        return users.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
