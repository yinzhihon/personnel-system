package com.personnel.service;

import com.personnel.pojo.PageBean;
import com.personnel.pojo.Person;
import com.personnel.pojo.Personnel;

public interface PersonnelService {

    Integer insertPersonnel(Person oldPerson,Person newPerson, Integer changeCode);

    Integer deleteById(Integer id);

    Personnel selectById(Integer id);

    PageBean<Personnel> selectPageCondition(Personnel personnel, Integer currentPage, Integer pageSize);

    Integer update(Personnel personnel);
}
