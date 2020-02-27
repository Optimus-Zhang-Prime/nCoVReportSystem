package com.wizz.entity;

<<<<<<< HEAD
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
=======
public class User {//学生教职工
    public Integer id;
    public String phone;
    public String name;
    public String number;
    public Integer index;//易感指数
    public Boolean gender;//性别
    public Boolean sick;//是否生病
    public Integer classA;//所属一级组织
    public Integer classB;
    public Integer classC;


>>>>>>> 6a53a11f8ba22719f710872a07efe52ae70a959f
}
