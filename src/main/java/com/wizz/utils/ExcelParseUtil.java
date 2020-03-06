package com.wizz.utils;

import com.wizz.entity.ExcelContent;
import com.wizz.entity.jsonReturn.ReportsByDate;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @descrip：解析Excel的工具类
 * @author: 李佳
 * @create： 2020-02-24-20:23
 **/
@Component
public class ExcelParseUtil {
    public static void  ExcelParseUtil (InputStream inputStream, String fileName){
        /**
         * 这个inputStream文件可以来源于本地文件的流，
         *  也可以来源与上传上来的文件的流，也就是MultipartFile的流，
         *  使用getInputStream()方法进行获取。
         */
        /**
         * 然后再读取文件的时候，应该excel文件的后缀名在不同的版本中对应的解析类不一样
         * 要对fileName进行后缀的解析
         */
        Workbook workbook = null;
        try {
            //判断什么类型文件
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook == null) {
            return ;
        } else {
            //获取所有的工作表的的数量
            int numOfSheet = workbook.getNumberOfSheets();
            System.out.println(numOfSheet+"--->numOfSheet");
            //遍历表
            for (int i = 0; i < numOfSheet; i++) {
                //获取一个sheet也就是一个工作本。
                Sheet sheet = workbook.getSheetAt(i);
                if(sheet == null) continue;
                //获取一个sheet有多少Row
                int lastRowNum = sheet.getLastRowNum();
                System.out.println(lastRowNum + "-------> row");
                if(lastRowNum == 0) continue;
                Row row ;
                for (int j  = 0; j <= lastRowNum; j++) {
                    row = sheet.getRow(j);
                    if(row == null) {
                        continue;
                    }
                    //获取一个Row有多少Cell
                    short lastCellNum = row.getLastCellNum();
                    System.out.println(lastCellNum + "------> cellNum");
                    for (int k = 0; k <= lastCellNum; k++) {
                        if(row.getCell(k)==null) {
                            continue;
                        }
                        row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                        String res = row.getCell(k).getStringCellValue().trim();
                        //打印出cell(单元格的内容)
                        System.out.println(res);
                    }

                }
            }
        }

    }
    public void writeExcel(ExcelContent<List<ReportsByDate>> excelContent, HttpServletResponse httpServletResponse){
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一个工作本
        HSSFSheet sheet = wb.createSheet(excelContent.getWorkBookName());
        //设置表头
        setTitle(excelContent,wb,sheet);
        for (int i = 0; i < excelContent.getContent().size(); i++) {
            // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(i + 1);
            // 创建HSSFCell对象 设置单元格的值
            row.createCell(0).setCellValue(excelContent.getContent().get(i).getCreateTime_str());
            row.createCell(1).setCellValue(excelContent.getContent().get(i).getUser().get(0).getName());
            row.createCell(2).setCellValue(excelContent.getContent().get(i).getUser().get(0).getNumber());
            row.createCell(3).setCellValue(excelContent.getContent().get(i).getUser().get(0).getPhone());
            row.createCell(4).setCellValue(excelContent.getContent().get(i).getAddress());
            row.createCell(5).setCellValue(excelContent.getContent().get(i).getSymptoms());
            row.createCell(6).setCellValue(excelContent.getContent().get(i).getStatus());
            row.createCell(7).setCellValue(excelContent.getContent().get(i).getSubversionStatus());
        }


//        response.setHeader("Content-Disposition", "attchement;filename=" + new String("人员信息.xls".getBytes("gb2312"), "ISO8859-1"));
//        response.setContentType("application/msexcel");
        try {
            // 清空response
            httpServletResponse.reset();
            httpServletResponse.addHeader("Content-Disposition", "attachment;filename="+ new String(excelContent.getFileNames().getBytes("UTF-8"),"ISO-8859-1"));
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
            OutputStream os = new BufferedOutputStream(httpServletResponse.getOutputStream());
            wb.write(os);
            os.flush();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void setTitle(ExcelContent<List<ReportsByDate>> excelContent,HSSFWorkbook workbook, HSSFSheet sheet){
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i <excelContent.getColumnNumber(); i++) {
            sheet.setColumnWidth(i, 50*256);
        }
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        // 设置第一行的单元块
        HSSFCell cell;
        for (int i = 0;i < excelContent.getColumnNumber(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelContent.getColumnName().get(i));
            cell.setCellStyle(style);
        }
    }
}
