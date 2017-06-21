package com.example.choi.tapp.base.main.presenter;

import android.content.Context;

import com.example.choi.tapp.adapter.contact.GithubUserAdapterContact;
import com.example.choi.tapp.model.repository.UserRepository;

/**
 * Created by choi on 2017. 6. 8..
 * 하나의 Contact Interface에 View와 Presenter를 선언하고, 구현은 View / Presenter 각각
 */

public interface MainContact {

    interface View {

        void showToast(String title);
    }

    interface Presenter {
        void attachView(View view, UserRepository userRepository);

        void setAdapterModel(GithubUserAdapterContact.Model adapterModel);

        void setAdapterView(GithubUserAdapterContact.View adapterView);

        void detachView();

        void requestGetGithubUsers();

        void loadItems(Context context, boolean isClear);
    }
}
