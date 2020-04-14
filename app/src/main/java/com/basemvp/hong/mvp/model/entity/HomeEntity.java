package com.basemvp.hong.mvp.model.entity;

import com.basemvp.hong.request.Result;

/**
 * Create by Hong on 2020/4/14 13:55.
 */
public class HomeEntity extends Result {
    String name;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
