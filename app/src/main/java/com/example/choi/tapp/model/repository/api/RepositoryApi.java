package com.example.choi.tapp.model.repository.api;

import com.example.choi.tapp.model.domain.Repository;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryApi {
    Observable<Response<ArrayList<Repository>>> requestGetUserRepository(String userID);
}
