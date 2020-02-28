package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.entity.User;
import com.wizz.service.SeeStateService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


public class SeeOrgStateController {//按组织查看疫情信息
    @Autowired
    SeeStateService seeStateService;

    @ResponseBody
    @RequestMapping(path = "user/seeorgstate/", method = RequestMethod.POST)
    public JSONObject seeOrgState(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgid") Integer orgid){
        seeStateService.calculate();//计算易感指数
        List<User> illUsers =seeStateService.getIllUser(orggrade,orgid);//确诊人数
        List<User> HdangerUsers=seeStateService.getHdangerUser(orggrade,orgid);//高度易感
        List<User> MdamgerUsers=seeStateService.getMdangerUser(orggrade,orgid);//易感
        List<User> LdangerUsers=seeStateService.getLdangerUser(orggrade,orgid);//无风险用户
        JSONObject ajsonObject = new JSONObject();
        ajsonObject.put("illUsers", illUsers);
        ajsonObject.put("HdangerUsers", HdangerUsers);
        ajsonObject.put("MdangerUsers", MdamgerUsers);
        ajsonObject.put("LdangerUsers", LdangerUsers);
        return ajsonObject;
    }

    @ResponseBody
    @RequestMapping(path = "user/seealluser/", method = RequestMethod.POST)
    public JSONObject seeAllUser(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgid") Integer orgid){
        List<User> allUsers =seeStateService.getAllUser(orggrade,orgid);//组织内全部用户
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allUsers", allUsers);
        return jsonObject;
    }
}