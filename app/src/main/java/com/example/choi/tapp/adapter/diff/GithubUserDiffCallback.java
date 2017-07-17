package com.example.choi.tapp.adapter.diff;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.choi.tapp.model.domain.User;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 7. 17..
 */

public class GithubUserDiffCallback extends DiffUtil.Callback {
    private final ArrayList<User> oldUsers = new ArrayList<>();
    private final ArrayList<User> newUsers = new ArrayList<>();

    @Override
    public int getOldListSize() {
        return oldUsers.size();
    }

    @Override
    public int getNewListSize() {
        return newUsers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).getId() == newUsers.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final User oldUser = oldUsers.get(oldItemPosition);
        final User newUser = newUsers.get(newItemPosition);
        return oldUser.getName().equals(newUser.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
