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

    @ResponseBody//组织内成员的详细状况
    @RequestMapping(path = "user/seeorgstate/", method = RequestMethod.POST)
    public String seeOrgState(@RequestParam("orggrade")Integer orggrade,@RequestParam("orggrandfathername") String orggrandfathername,@RequestParam("orgfathername") String orgfathername,@RequestParam("orgname") String orgname){
        List<User> illUsers =seeStateService.getIllUser(orggrandfathername,orggrade,orgfathername,orgname);//确诊人数
        List<User> SuspectedUsers=seeStateService.getSuspectedUser(orggrandfathername,orggrade,orgfathername,orgname);//疑似患者
        List<User> HdangerUsers=seeStateService.getHdangerUser(orggrandfathername,orggrade,orgfathername,orgname);//高度易感
        List<User> MdangerUsers=seeStateService.getMdangerUser(orggrandfathername,orggrade,orgfathername,orgname);//易感
        List<User> LdangerUsers=seeStateService.getLdangerUser(orggrandfathername,orggrade,orgfathername,orgname);//无风险用户
        Map<String,List<User>> map = new HashMap<>();
        map.put("illUsers", illUsers);
        map.put("HdangerUsers", HdangerUsers);
        map.put("SuspectedUsers", SuspectedUsers);
        map.put("MdangerUsers", MdangerUsers);
        map.put("LdangerUsers", LdangerUsers);
        String result = JSON.toJSONString(map);
        return result;
    }

    @ResponseBody//返回组织内全部用户
    @RequestMapping(path = "user/seealluser/", method = RequestMethod.POST)
    public String seeAllUser(@RequestParam("orggrade")Integer orggrade,@RequestParam("orggrandfathername") String orggrandfathername,@RequestParam("orgfathername") String orgfathername,@RequestParam("orgname") String orgname,@RequestParam("page")Integer page){
        List<User> allUsers =seeStateService.getAllUser(orggrandfathername,orggrade,orgfathername,orgname,page);//组织内全部用户
        String result = JSON.toJSONString(allUsers);
        return result;
    }
    
    @ResponseBody//查看指定项目下的一级组织
    @RequestMapping(path = "user/seeorgByproject/", method = RequestMethod.POST)
    public String seeOrgInProject(@RequestParam("projectid")String projectid){
        List<Org> org =seeStateService.getorgByProject(projectid);
        String result = JSON.toJSONString(org);
        return result;
    }
    @ResponseBody//查看指定组织的下一级组织
    @RequestMapping(path = "user/seeorgbyparent/", method = RequestMethod.POST)
    public String seeOrgByParentClass(@RequestParam("orggrade")Integer orggrade,@RequestParam("orgid")String orgid){
        List<Org> org =seeStateService.getorgByParentClass(orggrade,orgid);
        String result = JSON.toJSONString(org);
        return result;
    }
}
