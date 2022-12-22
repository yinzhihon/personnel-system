package com.personnel.controller;

import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class UpdatePersonController {

    public TextField passwd;
    public ChoiceBox<String> authorityBox;
    public TextField name;
    public ChoiceBox<String> sexBox;
    public DatePicker birthdayBox;
    public ChoiceBox<String> departmentBox;
    public ChoiceBox<String> jobBox;
    public ChoiceBox<String> eduLevelBox;
    public TextField specialty;
    public TextField address;
    public TextField tel;
    public ChoiceBox<String>  stateBox;
    public TextField email;
    public TextArea remake;

    public Person editPerson;
    public PersonController personController;

    @FXML
    private BorderPane addPersonMainPane;

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

        stateBox.getItems().add("员工");
        stateBox.getItems().add("已辞退");

        sexBox.getItems().add("男");
        sexBox.getItems().add("女");

        authorityBox.getItems().add("管理员");
        authorityBox.getItems().add("员工");

    }

    @FXML
    public void submitUpdate() {
        //获取完整的旧的person
        PersonService personService = SpringUtil.getBean("personService", PersonService.class);
        Person oldPerson = personService.selectById(editPerson.getId());
        //获取新的person
        Person newPerson = new Person(passwd.getText(), authorityBox.getValue(), name.getText(), sexBox.getValue(),
                birthdayBox.getValue(), departmentBox.getValue(), jobBox.getValue(), eduLevelBox.getValue(),
                specialty.getText(),  address.getText(), tel.getText(), email.getText(), stateBox.getValue(),remake.getText());

        newPerson.setId(editPerson.getId());  //将id写入person
        //数据库更新

        Integer count = -1;
        if(!Objects.equals(name.getText(), "") && !Objects.equals(passwd.getText(), "")){
            if(!oldPerson.equals(newPerson)) {
                count = personService.update(newPerson);
            }
        }else {
            count = -2;
        }


        //结果处理
        if(count > 0){
            personController.refresh();
            closeUpdate();
        }else if(count == -1){
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败! ");
            success.setContentText("请更改信息后在尝试");
            success.showAndWait();
        }else if(count == -2){
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败! ");
            success.setContentText("用户名和密码不能为空");
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
    public void closeUpdate() {
        Stage stage = (Stage) addPersonMainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void refresh(){

        departmentBox.setValue(editPerson.getDepartmentName());
        jobBox.setValue(editPerson.getJobName());
        eduLevelBox.setValue(editPerson.getEduLevelName());
        stateBox.setValue(editPerson.getState());
        sexBox.setValue(editPerson.getSex());
        authorityBox.setValue(editPerson.getAuthority());
        stateBox.setValue(editPerson.getState());

        passwd.setText(editPerson.getPasswd());
        name.setText(editPerson.getName());
        birthdayBox.setValue(editPerson.getBirthday());
        specialty.setText(editPerson.getSpecialty());
        address.setText(editPerson.getAddress());
        tel.setText(editPerson.getTel());
        email.setText(editPerson.getEmail());
        remake.setText(editPerson.getRemake());
    }
}
