package com.wizz.controller;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.Org;
import com.wizz.entity.User;
import com.wizz.service.SeeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SeeOrgStateController {//按组织查看疫情信息
    @Autowired
    SeeStateService seeStateService;

    @ResponseBody
    @RequestMapping(path = "user/seeorgstate/", method = RequestMethod.POST)
    public String seeOrgState(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgname") String orgid){
        seeStateService.calculate();//计算易感指数
        List<User> illUsers =seeStateService.getIllUser(orggrade,orgid);//确诊人数
        List<User> SuspectedUsers=seeStateService.getSuspectedUser(orggrade,orgid);//疑似患者
        List<User> HdangerUsers=seeStateService.getHdangerUser(orggrade,orgid);//高度易感
        List<User> MdangerUsers=seeStateService.getMdangerUser(orggrade,orgid);//易感
        List<User> LdangerUsers=seeStateService.getLdangerUser(orggrade,orgid);//无风险用户
//        JSONObject ajsonObject = new JSONObject();
        Map<String,List<User>> map = new HashMap<>();
        map.put("illUsers", illUsers);
        map.put("HdangerUsers", HdangerUsers);
        map.put("SuspectedUsers", SuspectedUsers);
        map.put("MdangerUsers", MdangerUsers);
        map.put("LdangerUsers", LdangerUsers);
        String result = JSON.toJSONString(map);
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "user/seealluser/", method = RequestMethod.POST)
    public String seeAllUser(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgname") String orgid,@RequestParam("page")Integer page){
        List<User> allUsers =seeStateService.getAllUser(orggrade,orgid,page);//组织内全部用户
//        JSONObject jsonObject = new JSONObject();
        String result = JSON.toJSONString(allUsers);
//        jsonObject.put("allUsers", allUsers);
        return result;
    }
    
    @ResponseBody
    @RequestMapping(path = "user/seeorgByproject/", method = RequestMethod.POST)
    public String seeOrgInProject(@RequestParam("projectid")String projectid){
        List<Org> org =seeStateService.getorgByProject(projectid);
        String result = JSON.toJSONString(org);
        return result;
    }
    @ResponseBody
    @RequestMapping(path = "user/seeorgbyparent/", method = RequestMethod.POST)
    public String seeOrgByParentClass(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgid")String orgid){
        List<Org> org =seeStateService.getorgByParentClass(orggrade,orgid);
        String result = JSON.toJSONString(org);
        return result;
    }
}
