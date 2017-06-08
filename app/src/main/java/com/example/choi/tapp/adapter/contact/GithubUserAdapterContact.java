package com.example.choi.tapp.adapter.contact;

import com.example.choi.tapp.domain.User;
import com.example.choi.tapp.widget.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 6. 8..
 */

public interface GithubUserAdapterContact {

    interface View {
        void setOnClickListener(OnItemClickListener onItemClickListener);

        void notifyAdapter();
    }

    interface Model {
        void addItems(ArrayList<User> users);

        void clear();

        User getUser(int position);
    }
}
