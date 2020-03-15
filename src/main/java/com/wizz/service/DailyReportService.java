package com.wizz.service;


import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.entity.Report;
import com.wizz.entity.Location;
import com.wizz.entity.ReportLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyReportService {
    @Autowired
    ReportDaoImpl reportDao;

    public List<Report> seeUserReport(String id) {
            return reportDao.getReportByUserId(id);
    }
    public Integer helpReport(String userid, ReportLocation Address, Boolean symbol, String status, String subversion,String openid){
        try {
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport(userid, Address, symbol, status, subversion,String.valueOf(System.currentTimeMillis()),openid);
            return 1000;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1006;
        }
    }
    public Integer helpReport(String userid,ReportLocation Address,Boolean symbol,String status,String subversion,String travelNumber,String openid){
        try{
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport2(userid,Address,symbol,status,subversion,travelNumber,String.valueOf(System.currentTimeMillis()),openid);
            return 1000;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1006;
        }
    }
    public Integer changeReport(String reportId,ReportLocation Address,Boolean symbol,String status,String subversion,String openid){
        try {
            reportDao.changeReport(reportId, Address, symbol, status, subversion,openid);
            return 1000;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1006;
        }
    }
    public Integer changeReport(String reportId,ReportLocation Address,Boolean symbol,String status,String subversion,String travelNumber,String openid){
        try {
            reportDao.changeReport2(reportId, Address, symbol, status, subversion,travelNumber,openid);
            return 1000;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1006;
        }
    }
}
