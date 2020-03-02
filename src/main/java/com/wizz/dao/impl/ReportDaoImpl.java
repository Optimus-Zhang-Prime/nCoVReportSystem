package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.ReportDao;
import com.wizz.entity.Report;

import com.wizz.entity.jsonReturn.QueryReturn;

import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @descrip：reportDao的实现类
 * @author: 李佳
 * @create： 2020-02-29-01:26
 **/
@Repository
public class ReportDaoImpl implements ReportDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
//    @Select({"select * from report where uid=#{id}"})
    @Override
    public List<Report> getReportByUserId(String id) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('report').limit(1000).where({_openid: '%s'}).get()", id);

        List<String> strOutput = queryReturn.getData();

        String errcode = queryReturn.getErrcode();

        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        }
        List<Report> tempList = new ArrayList<>();
        // 转换包装
        for (Iterator<String> iterator = strOutput.iterator(); iterator.hasNext();) {
            String temp = iterator.next();
            Report temp1 = JSON.parseObject(temp, Report.class);
            tempList.add(temp1);
        }
        return tempList;
    }
//    @Insert({"insert into report"})//添加填报信息
    @Override
    public void saveReport(String userid, String Address, Boolean symptom, String status, String subversion, String createTime) {
        dataBaseUtils.addData("db.collection('report').add({data:[{_openid: '%s', address: '%s',isSymptom: '%s',status: '%s',subversionStatus: '%s' ,createTime: '%s'}]})",userid,Address,symptom,status,subversion,createTime);
    }
//    @Insert({"insert into report"})//添加填报信息，重载，多了交通工具班次参数
    @Override
    public void saveReport2(String userid, String Address, Boolean symptom, String status, String subversion, String travelNumber, String createTime) {
        dataBaseUtils.addData("db.collection('report').add({data:[{_openid: '%s', address: '%s',isSymptom: '%s',status: '%s',subversionStatus: '%s' ,createTime: '%s', travelNumber: '%s'}]})",userid,Address,symptom,status,subversion,createTime,travelNumber);
    }

    // 待验证
//    @Select({"select uid from report where isSymptom=True"})
    @Override
    public List<String> getSymptomUserid() {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('report').limit(1000).field({_openid: true}).where({isSymptom: %s}).get()", true);

        List<String> strOutput = queryReturn.getData();
        String errcode = queryReturn.getErrcode();

        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        }
        List<String> tempList = new ArrayList<>();
        // 转换包装
        for (Iterator<String> iterator = strOutput.iterator(); iterator.hasNext();) {
            String temp = iterator.next();
            Report temp1 = JSON.parseObject(temp, Report.class);
            tempList.add(temp1.getUid());
        }
        return tempList;
    }
//    @Update({"update report         where id=#{reportId}"})
    @Override
    public void changeReport(String reportId, String Address, Boolean symptom, String status, String subversion) {
        dataBaseUtils.updateData("db.collection('report').where({_id: '%s'}).update({data:{address: '%s',isSymptom: '%s',status: '%s',subversionStatus: '%s'}})",reportId,Address,symptom,status,subversion);
    }
//    @Update({"update report         where id=#{reportId}"})
    @Override
    public void changeReport2(String reportId, String Address, Boolean symptom, String status, String subversion, String travelNumber) {
        dataBaseUtils.updateData("db.collection('report').where({_id: '%s'}).update({data:{address: '%s',isSymptom: '%s',status: '%s',subversionStatus: '%s',travelNumber: '%s'}})",reportId,Address,symptom,status,subversion,travelNumber);
    }
}
