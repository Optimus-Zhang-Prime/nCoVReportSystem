package com.wizz.controller;

import com.wizz.entity.ExcelContent;
import com.wizz.entity.jsonReturn.ReportsByDate;
import com.wizz.property.FileProperties;
import com.wizz.service.SeeStateService;
import com.wizz.utils.ExcelParseUtil;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @descrip：资源文件的上传与下载
 * @author: 李佳
 * @create： 2020-02-25-23:34
 **/

@Controller
@Validated
public class FileUpAndDownController {
    // 文件上传的功能暂时不用
    // 上传文件的存储路径
    @Autowired
    FileProperties fileProperties;
    @Autowired
    ExcelParseUtil excelParseUtil;
    @Autowired
    SeeStateService seeStateService;
    @PostMapping("/uploadSheet")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
        // 上传文件为空
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return null;
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileProperties.getPath() + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return null;
    }
    //还需要一个时间参数，期待的时间参数是月份+日即可
    @RequestMapping(path = "excel/",method = RequestMethod.POST)
    public void exportExcel(@RequestParam("orggrade")Integer orggrade, @RequestParam("orggrandfathername") String orggrandfathername, @RequestParam("orgfathername") String orgfathername, @RequestParam("orgname") String orgid, @Valid @Range(min = 1,max = 12,message = "月份不符合规范")@RequestParam("month") Integer month, @Valid @Range(min = 1,max = 31,message = "日份不符合规范")@RequestParam("day") Integer day, HttpServletRequest request, HttpServletResponse response) {
        List<ReportsByDate> reports = seeStateService.getReportsByDate(orggrandfathername,orgfathername,orggrade, orgid, month, day);
        if("研究生".equals(orgid)) {
            orgid = "Postgraduate";
        }
        String fileName = String.format("%s-%s-%s.xls",orgid,month,day);
        List<String> columnNames = new ArrayList<>();
        columnNames.add("日期");
        columnNames.add("姓名");
        columnNames.add("学号");
        columnNames.add("手机号");
        columnNames.add("地理位置");
        columnNames.add("身体状况");
        columnNames.add("接触情况");
        columnNames.add("就医记录");
        String workBookName = month+"月"+day+"日"+"打卡信息";
        ExcelContent<List<ReportsByDate>> excel = new ExcelContent(fileName,8,columnNames,workBookName,reports);
        excelParseUtil.writeExcel(excel,response);
    }
}
