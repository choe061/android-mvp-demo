package com.example.choi.tapp.network;

import com.example.choi.tapp.domain.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by choi on 2017. 5. 20..
 */

public interface HttpService {

    @GET("/users")
    Observable<Response<ArrayList<User>>> getGithubUsers();

    @GET("/users/{user}")
    Observable<Response<Void>> getUser(@Path("user") String userID);
}
