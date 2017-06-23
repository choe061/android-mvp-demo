package com.example.choi.tapp.model.api;

import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.network.ApiCallback;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryApi {
    Disposable requestGetUserRepository(String userID, ApiCallback<Response<ArrayList<Repository>>> callback);
}
