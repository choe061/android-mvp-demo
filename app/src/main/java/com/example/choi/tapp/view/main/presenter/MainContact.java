package com.example.choi.tapp.view.main.presenter;

import android.content.Context;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.model.repository.api.UserApi;

/**
 * Created by choi on 2017. 6. 8..
 * 하나의 Contact Interface에 View와 Presenter를 선언하고, 구현은 View / Presenter 각각
 */

public interface MainContact {

    interface View {

        void showToast(String title);

        void showDialog(User user);
    }

    interface Presenter {

        void attachView(View view, UserApi userApi);

        void setAdapterModel(BaseAdapterContact.Model adapterModel);

        void setAdapterView(BaseAdapterContact.View adapterView);

        void detachView();

        void requestGetGithubUsers();

        void requestGetGithubUser(String userID);

        void loadItems(Context context, boolean isClear);
    }
}
