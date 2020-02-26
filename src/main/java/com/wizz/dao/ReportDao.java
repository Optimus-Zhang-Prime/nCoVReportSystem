package com.wizz.dao;

import com.wizz.entity.User;
import com.wizz.entity.report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReportDao {//汇报信息

    @Select({"select * from report where sick=True"})
    List<report> getIllReport(Integer orgid);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{name},#{password})"})
    void addUser(User user);
}
