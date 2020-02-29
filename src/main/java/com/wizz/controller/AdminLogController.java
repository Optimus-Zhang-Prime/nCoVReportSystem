package com.wizz.controller;


import com.wizz.service.AdminlogService;

import com.wizz.utils.ForumUtils;


import com.wizz.utils.TokenUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AdminLogController {

    @Autowired
    AdminlogService adminlogService;

    @ResponseBody//登录
    @RequestMapping(path = "login/", method = RequestMethod.POST)
    public String login(@RequestParam("tel") String name, @RequestParam("password") String password, HttpServletResponse response) throws JSONException {
        Integer code = adminlogService.login(name, password);
        if (code == 1000) {
            Cookie cookie = new Cookie("name", name);
            response.addCookie(cookie);
        }
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//退出
    @RequestMapping(path = "user/logout/", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        Integer code = adminlogService.logout(response);
        return ForumUtils.toJsonString(code);
    }
}

