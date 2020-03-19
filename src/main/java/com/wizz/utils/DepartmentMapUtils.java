package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.Department;
import com.wizz.entity.Org;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @descrip：查询院系映射关系
 * @author: 李佳
 * @create： 2020-03-03-20:35
 **/
@Component
public class DepartmentMapUtils {
    @Autowired
    DataBaseUtils dataBaseUtils;
    public String getDepartmentid (String depName) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('department').where({depName: '%s'}).get()",depName);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException("department表查询失败");
        } else if (strOutput.isEmpty()) {
            throw new DbErrorException("此院系不存在");
        }
        // 获得唯一的院系信息 json
        String output = strOutput.get(0);
        Department department = JSON.parseObject(output, Department.class);
        String id = department.getDepId();
        return id;
    }
}
