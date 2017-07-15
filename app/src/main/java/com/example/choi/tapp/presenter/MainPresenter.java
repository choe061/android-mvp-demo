package com.example.choi.tapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.api.UserApi;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.HttpService;
import com.example.choi.tapp.presenter.contact.MainContact;
import com.example.choi.tapp.util.OnItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 8..
 * ~Presenter는 ~Contact에 정의된 Presenter를 상속받아 구현
 */

public class MainPresenter implements MainContact.Presenter, OnItemClickListener {

    private static final String TAG = MainPresenter.class.getName();

    private UserApi userApi;
    private MainContact.View view;          //데이터를 받아 view를 수정

    private BaseAdapterContact.Model adapterModel;
    private BaseAdapterContact.View adapterView;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(MainContact.View view, HttpService httpService) {
        this.view = view;
        this.userApi = new UserApi(httpService);
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setAdapterModel(BaseAdapterContact.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(BaseAdapterContact.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.dispose();
        //명시적으로 구독을 종료하지 않으면 메모리릭이 발생할 수도 있다
    }

    @SuppressWarnings("unchecked")
    @Override
    public void requestGetGithubUsers() {
        Disposable disposable = userApi.requestGetGithubUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapterModel.addItems(response.body());
                    adapterView.notifyAdapter();
                    view.showToast("유저 목록을 성공적으로 조회했습니다.");
                }, throwable -> {
                    Log.e(TAG, throwable.getMessage());
                    view.showToast("유저 목록을 가져오지 못했습니다.");
                });
        compositeDisposable.add(disposable);
        //CompositeDisposable에 넣어 관리하면 데이터 구독을 원할때 끊을 수 있다
    }

    @Override
    public void requestGetGithubUser(String userID) {
        Disposable disposable = userApi.requestGetGithubUser(userID, new ApiCallback<Response<User>>() {
            @Override
            public void onSuccess(Response<User> model) {
                Log.d(TAG, String.valueOf(model));
                view.showDialog(model.body());
            }

            @Override
            public void onError(String msg) {
                view.showToast("유저 정보를 가져오지 못했습니다.");
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void loadItems(Context context, boolean isClear) {

    }

    @Override
    public void onItemClick(int position) {
        User user = (User) adapterModel.getItem(position);
        requestGetGithubUser(user.getLogin());
    }
}
