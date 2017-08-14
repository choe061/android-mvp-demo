package com.example.choi.tapp.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.choi.tapp.R;
import com.example.choi.tapp.model.domain.User;
import com.example.choi.tapp.util.DataProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by choi on 2017. 8. 14..
 */

public class UserDialogFragment extends DialogFragment {
    private final String TAG = UserDialogFragment.class.getName();
    private DataProvider dataProvider;
    private User user;

    @BindView(R.id.profile_img) ImageView profile_img;
    @BindView(R.id.login) TextView login;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.company) TextView company;
    @BindView(R.id._followers) TextView followers;
    @BindView(R.id.following) TextView following;
    @BindView(R.id.created_at) TextView created_at;
    @BindView(R.id.updated_at) TextView updated_at;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataProvider = (DataProvider) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DataProvider");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            user = getArguments().getParcelable("user");
        } catch (NullPointerException npe) {
            throw new NullPointerException(getContext().toString() + " user is null");
        }
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main_user_dialog, null);
        ButterKnife.bind(this, view);
        setView();

        builder.setView(view)
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    dataProvider.onDataReceived(this);  //class가 아닌 this//UserDialogFragment.class를 넣으면 ClassCastException
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.red_700));
        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        return dialog;
    }

    private void setView() {
        Glide.with(getContext())
                .load(user.getAvatar_url())
                .placeholder(R.drawable.default_profile_img)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(profile_img);
        login.setText(user.getLogin());
        name.setText(user.getName());
        company.setText(user.getCompany());
        followers.setText(String.valueOf(user.getFollowers()));
        following.setText(String.valueOf(user.getFollowing()));
        created_at.setText(user.getCreated_at());
        updated_at.setText(user.getUpdated_at());
    }

    public String getUserID() {
        return user.getLogin();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
