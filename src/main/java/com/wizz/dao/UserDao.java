package com.wizz.dao;


import com.wizz.entity.User;

import java.util.List;


public interface UserDao {
    User getUserById(String id); //根据用户openid获取该用户
    void setUserIndex90(String id);//将该用户的易感指数设为90（后来改成91了）
    void setUserIndex50(String id);//将该用户的易感指数设为50
    void setUserIndex75(String id);//将该用户的易感指数设为75
    Integer getUserAccount (); // 获取当前系统中的所有的用户的数量
    List<String> getUserid(Integer n); // 分页返回所有用户的openid n为页数 从1开始
    void setUserConditionWuFengXian (String uid); // 根据openid设置用户状态为无风险
    void setUserConditionYiGan(String uid);  // 根据openID设置用户状态为易感
    void setUserConditionGaoduYiGan(String uid); // 根据openID设置用户状态为高度易感
    void setUserConditionYiSi(String uid); // 根据openID设置用户的用户状态为疑似感染
    void setUserIndex(String id,Integer index); // 设置用户的易感指数
    // 获取某组织中的没有打卡的用户
    // 需要注意的是因为需要从用户中获取组织，所以需要注意命名的规则
    // 如果是一级组织的话，orgid是本科生/研究生 orgfathername可为空 orggrandfathername可为空
    // 如果是二级组织的话，orgid是院系代码 orgfathername研究生、本科生 orggrandfathername可为空
    // 如果是三级组织的话，orgid是17、18、19 orgfathername为院系代码 orggrandfathername为研究生或者本科生
    List<User> UserWithoutReport (String orgfathername,Integer orggrade,String orgid,String orggrandfathername);
    void setUserTel (String openid,String tel);  // 根据openID更改、设置用户的电话
}
