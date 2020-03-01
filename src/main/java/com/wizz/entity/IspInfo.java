package com.wizz.entity;

/**
 * @descrip：运营商数据封装
 * @author: 李佳
 * @create： 2020-02-27-18:20
 **/
public class IspInfo {
    private String _id;
    private String _openid;
    private String time;
    private String location;

    public IspInfo(String _id, String _openid, String time, String location) {
        this._id = _id;
        this._openid = _openid;
        this.time = time;
        this.location = location;
    }

    public IspInfo() {
    }

    public String get_openid() {
        return _openid;
    }

    public void set_openid(String _openid) {
        this._openid = _openid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
