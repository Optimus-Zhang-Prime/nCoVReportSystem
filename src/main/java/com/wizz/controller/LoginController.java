package com.wizz.controller;

import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.entity.User;
import com.wizz.service.UserService;
import com.wizz.utils.ForumUtils;


import com.wizz.utils.TokenUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    TokenUtils tokenUtils;



    @ResponseBody//注册
    @RequestMapping(path="register/",method = RequestMethod.POST)
    public String register(@RequestParam("name") String name, @RequestParam("password") String  password) throws JSONException {
        Integer code=userService.register(name,password);
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//登录
    @RequestMapping(path="login/",method = RequestMethod.POST)
    public String login(@RequestParam("name") String name, @RequestParam("password") String  password, HttpServletResponse response) throws JSONException {
        Integer code=userService.login(name,password);
        if(code==1000){
            Cookie cookie=new Cookie("name",name);
            response.addCookie(cookie);
        }
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//退出
    @RequestMapping(path="user/logout/",method = RequestMethod.POST)
    public String logout(HttpServletRequest request,HttpServletResponse response) throws JSONException {
        Integer code=userService.logout(response);
        return ForumUtils.toJsonString(code);
    }



    // 用来测试
    @ResponseBody
    @RequestMapping("/user/{username}")
    public String test (@PathVariable("username") String username) {
        return userDao.getUserByName(username);
    }
    // 用来测试
    @ResponseBody
    @RequestMapping("/token")
    public String test1 () {
        return tokenUtils.getAccessToken();
    }
    // 用来测试
    @ResponseBody
    @RequestMapping("/user")
    public String test1 (User user) {
        userDao.addUser(user);
        return "成功";
    }
}
