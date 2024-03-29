package com.wizz.utils;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.OrgDao;
import com.wizz.dao.ReportDao;
import com.wizz.dao.UserDao;
import com.wizz.dao.impl.OrgDaoImpl;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.Org;
import com.wizz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AutoSendMessScheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ReportDaoImpl reportDao;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    OrgDaoImpl orgDao;

    //每天晚上18点00自动执行（为Application类添加@EnableScheduling注释后启动）
    @Scheduled(cron = "0 00 18 ? * *")
    public void SendMessToUser() {
        int FailSending = 0;
        System.out.println("向未打卡用户发送消息提醒，任务执行时间：" + dateFormat.format(new Date()));
        List<Org> orgList = orgDao.getclassCOrg();// 返回所有三级组织
        
        List<String> list=new ArrayList();//用户电话列表
        
        for (Org org : orgList) {//每个组织
            List<User> userWithoutReportList = userDao.UserWithoutReport(orgDao.getorgByid(org.getClassB()).getOrgIdForClassB(), 3, org.getName(),orgDao.getorgByid(org.getClassA()).getName());
            for (User user : userWithoutReportList) {//每个用户
                try {
                    list.add(user.getPhone());
                } catch (Exception e) {
                    e.printStackTrace();
                    FailSending += 1;
                }
            }

        }
        String[] phoneNumbers=new String[list.size()];
    	phoneNumbers=list.toArray(phoneNumbers);
        SendMess.sendNotice(phoneNumbers);//发送短信
        
        System.out.println(FailSending);
    }

    //每天晚上10点自动执行（为Application类添加@EnableScheduling注释后启动）
    @Scheduled(cron = "0 00 22 ? * *")
    public void SendMessToAdmin() {
        int FailSending = 0;
        System.out.println("向三级组织管理员发送消息提醒，任务执行时间：" + dateFormat.format(new Date()));
        List<Org> orgList = orgDao.getclassCOrg();// 返回所有三级组织
        for (Org org : orgList) {//每个组织
            List<User> userWithoutReportList = userDao.UserWithoutReport(orgDao.getorgByid(org.getClassB()).getOrgIdForClassB(), 3, org.getName(),orgDao.getorgByid(org.getClassA()).getName());
            Integer num = userWithoutReportList.size();
            try{           
                for (String tel : org.getAdmins()) {//该组织的每个管理员
                    try {
                        SendMess.sendNoticeToAdmin(tel,num);
                    } catch (Exception e) {
                        e.printStackTrace();
                        FailSending += 1;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("有组织没有管理员");
            }

        }
        System.out.println(FailSending);
    }
}

