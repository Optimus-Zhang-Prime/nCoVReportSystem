package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.entity.Report;
import com.wizz.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class UserReportController {
    @Autowired
    DailyReportService dailyReportService;

    @ResponseBody//查看用户打卡
    @RequestMapping(path = "user/seeuserreport/", method = RequestMethod.POST)
    public JSONObject seeUserReport(@RequestParam("userid")String userid){
        List<Report> reportList=dailyReportService.seeUserReport(userid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allReport", reportList);
        return jsonObject;
    }
    @ResponseBody//代替用户打卡，无出行
    @RequestMapping(path = "user/adminreport/", method = RequestMethod.POST)
    public Integer adminHelpReport(@RequestParam("userid")String userid,@RequestParam("address")String Address,@RequestParam("symbol")Boolean symbol,@RequestParam("status")String status,@RequestParam("subversion") String subversion){
        return dailyReportService.helpReport(userid,Address,symbol,status,subversion);
    }
    @ResponseBody//代替用户打卡,有出行
    @RequestMapping(path = "user/adminreport/", method = RequestMethod.POST)
    public Integer adminHelpReport2(@RequestParam("userid")String userid,@RequestParam("address")String Address,@RequestParam("symbol")Boolean symbol,@RequestParam("status")String status,@RequestParam("subversion") String subversion,@RequestParam("travelNumber")String travelNumber){
        return dailyReportService.helpReport(userid,Address,symbol,status,subversion,travelNumber);
    }

    @ResponseBody//修改用户打卡,无出行
    @RequestMapping(path = "user/changereport/", method = RequestMethod.POST)
    public Integer changeReport(@RequestParam("reportid")String reportid,@RequestParam("address")String Address,@RequestParam("symbol")Boolean symbol,@RequestParam("status")String status,@RequestParam("subversion") String subversion){
        return dailyReportService.changeReport(reportid,Address,symbol,status,subversion);
    }
    @ResponseBody//修改用户打卡,有出行
    @RequestMapping(path = "user/changereport/", method = RequestMethod.POST)
    public Integer changeReport2(@RequestParam("reportid")String reportid,@RequestParam("address")String Address,@RequestParam("symbol")Boolean symbol,@RequestParam("status")String status,@RequestParam("subversion") String subversion,@RequestParam("travelNumber")String travelNumber){
        return dailyReportService.changeReport(reportid,Address,symbol,status,subversion,travelNumber);
    }
}
