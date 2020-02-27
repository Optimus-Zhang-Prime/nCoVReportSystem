package com.wizz.dao;

import com.wizz.entity.User;

import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
//@Repository
//public interface UserStateDao {//按组织查看学生身体状况
//
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
//    List<User> getClassAilluser(Integer orgid);//返回一级组织（组织id为orgid）确诊用户
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
//    List<User> getClassBilluser(Integer orgid);//返回二级组织确诊用户
//    @Select({"select * from user where sick=True AND classA=#{orgid}"})
//    List<User> getClassCilluser(Integer orgid);//返回三级组织确诊用户
//
//    @Select({"select * from user where (index BETWEEN 70 AND 100) AND (classA=#{orgid})"})
//    List<User> getClassAHdangeruser(Integer orgid);//返回一级组织高度易感用户（指数70-100）
//    @Select({"select * from user where (index BETWEEN 70 AND 100) AND (classA=#{orgid})"})
//    List<User> getClassBHdangeruser(Integer orgid);//返回二级组织高度易感用户
//    @Select({"select * from user where (index BETWEEN 70 AND 100) AND (classA=#{orgid})"})
//    List<User> getClassCHdangeruser(Integer orgid);//返回三级组织高度易感用户
//
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassAMdangeruser(Integer orgid);//返回一级组织易感用户（指数21-69）
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassBMdangeruser(Integer orgid);//返回二级组织易感用户
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassCMdangeruser(Integer orgid);//返回三级组织易感用户
//
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassALdangeruser(Integer orgid);//返回一级组织无风险用户（指数0-20）
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassBLdangeruser(Integer orgid);//二级组织无风险用户
//    @Select({"select * from user where (index BETWEEN 21 AND 69) AND (classA=#{orgid})"})
//    List<User> getClassCLdangeruser(Integer orgid);//三级组织无风险用户
//
//    @Select({"select * from user where classA=#{orgid}" })
//    List<User> getClassAAllUser(Integer orgid);//返回一级组织内全部用户
//    @Select({"select * from user where classB=#{orgid} "})
//    List<User> getClassBAllUser(Integer orgid);//返回二级组织内全部用户
//    @Select({"select * from user where classC=#{orgid} "})
//    List<User> getClassCAllUser(Integer orgid);//返回三级组织内全部用户
//
//}
