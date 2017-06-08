package com.example.choi.tapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.example.choi.tapp.R;
import com.example.choi.tapp.adapter.contact.GithubUserAdapterContact;
import com.example.choi.tapp.adapter.viewholder.GithubUserViewHolder;
import com.example.choi.tapp.domain.User;
import com.example.choi.tapp.widget.OnItemClickListener;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by choi on 2017. 5. 25..
 */

public class GithubUserRecyclerAdapter extends RecyclerView.Adapter<GithubUserViewHolder> implements GithubUserAdapterContact.Model, GithubUserAdapterContact.View {

    private OnItemClickListener onItemClickListener;
    private ArrayList<User> users = new ArrayList<>();
    private RequestManager requestManager;
    private Context context;

    public GithubUserRecyclerAdapter(Context context, RequestManager requestManager) {
        this.context = context;
        this.requestManager = requestManager;
    }

    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_main_item, parent, false);
        return new GithubUserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(GithubUserViewHolder holder, int position) {
        try {
            User user = users.get(position);
            Log.e("img url", user.getAvatar_url() + ", id : " + user.getId() + ", login : " + user.getLogin());
            requestManager.load(user.getAvatar_url())
                    .placeholder(R.drawable.default_profile_img)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.avatar_url);

            holder.id.setText("id : " + user.getId());
            holder.login.setText("login : " + user.getLogin());
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            });
        } catch (NullPointerException ignored) {}
    }

    @Override
    public int getItemCount() {
        return ((users != null) ? users.size() : 0);
    }

    @Override
    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public void clear() {
        if (users != null) {
            users.clear();
        }
    }

    @Override
    public User getUser(int position) {
        return users.get(position);
    }
}
