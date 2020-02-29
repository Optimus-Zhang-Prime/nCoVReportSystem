package com.wizz.dao.impl;

import com.wizz.Application;
import com.wizz.entity.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReportDaoImplTest {
    @Autowired
    ReportDaoImpl reportDao;
    @Test
    public void getReportByUserId() {
        List<Report> reportByUserId = reportDao.getReportByUserId("12");
        System.out.println(reportByUserId);
    }

    @Test
    public void saveReport() {
        reportDao.saveReport("12","河北省",false,"还好","wefwef",String.valueOf(System.currentTimeMillis()));
    }

    @Test
    public void saveReport2() {
        reportDao.saveReport2("12","河北省",false,"还好","wefwef","12",String.valueOf(System.currentTimeMillis()));
    }

    @Test
    public void getSymptomUserid() {
        List<String> symptomUserid = reportDao.getSymptomUserid();
        System.out.println(symptomUserid);
    }

    @Test
    public void changeReport() {
        reportDao.changeReport("905f52d1-f1ac-4845-8946-d5a30bc0e252","河南省",true,"dsad","asdasdasd");
    }

    @Test
    public void changeReport2() {
        reportDao.changeReport2("905f52d1-f1ac-4845-8946-d5a30bc0e252","河南省",true,"dsad","asdasdasd","12121212");
    }
}