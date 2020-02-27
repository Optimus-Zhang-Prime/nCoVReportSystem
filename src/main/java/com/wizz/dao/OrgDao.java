package com.wizz.dao;

import com.wizz.entity.Org;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrgDao {//组织
    String TABLE_NAME = "org";//表名

    @Select({"select * from", TABLE_NAME, "where id=#{id}"})
    Org getOrgById(Integer id);//根据id查找组织


    //前端传过来的参数数量不同，若创建的是一级组织则无classA，二级组织无classAB
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade})"})
    void addOrg(Integer project,String name, Integer grade);//添加组织，grade为几级组织
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade},#{classA})"})
    void addOrg(Integer project,String name, Integer grade,Integer classA);//添加组织，grade为几级组织
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade},#{classA},#{classB})"})
    void addOrg(Integer project,String name, Integer grade,Integer classA,Integer classB);//添加组织，grade为几级组织

    @Update({"update org set admin1=#{tel} where id=#{orgid}"})
    void addAdmin1(int orgid, String tel);//添加管理员1号

    @Update({"update org set admin2=#{tel} where id=#{orgid}"})
    void addAdmin2(int orgid, String tel);

    @Update({"update org set admin3=#{tel} where id=#{orgid}"})
    void addAdmin3(int orgid, String tel);
}
