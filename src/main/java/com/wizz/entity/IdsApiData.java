package com.wizz.entity;

/**
 * @descrip：ids数据
 * @author: 李佳
 * @create： 2020-02-27-18:50
 **/
public class IdsApiData {
    private String _id;
    private String _openid;
    private String name;
    private String id;

    public IdsApiData(String _id, String _openid, String name, String id) {
        this._id = _id;
        this._openid = _openid;
        this.name = name;
        this.id = id;
    }

    public IdsApiData() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
