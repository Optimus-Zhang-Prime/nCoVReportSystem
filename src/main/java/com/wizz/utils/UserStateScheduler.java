package com.wizz.utils;
import com.wizz.dao.ReportDao;
import com.wizz.dao.UserDao;
import com.wizz.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UserStateScheduler{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ReportDao reportDao;
    @Autowired
    UserDao userDao;

    //每天晚上18点05执行（为Application类添加@EnableScheduling注释后启动）
    @Scheduled(cron = "0 05 18 ? * *")
    public void calculateUserState() {
        System.out.println("计算用户身体状况，任务执行时间：" + dateFormat.format(new Date()));


        //计算累积易感指数的部分
        Integer account = userDao.getUserAccount();//获取当前用户数量
        int n=1;
        while (n*900<account){//为满足数据库一千条的限制，一次取900
            List<String> userIDList=UserDao.getUserid(n);//分页取用户id列表
            for (String uid:userIDList){//每个用户执行一次
               List<Report> userReportList=getReportByUserId(uid);//该用户所有汇报
               int sum=0;
               for(Report report:userReportList){
                   sum+=report.index;//对易感指数求和
               }
               Integer average=sum/userReportList.size();//求平均
               if(average<21){
                   UserDao.setUserConditionWuFengXian(uid);//无风险
               }
               else if(average<70){
                   UserDao.setUserConditionYiGan(uid);//易感
               }
               else if(average<=90){
                   UserDao.setUserConditionGaoduYiGan(uid);//高度易感
               }
               else if(average<=100){
                   UserDao.setUserConditionYiSi(uid);//疑似
               }

            }
            n+=1;//新一轮
        }


        //用户有情况的部分，不管易感系数，直接设为相应的状态
        List<String> useridList1=reportDao.getStatus123Userid();//获取汇报status为“1/2/3”的用户id
        /*1.有疫区旅居史。2.接触过流行病高发地区（湖北省）人员。3.接触过疑似患者。*/
        for(String uid:useridList1){
            userDao.setUserConditionYiGan(uid);//将id=uid的用户状态设为易感
        }
        List<String> useridList2=reportDao.getStatus4Userid();//获取汇报status为“4.接触过确诊患者”的用户id
        for(String uid:useridList2){
            userDao.setUserConditionGaoduYiGan(uid);//将id=uid的用户状态设为高度易感
        }
        List<String> useridList3 = reportDao.getSymptomUserid();//体温高的用户
        for(String uid:useridList3){
            userDao.setUserConditionYiSi(uid);//设为疑似
        }
    }
}

