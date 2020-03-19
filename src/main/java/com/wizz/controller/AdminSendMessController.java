package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.ReportDao;
import com.wizz.dao.UserDao;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.Report;
import com.wizz.entity.User;
import com.wizz.utils.SendMess;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Validated
public class AdminSendMessController {
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    ReportDaoImpl reportDao;

    @ResponseBody//获取组织内未打卡成员
    @RequestMapping(path = "getuserwithoutreport/", method = RequestMethod.POST)
    public JSONObject getUserWithoutReport(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgfathername") String orgfathername,@RequestParam("orgname")String orgname,@RequestParam("orggrandfathername") String  orggrandfathername) {
        //返回未打卡用户列表
        List<User> userList=userDao.UserWithoutReport(orgfathername,orggrade,orgname,orggrandfathername);
        JSONObject ajsonObject = new JSONObject();
        ajsonObject.put("userWithoutReport", userList);
        return ajsonObject;
    }
    @ResponseBody//获取组织内未打卡且没绑定电话用户
    @RequestMapping(path = "getuserwithoutreportandtel/", method = RequestMethod.POST)
    public JSONObject getUserWithoutReportAndTel(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgfathername") String orgfathername,@RequestParam("orgname")String orgname,@RequestParam("orggrandfathername") String orggrandfathername) {
        List<User> userWithoutReportList=userDao.UserWithoutReport(orgfathername,orggrade,orgname,orggrandfathername);
        List<User> userWithoutReportAndTel=new ArrayList<User>();
        try {
            for (User user : userWithoutReportList) {
                if (user.getPhone() == null || user.getPhone().equals("")){
                    userWithoutReportAndTel.add(user);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("");
        }
        JSONObject ajsonObject = new JSONObject();
        ajsonObject.put("userWithoutReportAndTel", userWithoutReportAndTel);
        return ajsonObject;
    }
    @ResponseBody//用户电话
    @RequestMapping(path = "setusertel/", method = RequestMethod.POST)
    public Integer setUserTel(@RequestParam("useropenid") String useropenid,@Valid @Length(min = 11,max = 11,message = "手机号不符合规范")@RequestParam("tel")String tel) {
        //设置用户电话
        userDao.setUserTel(useropenid,tel);
        return 1000;
    }
    @ResponseBody//发送短信提醒
    @RequestMapping(path = "sendmess/", method = RequestMethod.POST)
    public Integer setUserTel(@RequestParam("orggrade") Integer orggrade,@RequestParam("orgfathername") String orgfathername,@RequestParam("orgname")String orgname,@RequestParam("orggrandfathername") String orggrandfathername) {
        List<User> userWithoutReportList=userDao.UserWithoutReport(orgfathername,orggrade,orgname,orggrandfathername);
        List<String> list=new ArrayList();//用户电话列表
            int FailSending=0;
            for(User user:userWithoutReportList){
                try{
                    list.add(user.getPhone());
                }
                catch (Exception e){
                    e.printStackTrace();
                    FailSending+=1;
                }
            }
        String[] phoneNumbers=new String[list.size()];
    	phoneNumbers=list.toArray(phoneNumbers);
        SendMess.sendNotice(phoneNumbers);//发送短信
        return  FailSending;
    }
}
