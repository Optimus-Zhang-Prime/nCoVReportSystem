package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.YanzhengDao;
import com.wizz.entity.User;
import com.wizz.entity.Vcode;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @descrip：验证码接口实现
 * @author: 李佳
 * @create： 2020-03-07-21:56
 **/
@Repository
public class YanzhengDaoImpl  implements YanzhengDao {
    @Autowired
    DataBaseUtils dataBaseUtils;
    @Override
    public void save(String tel, String code) {
        try {
            get(tel);
            // 有此手机号对应的验证码
            dataBaseUtils.updateData("db.collection('vcode').where({tel: '%s'}).update({data:{code: '%s'}})",tel,code);
        } catch (DbErrorException e) {
            if ("没有此手机号对应的验证码".equals(e.getMessage())) {
                // 没有此手机号对应的验证码
                 dataBaseUtils.addData("db.collection('vcode').add({data:[{tel: '%s', code: '%s'}]})",tel,code);
            }
        }


    }

    @Override
    public String get(String tel) {
        QueryReturn queryReturn = dataBaseUtils.getQueryResult("db.collection('vcode').where({tel: '%s'}).get()", tel);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        } else if (strOutput.isEmpty()) {
            throw new DbErrorException("没有此手机号对应的验证码");
        }
        // 获得唯一的用户信息 json
        String output = strOutput.get(0);
        Vcode result = JSON.parseObject(output, Vcode.class);
        return result.getCode();
    }
}
