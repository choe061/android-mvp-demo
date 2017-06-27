package com.example.choi.tapp.view;

/**
 * Created by choi on 2017. 6. 26..
 */

public interface BaseContact {

    interface BasePresenter<T> {
        void start(T view);
    }

    interface BaseView<T> {
        void setPresenter(T presenter);
    }
}
