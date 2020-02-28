package com.wizz.service;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserDao;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.User;
import com.wizz.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
// 服务层的代码在controller中使用，目前先不管理这一层的代码，主要集中在DAO层
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
//@Component
//public class UserService {
//    @Autowired
//    UserDaoImpl userdao;
//
//    //注册
//    public Integer register(String name, String password) {
//        try {
//            if (password == null | "".equals(password)) {
//                return 1001;
//            }
//            if (name == null | "".equals(name)) {
//                return 1002;
//            }
//
//            String auser = userdao.getUserById(name);
//            if (auser != null) {
//                return 1003;
//            }
//            User user = new User(name, password);
//            userdao.addUser(user);
//            return 1000;//注册成功
//
//        } catch (Exception e) {
//            return 1006;
//        }
//    }
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
//            String auser = userdao.getUserByName(name);
//            User user = JSON.parseObject(auser,User.class);
//            if (auser == null) {
//                return 1004;
//            }
//            if (user.getPassword().equals(password)) {
//                return 1000;//登陆成功
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
