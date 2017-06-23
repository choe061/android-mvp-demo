package com.example.choi.tapp.model.api;

import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.network.ApiCallback;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 18..
 * User model에 대한 CRUD 기능을 선언하는 Interface 작성
 */

public interface UserApi {
    Disposable requestGetGithubUsers(ApiCallback<Response<ArrayList<User>>> callback);
    Disposable requestGetGithubUser(String userID, ApiCallback<Response<User>> callback);
}