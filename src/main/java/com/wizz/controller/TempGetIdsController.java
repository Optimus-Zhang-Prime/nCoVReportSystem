package com.wizz.controller;

import com.wizz.dao.impl.ProjectDaoImpl;
import com.wizz.entity.Project;
import com.wizz.utils.TempGetIdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @descrip：临时获取ids数据的controller，注意只是项目初始化的时候使用，非异步，会很慢，并且需要学校VPN
 * @author: 李佳
 * @create： 2020-02-27-18:57
 **/
@Controller
public class TempGetIdsController {
    @Autowired
    TempGetIdsUtils tempGetIdsUtils;
    @ResponseBody
    @RequestMapping(path = "/ids-lijia-dd-11663",method = RequestMethod.GET)
    public String test () {
        return tempGetIdsUtils.dbTransfer();
    }
    @ResponseBody
    @RequestMapping(path = "/ids-11663343536", method = RequestMethod.GET)
    public String test1 () {
        return tempGetIdsUtils.dbTransferDepartment();
    }
}
