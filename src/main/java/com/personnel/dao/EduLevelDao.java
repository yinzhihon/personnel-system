package com.personnel.dao;

import com.personnel.pojo.EduLevel;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface EduLevelDao {

    //查询所有
    @Select("select * from t_edu_level")
    List<EduLevel> selectAll();

    //查询通过code
    @Select("select * from t_edu_level where code = #{code}")
    EduLevel selectById(Integer id);

    //查询通过description
    @Select("select * from t_edu_level where description = #{description}")
    EduLevel selectByDescription(String description);
}
