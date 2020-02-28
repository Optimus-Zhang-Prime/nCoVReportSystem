package com.wizz.dao;

import com.wizz.entity.Org;


public interface OrgDao {//组织
    String TABLE_NAME = "org";//表名

//    @Select({"select * from", TABLE_NAME, "where id=#{id}"})
    Org getOrgById(Integer id);//根据id查找组织

//    @Insert({"insert into ", TABLE_NAME, "(", "name,grade", ") values (#{name},#{grade})"})
    void addOrg(String name, int grade);//添加组织，grade为几级组织

//    @Update({"update org set admin1=#{tel} where id=#{orgid}"})
    void addAdmin1(int orgid, String tel);//添加管理员1号

//    @Update({"update org set admin2=#{tel} where id=#{orgid}"})
    void addAdmin2(int orgid, String tel);

//    @Update({"update org set admin3=#{tel} where id=#{orgid}"})
    void addAdmin3(int orgid, String tel);
}
