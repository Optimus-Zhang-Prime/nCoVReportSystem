package com.wizz.controller;

import com.alibaba.fastjson.JSONArray;
import com.wizz.utils.TempGetIdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @descrip：临时获取ids数据的controller
 * @author: 李佳
 * @create： 2020-02-27-18:57
 **/
@Controller
public class TempGetIdsController {
    @Autowired
    TempGetIdsUtils tempGetIdsUtils;
    @ResponseBody
    @RequestMapping("/ids")
    public String test () {
        return tempGetIdsUtils.dbTransfer();
    }
}
