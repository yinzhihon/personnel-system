package com.personnel.dao;

import com.personnel.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonDao {

    //查询所有
    List<Person> selectAll();

    //查询byId
    Person selectById(Integer id);

    //条件查询
    List<Person> selectByCondition(Person person);

    //条件统计记录数
    Integer selectCountCondition(Person person);

    //模糊条件分页查询
    List<Person> selectPageCondition(@Param("per") Person person, @Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectTotalCountCondition(Person person);

    Integer add(Person person);

    //通过id删除
    @Delete("delete from t_person where id = #{id}")
    Integer deleteById(Integer id);

    //修改
    Integer update(Person person);

    //动态修改
    Integer updateDynamic(Person person);

}
