package com.example.choi.tapp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.choi.tapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by choi on 2017. 6. 22..
 */

public class GithubRepositoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.html_url)
    public TextView html_url;

    @BindView(R.id._private)
    public TextView _private;

    @BindView(R.id.description)
    public TextView description;

    public GithubRepositoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
