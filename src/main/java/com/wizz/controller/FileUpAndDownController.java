package com.wizz.controller;

import com.wizz.property.FileProperties;
import com.wizz.utils.ExcelParseUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @descrip：资源文件的上传与下载
 * @author: 李佳
 * @create： 2020-02-25-23:34
 **/

@Controller
public class FileUpAndDownController {
    // 上传文件的存储路径
    @Autowired
    FileProperties fileProperties;
    @Autowired
    ExcelParseUtil excelParseUtil;
    @PostMapping("/uploadSheet")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
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

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException  e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    @RequestMapping(path = "excel/",method = RequestMethod.POST)
    public void exportExcel(@RequestParam("orggrade")Integer orggrade, @RequestParam("orgname") String orgid,HttpServletRequest request,HttpServletResponse response) {

        excelParseUtil.writeExcel("学生信息.xlsx",response);
    }
}
