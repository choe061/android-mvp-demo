package com.example.choi.tapp.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 6. 1..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String login;
    private int id;
    private String avatar_url;
    private String name;
    private String company;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;
}
