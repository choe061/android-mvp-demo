package com.example.choi.tapp.view.repository.presenter;

import android.content.Context;
import android.util.Log;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.model.repository.api.RepositoryApi;

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

    private BaseAdapterContact.Model adapterModel;
    private BaseAdapterContact.View adapterView;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(RepositoryContact.View view, RepositoryApi repositoryApi) {
        this.view = view;
        this.repositoryApi = repositoryApi;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setAdapterModel(BaseAdapterContact.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(BaseAdapterContact.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.dispose();
    }

    @Override
    public void requestGetGithubUsers(String userID) {
//        repositoryApi.requestGetUserRepository(userID).subscribe(new Observer() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//            }
//
//            @Override
//            public void onNext(@NonNull Object o) {
//                adapterModel.addItems(((Response<ArrayList<Repository>>) o).body());
//                adapterView.notifyAdapter();
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });

        @SuppressWarnings("unchecked")
        Disposable disposable = repositoryApi.requestGetUserRepository(userID).subscribe(o -> {
            adapterModel.addItems(((Response<ArrayList<Repository>>) o).body());
            adapterView.notifyAdapter();
        }, throwable -> {
            Log.e(TAG, throwable.toString());
            view.showToast("유저의 저장소를 조회하지 못했습니다.");
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void loadItems(Context context, boolean isClear) {

    }
}
