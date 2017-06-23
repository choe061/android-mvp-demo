package com.example.choi.tapp.base.repository.presenter;

import android.content.Context;
import android.util.Log;

import com.example.choi.tapp.adapter.contact.GithubRepositoryAdapterContact;
import com.example.choi.tapp.model.api.RepositoryApi;
import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.network.ApiCallback;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 22..
 */

public class RepositoryPresenter implements RepositoryContact.Presenter {

    private static final String TAG = RepositoryPresenter.class.getName();

    private RepositoryApi repositoryApi;
    private RepositoryContact.View view;

    private GithubRepositoryAdapterContact.Model adapterModel;
    private GithubRepositoryAdapterContact.View adapterView;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(RepositoryContact.View view, RepositoryApi repositoryApi) {
        this.view = view;
        this.repositoryApi = repositoryApi;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setAdapterModel(GithubRepositoryAdapterContact.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(GithubRepositoryAdapterContact.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.dispose();
    }

    @Override
    public void requestGetGithubUsers(String userID) {
        Disposable disposable = repositoryApi.requestGetUserRepository(userID, new ApiCallback<Response<ArrayList<Repository>>>() {
            @Override
            public void onSuccess(Response<ArrayList<Repository>> model) {
                adapterModel.addItems(model.body());
                adapterView.notifyAdapter();
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, msg);
                view.showToast("유저의 저장소를 조회하지 못했습니다.");
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void loadItems(Context context, boolean isClear) {

    }
}