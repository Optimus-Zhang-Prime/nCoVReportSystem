package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.ProjectDao;
import com.wizz.entity.Project;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @descrip：ProjectDao的实现类
 * @author: 李佳
 * @create： 2020-02-28-00:03
 **/
@Repository
public class ProjectDaoImpl  implements ProjectDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
    @Override
    public List<Project> getAll() {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('project').get()","");
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
        if (!"0".equals(errcode)) {
            throw new DbErrorException("project数据库访问出错了");
        }
        List<Project> projectList = new ArrayList<>();
        // 转换包装
        for (Iterator<String> iterator = strOutput.iterator();iterator.hasNext();) {
            String project = iterator.next();
            Project project1 = JSON.parseObject(project, Project.class);
            projectList.add(project1);
        }
        return projectList;
    }

    @Override
    public void addProject(String name) {
        dataBaseUtils.addData("db.collection('project').add({data:[{name: '%s', state: %s,owner: '%s'}]})",name,true,"xdwgzs");
    }
}
