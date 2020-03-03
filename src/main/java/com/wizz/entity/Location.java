package com.wizz.entity;

/**
 * @descrip：爬虫的疫情数据
 * @author: 李佳
 * @create： 2020-02-27-18:49
 **/
public class Location {
    private String _id;
    private String address;
    private String city;
    private String dataSource;
    private String date;
    private String district;
    private String districtCode;
    private String location;
    private String number;
    private String province;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Location() {
    }

    public Location(String _id, String address, String city, String dataSource, String date, String district, String districtCode, String location, String number, String province) {
        this._id = _id;
        this.address = address;
        this.city = city;
        this.dataSource = dataSource;
        this.date = date;
        this.district = district;
        this.districtCode = districtCode;
        this.location = location;
        this.number = number;
        this.province = province;
    }
}