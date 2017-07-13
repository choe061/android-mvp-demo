package com.example.choi.tapp.model.repository.request;

import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.model.repository.api.RepositoryApi;
import com.example.choi.tapp.network.HttpService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 * Observable을 이용
 */

public class RepositoryApiRequest implements RepositoryApi {

    private HttpService httpService;

    public RepositoryApiRequest(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public Observable<Response<ArrayList<Repository>>> requestGetUserRepository(String userID) {
        return httpService.getUserRepository(userID);
    }
}
