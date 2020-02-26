package com.wizz.entity;

/**
 * @descrip：userBean
 * @author: 李佳
 * @create： 2020-02-26-01:53
 **/
public class User {
    private String username;
    private String password;
    private String _id;
    public User() {
    }

    public User(String username, String password, String _id) {
        this.username = username;
        this.password = password;
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public User(String username, String password) {
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
