package com.example.choi.tapp.model.api;

import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.network.HttpService;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 * Observable을 이용
 */

public class RepositoryApi {

    private HttpService httpService;

    public RepositoryApi(HttpService httpService) {
        this.httpService = httpService;
    }

    public Observable<Response<ArrayList<Repository>>> requestGetUserRepository(String userID) {
        return httpService.getUserRepository(userID);
    }
}
