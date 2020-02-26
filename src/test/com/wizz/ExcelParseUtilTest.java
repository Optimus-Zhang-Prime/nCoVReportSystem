package com.wizz;

import com.wizz.utils.ExcelParseUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ExcelParseUtilTest {

    @Test
    public void excelParseUtil() {
        try(InputStream in = new FileInputStream("/Users/lijia/Downloads/demo.xlsx")) {
            ExcelParseUtil.ExcelParseUtil(in,"demo.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}