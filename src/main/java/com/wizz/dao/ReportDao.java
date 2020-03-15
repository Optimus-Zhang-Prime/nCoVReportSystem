package com.wizz.dao;


import com.wizz.entity.Location;
import com.wizz.entity.Report;
import com.wizz.entity.ReportLocation;
import com.wizz.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface ReportDao {//每日汇报信息

    List<String> getStatus123Userid ();////获取汇报status为“1/2/3”的用户id
    List<String> getStatus4Userid (); ////获取汇报status为“4.接触过确诊患者”的用户id
    List<Report> getReportByUserId(String id);//查找该用户所有每日填报信息


     void saveReport(String userid, ReportLocation Address, Boolean symptom, String status, String subversion, String createTime);
     //参数分别为：用户id，地址是否有症状，“是否有下列情况”，“就医记录”,"创建时间


     void saveReport2(String userid,ReportLocation Address,Boolean symptom,String status,String subversion,String travelNumber,String createTime)  ;


     List<String> getSymptomUserid();//获取填报信息中有症状的用户id，用于计算易感指数

    //修改指定id的report
     void changeReport(String reportId,ReportLocation Address,Boolean symptom,String status,String subversion,String openid);

     //修改指定id的report,多了交通班次参数
     void changeReport2(String reportId,ReportLocation Address,Boolean symptom,String status,String subversion,String travelNumber,String openid);
//     List<Location> getUserLocation (String id);

}
