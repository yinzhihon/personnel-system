package com.personnel.dao;

import com.personnel.pojo.Personnel;
import com.personnel.pojo.PersonnelChange;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonnelDao {

    //查询所有
    @Select("select * from t_personnel")
    List<Personnel> selectAll();

    //查询通过id
    @Select("select * from t_personnel where id = #{id}")
    Personnel selectById(Integer id);

    //insert
    @Insert("insert into t_personnel values(null, #{personId}, #{changeCode}, #{description})")
    Integer add(Personnel personnel);

    @Delete("delete from t_personnel where id = #{id}")
    Integer deleteById(Integer id);

    //模糊条件分页查询
    List<Personnel> selectPageCondition(@Param("per") Personnel personnel, @Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectTotalCountCondition(Personnel personnel);

    Integer update(Personnel personnel);

    @Select("select * from t_personnel_change where description = #{description}")
    PersonnelChange selectByChangeDes(String description);

}
