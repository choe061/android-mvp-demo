package com.example.choi.tapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choi.tapp.R;
import com.example.choi.tapp.adapter.contact.BaseAdapterContact;
import com.example.choi.tapp.adapter.viewholder.GithubRepositoryViewHolder;
import com.example.choi.tapp.model.domain.Repository;
import com.example.choi.tapp.util.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 6. 22..
 */

public class GithubRepositoryRecyclerAdapter extends RecyclerView.Adapter<GithubRepositoryViewHolder> implements BaseAdapterContact.Model<Repository>, BaseAdapterContact.View {

    private OnItemClickListener onItemClickListener;
    private ArrayList<Repository> repositories = new ArrayList<>();
    private Context context;

    public GithubRepositoryRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GithubRepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_repository_item, parent, false);
        return new GithubRepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubRepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.name.setText(repository.getName());
        holder.html_url.setText(repository.getHtml_url());
        if (repository.is_private()) {
            holder._private.setText("private project");
        } else {
            holder._private.setText("public project");
        }
        holder.description.setText(repository.getDescription());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
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
    public void addItems(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void clear() {
        if (repositories != null) {
            repositories.clear();
        }
    }

    @Override
    public Repository getItem(int position) {
        return repositories.get(position);
    }
}
