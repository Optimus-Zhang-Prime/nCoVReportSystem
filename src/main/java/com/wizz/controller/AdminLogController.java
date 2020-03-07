package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.YanzhengDao;
import com.wizz.dao.impl.YanzhengDaoImpl;
import com.wizz.entity.Org;
import com.wizz.service.AdminlogService;
import com.wizz.utils.ForumUtils;
import com.wizz.utils.SendMess;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
public class AdminLogController {

    @Autowired
    AdminlogService adminlogService;
    
    @Autowired
    YanzhengDaoImpl yanzhengdao;
    
    @ResponseBody//发送验证码
    @RequestMapping(path = "yanzheng/", method = RequestMethod.POST)
    public Integer yanZheng(@RequestParam("tel") String tel) {
        try {
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(9999));
            SendMess.sendCode(tel,code);
            yanzhengdao.save(tel,code);//保存或修改
            return 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 1006;
        }
    }

    @ResponseBody//获取用户管理的所有组织
    @RequestMapping(path = "getorgbyadmin/", method = RequestMethod.POST)
    public JSONObject getOrgByUser(@RequestParam("tel") String tel,@RequestParam("code") String code,HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String acode = "";
        try{
            acode=yanzhengdao.get(tel);//获取验证码

        }
        catch(Exception e){
            jsonObject.put("code", 1006);
            return jsonObject;
        }
        if(code.equals(acode)){
            Cookie cookie = new Cookie("name", tel);
            response.addCookie(cookie);
            jsonObject.put("code", 1000);
            try {
                List<Org> orgList = adminlogService.getOrgByAdmin(tel);
                jsonObject.put("orgList", orgList);
            }
            catch (Exception e){
                jsonObject.put("orgList", "");
            }
            return jsonObject;
        }
        else{
            jsonObject.put("code", 1006);
            return jsonObject;
        }
//
    }
//    @ResponseBody//登录
//    @RequestMapping(path = "login/", method = RequestMethod.POST)
//    public String login(@RequestParam("tel") String tel, @RequestParam("password") String password, HttpServletResponse response,HttpSession httpSession) throws JSONException {
//        JSONObject userCode = (JSONObject)httpSession.getAttribute("code");
//        //验证码
//        userCode .get("code");
//        //手机号
//        userCode.get("memPhone");
//        Integer code = adminlogService.login(tel);
//        if (code == 1000) {
//            Cookie cookie = new Cookie("name", tel);
//            response.addCookie(cookie);
//        }
//        return ForumUtils.toJsonString(code);
//    }

    @ResponseBody//退出
    @RequestMapping(path = "user/logout/", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        Integer code = adminlogService.logout(response);
        return ForumUtils.toJsonString(code);
    }
}
