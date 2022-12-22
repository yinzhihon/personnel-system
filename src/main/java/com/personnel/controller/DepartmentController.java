package com.personnel.controller;

import com.personnel.pojo.Department;
import com.personnel.service.DepartmentService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class DepartmentController {

    public Label departmentName1;
    public Label departmentId1;
    public Label departmentMsg1;
    public Label departmentMan1;
    public Label departmentName2;
    public Label departmentId2;
    public Label departmentMsg2;
    public Label departmentMan2;
    public Label departmentName3;
    public Label departmentId3;
    public Label departmentMsg3;
    public Label departmentMan3;
    public Label departmentName4;
    public Label departmentId4;
    public Label departmentMsg4;
    public Label departmentMan4;


    @FXML
    public void refresh(){
        DepartmentService departmentService = SpringUtil.getBean(DepartmentService.class);
        List<Department> departments = departmentService.selectAll();
        Department department1 = departments.get(0);
        departmentName1.setText( department1.getName());
        departmentId1.setText(String.valueOf(department1.getId()));
        departmentMsg1.setText( department1.getIntro() );
        departmentMan1.setText( department1.getManagerName());

        Department department2 = departments.get(1);
        departmentName2.setText( department2.getName());
        departmentId2.setText(String.valueOf(department2.getId()));
        departmentMsg2.setText( department2.getIntro() );
        departmentMan2.setText( department2.getManagerName() );

        Department department3 = departments.get(2);
        departmentName3.setText( department3.getName());
        departmentId3.setText(String.valueOf(department3.getId()));
        departmentMsg3.setText( department3.getIntro() );
        departmentMan3.setText( department3.getManagerName() );

        Department department4 = departments.get(3);
        departmentName4.setText( department4.getName());
        departmentId4.setText(String.valueOf(department4.getId()));
        departmentMsg4.setText( department4.getIntro() );
        departmentMan4.setText( department4.getManagerName() );
    }

}
