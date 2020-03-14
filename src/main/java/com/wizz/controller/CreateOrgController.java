package com.wizz.controller;

import com.wizz.entity.Org;
import com.wizz.service.AdminService;
import com.wizz.service.SeeStateService;
import com.wizz.utils.ForumUtils;
import org.hibernate.validator.constraints.Length;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


@Controller
@Validated
public class CreateOrgController {
    @Autowired
    AdminService adminService;
    @Autowired
    SeeStateService seeStateService;

    @ResponseBody//创建一级组织
    @RequestMapping(path = "user/createorg1/", method = RequestMethod.POST)
    public String createOrg(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") Integer grade) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade);
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//创建二级组织
    @RequestMapping(path = "user/createorg2/", method = RequestMethod.POST)
    public String createOrg2(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") int grade,@RequestParam("classA")String classA ) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade,classA);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//创建三级组织
    @RequestMapping(path = "user/createorg3/", method = RequestMethod.POST)
    public String createOrg3(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") int grade,@RequestParam("classA")String classA ,@RequestParam("classB") String classB) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade,classA,classB);
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//添加组织管理员
    @RequestMapping(path = "user/addAdmin/", method = RequestMethod.POST)
    public String addOrgAdmin(@RequestParam("orgid") String orgid,@Valid @Length(min = 11,max = 11,message = "手机号不符合规范")@RequestParam("tel")  String tel)  throws JSONException {
        Integer code = adminService.addAdmin(orgid, tel);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//删除组织管理员
    @RequestMapping(path = "user/deleteAdmin/", method = RequestMethod.POST)
    public String deleteOrgAdmin(@RequestParam("orgid") String orgid,@Valid @Length(min = 11,max = 11,message = "手机号不符合规范")@RequestParam("tel") String tel)  throws JSONException {
        Integer code = adminService.deleteAdmin(orgid, tel);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//删除组织
    @RequestMapping(path = "user/deleteorg/", method = RequestMethod.POST)
    public String deleteOrg(@RequestParam("orgid")String orgid) throws JSONException {
        Org org=seeStateService.getorgByid(orgid);
        Integer grade=org.getGrade();//获取该组织的级数，用于找到子组织
        Integer code = adminService.deleteOrg(orgid);
        List<Org> orgToDelete = seeStateService.getorgByParentClass(grade,orgid);
        for(Org org1:orgToDelete){//逐个删除子组织
            adminService.deleteOrg(org1.get_id());
        }
        return ForumUtils.toJsonString(code);
    }

}
