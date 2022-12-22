package com.personnel.controller;

import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.service.PersonnelService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Objects;

public class JobChangeController {

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
    public Person jobChangePerson;

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

       /* System.out.println(jobChangePerson);  jobChangePerson为空*/
    }

    @FXML
    public void submitChange() {
        //获取新的person
        Person person = new Person(passwd.getText(), authorityBox.getValue(), name.getText(), sexBox.getValue(),
                birthdayBox.getValue(), departmentBox.getValue(), jobBox.getValue(), eduLevelBox.getValue(),
                specialty.getText(),  address.getText(), tel.getText(), email.getText(), stateBox.getValue(),remake.getText());

        person.setId(jobChangePerson.getId());  //将id写入person
        //数据库更新
        Integer count = -1;
        if(!Objects.equals(jobChangePerson.getDepartmentName(), person.getDepartmentName())
                || !Objects.equals(jobChangePerson.getJobName(), person.getJobName())){
            PersonService personService = SpringUtil.getBean("personService", PersonService.class);
            count = personService.update(person);
        }

        //结果处理
        if(count > 0){
            PersonnelService personnelService = SpringUtil.getBean(PersonnelService.class);
            personnelService.insertPersonnel(jobChangePerson, person, 1);
            closeChange();
        }else if(count == -1){
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败! ");
            success.setContentText("请更改信息后在尝试");
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
    public void closeChange() {
        Stage stage = (Stage) addPersonMainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void refresh() {

        departmentBox.setValue(jobChangePerson.getDepartmentName());
        jobBox.setValue(jobChangePerson.getJobName());
        eduLevelBox.setValue(jobChangePerson.getEduLevelName());
        stateBox.setValue(jobChangePerson.getState());
        sexBox.setValue(jobChangePerson.getSex());
        authorityBox.setValue(jobChangePerson.getAuthority());
        stateBox.setValue(jobChangePerson.getState());

        passwd.setText(jobChangePerson.getPasswd());
        name.setText(jobChangePerson.getName());
        birthdayBox.setValue(jobChangePerson.getBirthday());
        specialty.setText(jobChangePerson.getSpecialty());
        address.setText(jobChangePerson.getAddress());
        tel.setText(jobChangePerson.getTel());
        email.setText(jobChangePerson.getEmail());
        remake.setText(jobChangePerson.getRemake());
    }

}
