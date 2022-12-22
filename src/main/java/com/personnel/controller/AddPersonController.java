package com.personnel.controller;

import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.service.PersonnelService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

public class AddPersonController {

    public TextField passwd;
    public ChoiceBox<String> authorityBox;
    public TextField name;
    public ChoiceBox<String> sexBox;
    public DatePicker birthday;
    public ChoiceBox<String> departmentBox;
    public ChoiceBox<String> jobBox;
    public ChoiceBox<String> eduLevelBox;
    public TextField specialty;
    public TextField address;
    public TextField tel;
    public String  state = "T";
    public TextField email;
    public TextArea remake;
    public AnchorPane addPersonMainPane;

    @FXML
    public void initialize() {
        departmentBox.getItems().add("研发部");
        departmentBox.getItems().add("运营部");
        departmentBox.getItems().add("财务部");
        departmentBox.getItems().add("人事部");

        jobBox.getItems().add("实习员工");
        jobBox.getItems().add("普通员工");
        jobBox.getItems().add("主管");
        jobBox.getItems().add("经理");
        jobBox.getItems().add("部门总监");
        jobBox.getItems().add("副总经理");
        jobBox.getItems().add("总经理");

        eduLevelBox.getItems().add("小学");
        eduLevelBox.getItems().add("初中");
        eduLevelBox.getItems().add("高中");
        eduLevelBox.getItems().add("职高");
        eduLevelBox.getItems().add("大专");
        eduLevelBox.getItems().add("本科");
        eduLevelBox.getItems().add("硕士");
        eduLevelBox.getItems().add("博士");
        eduLevelBox.getItems().add("博士后");


        sexBox.getItems().add("男");
        sexBox.getItems().add("女");

        authorityBox.getItems().add("管理员");
        authorityBox.getItems().add("员工");

        departmentBox.setValue("研发部");
        jobBox.setValue("普通员工");
        eduLevelBox.setValue("本科");
        authorityBox.setValue("员工");
        sexBox.setValue("男");
        //员工初始密码
        passwd.setText("123456");
        birthday.setValue(LocalDate.of(1998,6,18));
        address.setText("无");
        tel.setText("无");
        specialty.setText("软件开发");
        email.setText("@163.com");

    }

    @FXML
    public void submitAdd() {
        Person person = new Person(passwd.getText(), authorityBox.getValue(), name.getText(), sexBox.getValue(),
                birthday.getValue(), departmentBox.getValue(), jobBox.getValue(), eduLevelBox.getValue(),
                specialty.getText(), tel.getText(), address.getText(), email.getText(), state,remake.getText());

        Integer count = -1;

        if(!Objects.equals(passwd.getText(), "") && !Objects.equals(authorityBox.getValue(), "") &&
           !Objects.equals(name.getText(), "") && !Objects.equals(sexBox.getValue(), "") &&
           !Objects.equals(birthday.getValue().toString(), "") && !Objects.equals(departmentBox.getValue(), "") &&
           !Objects.equals(jobBox.getValue(), "") && !Objects.equals(eduLevelBox.getValue(), "") &&
           !Objects.equals(specialty.getText(), "") && !Objects.equals(tel.getText(), "") &&
           !Objects.equals(address.getText(), "") && !Objects.equals(email.getText(), "") &&
           !Objects.equals(remake.getText(), "")) {

            PersonService personService = SpringUtil.getBean("personService", PersonService.class);
            count = personService.add(person);
            int id = person.getId();
            System.out.println(id);
        }

        //结果处理
        if(count > 0){
            //记录到人事变动表
            PersonnelService personnelService = SpringUtil.getBean(PersonnelService.class);
            personnelService.insertPersonnel(person, person,0);
            closeAdd();
        }else if(count == -1){
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败! ");
            success.setContentText("请将信息完善后在尝试");
            success.showAndWait();
        } else{
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败!");
            success.setContentText("请联系管理员，电话：19848151288");
            success.showAndWait();
        }


    }

    @FXML
    public void closeAdd() {
        Stage stage = (Stage) addPersonMainPane.getScene().getWindow();
        stage.close();
    }
}
