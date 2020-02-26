package com.wizz.service;

import com.wizz.dao.ReportDao;
import com.wizz.dao.UserDao;
import com.wizz.entity.report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeeStateService {//按组织查看疫情信息

    @Autowired
    ReportDao reportDao;
    public List<report> getIllStudent(Integer orgid){
        return reportDao.getIllReport(orgid);//获取生病的学生报告
    }


}
