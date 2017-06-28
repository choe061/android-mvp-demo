package com.example.choi.tapp.view.repository.presenter;

import android.content.Context;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.model.remote.api.RepositoryApi;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface RepositoryContact {

    interface View {

        void showToast(String title);

    }

    interface Presenter {
        void attachView(RepositoryContact.View view, RepositoryApi repositoryApi);

        void setAdapterModel(BaseAdapterContact.Model adapterModel);

        void setAdapterView(BaseAdapterContact.View adapterView);

        void detachView();

        void requestGetGithubUsers(String userID);

        void loadItems(Context context, boolean isClear);
    }
}
