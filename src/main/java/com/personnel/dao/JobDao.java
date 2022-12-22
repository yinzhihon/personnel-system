package com.personnel.dao;

import com.personnel.pojo.Job;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobDao {

    //查询所有
    @Select("select * from t_job")
    List<Job> selectAll();

    //查询通过code
    @Select("select * from t_job where code = #{code}")
    Job selectById(Integer id);

    //查询通过description
    @Select("select * from t_job where description = #{description}")
    Job selectByDescription(String description);

}
