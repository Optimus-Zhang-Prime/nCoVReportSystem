package com.wizz.dao;

import com.wizz.entity.Org;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
*
* org表的DAO
* 注意所有带*的注释的参数都要满足以下的条件
* classA 一级组织应是本科生、研究生
* classB 二级组织应是1102这样的院系代码
* classC 三级组织应是19、18、17这样的
 */
public interface OrgDao {//组织
    //mongoDB里查询数组的方法可参考http://www.imooc.com/article/26885

    List<Org> getAdminUser_Org(String name);//根据管理员的手机获得管理员管理的全部组织

    void addAdmin(String orgid, String tel);//为某组织添加管理员电话

    Org getorgByid(String id);//根据组织id查找组织

    void addOrg1(String project,String name, Integer grade);//添加一级组织

    void addOrg2(String project,String name, Integer grade,String classA);//添加二级组织

    void addOrg3(String project,String name, Integer grade,String classA,String classB);//添加三级组织

    void deleteAdmin (String orgID,String tel); // 根据组织id删除组织管理员

    List<Org> gerOrgByProjectId (String projectID); // 根据项目的id获取所有一级组织

    void deleteOrg (String orgId); // 根据组织id删除组织

    List<Org> getClassBOrgByParentClass (String orgId); // 根据一级组织的id查询其所属的二级组织

    List<Org> getClassCOrgByParentClass (String orgId); // 根据二级组织id查询所属的三级组织

    List<Org> getclassCOrg (); // 获取所有的三级组织

    Integer getUserAccount (String classA,String classB,String classC); // 获取三级组织的成员数量*

    Integer getUserAccount (String classA,String classB); // 获取二级组织的成员数量*

    Integer getUserAccount (String classA); // 获取一级的成员数量*

}
