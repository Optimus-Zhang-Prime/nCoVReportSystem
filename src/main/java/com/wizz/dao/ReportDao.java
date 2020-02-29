package com.wizz.dao;


import com.wizz.entity.Report;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportDao {//每日汇报信息

     List<Report> getReportByUserId(String id);//查找该用户所有每日填报信息


     void saveReport(String userid, String Address, Boolean symptom, String status, String subversion, String createTime);
     //参数分别为：用户id，地址是否有症状，“是否有下列情况”，“就医记录”,"创建时间


     void saveReport2(String userid,String Address,Boolean symptom,String status,String subversion,String travelNumber,String createTime)  ;


     List<String> getSymptomUserid();//获取填报信息中有症状的用户id，用于计算易感指数

    //修改指定id的report
     void changeReport(String reportId,String Address,Boolean symptom,String status,String subversion);

     //修改指定id的report,多了交通班次参数
     void changeReport2(String reportId,String Address,Boolean symptom,String status,String subversion,String travelNumber);
}
