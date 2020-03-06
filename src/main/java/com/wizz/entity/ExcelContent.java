package com.wizz.entity;

import java.util.List;

/**
 * @descrip：Excel内容的封装
 * @author: 李佳
 * @create： 2020-03-06-17:30
 **/
public class ExcelContent<T> {
    private String fileNames;
    private Integer columnNumber; // 列数
    private List<String> columnName; // 列标题
    private String workBookName;// 工作本名称
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public ExcelContent() {
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public String getWorkBookName() {
        return workBookName;
    }

    public void setWorkBookName(String workBookName) {
        this.workBookName = workBookName;
    }

    public ExcelContent(String fileNames, Integer columnNumber, List<String> columnName, String workBookName, T content) {
        this.fileNames = fileNames;
        this.columnNumber = columnNumber;
        this.columnName = columnName;
        this.workBookName = workBookName;
        this.content = content;
    }
}
