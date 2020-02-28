package com.wizz.dao;


import com.wizz.entity.Report;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReportDao {//每日汇报信息
     @Select({"select * from report where uid=#{id}"})
     List<Report> getReportByUserId(Integer id);//查找该用户所有填报
}
