package com.wizz.dao.impl;


import com.alibaba.fastjson.JSON;
import com.wizz.dao.OrgDao;
import com.wizz.entity.Org;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @descrip：orgDao的实现
 * @author: 李佳
 * @create： 2020-02-28-01:04
 **/
@Repository
public class OrgDaoImpl implements OrgDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
//    @Select({"select * from org where admin数组里有该name(电话号码)"})
    @Override
    public List<Org> getAdminUser_Org(String name) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(100).where({admins: '%s'}).get()",name);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
        if (!"0".equals(errcode)) {
            throw new DbErrorException("org数据库访问出错了");
        }
        List<Org> tempList = new ArrayList<>();
        // 转换包装
        for (Iterator<String> iterator = strOutput.iterator(); iterator.hasNext();) {
            String temp = iterator.next();
            Org temp1 = JSON.parseObject(temp, Org.class);
            tempList.add(temp1);
        }
        return tempList;
    }
//    @Update({"update org 把admin表加上tel where id=#{orgid}"})
    @Override
    public void addAdmin(String orgid, String tel) {
        dataBaseUtils.updateData("db.collection('org').where({_id: '%s'}).update({data:{admins: _.push('%s') }})",orgid,tel);
    }

    @Override
    public Org getOrgById(String id) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(100).where({_id: '%s'}).get()",id);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
        if (!"0".equals(errcode)) {
            throw new DbErrorException("");
        } else if (strOutput.isEmpty()) {
            throw new DbErrorException("此组织不存在");
        }
        // 获得唯一的用户信息 json
        String output = strOutput.get(0);
        Org org = JSON.parseObject(output,Org.class);
        return org;
    }
//    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade})"})
    @Override
    public void addOrg1(String project, String name, Integer grade) {
        dataBaseUtils.addData("db.collection('org').add({data:[{parent: '%s', name: '%s',grade: %d}]})",project,name,grade);
    }

    @Override
    public void addOrg2(String project, String name, Integer grade, String classA) {
        dataBaseUtils.addData("db.collection('org').add({data:[{parent: '%s', name: '%s',grade: %d,classA: '%s'}]})",project,name,grade,classA);
    }
    @Override
    public void addOrg3(String project, String name, Integer grade, String classA, String classB) {
        dataBaseUtils.addData("db.collection('org').add({data:[{parent: '%s', name: '%s',grade: %d,classA: '%s',classB: '%s'}]})",project,name,grade,classA,classB);
    }
}
