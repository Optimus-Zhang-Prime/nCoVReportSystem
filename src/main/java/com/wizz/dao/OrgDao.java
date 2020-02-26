package com.wizz.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrgDao {//组织
    String TABLE_NAME = "org";//表名
    String SELECT_FIELDS = "id,name,password";//可查询的字段

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where name=#{name}"})
    User getUserByName(String name);

    @Insert({"insert into ", TABLE_NAME, "(", "name,grade", ") values (#{name},#{grade})"})
    void addOrg(String name, int grade);//添加组织
    @Update({"update org set admin1=#{tel} where id=#{orgid}"})
    void addAdmin1(int orgid,String tel);
    @Update({"update org set admin2=#{tel} where id=#{orgid}"})
    void addAdmin2(int orgid,String tel);
    @Update({"update org set admin3=#{tel} where id=#{orgid}"})
    void addAdmin3(int orgid,String tel);
    //表名 set 列名=值 列名2=值 where 条件
}
