package com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity;

/**
 *  用户的实体类（注册时的请求体）
 */

public class UserEntity {

    private String username;
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
