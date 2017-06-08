package com.example.choi.tapp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.choi.tapp.R;
import com.example.choi.tapp.domain.User;
import com.example.choi.tapp.widget.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by choi on 2017. 6. 7..
 */

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar_url)
    public ImageView avatar_url;

    @BindView(R.id.id)
    public TextView id;

    @BindView(R.id.login)
    public TextView login;

    public GithubUserViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }
}
