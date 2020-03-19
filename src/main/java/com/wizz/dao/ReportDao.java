package com.wizz.dao;

import com.wizz.entity.Report;
import com.wizz.entity.ReportLocation;
import java.util.List;

/*
*每日打卡信息的操作
 */
public interface ReportDao {

    List<String> getStatus123Userid ();////获取汇报status为 非 接触过确诊患者和无以上情况 的用户openid

    List<String> getStatus4Userid (); ////获取汇报status为“接触过确诊患者”的用户openid

    List<Report> getReportByUserId(String id);//根据用户的openid 查找该用户所有的每日填报信息

    //帮助打卡 参数分别为：用户openid，地址（ReportLocation格式），是否有症状，是否有下列情况，就医记录,创建时间
    void saveReport(String userid, ReportLocation Address, Boolean symptom, String status, String subversion, String createTime);

    //帮助打卡 参数分别为：用户openid，地址（ReportLocation格式），是否有症状，是否有下列情况，就医记录,出行交通工具的代码,创建时间
    void saveReport2(String userid,ReportLocation Address,Boolean symptom,String status,String subversion,String travelNumber,String createTime)  ;


    List<String> getSymptomUserid();//获取填报信息中有症状(isSymptom为true)的用户id，用于计算易感指数

    //修改指定id的report
    // 参数分别为：用户openid，地址（ReportLocation格式），是否有症状，是否有下列情况，就医记录,此记录所属的用户的openID
    void changeReport(String reportId,ReportLocation Address,Boolean symptom,String status,String subversion,String openid);

    //修改指定id的report,多了交通班次参数
    //参数分别为：用户openid，地址（ReportLocation格式），是否有症状，是否有下列情况，就医记录,出行的交通工具的代码,此记录所属的用户的openID
    void changeReport2(String reportId,ReportLocation Address,Boolean symptom,String status,String subversion,String travelNumber,String openid);

}
