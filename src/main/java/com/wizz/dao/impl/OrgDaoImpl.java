package com.wizz.dao.impl;


import com.alibaba.fastjson.JSON;
import com.wizz.dao.OrgDao;
import com.wizz.entity.Org;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.CloudFunctionUtils;
import com.wizz.utils.DataBaseUtils;
import com.wizz.utils.DepartmentMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @descrip：orgDao的实现
 * @author: 李佳
 * @create： 2020-02-28-01:04
 **/
@Repository
public class OrgDaoImpl implements OrgDao {
    @Autowired
    DepartmentMapUtils departmentMapUtils;
    @Autowired
    CloudFunctionUtils cloudFunctionUtils;
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
            throw new DbErrorException("org数据库访问出错了--getAdminUser_Org");
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
    public Org getorgByid(String id) {
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
        // 获得唯一的组织信息 json
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
        // 添加组织信息的时候，需要把院系的标准化名字转为院系代码，添加到组织信息中，便于对应用户
        String departmentid = departmentMapUtils.getDepartmentid(name);
        dataBaseUtils.addData("db.collection('org').add({data:[{parent: '%s', name: '%s',grade: %d,classA: '%s',orgIdForClassB: '%s'}]})",project,name,grade,classA,departmentid);
    }

    @Override
    public void addOrg3(String project, String name, Integer grade, String classA, String classB) {
        dataBaseUtils.addData("db.collection('org').add({data:[{parent: '%s', name: '%s',grade: %d,classA: '%s',classB: '%s'}]})",project,name,grade,classA,classB);
    }

    @Override
    public void deleteAdmin(String orgID, String tel) {
        Map<String,Object> map = new HashMap<>();
        map.put("orgid",orgID);
        map.put("tel",tel);
        // 之所以这里使用云函数是因为小程序数据库奇葩地不支持删除数组中的成员的操作
        cloudFunctionUtils.InvokeFunction("listDelete", map);
    }

    @Override
    public List<Org> gerOrgByProjectId(String projectID) {//只返回一级组织
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(1000).where({parent: '%s',grade: %d}).get()",projectID,1);
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
    @Override
    public void deleteOrg(String orgId) {
        dataBaseUtils.deleteData("db.collection('org').where({_id:'%s'}).remove()",orgId);
    }

    @Override
    public List<Org> getClassBOrgByParentClass(String orgId) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(1000).where({classA: '%s',grade: %d}).get()",orgId,2);
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

    @Override
    public List<Org> getClassCOrgByParentClass(String orgId) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(1000).where({classB: '%s',grade: %d}).get()",orgId,3);
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

    @Override
    public List<Org> getclassCOrg() {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').limit(1000).where({grade: %d}).get()",3);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException("org数据库访问出错了----getclassCOrg");
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

    @Override
    public Integer getUserAccount(String classA,String classB,String classC) {
        return dataBaseUtils.getCount("db.collection('user-1').where({classA:'%s',classB:'%s',classC:'%s'}).count()",classA,classB,classC);
    }

    @Override
    public Integer getUserAccount(String classA, String classB) {
        return dataBaseUtils.getCount("db.collection('user-1').where({classA:'%s',classB:'%s'}).count()",classA,classB);
    }

    @Override
    public Integer getUserAccount(String classA) {
        return dataBaseUtils.getCount("db.collection('user-1').where({classA:'%s'}).count()",classA);
    }
}
