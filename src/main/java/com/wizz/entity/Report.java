package com.wizz.entity;

public class Report {//每日报告
    private String _id;
    private String _openid;
    private String createTime;
    private String phone;
    private String location;
    private String geoHash;
    private String address;
    private Boolean sick;
    private String subversionStatus;
    private Boolean isSymptom;
    private Boolean isTravel;
    private String status;
    private String travelType;
    private String travelNumber;
    private String extra;
    private String regionalIndex;
    private Double createTime_num;

    public Double getCreateTime_num() {
        return createTime_num;
    }

    public void setCreateTime_num(Double createTime_num) {
        this.createTime_num = createTime_num;
    }

    @Override
    public String toString() {
        return "Report{" +
                "_id='" + _id + '\'' +
                ", _openid='" + _openid + '\'' +
                ", createTime='" + createTime + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", geoHash='" + geoHash + '\'' +
                ", address='" + address + '\'' +
                ", sick=" + sick +
                ", subversionStatus='" + subversionStatus + '\'' +
                ", isSymptom=" + isSymptom +
                ", isTravel=" + isTravel +
                ", status='" + status + '\'' +
                ", travelType='" + travelType + '\'' +
                ", travelNumber='" + travelNumber + '\'' +
                ", extra='" + extra + '\'' +
                ", regionalIndex='" + regionalIndex + '\'' +
                ", createTime_num=" + createTime_num +
                '}';
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getRegionalIndex() {
        return regionalIndex;
    }

    public void setRegionalIndex(String regionalIndex) {
        this.regionalIndex = regionalIndex;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSick() {
        return sick;
    }

    public void setSick(Boolean sick) {
        this.sick = sick;
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

    public Boolean getTravel() {
        return isTravel;
    }

    public void setTravel(Boolean travel) {
        isTravel = travel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTravelNumber() {
        return travelNumber;
    }

    public void setTravelNumber(String travelNumber) {
        this.travelNumber = travelNumber;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Report() {
    }

    public Report(String _id, String _openid, String createTime, String phone, String location, String geoHash, String address, Boolean sick, String subversionStatus, Boolean isSymptom, Boolean isTravel, String status, String travelType, String travelNumber, String extra, String regionalIndex, Double createTime_num) {
        this._id = _id;
        this._openid = _openid;
        this.createTime = createTime;
        this.phone = phone;
        this.location = location;
        this.geoHash = geoHash;
        this.address = address;
        this.sick = sick;
        this.subversionStatus = subversionStatus;
        this.isSymptom = isSymptom;
        this.isTravel = isTravel;
        this.status = status;
        this.travelType = travelType;
        this.travelNumber = travelNumber;
        this.extra = extra;
        this.regionalIndex = regionalIndex;
        this.createTime_num = createTime_num;
    }
}
