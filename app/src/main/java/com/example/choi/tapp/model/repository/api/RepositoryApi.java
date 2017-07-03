package com.example.choi.tapp.model.repository.api;

import io.reactivex.Observable;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryApi {
    Observable requestGetUserRepository(String userID);
}
