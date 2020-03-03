package com.wizz.service;


import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.entity.Report;
import com.wizz.entity.Location;
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
    public Integer helpReport(String userid,String Address,Boolean symbol,String status,String subversion){
        try {
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport(userid, Address, symbol, status, subversion,String.valueOf(System.currentTimeMillis()));
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer helpReport(String userid,String Address,Boolean symbol,String status,String subversion,String travelNumber){
        try{
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport2(userid,Address,symbol,status,subversion,travelNumber,String.valueOf(System.currentTimeMillis()));
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer changeReport(String reportId,String Address,Boolean symbol,String status,String subversion){
        try {
            reportDao.changeReport(reportId, Address, symbol, status, subversion);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer changeReport(String reportId,String Address,Boolean symbol,String status,String subversion,String travelNumber){
        try {
            reportDao.changeReport2(reportId, Address, symbol, status, subversion,travelNumber);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public List<Location> getUserLocation(String id){
            return reportDao.getUserLocation(id);
    }
}
