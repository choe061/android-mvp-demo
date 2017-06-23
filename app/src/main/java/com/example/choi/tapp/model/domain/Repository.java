package com.example.choi.tapp.model.domain;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 6. 22..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    private long id;
    private String name;
    private String html_url;
    @SerializedName("private")
    private boolean _private;
    private String description;
    private String downloads_url;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String language;
}
