package com.wizz.entity.jsonReturn;

import com.wizz.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @descrip：获取特定日期的特定组织的打卡信息的返回值的映射
 * @author: 李佳
 * @create： 2020-03-06-16:59
 **/
public class ReportsByDate {
    private String _id;
    private String _openid;
    private String address;
    private String createTime_str;
    private Integer dayOfMonth;
    private String status;
    private String subversionStatus;
    private Boolean isSymptom;
    private Integer month;
    private List<User> user;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime_str() {
        return createTime_str;
    }

    public void setCreateTime_str(String createTime_str) {
        this.createTime_str = createTime_str;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubversionStatus() {
        return subversionStatus;
    }

    public void setSubversionStatus(String subversionStatus) {
        this.subversionStatus = subversionStatus;
    }

    public Boolean getSymptom() {
        return isSymptom;
    }

    public void setSymptom(Boolean symptom) {
        isSymptom = symptom;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public ReportsByDate() {
    }

    public ReportsByDate(String _id, String _openid, String address, String createTime_str, Integer dayOfMonth, String status, String subversionStatus, Boolean isSymptom, Integer month, List<User> user) {
        this._id = _id;
        this._openid = _openid;
        this.address = address;
        this.createTime_str = createTime_str;
        this.dayOfMonth = dayOfMonth;
        this.status = status;
        this.subversionStatus = subversionStatus;
        this.isSymptom = isSymptom;
        this.month = month;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReportsByDate{" +
                "_id='" + _id + '\'' +
                ", _openid='" + _openid + '\'' +
                ", address='" + address + '\'' +
                ", createTime_str='" + createTime_str + '\'' +
                ", dayOfMonth=" + dayOfMonth +
                ", status='" + status + '\'' +
                ", subversionStatus='" + subversionStatus + '\'' +
                ", isSymptom=" + isSymptom +
                ", month=" + month +
                ", user=" + user +
                '}';
    }
}
