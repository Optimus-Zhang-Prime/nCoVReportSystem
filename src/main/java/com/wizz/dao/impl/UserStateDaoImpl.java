package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserStateDao;
import com.wizz.entity.FinalStatus;
import com.wizz.entity.User;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBasePageUtils;
import com.wizz.utils.DataBaseUtils;
import com.wizz.utils.DepartmentMapUtils;
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
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classA: '%s',}).get()", true, orgid);
    }
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
    @Override
    public List<User> getClassBilluser(String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classB: '%s',classA: '%s'}).get()", true, orgid,orgfathername);
    }

    @Override
    public List<User> getClassCilluser(String orggrandfathername,String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({sick: %s,classC: '%s',classB: '%s',classA: '%s'}).get()", true, orgid,orgfathername,orggrandfathername);
    }


    @Override
    public List<User> getClassASuspectedUser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classA: '%s'}).get()", FinalStatus.YISIGANRAN.toString(),orgid);
    }

    @Override
    public List<User> getClassBSuspectedUser(String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.YISIGANRAN.toString(),orgid,orgfathername);
    }

    @Override
    public List<User> getClassCSuspectedUser(String orggrandfathername,String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classC: '%s',classB:'%s',classA: '%s'}).get()", FinalStatus.YISIGANRAN.toString(),orgid,orgfathername,orggrandfathername);
    }



//    @Select({"select * from user where (index BETWEEN 70 AND 100) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassAHdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classA: '%s'}).get()", FinalStatus.GAODUYIGAN.toString(),orgid);
    }

    @Override
    public List<User> getClassBHdangeruser(String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.GAODUYIGAN.toString(),orgid,orgfathername);
    }

    @Override
    public List<User> getClassCHdangeruser(String orggrandfathername,String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classC: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.GAODUYIGAN.toString(),orgid,orgfathername,orggrandfathername);
    }
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassAMdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classA: '%s'}).get()", FinalStatus.YIGAN.toString(),orgid);
    }

    @Override
    public List<User> getClassBMdangeruser(String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.YIGAN.toString(),orgid,orgfathername);
    }

    @Override
    public List<User> getClassCMdangeruser(String orggrandfathername,String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classC: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.YIGAN.toString(),orgid,orgfathername,orggrandfathername);
    }

//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
    @Override
    public List<User> getClassALdangeruser(String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classA: '%s'}).get()", FinalStatus.WUFENGXIAN.toString(),orgid);
    }

    @Override
    public List<User> getClassBLdangeruser(String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classB: '%s',classA: '%s'}).get()", FinalStatus.WUFENGXIAN.toString(),orgid,orgfathername);
    }

    @Override
    public List<User> getClassCLdangeruser(String orggrandfathername,String orgfathername,String orgid) {
        return getData("db.collection('user-1').limit(1000).where({finalStatus: '%s',classC: '%s', classB: '%s',classA: '%s'}).get()", FinalStatus.WUFENGXIAN.toString(),orgid,orgfathername,orggrandfathername);
    }
//    @Select({"select * from user where classA=#{orgid}" })
    @Override
    public List<User> getClassAAllUser(String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classA: '%s'}).get()", skip,orgid);
    }

    @Override
    public List<User> getClassBAllUser(String orgfathername,String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classB: '%s',classA: '%s'}).get()", skip, orgid,orgfathername);
    }

    @Override
    public List<User> getClassCAllUser(String orggrandfathername,String orgfathername,String orgid,Integer page) {
        Integer skip = dataBasePageUtils.getSkip(page);
        return getData("db.collection('user-1').skip(%d).limit(50).where({classC: '%s',classB: '%s',classA: '%s'}).get()", skip,orgid,orgfathername,orggrandfathername);
    }
}
