package com.wizz.dao.impl;


import com.alibaba.fastjson.JSON;
import com.wizz.dao.OrgDao;
import com.wizz.entity.Org;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    @Override
    public Org getOrgById(Integer id) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('org').where({_id: '%s'}).get()",id);
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

    @Override
    public void addOrg(String name, int grade) {
        dataBaseUtils.addData("db.collection('org').add({data:[{name: '%s', state: %s,owner: '%s'}]})",name);
    }

    @Override
    public void addAdmin1(int orgid, String tel) {

    }

    @Override
    public void addAdmin2(int orgid, String tel) {

    }

    @Override
    public void addAdmin3(int orgid, String tel) {

    }
}
