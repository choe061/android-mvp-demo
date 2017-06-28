package com.example.choi.tapp.view.main.presenter;

import android.content.Context;
import android.util.Log;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.remote.api.UserApi;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.util.OnItemClickListener;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
    public void attachView(MainContact.View view, UserApi userApi) {
        this.view = view;
        this.userApi = userApi;
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
    }

    /**
     * api요청 과정
     * 1. model/api패키지에서 repository의 interface를 상속받아 Api 통신을 담당하는 클래스 생성
     * 2. 데이터 통신이 오래 걸릴 경우 - presenter에서 service패키지에서 service컴포넌트 생성
     * 3. presenter에서 Api 응답 결과 요청
     */
    @Override
    public void requestGetGithubUsers() {
        Disposable disposable = userApi.requestGetGithubUsers(new ApiCallback<Response<ArrayList<User>>>() {
            @Override
            public void onSuccess(Response<ArrayList<User>> model) {
                adapterModel.addItems(model.body());
                adapterView.notifyAdapter();
                view.showToast("유저 목록을 성공적으로 조회했습니다.");
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, msg);
                view.showToast("유저 목록을 가져오지 못했습니다.");
            }
        });
        compositeDisposable.add(disposable);
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
