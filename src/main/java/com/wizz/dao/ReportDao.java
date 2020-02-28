package com.wizz.dao;


import com.wizz.entity.Report;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReportDao {//每日汇报信息
     @Select({"select * from report where uid=#{id}"})
     List<Report> getReportByUserId(Integer id);//查找该用户所有每日填报信息

     @Insert({"insert into report"})//添加填报信息
     void saveReport(Integer userid,String Address,Boolean symptom,String status,String subversion,String createTime);
     //参数分别为：用户id，地址是否有症状，“是否有下列情况”，“就医记录”,"创建时间

     @Insert({"insert into report"})//添加填报信息，重载，多了交通工具班次参数
     void saveReport2(Integer userid,String Address,Boolean symptom,String status,String subversion,String travelNumber,String createTime)  ;

     @Select({"select uid from report where isSymptom=True"})
     List<Integer> getSymptomUserid();//获取填报信息中有症状的用户id，用于计算易感指数

     @Update({"update report         where id=#{reportId}"})//修改指定id的report
     void changeReport(Integer reportId,String Address,Boolean symptom,String status,String subversion);

     @Update({"update report         where id=#{reportId}"})//修改指定id的report,多了交通班次参数
     void changeReport2(Integer reportId,String Address,Boolean symptom,String status,String subversion,String travelNumber);
}
