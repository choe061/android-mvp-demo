package com.example.choi.tapp;

/**
 * Created by choi on 2017. 6. 8..
 */

public interface BasePresenter<T> {
    void start(T view);
}
