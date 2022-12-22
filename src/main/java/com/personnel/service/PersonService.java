package com.personnel.service;

import com.personnel.pojo.PageBean;
import com.personnel.pojo.Person;

import java.util.List;

public interface PersonService {
    List<Person> selectAll();

    Person selectById(Integer id);

    Integer checkPassword(String name, String passwd);

    List<Person> selectByCondition(Person person);

    PageBean<Person> selectPageCondition(Person person, Integer currentPage, Integer pageSize);

    Integer add(Person person);

    Integer delete(Integer id);

    Integer update(Person person);

    Integer updateDynamic(Person person);

    Integer selectCount(Person person);
}
