package com.wizz.service;

import com.wizz.dao.ReportDao;
import com.wizz.dao.UserStateDao;
import com.wizz.entity.Report;
import com.wizz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyReportService {
    @Autowired
    ReportDao reportDao;

    public List<Report> seeUserReport(Integer id) {
            return reportDao.getReportByUserId(id);
    }
    public Integer helpReport(Integer userid,String Address,Boolean symbol,String status,String subversion){
        try {
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport(userid, Address, symbol, status, subversion,df.format(day));
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer helpReport(Integer userid,String Address,Boolean symbol,String status,String subversion,String travelNumber){
        try{
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            reportDao.saveReport2(userid,Address,symbol,status,subversion,travelNumber,df.format(day));
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer changeReport(Integer reportId,String Address,Boolean symbol,String status,String subversion){
        try {
            reportDao.changeReport(reportId, Address, symbol, status, subversion);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
    public Integer changeReport(Integer reportId,String Address,Boolean symbol,String status,String subversion,String travelNumber){
        try {
            reportDao.changeReport2(reportId, Address, symbol, status, subversion,travelNumber);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
}
