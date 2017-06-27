package com.example.choi.tapp.view.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.choi.tapp.R;
import com.example.choi.tapp.view.repository.RepositoryActivity;
import com.example.choi.tapp.model.domain.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by choi on 2017. 6. 22..
 */

public class UserDialog extends Dialog {

    @BindView(R.id.profile_img)
    ImageView profile_img;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.company)
    TextView company;

    @BindView(R.id._followers)
    TextView followers;

    @BindView(R.id.following)
    TextView following;

    @BindView(R.id.created_at)
    TextView created_at;

    @BindView(R.id.updated_at)
    TextView updated_at;

    private RequestManager requestManager;
    private String userID;

    public UserDialog(@NonNull Context context, RequestManager requestManager) {
        super(context);
        this.requestManager = requestManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_dialog);
        ButterKnife.bind(this);
    }

    public void setDialogText(User user) {
        userID = user.getLogin();
        requestManager.load(user.getAvatar_url())
                .placeholder(R.drawable.default_profile_img)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(profile_img);
        login.setText(user.getLogin());
        name.setText(user.getName());
        company.setText(user.getCompany());
        //int형 setText 시 에러 : Resources$NotFoundException: String resource ID #0x~~
        followers.setText(String.valueOf(user.getFollowers()));
        following.setText(String.valueOf(user.getFollowing()));
        created_at.setText(user.getCreated_at());
        updated_at.setText(user.getUpdated_at());
    }


    @OnClick(R.id.ok)
    public void startRepositoryActivity() {
        Intent intent = new Intent(getContext(), RepositoryActivity.class);
        intent.putExtra("userID", userID);
        getContext().startActivity(intent);
    }

    @OnClick(R.id.cancel)
    public void cancelClick() {
        this.cancel();
    }

}
