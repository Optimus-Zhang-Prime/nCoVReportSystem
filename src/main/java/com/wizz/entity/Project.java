package com.wizz.entity;

import java.util.ArrayList;

public class Project {
    private String _id;
    private String name;
    private Boolean state;
    private ArrayList<String> admins;
    private String owner;

    @Override
    public String toString() {
        return "Project{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", admins=" + admins +
                ", owner='" + owner + '\'' +
                '}';
    }

    public Project() {
    }

    public Project(String _id, String name, Boolean state, ArrayList<String> admins, String owner) {
        this._id = _id;
        this.name = name;
        this.state = state;
        this.admins = admins;
        this.owner = owner;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
