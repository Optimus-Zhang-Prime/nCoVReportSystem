package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.ReportDao;
import com.wizz.entity.Report;

import com.wizz.entity.ReportLocation;
import com.wizz.entity.ReportStatus;
import com.wizz.entity.jsonReturn.QueryReturn;

import com.wizz.exception.DbErrorException;
import com.wizz.property.TencentAPIProperties;
import com.wizz.utils.CloudFunctionUtils;
import com.wizz.utils.DataBaseUtils;
import com.wizz.utils.GetAdcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * @descrip：reportDao的实现类
 * @author: 李佳
 * @create： 2020-02-29-01:26
 **/
@Repository
public class ReportDaoImpl implements ReportDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
    @Autowired
    TencentAPIProperties tencentAPIProperties;
    @Autowired
    CloudFunctionUtils cloudFunctionUtils;
    @Override
    public List<String> getStatus123Userid() {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('report').limit(1000).field({_openid: true}).where({status: _.and(_.neq('%s'),_.neq('%s'))}).get()", ReportStatus.FIFTH.toString(),ReportStatus.FOURTH.toString());

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
            tempList.add(temp1.get_openid());
        }
        return tempList;
    }
    @Override
    public List<String> getStatus4Userid() {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('report').limit(1000).field({_openid: true}).where({status: '%s'}).get()", ReportStatus.FOURTH.toString());

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
            tempList.add(temp1.get_openid());
        }
        return tempList;
    }
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
    @Override
    public void saveReport(String userid, ReportLocation Address, Boolean symptom, String status, String subversion, String createTime) {
        String lon = Address.getLng();
        String lat = Address.getLat();
        String key = tencentAPIProperties.getKey();
        Map<String, String> adcodeMap = GetAdcode.getAdcode(lon, lat, key);
        String address = adcodeMap.get("address");
        String code = adcodeMap.get("adcode");
        // 需要调用计算地区易感指数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("longitude",Double.valueOf(lon));
        paramMap.put("latitude",Double.valueOf(lat));
        paramMap.put("districtCode",code);
        String getRegionalIndex = cloudFunctionUtils.InvokeFunction("getRegionalIndex", paramMap);
        String regionalIndex = JSON.parseObject(getRegionalIndex).getString("result");

        // 需要调用计算易感指数
        Map<String,Object> paramMap1 = new HashMap<>();
        paramMap1.put("longitude",Double.valueOf(lon));
        paramMap1.put("latitude",Double.valueOf(lat));
        paramMap1.put("adcode",code);
        paramMap1.put("_openid",userid);
        String covIndexCalcualate = cloudFunctionUtils.InvokeFunction("covIndexCalculate", paramMap1);
        String index = JSON.parseObject(covIndexCalcualate).getString("index");
        Boolean hasSick = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("hasSick"));
        Boolean isDg1 = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("isDistancegt1"));
        dataBaseUtils.addData("db.collection('report')" +
                ".add({data:[{_openid: '%s', address: '%s',isSymptom: %s,status: '%s',subversionStatus: '%s' ," +
                "createTime: '%s',adcode: '%s',covIndex: %d,hasSick: %s,isDistancegt1: %s, isTravel: %s,regionalIndex: %d,sick: %s}]})",userid,address,symptom,status,subversion,createTime,code,Integer.valueOf(index),hasSick,isDg1,false,Integer.valueOf(regionalIndex),false);
    }
    @Override
    public void saveReport2(String userid, ReportLocation Address, Boolean symptom, String status, String subversion, String travelNumber, String createTime) {
        String lon = Address.getLng();
        String lat = Address.getLat();
        String key = tencentAPIProperties.getKey();
        Map<String, String> adcodeMap = GetAdcode.getAdcode(lon, lat, key);
        String address = adcodeMap.get("address");
        String code = adcodeMap.get("adcode");
        // 需要调用计算地区易感指数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("longitude",Double.valueOf(lon));
        paramMap.put("latitude",Double.valueOf(lat));
        paramMap.put("districtCode",code);
        String getRegionalIndex = cloudFunctionUtils.InvokeFunction("getRegionalIndex", paramMap);
        String regionalIndex = JSON.parseObject(getRegionalIndex).getString("result");

        // 需要调用计算易感指数
        Map<String,Object> paramMap1 = new HashMap<>();
        paramMap1.put("longitude",Double.valueOf(lon));
        paramMap1.put("latitude",Double.valueOf(lat));
        paramMap1.put("adcode",code);
        paramMap1.put("_openid",userid);
        String covIndexCalcualate = cloudFunctionUtils.InvokeFunction("covIndexCalculate", paramMap1);
        String index = JSON.parseObject(covIndexCalcualate).getString("index");
        Boolean hasSick = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("hasSick"));
        Boolean isDg1 = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("isDistancegt1"));
        dataBaseUtils.addData("db.collection('report')" +
                ".add({data:[{_openid: '%s', address: '%s',isSymptom: %s,status: '%s',subversionStatus: '%s' ," +
                "createTime: '%s',adcode: '%s',covIndex: %d,hasSick: %s,isDistancegt1: %s, isTravel: %s,regionalIndex: %d,sick: %s,travelNumber: '%s'}]})",userid,address,symptom,status,subversion,createTime,code,Integer.valueOf(index),hasSick,isDg1,false,Integer.valueOf(regionalIndex),false,travelNumber);
    }
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
            tempList.add(temp1.get_openid());
        }
        return tempList;
    }

    @Override
    public void changeReport(String reportId, ReportLocation Address, Boolean symptom, String status, String subversion,String openid) {
        String lon = Address.getLng();
        String lat = Address.getLat();
        String key = tencentAPIProperties.getKey();
        Map<String, String> adcodeMap = GetAdcode.getAdcode(lon, lat, key);
        String address = adcodeMap.get("address");
        String code = adcodeMap.get("adcode");
        // 需要调用计算地区易感指数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("longitude",Double.valueOf(lon));
        paramMap.put("latitude",Double.valueOf(lat));
        paramMap.put("districtCode",code);
        String getRegionalIndex = cloudFunctionUtils.InvokeFunction("getRegionalIndex", paramMap);
        String regionalIndex = JSON.parseObject(getRegionalIndex).getString("result");

        // 需要调用计算易感指数
        Map<String,Object> paramMap1 = new HashMap<>();
        paramMap1.put("longitude",Double.valueOf(lon));
        paramMap1.put("latitude",Double.valueOf(lat));
        paramMap1.put("adcode",code);
        paramMap1.put("_openid",openid);
        String covIndexCalcualate = cloudFunctionUtils.InvokeFunction("covIndexCalculate", paramMap1);
        String index = JSON.parseObject(covIndexCalcualate).getString("index");
        Boolean hasSick = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("hasSick"));
        Boolean isDg1 = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("isDistancegt1"));
        dataBaseUtils.updateData("db.collection('report')." +
                "where({_id: '%s'})." +
                "update({data:{address: '%s',isSymptom: '%s',status: '%s'," +
                "subversionStatus: '%s',isTravel: %s,adcode: '%s'," +
                "regionalIndex: %d, covIndex: %d,hasSick: %s,isDistancegt1: %s}})",reportId,address,symptom,status,subversion,false,code,Integer.valueOf(regionalIndex),Integer.valueOf(index),hasSick,isDg1);
    }
    @Override
    public void changeReport2(String reportId, ReportLocation Address, Boolean symptom, String status, String subversion, String travelNumber,String openid) {
        String lon = Address.getLng();
        String lat = Address.getLat();
        String key = tencentAPIProperties.getKey();
        Map<String, String> adcodeMap = GetAdcode.getAdcode(lon, lat, key);
        String address = adcodeMap.get("address");
        String code = adcodeMap.get("adcode");
        // 需要调用计算地区易感指数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("longitude",Double.valueOf(lon));
        paramMap.put("latitude",Double.valueOf(lat));
        paramMap.put("districtCode",code);
        String getRegionalIndex = cloudFunctionUtils.InvokeFunction("getRegionalIndex", paramMap);
        String regionalIndex = JSON.parseObject(getRegionalIndex).getString("result");

        // 需要调用计算易感指数
        Map<String,Object> paramMap1 = new HashMap<>();
        paramMap1.put("longitude",Double.valueOf(lon));
        paramMap1.put("latitude",Double.valueOf(lat));
        paramMap1.put("adcode",code);
        paramMap1.put("_openid",openid);
        String covIndexCalcualate = cloudFunctionUtils.InvokeFunction("covIndexCalculate", paramMap1);
        String index = JSON.parseObject(covIndexCalcualate).getString("index");
        Boolean hasSick = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("hasSick"));
        Boolean isDg1 = Boolean.valueOf(JSON.parseObject(covIndexCalcualate).getString("isDistancegt1"));
        dataBaseUtils.updateData("db.collection('report')." +
                "where({_id: '%s'})." +
                "update({data:{address: '%s',isSymptom: '%s',status: '%s'," +
                "subversionStatus: '%s',isTravel: %s,adcode: '%s'," +
                "regionalIndex: %d, covIndex: %d,hasSick: %s,isDistancegt1: %s,travelNumber: '%s'}})",reportId,address,symptom,status,subversion,true,code,Integer.valueOf(regionalIndex),Integer.valueOf(index),hasSick,isDg1,travelNumber);
    }
}
