package com.wizz.service;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserDao;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.User;
import com.wizz.utils.CookieUtil;

import com.wizz.dao.OrgDao;
import com.wizz.entity.Org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;



//@Service
//public class AdminlogService {
//    @Autowired
//    OrgDao orgDao;//管理员电话存在组织表中
//
//
//
//    //登录
//    public Integer login(String name, String password) {
//        try {
//            if (password == null | "".equals(password)) {
//                return 1001;
//            }
//            if (name == null | "".equals(name)) {
//                return 1002;
//            }
//
//            List<Org> orgList = orgDao.getAdminUser(name);
//            if (orgList == null) {
//                return 1004;
//            }
//            if (     ) {
//                //待定，要发短信
//                //登陆成功
//            }
//            else {
//                return 1005;
//            }
//        } catch (Exception e) {
//            return 1006;
//        }
//    }
//
//    public Integer logout(HttpServletResponse response){
//        try {
//            //CookieUtil.removeCookie(response, "name");
//            return 1000;
//        }
//        catch (Exception e){
//            return 1006;
//        }
//    }
//
//}
