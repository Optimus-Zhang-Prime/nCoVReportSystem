package com.wizz.entity;

/**
 * @descrip：爬虫的疫情数据
 * @author: 李佳
 * @create： 2020-02-27-18:49
 **/
public class SpamData {
    private String _id;
    private String _openid;
    private String location;
    private Integer number;
    private String address;

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

    public SpamData(String _id, String _openid, String location, Integer number, String address) {
        this._id = _id;
        this._openid = _openid;
        this.location = location;
        this.number = number;
        this.address = address;
    }

    public SpamData() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
