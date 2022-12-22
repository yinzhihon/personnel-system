package com.personnel.controller;

import com.personnel.service.PersonService;
import com.personnel.utils.SpringUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public TextField setUsernameField;
    public PasswordField setPasswordField;
    public Label setErrorMsg;
    public BorderPane root;
    public Button closeButton;
    public Button submitButton;

    private Stage homeStage;

    @FXML
    private void  initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = fxmlLoader.load();
        homeStage = new Stage();
        homeStage.setTitle("人事管理系统");
        homeStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));

        homeStage.setResizable(false);
        homeStage.setScene(new Scene(root));
        homeStage.initStyle(StageStyle.DECORATED);
    }

    @FXML
    private void closeButtonAction() {
        Stage stage= (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submitButtonAction(){

        Stage stage= (Stage) submitButton.getScene().getWindow();

        PersonService personService = SpringUtil.getBean("personService", PersonService.class);
        int flag = -1;
        if(!Objects.equals(setUsernameField.getText(), "") && !Objects.equals(setPasswordField.getText(), "")){
            flag = personService.checkPassword(setUsernameField.getText(), setPasswordField.getText());
        }

        if(flag == 3){
            login();
            stage.close();
            System.out.println("login!");
        }else if(flag == 2){
            setErrorMsg.setText("您不是管理员!");
        }else if(flag == 1){
            setErrorMsg.setText("您已不是本司员工!");
        }else if(flag == 0){
            setErrorMsg.setText("密码或学工号错误!");
        }else{
            setErrorMsg.setText("输入不能为空！");
        }

    }

    private void login(){
        homeStage.show();
    }

}
