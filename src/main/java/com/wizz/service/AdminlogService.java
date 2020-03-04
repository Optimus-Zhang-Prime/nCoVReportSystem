package com.wizz.service;

import com.wizz.dao.OrgDao;
import com.wizz.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 对每个POST请求返回状态码code
 * 1000：登录/注册成功
 * 1001：密码为空
 * 1002：用户名为空
 * 1003：注册时用户名已存在
 * 1004：登录时用户名不存在
 * 1005：登陆时密码错误
 * 1006：其他错误
 * 1007:用户未登录（没有name的cookie）
 */

@Service
public class AdminlogService {
    @Autowired
    OrgDao orgDao;//管理员电话存在组织表中


    //登录
    public List<Org> getOrgByAdmin(String tel) {
        return orgDao.getAdminUser_Org(tel);
    }

    public Integer logout(HttpServletResponse response) {
        try {
            //CookieUtil.removeCookie(response, "name");
            return 1000;
        } catch (Exception e) {
            return 1006;
        }
    }

}