package com.wizz.dao;

import com.wizz.entity.Org;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface OrgDao {//组织

    //28号新加的
    //这一条因为mongoDB里有数组格式，sql里没有数组格式，所以没写完整
    //mongoDB里查询数组的方法可参考http://www.imooc.com/article/26885

    List<Org> getAdminUser_Org(String name);//查询哪些组织里有该管理员电话（name）

    //同上

    void addAdmin(String orgid, String tel);//为某组织添加管理员电话

    Org getOrgById(String id);//根据id查找组织


    //前端传过来的参数数量不同，若创建的是一级组织则无classA，二级组织无classAB

    void addOrg1(String project,String name, Integer grade);//添加组织，grade为几级组织

    void addOrg2(String project,String name, Integer grade,String classA);//添加组织，grade为几级组织

    void addOrg3(String project,String name, Integer grade,String classA,String classB);//添加组织，grade为几级组织
    void deleteAdmin (String orgID,String tel);
    List<Org> gerOrgByProjectId (String projectID);
    void deleteOrg (String orgId);
    List<Org> getClassBOrgByParentClass (String orgId);
    List<Org> getClassCOrgByParentClass (String orgId);
}
