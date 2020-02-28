package com.wizz.dao;

import com.wizz.entity.Org;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrgDao {//组织

    //28号新加的
    //这一条因为mongoDB里有数组格式，sql里没有数组格式，所以没写完整
    //mongoDB里查询数组的方法可参考http://www.imooc.com/article/26885
    @Select({"select * from org where admin数组里有该name"})
    List<Org> getAdminUser(String name);//查询哪些组织里有该管理员电话（name）

    //同上
    @Update({"update org 把admin表加上tel where id=#{orgid}"})
    void addAdmin(int orgid, String tel);//添加管理员电话



    @Select({"select * from", TABLE_NAME, "where id=#{id}"})
    Org getOrgById(Integer id);//根据id查找组织


    //前端传过来的参数数量不同，若创建的是一级组织则无classA，二级组织无classAB
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade})"})
    void addOrg(Integer project,String name, Integer grade);//添加组织，grade为几级组织
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade},#{classA})"})
    void addOrg(Integer project,String name, Integer grade,Integer classA);//添加组织，grade为几级组织
    @Insert({"insert into ", TABLE_NAME, "(", "project,name,grade", ") values (#{project},#{name},#{grade},#{classA},#{classB})"})
    void addOrg(Integer project,String name, Integer grade,Integer classA,Integer classB);//添加组织，grade为几级组织
}
