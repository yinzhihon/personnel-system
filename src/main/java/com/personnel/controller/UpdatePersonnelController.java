package com.personnel.controller;

import com.personnel.pojo.Personnel;
import com.personnel.service.PersonnelService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UpdatePersonnelController{
    public BorderPane editPersonnelMainPane;

    public TextField id;
    public TextField personId;
    public TextField personName;
    public ChoiceBox<String> changeBox;
    public TextArea description;


    public Personnel editPersonnel;
    public PersonnelController personnelController;


    @FXML
    public void initialize() {
        changeBox.getItems().add("新员工加入");
        changeBox.getItems().add("职务变动");
        changeBox.getItems().add("辞退");
    }

    @FXML
    public void submitUpdate() {
        //获取旧的personnel
        Personnel oldPersonnel = editPersonnel;
        //获取新的personnel
        Personnel newPersonnel = new Personnel(Integer.parseInt(id.getText()),Integer.parseInt(personId.getText()),
                                                personName.getText(), editPersonnel.getChangeCode(),changeBox.getValue(),description.getText());

        Integer count = -1;
        if (!oldPersonnel.equals(newPersonnel)) {
            PersonnelService personnelService = SpringUtil.getBean("personnelService", PersonnelService.class);
            count = personnelService.update(newPersonnel);
        }

        System.out.println(!oldPersonnel.equals(newPersonnel));
        System.out.println("oldPersonnel: "+oldPersonnel);
        System.out.println("newPersonnel: "+newPersonnel);

        //结果处理
        if (count > 0) {
            personnelController.refresh();
            closeUpdate();
        } else if (count == -1) {
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败! ");
            success.setContentText("请更改信息后在尝试");
            success.showAndWait();
        } else {
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("提示");
            success.setHeaderText("提交信息失败!");
            success.setContentText("请联系管理员，电话：19848151288");
            success.showAndWait();
        }
    }

    @FXML
    public void closeUpdate() {
        Stage stage = (Stage) editPersonnelMainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void refresh() {

        id.setText(String.valueOf(editPersonnel.getId()));
        personId.setText(String.valueOf(editPersonnel.getPersonId()));
        personName.setText(editPersonnel.getPersonName());
        changeBox.setValue(editPersonnel.getChangeName());
        description.setText(editPersonnel.getDescription());
        System.out.println(personnelController);
    }

}

