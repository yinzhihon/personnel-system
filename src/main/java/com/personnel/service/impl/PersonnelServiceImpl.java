package com.personnel.service.impl;

import com.personnel.dao.DepartmentDao;
import com.personnel.dao.PersonnelDao;
import com.personnel.pojo.*;
import com.personnel.service.PersonnelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("personnelService")
public class PersonnelServiceImpl implements PersonnelService {

    @Resource
    private PersonnelDao personnelDao;

    @Resource
    private DepartmentDao departmentDao;

    public Integer insertPersonnel(Person oldPerson, Person newPerson, Integer changeCode) {

        Personnel personnel = new Personnel();
        personnel.setPersonId(newPerson.getId());
        personnel.setChangeCode(changeCode);

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(date);

        if(changeCode == 1){
            if(Objects.equals(newPerson.getJobName(), "部门总监")){
                //设置部门经理
                Department department = departmentDao.selectByDescription(newPerson.getDepartmentName());
                department.setManagerId(newPerson.getId());
                departmentDao.updateById(department);
            }
            personnel.setDescription("原职务: "+oldPerson.getDepartmentName()+" "+oldPerson.getJobName()+"--"
                    +"现职务: "+newPerson.getDepartmentName()+" "+newPerson.getJobName() +"--操作时间:"+currentDate);
        }else if(changeCode == 2){
            personnel.setDescription("员工id: "+newPerson.getId()+"--操作时间:"+currentDate);
        }else if(changeCode == 0){
            personnel.setDescription("员工id: "+newPerson.getId()+"--操作时间:"+currentDate);
        }


        Integer count = personnelDao.add(personnel);
        if(count > 0){
            System.out.println("---PersonnelService-insertPersonnel-成功!---\n"+personnel);
        }else{
            System.out.println("---PersonnelService-insertPersonnel-失败!---\n"+personnel);
        }

        return count;
    }

    public Integer deleteById(Integer id) {
        Integer count = personnelDao.deleteById(id);
        if(count > 0){
            System.out.println("--PersonnelService--删除成功："+id);
        }else{
            System.out.println("--PersonnelService--删除失败："+id);
        }
        return count;
    }

    @Override
    public Personnel selectById(Integer id) {
        return personnelDao.selectById(id);
    }

    @Override
    public PageBean<Personnel> selectPageCondition(Personnel personnel, Integer currentPage, Integer pageSize) {
        System.out.println("---PersonService--selectPageCondition---");
        System.out.println(personnel);
        System.out.println("---pageSize:"+pageSize+"  "+"currentPage:"+currentPage + "---");

        List<Personnel> personnels = personnelDao.selectPageCondition(personnel,(currentPage-1)*pageSize, pageSize);//执行方法
        Integer totalCount = personnelDao.selectTotalCountCondition(personnel);
        System.out.println(personnels);
        System.out.println(totalCount);

        PageBean<Personnel> pageBean = new PageBean<>();
        pageBean.setRows(personnels);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    public Integer update(Personnel personnel) {
        //获取更改后的changeCode
        PersonnelChange personnelChange = personnelDao.selectByChangeDes(personnel.getChangeName());
        personnel.setChangeCode(personnelChange.getCode());

        //更改记录
        Integer count = personnelDao.update(personnel);

        if(count > 0){
            System.out.println("--PersonnelService--修改成功："+personnel);
        }else{
            System.out.println("--PersonnelService--修改失败："+personnel);
        }

        return count;
    }


}
