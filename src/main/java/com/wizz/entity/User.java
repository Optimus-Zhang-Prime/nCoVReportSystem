package com.wizz.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @descrip：userBean
 * @author: 李佳
 * @create： 2020-02-26-01:53
 **/
public class User {
    private Integer pushTimes;
    private Boolean isPushed;
    private String password;
    private String _id;
    private String _openid;
    private String phone;
    private String name;
    private String number;
    private Integer index;//易感指数
    private Boolean gender;//性别
    private Boolean sick;//是否生病
    private String classA;//所属一级组织
    private String classB;
    private String classC;
    private String createTime;
    private String userType;
    private String status;
    private ArrayList<IspInfo>ispInfo;
    private Boolean inSchool;
    private String idNumber;
    private String province;
    private String city;
    private String town;
    private String location;
    private String counselor;
    private String school;
    private String clas;
    private String dormitory;
    private String parentPhone;
    private String department;
    private Boolean haveHouse;

    public Integer getPushTimes() {
        return pushTimes;
    }

    public void setPushTimes(Integer pushTimes) {
        this.pushTimes = pushTimes;
    }

    public Boolean getPushed() {
        return isPushed;
    }

    public void setPushed(Boolean pushed) {
        isPushed = pushed;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "pushTimes=" + pushTimes +
                ", isPushed=" + isPushed +
                ", password='" + password + '\'' +
                ", _id='" + _id + '\'' +
                ", _openid='" + _openid + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", index=" + index +
                ", gender=" + gender +
                ", sick=" + sick +
                ", classA='" + classA + '\'' +
                ", classB='" + classB + '\'' +
                ", classC='" + classC + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userType='" + userType + '\'' +
                ", status='" + status + '\'' +
                ", ispInfo=" + ispInfo +
                ", inSchool=" + inSchool +
                ", idNumber='" + idNumber + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", location='" + location + '\'' +
                ", counselor='" + counselor + '\'' +
                ", school='" + school + '\'' +
                ", clas='" + clas + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", parentPhone='" + parentPhone + '\'' +
                ", department='" + department + '\'' +
                ", haveHouse=" + haveHouse +
                '}';
    }

    public User(Integer pushTimes, Boolean isPushed, String password, String _id, String _openid, String phone, String name, String number, Integer index, Boolean gender, Boolean sick, String classA, String classB, String classC, String createTime, String userType, String status, ArrayList<IspInfo> ispInfo, Boolean inSchool, String idNumber, String province, String city, String town, String location, String counselor, String school, String clas, String dormitory, String parentPhone, String department, Boolean haveHouse) {
        this.pushTimes = pushTimes;
        this.isPushed = isPushed;
        this.password = password;
        this._id = _id;
        this._openid = _openid;
        this.phone = phone;
        this.name = name;
        this.number = number;
        this.index = index;
        this.gender = gender;
        this.sick = sick;
        this.classA = classA;
        this.classB = classB;
        this.classC = classC;
        this.createTime = createTime;
        this.userType = userType;
        this.status = status;
        this.ispInfo = ispInfo;
        this.inSchool = inSchool;
        this.idNumber = idNumber;
        this.province = province;
        this.city = city;
        this.town = town;
        this.location = location;
        this.counselor = counselor;
        this.school = school;
        this.clas = clas;
        this.dormitory = dormitory;
        this.parentPhone = parentPhone;
        this.department = department;
        this.haveHouse = haveHouse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getSick() {
        return sick;
    }

    public void setSick(Boolean sick) {
        this.sick = sick;
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

    public String getClassC() {
        return classC;
    }

    public void setClassC(String classC) {
        this.classC = classC;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<IspInfo> getIspInfo() {
        return ispInfo;
    }

    public void setIspInfo(ArrayList<IspInfo> ispInfo) {
        this.ispInfo = ispInfo;
    }

    public Boolean getInSchool() {
        return inSchool;
    }

    public void setInSchool(Boolean inSchool) {
        this.inSchool = inSchool;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getHaveHouse() {
        return haveHouse;
    }

    public void setHaveHouse(Boolean haveHouse) {
        this.haveHouse = haveHouse;
    }
}
