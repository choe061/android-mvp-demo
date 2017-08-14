package com.example.choi.tapp.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 6. 1..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Parcelable {
    private String login;
    private int id;
    private String avatar_url;
    private String name;
    private String company;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;

    protected User(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatar_url = in.readString();
        name = in.readString();
        company = in.readString();
        followers = in.readInt();
        following = in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(id);
        dest.writeString(avatar_url);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
