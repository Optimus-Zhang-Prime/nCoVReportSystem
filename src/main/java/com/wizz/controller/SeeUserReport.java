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

public class SeeUserReport {
    @Autowired
    DailyReportService dailyReportService;

    @ResponseBody//查看用户打卡
    @RequestMapping(path = "user/seeuserreport/", method = RequestMethod.POST)
    public JSONObject seeUserReport(@RequestParam("userid")Integer userid){
        List<Report> reportList=dailyReportService.seeUserReport(userid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allReport", reportList);
        return jsonObject;
    }
}
