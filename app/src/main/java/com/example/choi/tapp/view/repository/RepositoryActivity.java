package com.example.choi.tapp.view.repository;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.choi.tapp.R;
import com.example.choi.tapp.adapter.GithubRepositoryRecyclerAdapter;
import com.example.choi.tapp.view.BaseActivity;
import com.example.choi.tapp.view.repository.presenter.RepositoryContact;
import com.example.choi.tapp.view.repository.presenter.RepositoryPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by choi on 2017. 6. 22..
 */

public class RepositoryActivity extends BaseActivity implements RepositoryContact.View {

    private static final String TAG = RepositoryActivity.class.getName();

    private RepositoryPresenter repositoryPresenter;
    private String userID;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userID = intent.getExtras().getString("userID");

        GithubRepositoryRecyclerAdapter githubRepositoryRecyclerAdapter = new GithubRepositoryRecyclerAdapter(this);
        recyclerView.setAdapter(githubRepositoryRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repositoryPresenter = new RepositoryPresenter();
        repositoryPresenter.attachView(this, httpService);
        repositoryPresenter.setAdapterModel(githubRepositoryRecyclerAdapter);
        repositoryPresenter.setAdapterView(githubRepositoryRecyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        repositoryPresenter.requestGetGithubUsers(userID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repositoryPresenter.detachView();
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
