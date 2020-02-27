package com.wizz.entity;

import java.util.ArrayList;

public class Org//组织
{
    private String _id;
    private String _openid;
    private String name;
    private String owner;
    private ArrayList<String> admins;
    private Integer grade;//几级组织
    private Integer father;//所属的组织
    private Integer classA;
    private Integer classB;

    public Org() {
    }

    public Org(String _id, String _openid, String name, String owner, ArrayList<String> admins, Integer grade, Integer father, Integer classA, Integer classB) {
        this._id = _id;
        this._openid = _openid;
        this.name = name;
        this.owner = owner;
        this.admins = admins;
        this.grade = grade;
        this.father = father;
        this.classA = classA;
        this.classB = classB;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_openid() {
        return _openid;
    }

    public void set_openid(String _openid) {
        this._openid = _openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<String> admins) {
        this.admins = admins;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public Integer getClassA() {
        return classA;
    }

    public void setClassA(Integer classA) {
        this.classA = classA;
    }

    public Integer getClassB() {
        return classB;
    }

    public void setClassB(Integer classB) {
        this.classB = classB;
    }
}
