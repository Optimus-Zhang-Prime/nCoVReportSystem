package com.wizz.entity;

import java.util.ArrayList;

public class Project {
    private Integer _id;
    private Integer _openid;
    private String name;
    private Boolean state;
    private ArrayList<String> admins;
    private String owner;

    public Project() {
    }

    public Project(Integer _id, Integer _openid, String name, Boolean state, ArrayList<String> admins, String owner) {
        this._id = _id;
        this._openid = _openid;
        this.name = name;
        this.state = state;
        this.admins = admins;
        this.owner = owner;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer get_openid() {
        return _openid;
    }

    public void set_openid(Integer _openid) {
        this._openid = _openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public ArrayList<String> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<String> admins) {
        this.admins = admins;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
