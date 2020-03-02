package com.wizz.dao;

import com.wizz.entity.User;


import java.util.List;


public interface UserStateDao {//按组织查看学生身体状况


    List<User> getClassAilluser(String orgid);//返回一级组织（组织id为orgid）确诊用户

    List<User> getClassBilluser(String orgid);//返回二级组织确诊用户

    List<User> getClassCilluser(String orgid);//返回三级组织确诊用户


    List<User> getClassAHdangeruser(String orgid);//返回一级组织高度易感用户（指数70-100）

    List<User> getClassBHdangeruser(String orgid);//返回二级组织高度易感用户

    List<User> getClassCHdangeruser(String orgid);//返回三级组织高度易感用户


    List<User> getClassAMdangeruser(String orgid);//返回一级组织易感用户（指数21-69）

    List<User> getClassBMdangeruser(String orgid);//返回二级组织易感用户

    List<User> getClassCMdangeruser(String orgid);//返回三级组织易感用户


    List<User> getClassALdangeruser(String orgid);//返回一级组织无风险用户（指数0-20）

    List<User> getClassBLdangeruser(String orgid);//二级组织无风险用户

    List<User> getClassCLdangeruser(String orgid);//三级组织无风险用户


    List<User> getClassAAllUser(String orgid,Integer page);//返回某一级组织内全部用户

    List<User> getClassBAllUser(String orgid,Integer page);//返回二级组织内全部用户

    List<User> getClassCAllUser(String orgid,Integer page);//返回三级组织内全部用户

}
