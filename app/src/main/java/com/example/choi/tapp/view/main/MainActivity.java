package com.example.choi.tapp.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.choi.tapp.R;
import com.example.choi.tapp.adapter.GithubUserRecyclerAdapter;
import com.example.choi.tapp.network.service.MainService;
import com.example.choi.tapp.view.main.presenter.MainContact;
import com.example.choi.tapp.view.main.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContact.View {

    private static final String TAG = MainActivity.class.getName();

    private MainPresenter mainPresenter;
    private MainService mainService;

    private GithubUserRecyclerAdapter githubUserRecyclerAdapter;
    private RequestManager requestManager;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestManager = Glide.with(this);
        githubUserRecyclerAdapter = new GithubUserRecyclerAdapter(this, requestManager);
        recyclerView.setAdapter(githubUserRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainService = new MainService();
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this, mainService);
        mainPresenter.setAdapterModel(githubUserRecyclerAdapter);
        mainPresenter.setAdapterView(githubUserRecyclerAdapter);
        mainPresenter.requestGetGithubUsers();
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
