package com.personnel.service.impl;

import com.personnel.dao.DepartmentDao;
import com.personnel.dao.EduLevelDao;
import com.personnel.dao.JobDao;
import com.personnel.dao.PersonDao;
import com.personnel.pojo.*;
import com.personnel.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private JobDao jobDao;

    @Resource
    private EduLevelDao eduLevelDao;

    public List<Person> selectAll() {

        return personDao.selectAll();
    }

    @Override
    public Integer checkPassword(String name, String passwd) {
        Person person = new Person();
        person.setName(name);
        person.setPasswd(passwd);

        List<Person> result = personDao.selectByCondition(person);

        Person checkPerson;
        try{
            checkPerson = result.get(0);
        }catch(Exception e){
            return 0;
        }

        if(!checkPerson.getState().equals("员工")){
            return 1;
        }else if(!checkPerson.getAuthority().equals("管理员")){
            return 2;
        }else{
            return 3;
        }
    }

    public List<Person> selectByCondition(Person person) {

        return personDao.selectByCondition(person);
    }

    public PageBean<Person> selectPageCondition(Person person, Integer currentPage, Integer pageSize){

        System.out.println("---PersonService--selectPageCondition---");
        System.out.println(person);
        System.out.println("---pageSize:"+pageSize+"  "+"currentPage:"+currentPage + "---");

        List<Person> persons = personDao.selectPageCondition(person,(currentPage-1)*pageSize, pageSize);//执行方法
        Integer totalCount = personDao.selectTotalCountCondition(person);
        System.out.println(persons);
        System.out.println(totalCount);

        PageBean<Person> pageBean = new PageBean<>();
        pageBean.setRows(persons);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    public Integer add(Person person) {

        Department department = departmentDao.selectByDescription(person.getDepartmentName());
        Job job = jobDao.selectByDescription(person.getJobName());
        EduLevel eduLevel = eduLevelDao.selectByDescription(person.getEduLevelName());

        person.setDepartmentId(department.getId());
        person.setJobId(job.getCode());
        person.setEduLevelId(eduLevel.getCode());

        Integer count = personDao.add(person);

        if(count > 0){
            System.out.println("--PersonService--添加成功："+person);
        }else{
            System.out.println("--PersonService--添加失败："+person);
        }

        return count;
    }

    public Integer delete(Integer id) {

        Integer count = personDao.deleteById(id);

        if(count > 0){
            System.out.println("--PersonService--删除成功："+id);
        }else{
            System.out.println("--PersonService--删除失败："+id);
        }

        return count;
    }

    public Person selectById(Integer id) {

        return personDao.selectById(id);
    }

    public Integer update(Person person) {

        Department department = departmentDao.selectByDescription(person.getDepartmentName());
        Job job = jobDao.selectByDescription(person.getJobName());
        EduLevel eduLevel = eduLevelDao.selectByDescription(person.getEduLevelName());

        person.setDepartmentId(department.getId());
        person.setJobId(job.getCode());
        person.setEduLevelId(eduLevel.getCode());

        Integer count = personDao.update(person);

        if(count > 0){
            System.out.println("--PersonService--update成功："+person);
        }else{
            System.out.println("--PersonService--update失败："+person);
        }

        return count;
    }

    public Integer updateDynamic(Person person) {

        Integer count = personDao.updateDynamic(person);

        if(count > 0){
            System.out.println("--PersonService--updateDynamic成功："+person);
        }else{
            System.out.println("--PersonService--updateDynamic失败："+person);
        }

        return count;
    }

    public Integer selectCount(Person person) {
        return personDao.selectCountCondition(person);
    }


}
