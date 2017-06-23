package com.example.choi.tapp.adapter.contact;

import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.widget.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 6. 22..
 */

public interface GithubRepositoryAdapterContact {

    interface View {
        void setOnClickListener(OnItemClickListener onItemClickListener);

        void notifyAdapter();
    }

    interface Model {
        void addItems(ArrayList<Repository> users);

        void clear();
    }
}
