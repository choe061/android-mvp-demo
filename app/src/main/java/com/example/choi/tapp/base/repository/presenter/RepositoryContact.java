package com.example.choi.tapp.base.repository.presenter;

import android.content.Context;

import com.example.choi.tapp.adapter.contact.GithubRepositoryAdapterContact;
import com.example.choi.tapp.model.api.RepositoryApi;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryContact {

    interface View {

        void showToast(String title);

    }

    interface Presenter {
        void attachView(RepositoryContact.View view, RepositoryApi repositoryApi);

        void setAdapterModel(GithubRepositoryAdapterContact.Model adapterModel);

        void setAdapterView(GithubRepositoryAdapterContact.View adapterView);

        void detachView();

        void requestGetGithubUsers(String userID);

        void loadItems(Context context, boolean isClear);
    }
}
