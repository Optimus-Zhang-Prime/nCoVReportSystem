package com.wizz.entity;

/**
 * @descrip：运营商数据封装
 * @author: 李佳
 * @create： 2020-02-27-18:20
 **/
public class IspInfo {
    private String time;
    private String location;

    public IspInfo(String time, String location) {
        this.time = time;
        this.location = location;
    }

    public IspInfo() {
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
