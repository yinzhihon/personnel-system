package com.personnel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.io.IOException;
import java.util.Objects;

public class HomeController{

    //选项卡
    public TabPane tabPane;
    private Tab personTab;
    private Tab startTab;
    private Tab statsTab;
    private Tab departmentTab;
    private Tab personnelTab;

    //用于获取控制器中的方法
    private StatsController statsController;
    private PersonController personController;
    private DepartmentController departmentController;
    private PersonnelController personnelController;

    @FXML
    public void initialize() throws IOException {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);  //关闭按钮

        //加载start.fxml文件
        Parent start = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
        startTab = new Tab("起始页面");
        startTab.setClosable(true);
        startTab.setContent(start);
        tabPane.getTabs().add(startTab);

        //加载person.fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("person.fxml"));
        Parent root = loader.load();
        personController = loader.getController();
        personTab = new Tab("员工档案");
        personTab.setClosable(true);
        personTab.setContent(root);

        //加载stats.fxml文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stats.fxml"));
        Parent stats = fxmlLoader.load();
        statsController = fxmlLoader.getController();
        statsTab = new Tab("统计信息");
        statsTab.setClosable(true);
        statsTab.setContent(stats);

        //加载department.fxml文件
        FXMLLoader departmentLoader = new FXMLLoader(getClass().getResource("department.fxml"));
        Parent department = departmentLoader.load();
        departmentController = departmentLoader.getController();
        departmentTab = new Tab("部门信息");
        departmentTab.setClosable(true);
        departmentTab.setContent(department);

        //加载personnel.fxml文件
        FXMLLoader personnelLoader = new FXMLLoader(getClass().getResource("personnel.fxml"));
        Parent personnel = personnelLoader.load();
        personnelController = personnelLoader.getController();
        personnelTab = new Tab("变更记录");
        personnelTab.setClosable(true);
        personnelTab.setContent(personnel);

    }

    //展示员工档案
    public void showPersonStage() {
        boolean flag = true;
        for(int i = 0; i < tabPane.getTabs().size(); i++){
            if(Objects.equals(tabPane.getTabs().get(i).getText(), "员工档案")){
                flag = false;
            }
        }
        if(flag){
            tabPane.getTabs().add(personTab);
        }
        personController.refresh();
        tabPane.getSelectionModel().select(personTab);
    }

    //展示开始界面
    public void showStartStage() {
        boolean flag = true;
        for(int i = 0; i < tabPane.getTabs().size(); i++){
            if(Objects.equals(tabPane.getTabs().get(i).getText(), "起始页面")){
                flag = false;
            }
        }
        if(flag){
            tabPane.getTabs().add(startTab);
        }
        statsController.refresh();
        tabPane.getSelectionModel().select(startTab);
    }

    //展示统计信息
    public void showStatsStage() {
        boolean flag = true;
        for(int i = 0; i < tabPane.getTabs().size(); i++){
            if(Objects.equals(tabPane.getTabs().get(i).getText(), "统计信息")){
                flag = false;
            }
        }
        if(flag){
            tabPane.getTabs().add(statsTab);
        }
        tabPane.getSelectionModel().select(statsTab);
        statsController.refresh();
    }

    //展示部门信息
    public void showDepartmentStage(){
        boolean flag = true;
        for(int i = 0; i < tabPane.getTabs().size(); i++){
            if(Objects.equals(tabPane.getTabs().get(i).getText(), "部门信息")){
                flag = false;
            }
        }
        if(flag){
            tabPane.getTabs().add(departmentTab);
            tabPane.getSelectionModel().select(departmentTab);
            departmentController.refresh();
        }else{
            tabPane.getSelectionModel().select(departmentTab);
        }
    }

    //新增员工选项
    public void addPerson() {
        showPersonStage();
    }

    //人事变更选项
    public void showPersonChangeStage() {
        showPersonStage();
    }

    //展示变更记录
    public void showPersonnelStage() {
        boolean flag = true;
        for(int i = 0; i < tabPane.getTabs().size(); i++){
            if(Objects.equals(tabPane.getTabs().get(i).getText(), "变更记录")){
                flag = false;
            }
        }
        if(flag){
            tabPane.getTabs().add(personnelTab);
        }
        personnelController.refresh();
        tabPane.getSelectionModel().select(personnelTab);
    }
}
