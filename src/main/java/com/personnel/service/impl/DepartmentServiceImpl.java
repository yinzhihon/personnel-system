package com.personnel.service.impl;

import com.personnel.dao.DepartmentDao;
import com.personnel.pojo.Department;
import com.personnel.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public List<Department> selectAll() {
        List<Department> departments = departmentDao.selectAll();
        System.out.println("---DepartmentService-selectAll---:"+departments);
        return departments;
    }
}
