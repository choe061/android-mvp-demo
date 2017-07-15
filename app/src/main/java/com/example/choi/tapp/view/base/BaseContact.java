package com.example.choi.tapp.view.base;

import com.example.choi.tapp.adapter.contact.BaseAdapterContact;

/**
 * Created by choi on 2017. 6. 26..
 */

public interface BaseContact {

    interface BaseView {

        void showToast(String title);
    }

    interface BasePresenter {

        void setAdapterModel(BaseAdapterContact.Model adapterModel);

        void setAdapterView(BaseAdapterContact.View adapterView);

        void detachView();
    }
}
