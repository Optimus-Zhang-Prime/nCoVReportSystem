package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.YanzhengDao;
import com.wizz.dao.impl.YanzhengDaoImpl;
import com.wizz.entity.Org;
import com.wizz.exception.ControllerException;
import com.wizz.service.AdminlogService;
import com.wizz.utils.ForumUtils;
import com.wizz.utils.SendMess;
import org.hibernate.validator.constraints.Length;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@Validated
public class AdminLogController {

    @Autowired
    AdminlogService adminlogService;
    
    @Autowired
    YanzhengDaoImpl yanzhengdao;
    
    @ResponseBody//发送验证码
    @RequestMapping(path = "yanzheng/", method = RequestMethod.POST)
    public JSONObject yanZheng(@Valid @Length(min = 11,max = 11,message = "手机号不符合规范") @RequestParam("tel") String tel) {
        JSONObject jsonObject = new JSONObject();         
        List<Org> orgList = adminlogService.getOrgByAdmin(tel);           
        if(orgList == null || orgList.size() == 0 ){
                jsonObject.put("code", 1004);
                jsonObject.put("msg", "该电话不具有管理员权限");
                return jsonObject;
        }
        try {
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(9999));
            SendMess.sendCode(tel,code);// 发送验证码
            yanzhengdao.save(tel,code);//保存或修改
            jsonObject.put("code", 1000);
            jsonObject.put("msg", "短信发送成功");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 1006);
            jsonObject.put("msg", "短信发送失败");
            return jsonObject;
        }
    }

    @ResponseBody//获取用户管理的所有组织
    @RequestMapping(path = "getorgbyadmin/", method = RequestMethod.POST)
    public JSONObject getOrgByUser(@Valid @Length(min = 11,max = 11,message = "手机号不符合规范") @RequestParam("tel") String tel,@RequestParam("code") String code,HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String acode = "";//数据库中存的验证码
        try{
            acode=yanzhengdao.get(tel);//获取验证码
        }
        catch(Exception e){
            e.printStackTrace();
            jsonObject.put("code", 1006);
            jsonObject.put("msg", "未找到验证码");
            jsonObject.put("grade", "");
            jsonObject.put("orgList", "");
            return jsonObject;
        }
        if(code.equals(acode)){//验证成功
            Cookie cookie = new Cookie("name", tel);
            response.addCookie(cookie);
            try {
                List<Org> orgList = adminlogService.getOrgByAdmin(tel);
                Integer grade=3;
                for(Org org:orgList){//管理员所管理的最高级组织的等级
                    Integer g=org.getGrade();
                    if(g<grade){//选出最高级
                        grade=g;
                    }
                }
                jsonObject.put("code", 1000);
                jsonObject.put("msg", "验证成功");
                jsonObject.put("grade", grade);
                jsonObject.put("orgList", orgList);

            }
            catch (Exception e){
                e.printStackTrace();
                jsonObject.put("code", 1006);
                jsonObject.put("msg", "验证失败，请联系系统管理员");
                jsonObject.put("grade", "");
                jsonObject.put("orgList", "");
            }
            return jsonObject;
        }
        else{
            jsonObject.put("code", 1006);
            jsonObject.put("msg", "验证码错误");
            jsonObject.put("grade", "");
            jsonObject.put("orgList", "");
            return jsonObject;
        }
    }


    @ResponseBody//退出
    @RequestMapping(path = "user/logout/", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        Integer code = adminlogService.logout(response);
        return ForumUtils.toJsonString(code);
    }
}
