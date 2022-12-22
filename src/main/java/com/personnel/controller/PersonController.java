package com.personnel.controller;

import com.personnel.pojo.PageBean;
import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.service.PersonnelService;
import com.personnel.utils.SpringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PersonController implements Initializable {

    @FXML
    private TableView<Person> homeTable;
    @FXML
    private BorderPane personMainPane;
    

    //分页属性
    public Pagination pagination;
    public TextField currentPage;
    public TextField totalCount;
    public ChoiceBox<Integer> pageSize;

    //表格列属性
    public TableColumn<Person, Integer> tc_id;
    public TableColumn<Person, String> tc_name;
    public TableColumn<Person, String> tc_sex;
    public TableColumn<Person, String> tc_department;
    public TableColumn<Person, String> tc_job;
    public TableColumn<Person, String> tc_specialty;
    public TableColumn<Person, String> tc_edu_level;
    public TableColumn<Person, String> tc_remake;
    //表格列属性-按钮
    public TableColumn<Person, String> tc_edit;
    public TableColumn<Person, String> tc_delete;

    //查询框属性
    public TextField personId;
    public TextField name;
    public TextArea remake;
    public ChoiceBox<String> departmentBox;
    public ChoiceBox<String> jobBox;
    public ChoiceBox<String> sexBox;
    public ChoiceBox<String> stateBox;

    //初始化加载
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化条件查询框
        initSelectCondition();
        //初始化分页功能
        initPagination();

    }

    private void initPagination(){
        //初始化每页记录条数选项
        pageSize.getItems().add(1);
        pageSize.getItems().add(5);
        pageSize.getItems().add(10);
        pageSize.getItems().add(15);
        pageSize.getItems().add(20);
        //初始页面大小为5条记录
        pageSize.setValue(5);
        //初始当前页为1
        currentPage.setText("1");
        //初始分页条当前页索引为0
        pagination.setCurrentPageIndex(0);

        //监听分页条选中的当前页
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            currentPage.setText(String.valueOf(1 + pagination.getCurrentPageIndex()));
            //currentPage变化,重新加载数据
            selectPageCondition();
        });

        //监听当前页
        currentPage.textProperty().addListener((observable, oldValue, newValue) -> {

            try {
                if (Integer.parseInt(newValue) > pagination.getPageCount() || Integer.parseInt(newValue) <= 0) {
                    currentPage.setText(oldValue);
                } else {
                    //改变分页条选中的当前页，以重新加载数据
                    pagination.setCurrentPageIndex(Integer.parseInt(currentPage.getText()) - 1);
                }
            }catch (NumberFormatException e) {
                currentPage.setText("");
            }
        });

        //监听一页显示的条数
        pageSize.valueProperty().addListener(observable -> {
            currentPage.setText("1");
            //pageSize变化，重新加载数据
            selectPageCondition();
        });
    }

    private void initSelectCondition(){
        departmentBox.getItems().add("");
        departmentBox.getItems().add("研发部");
        departmentBox.getItems().add("运营部");
        departmentBox.getItems().add("财务部");
        departmentBox.getItems().add("人事部");

        jobBox.getItems().add("");
        jobBox.getItems().add("实习员工");
        jobBox.getItems().add("普通员工");
        jobBox.getItems().add("主管");
        jobBox.getItems().add("经理");
        jobBox.getItems().add("部门总监");
        jobBox.getItems().add("副总经理");
        jobBox.getItems().add("总经理");

        stateBox.getItems().add("");
        stateBox.getItems().add("员工");
        stateBox.getItems().add("已辞退");

        sexBox.getItems().add("");
        sexBox.getItems().add("男");
        sexBox.getItems().add("女");

        personId.setText("");
        name.setText("");
        remake.setText("");
        sexBox.setValue("");
        departmentBox.setValue("");
        jobBox.setValue("");
        stateBox.setValue("");

        personId.textProperty().addListener((observable, oldValue, newValue) -> {
            /*List<String> arr = new ArrayList<>();
            for(int i = 0; i <= 9; i++ ){
                arr.add(String.valueOf(i));
            }
            if(!arr.contains(newValue)){
                personId.setText("");
            }*/
            try {
                Integer.parseInt(newValue);
            }catch(Exception e){
                e.printStackTrace();
                personId.setText("");
            }

        });
    }

    @FXML
    public void selectAll() {
        System.out.println("---selectAll---");
        selectPageCondition();
    }

    @FXML
    public void selectByCondition() {
        System.out.println("---selectByCondition---");
        currentPage.setText("1");
        selectPageCondition();
    }

    @FXML
    public void addPerson() throws IOException {
        Stage stage = (Stage) personMainPane.getScene().getWindow();

        //加载addPerson.fxml文件
        Parent addPerson = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addPerson.fxml")));
        Scene scene = new Scene(addPerson);

        Stage addStage = new Stage();
        //指定父窗口
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initStyle(StageStyle.DECORATED);
        addStage.setResizable(false);
        addStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
        addStage.initOwner(stage);
        addStage.setScene(scene);
        addStage.setTitle("新增员工");
        addStage.show();
    }

    @FXML
    public void reset() {
        personId.setText("");
        name.setText("");
        remake.setText("");
        sexBox.setValue("");
        departmentBox.setValue("");
        jobBox.setValue("");
        stateBox.setValue("");

        homeTable.getItems().clear();
        selectPageCondition();
    }

    @FXML
    public void showStatsStage() throws IOException {

        Stage stage = (Stage) personMainPane.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stats.fxml"));
        Parent root = fxmlLoader.load();
        StatsController statsController = fxmlLoader.getController();
        statsController.refresh();

        Scene scene = new Scene(root);

        Stage addStage = new Stage();
        //指定父窗口
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initStyle(StageStyle.DECORATED);
        addStage.setResizable(false);
        addStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
        addStage.initOwner(stage);
        addStage.setScene(scene);
        addStage.setTitle("统计信息");
        addStage.show();

    }

    @FXML
    public void updateState() {
        //获取选中的列
        Person person = homeTable.getSelectionModel().getSelectedItem();
        //创建对话框
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("提示");
        dialog.getDialogPane().setPrefSize(400,50);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().lookupButton(ButtonType.OK);

        if(person == null){
            dialog.setContentText("请选择员工");
            dialog.show();
        }else{
            dialog.setContentText("此操作将辞退员工");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().lookupButton(ButtonType.CLOSE);

            //打开对话框
            Optional<ButtonType> result = dialog.showAndWait();
            boolean present = result.isPresent();
            if(present){
                if (result.get() == ButtonType.OK){
                    Person updatePerson = new Person();
                    updatePerson.setState("已辞退");
                    updatePerson.setId(person.getId());
                    PersonService personService = SpringUtil.getBean("personService", PersonService.class);

                    Integer count = personService.updateDynamic(updatePerson);

                    if(count > 0){
                        //记录到人事变动表
                        PersonnelService personnelService = SpringUtil.getBean(PersonnelService.class);
                        personnelService.insertPersonnel(person, updatePerson, 2);
                        //刷新
                        selectPageCondition();

                    }else{
                        //失败提示框
                        Dialog<ButtonType> failedDialog = new Dialog<>();
                        failedDialog.setTitle("Error");
                        failedDialog.setHeaderText("操作失败!");
                        failedDialog.setContentText("请联系管理员解决，电话：19848151288");
                        ImageView imageView = new ImageView(new Image("com/personnel/images/error_magic.png"));
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);
                        failedDialog.setGraphic(imageView);

                        failedDialog.getDialogPane().setPrefSize(400,50);

                        failedDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        failedDialog.getDialogPane().lookupButton(ButtonType.OK);
                        failedDialog.show();
                    }
                }
            }
        }

    }

    @FXML
    public void updateJob() {
        //获取选中的列
        Person person = homeTable.getSelectionModel().getSelectedItem();
        if(person == null){
            //创建对话框
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
            dialog.getDialogPane().setPrefSize(400,50);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().lookupButton(ButtonType.OK);
            dialog.setContentText("请选择员工");
            dialog.show();
        }else{
            //FXMLLoader必须使用参数初始化，否则getController会失败
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("jobChange.fxml")));
            Parent updatePerson = null;
            try {
                updatePerson = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //这个方法必须在load方法之后调用
            JobChangeController jobChangeController = loader.getController();
            //存入数据
            PersonService personService = SpringUtil.getBean("personService", PersonService.class);
            jobChangeController.jobChangePerson = personService.selectById(person.getId());
            //刷新
            jobChangeController.refresh();

            Stage stage = (Stage) personMainPane.getScene().getWindow();
            Stage jobChangeStage = new Stage();
            Scene scene = new Scene(Objects.requireNonNull(updatePerson));

            jobChangeStage.initModality(Modality.WINDOW_MODAL);
            jobChangeStage.initStyle(StageStyle.DECORATED);
            jobChangeStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
            jobChangeStage.setResizable(false);
            //指定父窗口
            jobChangeStage.initOwner(stage);
            jobChangeStage.setScene(scene);
            jobChangeStage.setTitle("职务变动");
            jobChangeStage.show();
        }

    }

    public void LoadData(List<Person> peoples) {
        //数据转换
        ObservableList<Person> list = FXCollections.observableArrayList();
        list.addAll(peoples);

        //反射调用getId() 加载数据
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tc_department.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        tc_job.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        tc_specialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        tc_edu_level.setCellValueFactory(new PropertyValueFactory<>("eduLevelName"));
        tc_remake.setCellValueFactory(new PropertyValueFactory<>("remake"));

        PersonController _this = this;  //获取PersonController并改别名
        //编辑按钮
        tc_edit.setCellFactory((col)-> new TableCell<Person, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button  button1 = new Button("编辑");
                button1.setStyle("-fx-background-color:  #338aee;-fx-text-fill: #ffffff");

                button1.setOnMouseClicked((col1) -> {
                    //获取list列表中的位置，进而获取列表对应的信息数据
                    Person person = list.get(getIndex());
                    System.out.println("--edit "+getIndex()+" : "+person);
                    //按钮事件
                    PersonService personService = SpringUtil.getBean("personService", PersonService.class);
                    Person editPerson = personService.selectById(person.getId());

                    //FXMLLoader必须使用参数初始化，否则getController会失败
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("updatePerson.fxml")));
                    Parent updatePerson = null;
                    try {
                        updatePerson = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //这个方法必须在load方法之后调用
                    UpdatePersonController updatePersonController = loader.getController();
                    //存入数据
                    updatePersonController.editPerson = editPerson;
                    updatePersonController.personController = _this;
                    //刷新
                    updatePersonController.refresh();

                    Stage stage = (Stage) personMainPane.getScene().getWindow();
                    Stage updateStage = new Stage();
                    Scene scene = new Scene(Objects.requireNonNull(updatePerson));

                    updateStage.initModality(Modality.WINDOW_MODAL);
                    updateStage.initStyle(StageStyle.DECORATED);
                    updateStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
                    updateStage.setResizable(false);
                    //指定父窗口
                    updateStage.initOwner(stage);
                    updateStage.setScene(scene);
                    updateStage.setTitle("编辑员工信息");
                    updateStage.show();
                });

                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setGraphic(button1);
                }
            }
        });
        //删除按钮
        tc_delete.setCellFactory((col)-> new TableCell<Person, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button  button2 = new Button("删除");
                button2.setStyle("-fx-background-color:  #e96151;-fx-text-fill: #ffffff");

                //按钮事件
                button2.setOnMouseClicked((col1) -> {
                    //创建对话框
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setTitle("提示");
                    dialog.setContentText("此操作将永久删除记录");

                    dialog.getDialogPane().setPrefSize(400,150);

                    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

                    dialog.getDialogPane().lookupButton(ButtonType.OK);
                    dialog.getDialogPane().lookupButton(ButtonType.CLOSE);

                    //打开对话框
                    Optional<ButtonType> result = dialog.showAndWait();
                    boolean present = result.isPresent();
                    if(present){
                        if (result.get() == ButtonType.OK){
                            //获取list列表中的位置，进而获取列表对应的信息数据
                            Person person = list.get(getIndex());

                            System.out.println("--delete "+getIndex()+" : "+person);
                            //删除数据
                            PersonService personService = SpringUtil.getBean("personService", PersonService.class);
                            Integer count = personService.delete(person.getId());

                            if(count < 0){
                                //删除失败提示框
                                Dialog<ButtonType> failedDialog = new Dialog<>();
                                failedDialog.setTitle("Error");
                                failedDialog.setHeaderText("删除失败!");
                                failedDialog.setContentText("请联系管理员解决，电话：19848151288");
                                ImageView imageView = new ImageView(new Image("com/personnel/images/error_magic.png"));
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);
                                failedDialog.setGraphic(imageView);

                                failedDialog.getDialogPane().setPrefSize(400,50);

                                failedDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                                failedDialog.getDialogPane().lookupButton(ButtonType.OK);
                                failedDialog.show();
                            }else{
                                //删除该列并刷新
                                homeTable.getItems().remove(getIndex());
                                homeTable.refresh();
                            }
                        }
                    }

                });
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setGraphic(button2);
                }
            }
        });

        //数据存入table
        homeTable.setItems(list);
    }

    public void selectPageCondition() {
        //获取并处理数据
        Integer id = null;
        System.out.println(personId.getText());
        if (personId.getText() != null && !"".equals(personId.getText())) {
            id = Integer.valueOf(personId.getText());
        }

        //数据存入person
        Person person  = new Person(id, name.getText(), sexBox.getValue(),
                departmentBox.getValue(), jobBox.getValue(), stateBox.getValue(),remake.getText());

        //工具类SpringUtil获取personService,调用业务层方法
        PersonService personService = SpringUtil.getBean("personService", PersonService.class);
        PageBean<Person> personPageBean = personService.selectPageCondition(person,
                Integer.valueOf(currentPage.getText()),  pageSize.getValue());
        //设置页数和总记录数
        pagination.setPageCount((int) Math.ceil(personPageBean.getTotalCount() / (double)pageSize.getValue()));
        totalCount.setText(String.valueOf(personPageBean.getTotalCount()));
        //获取数据
        List<Person> peoples = personPageBean.getRows();

        //加载数据到表格
        LoadData(peoples);

    }

    public void refresh(){
        selectPageCondition();
    }

}

