package com.wizz.entity;

public class User {
    private Integer id;
    private String password;
    private String name;

    public User(String aname,String apassword) {
        this.password = apassword;
        this.name = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
