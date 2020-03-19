package com.wizz.entity;

import java.util.ArrayList;

public class Org//组织
{
    private String _id;
    private String _openid;
    private String name;
    private String owner;
    private String orgIdForClassB;            //
    private ArrayList<String> admins;
    private Integer grade;//几级组织
    private String parent;//所属的项目
    private String classA;
    private String classB;
    private Integer num;//人数，数据库里没有这个

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrgIdForClassB() {
        return orgIdForClassB;
    }

    public void setOrgIdForClassB(String orgIdForClassB) {
        this.orgIdForClassB = orgIdForClassB;
    }

    public Org(String _id, String _openid, String name, String owner, String orgIdForClassB, ArrayList<String> admins, Integer grade, String parent, String classA, String classB, Integer num) {
        this._id = _id;
        this._openid = _openid;
        this.name = name;
        this.owner = owner;
        this.orgIdForClassB = orgIdForClassB;
        this.admins = admins;
        this.grade = grade;
        this.parent = parent;
        this.classA = classA;
        this.classB = classB;
        this.num = num;
    }

    public Org() {
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public String getFather() {
        return parent;
    }

    public void setFather(String father) {
        this.parent = father;
    }

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }

    public String getClassB() {
        return classB;
    }

    public void setClassB(String classB) {
        this.classB = classB;
    }
}
