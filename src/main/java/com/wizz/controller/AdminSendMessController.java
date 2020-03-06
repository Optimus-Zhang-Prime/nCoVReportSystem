package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.ReportDao;
import com.wizz.dao.UserDao;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.Report;
import com.wizz.entity.User;
import com.wizz.utils.SendMess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class AdminSendMessController {
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    ReportDaoImpl reportDao;

    @ResponseBody//获取组织内未打卡成员
    @RequestMapping(path = "getuserwithoutreport/", method = RequestMethod.POST)
    public JSONObject getUserWithoutReport(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgname")String orgname) {
        //返回未打卡用户列表
        List<User> userList=userDao.UserWithoutReport(orggrade,orgname);
        JSONObject ajsonObject = new JSONObject();
        ajsonObject.put("userWithoutReport", userList);
        return ajsonObject;
    }
    @ResponseBody//获取组织内未打卡且没绑定电话用户
    @RequestMapping(path = "getuserwithoutreportandtel/", method = RequestMethod.POST)
    public JSONObject getUserWithoutReportAndTel(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgname")String orgname) {
        List<User> userWithoutReportList=userDao.UserWithoutReport(orggrade,orgname);
        List<User> userWithoutReportAndTel=new ArrayList<User>();
        try {
            for (User user : userWithoutReportList) {
                if (user.getPhone() == null || user.getPhone().equals("")){
                    userWithoutReportAndTel.add(user);
                }
            }
        }
        catch (Exception e){
            System.out.println("");
        }
        JSONObject ajsonObject = new JSONObject();
        ajsonObject.put("userWithoutReportAndTel", userWithoutReportAndTel);
        return ajsonObject;
    }
    @ResponseBody//用户电话
    @RequestMapping(path = "setusertel/", method = RequestMethod.POST)
    public Integer setUserTel(@RequestParam("useropenid") String useropenid,@RequestParam("tel")String tel) {
        //设置用户电话
        userDao.setUserTel(useropenid,tel);
        return 1000;
    }
    @ResponseBody//发送短信提醒
    @RequestMapping(path = "sendmess/", method = RequestMethod.POST)
    public Integer setUserTel(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgname")String orgname) {
        List<User> userWithoutReportList=userDao.UserWithoutReport(orggrade,orgname);
            int FailSending=0;
            for(User user:userWithoutReportList){
                try{
                    SendMess.sendNotice(user.getPhone());
                }
                catch (Exception e){
                    FailSending+=1;
                }
            }
            return  FailSending;
    }
}
