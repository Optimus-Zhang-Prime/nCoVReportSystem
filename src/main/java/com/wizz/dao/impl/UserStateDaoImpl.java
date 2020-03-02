package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserStateDao;
import com.wizz.entity.User;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBasePageUtils;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @descrip：UserstateDAO的实现类
 * @author: 李佳
 * @create： 2020-02-29-00:15
 **/
@Repository
public class UserStateDaoImpl implements UserStateDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
    @Autowired
    DataBasePageUtils dataBasePageUtils;
    List<User> getData (String sql, Object ... args) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult(sql, args);
        List<String> strOutput = queryReturn.getData();

        String errcode = queryReturn.getErrcode();

        if (!"0".equals(errcode)) {
            throw new DbErrorException("");
        }
        List<User> tempList = new ArrayList<>();
        // 转换包装
        for (Iterator<String> iterator = strOutput.iterator(); iterator.hasNext();) {
            String temp = iterator.next();
            User temp1 = JSON.parseObject(temp, User.class);
            tempList.add(temp1);
        }
        return tempList;

    }
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
    @Override
    public List<User> getClassAilluser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classA: '%s'}).get()", true, orgid);
    }
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
    @Override
    public List<User> getClassBilluser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classB: '%s'}).get()", true, orgid);
    }

    @Override
    public List<User> getClassCilluser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classC: '%s'}).get()", true, orgid);
    }
//    @Select({"select * from user where (index BETWEEN 70 AND 100) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassAHdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(70), _.lte(90)),classA: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassBHdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(70), _.lte(90)),classB: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassCHdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(70), _.lte(90)),classC: '%s'}).get()", orgid);
    }
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassAMdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(21), _.lte(69)),classA: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassBMdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(21), _.lte(69)),classB: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassCMdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(21), _.lte(69)),classC: '%s'}).get()", orgid);
    }

    // **************************************指数范围有问题
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassALdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(0), _.lte(20)),classA: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassBLdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(0), _.lte(20)),classB: '%s'}).get()", orgid);
    }

    @Override
    public List<User> getClassCLdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({index: _.and(_.gte(0), _.lte(20)),classC: '%s'}).get()", orgid);
    }
//    @Select({"select * from user where classA=#{orgid}" })
    @Override
    public List<User> getClassAAllUser(String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classA: '%s'}).get()", skip,orgid);
    }

    @Override
    public List<User> getClassBAllUser(String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classB: '%s'}).get()", skip, orgid);
    }

    @Override
    public List<User> getClassCAllUser(String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classC: '%s'}).get()", skip,orgid);
    }
}
