package com.example.choi.tapp.view.main.presenter;

import android.content.Context;

import com.example.choi.tapp.adapter.contact.GithubUserAdapterContact;
import com.example.choi.tapp.domain.User;
import com.example.choi.tapp.network.ApiCallback;
import com.example.choi.tapp.network.service.MainService;
import com.example.choi.tapp.widget.OnItemClickListener;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by choi on 2017. 6. 8..
 * ~Presenter는 ~Contact에 정의된 Presenter를 상속받아 구현
 */

public class MainPresenter implements MainContact.Presenter, OnItemClickListener {

    private MainService mainService;
    private MainContact.View view;

    private GithubUserAdapterContact.Model adapterModel;
    private GithubUserAdapterContact.View adapterView;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(MainContact.View view, MainService mainService) {
        this.view = view;
        this.mainService = mainService;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setAdapterModel(GithubUserAdapterContact.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(GithubUserAdapterContact.View adapterView) {
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
     * 1. network - service패키지에서 Api 통신을 담당하는 클래스 생성
     * 2. 데이터 통신이 오래 걸릴 경우 - presenter에서 service패키지에서 service컴포넌트 생성
     * 3. presenter에서 Api 응답 결과 요청
     * 4.
     */
    @Override
    public void requestGetGithubUsers() {
        Disposable disposable = mainService.requestGetGithubUsers(new ApiCallback<Response<ArrayList<User>>>() {
            @Override
            public void onSuccess(Response<ArrayList<User>> model) {
                adapterModel.addItems(model.body());
                adapterView.notifyAdapter();
            }

            @Override
            public void onError(String msg) {
                view.showToast("유저 목록을 가져오지 못했습니다.");
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void loadItems(Context context, boolean isClear) {

    }

    @Override
    public void onItemClick(int position) {
        User user = adapterModel.getUser(position);
        view.showToast("유저 로그인 : " + user.getLogin());
    }
}
