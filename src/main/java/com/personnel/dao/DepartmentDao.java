package com.personnel.dao;

import com.personnel.pojo.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentDao {
    //查询所有
    List<Department> selectAll();

    //查询通过id
    @Select("select * from t_department where id = #{id}")
    Department selectById(Integer id);

    //查询通过name
    @Select("select * from t_department where name = #{name}")
    Department selectByDescription(String name);

    Integer updateById(Department department);

}
