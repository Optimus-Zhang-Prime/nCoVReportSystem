package com.wizz.entity;

/**
 * @descrip：department表
 * @author: 李佳
 * @create： 2020-03-03-20:41
 **/
public class Department {
    private String _id;
    private String depId;
    private String depName;

    @Override
    public String toString() {
        return "Department{" +
                "_id='" + _id + '\'' +
                ", depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Department() {
    }

    public Department(String _id, String depId, String depName) {
        this._id = _id;
        this.depId = depId;
        this.depName = depName;
    }
}
