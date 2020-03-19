package com.wizz.dao;

import com.wizz.entity.User;


import java.util.List;

/*
*按组织查看学生身体状况
* 如果是一级组织的话，orgid是本科生/研究生 orgfathername可为空 orggrandfathername可为空
* 如果是二级组织的话，orgid是院系代码 orgfathername研究生、本科生 orggrandfathername可为空
* 如果是三级组织的话，orgid是17、18、19 orgfathername为院系代码 orggrandfathername为研究生或者本科生
 */
public interface UserStateDao {

    List<User> getClassAilluser(String orgid);//返回一级组织（组织id为orgid）确诊用户

    List<User> getClassBilluser(String orgfathername,String orgid);//返回二级组织确诊用户

    List<User> getClassCilluser(String orggrandfathername,String orgfathername,String orgid);//返回三级组织确诊用户


    List<User> getClassAHdangeruser(String orgid);//返回一级组织高度易感用户（指数70-89）

    List<User> getClassBHdangeruser(String orgfathername,String orgid);//返回二级组织高度易感用户

    List<User> getClassCHdangeruser(String orggrandfathername,String orgfathername,String orgid);//返回三级组织高度易感用户


    List<User> getClassASuspectedUser(String orgid);//返回一级组织疑似用户（指数90-100）

    List<User> getClassBSuspectedUser(String orgfathername,String orgid);//返回二级组织疑似用户

    List<User> getClassCSuspectedUser(String orggrandfathername,String orgfathername,String orgid);//返回三级组织疑似用户


    List<User> getClassAMdangeruser(String orgid);//返回一级组织易感用户（指数21-69）

    List<User> getClassBMdangeruser(String orgfathername,String orgid);//返回二级组织易感用户

    List<User> getClassCMdangeruser(String orggrandfathername,String orgfathername,String orgid);//返回三级组织易感用户


    List<User> getClassALdangeruser(String orgid);//返回一级组织无风险用户（指数0-20）

    List<User> getClassBLdangeruser(String orgfathername,String orgid);//二级组织无风险用户

    List<User> getClassCLdangeruser(String orggrandfathername,String orgfathername,String orgid);//三级组织无风险用户


    List<User> getClassAAllUser(String orgid,Integer page);//返回某一级组织内全部用户

    List<User> getClassBAllUser(String orgfathername,String orgid,Integer page);//返回二级组织内全部用户

    List<User> getClassCAllUser(String orggrandfathername,String orgfathername,String orgid,Integer page);//返回三级组织内全部用户

}
