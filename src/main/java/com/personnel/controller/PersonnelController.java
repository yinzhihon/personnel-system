package com.personnel.controller;

import com.personnel.pojo.PageBean;
import com.personnel.pojo.Person;
import com.personnel.pojo.Personnel;
import com.personnel.service.PersonnelService;
import com.personnel.utils.SpringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PersonnelController {

    @FXML
    private TableView<Personnel> homeTable;
    public BorderPane personnelMainPane;
    public PersonnelController thisController;

    //分页属性
    public Pagination pagination;
    public TextField currentPage;
    public TextField totalCount;
    public ChoiceBox<Integer> pageSize;

    //表格列属性
    public TableColumn<Person, Integer> tc_id;
    public TableColumn<Person, String> tc_person_name;
    public TableColumn<Person, String> tc_change_name;
    public TableColumn<Person, String> tc_description;

    //表格列属性-按钮
    public TableColumn<Person, String> tc_edit;
    public TableColumn<Person, String> tc_delete;

    //查询框属性
    public TextField personId;
    public TextField name;
    public TextArea description;
    public ChoiceBox<String> changeBox;



    //初始化加载
    @FXML
    public void initialize() {
        //初始化条件查询框
        initSelectCondition();
        //初始化分页功能
        initPagination();
        //更改数据调用刷新用
        thisController = this;
    }

    private void initPagination() {
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
            } catch (NumberFormatException e) {
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

    private void initSelectCondition() {
        changeBox.getItems().add("新员工加入");
        changeBox.getItems().add("职务变动");
        changeBox.getItems().add("辞退");
        changeBox.getItems().add("");

        personId.setText("");
        name.setText("");
        description.setText("");
        changeBox.setValue("");
    }

    @FXML
    public void selectByCondition() {
        System.out.println("---selectByCondition---");
        currentPage.setText("1");
        selectPageCondition();
    }

    @FXML
    public void reset() {
        personId.setText("");
        name.setText("");
        description.setText("");
        changeBox.setValue("");

        homeTable.getItems().clear();
        selectPageCondition();
    }

    public void LoadData(List<Personnel> personnels) {
        //数据转换
        ObservableList<Personnel> list = FXCollections.observableArrayList();
        list.addAll(personnels);

        //反射调用getId() 加载数据
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_person_name.setCellValueFactory(new PropertyValueFactory<>("personName"));
        tc_change_name.setCellValueFactory(new PropertyValueFactory<>("changeName"));
        tc_description.setCellValueFactory(new PropertyValueFactory<>("description"));

        //编辑按钮
        tc_edit.setCellFactory((col) -> new TableCell<Person, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button1 = new Button("编辑");
                button1.setStyle("-fx-background-color:  #338aee;-fx-text-fill: #ffffff");

                button1.setOnMouseClicked((col1) -> {
                    //获取list列表中的位置，进而获取列表对应的信息数据
                    Personnel personnel = list.get(getIndex());
                    System.out.println("--edit " + getIndex() + " : " + personnel);

                    //FXMLLoader必须使用参数初始化，否则getController会失败
                    FXMLLoader personnelLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("updatePersonnel.fxml")));
                    Parent updatePersonnel = null;
                    try {
                        updatePersonnel = personnelLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //这个方法必须在load方法之后调用
                    UpdatePersonnelController updatePersonnelController = personnelLoader.getController();
                    //存入数据
                    updatePersonnelController.editPersonnel = personnel;
                    updatePersonnelController.personnelController = thisController;
                    //刷新
                    updatePersonnelController.refresh();

                    Stage stage = (Stage) personnelMainPane.getScene().getWindow();
                    Stage updateStage = new Stage();
                    Scene scene = new Scene(Objects.requireNonNull(updatePersonnel));

                    updateStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.out.println("setOnCloseRequest");
                        }
                    });


                    updateStage.initModality(Modality.WINDOW_MODAL);
                    updateStage.initStyle(StageStyle.DECORATED);
                    updateStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
                    updateStage.setResizable(false);
                    //指定父窗口
                    updateStage.initOwner(stage);
                    updateStage.setScene(scene);
                    updateStage.setTitle("新增员工");
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
        tc_delete.setCellFactory((col) -> new TableCell<Person, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button2 = new Button("删除");
                button2.setStyle("-fx-background-color:  #e96151;-fx-text-fill: #ffffff");

                //按钮事件
                button2.setOnMouseClicked((col1) -> {
                    //创建对话框
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setTitle("提示");
                    dialog.setContentText("此操作将永久删除记录");

                    dialog.getDialogPane().setPrefSize(400, 150);

                    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

                    dialog.getDialogPane().lookupButton(ButtonType.OK);
                    dialog.getDialogPane().lookupButton(ButtonType.CLOSE);

                    //打开对话框
                    Optional<ButtonType> result = dialog.showAndWait();
                    boolean present = result.isPresent();
                    if (present) {
                        if (result.get() == ButtonType.OK) {
                            //获取list列表中的位置，进而获取列表对应的信息数据
                            Personnel personnel = list.get(getIndex());

                            System.out.println("--delete " + getIndex() + " : " + personnel);
                            //删除数据
                            PersonnelService personnelService = SpringUtil.getBean("personnelService", PersonnelService.class);
                            Integer count = personnelService.deleteById(personnel.getId());

                            if (count < 0) {
                                //删除失败提示框
                                Dialog<ButtonType> failedDialog = new Dialog<>();
                                failedDialog.setTitle("Error");
                                failedDialog.setHeaderText("删除失败!");
                                failedDialog.setContentText("请联系管理员解决，电话：19848151288");
                                ImageView imageView = new ImageView(new Image("com/personnel/images/error_magic.png"));
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);
                                failedDialog.setGraphic(imageView);

                                failedDialog.getDialogPane().setPrefSize(400, 50);

                                failedDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                                failedDialog.getDialogPane().lookupButton(ButtonType.OK);
                                failedDialog.show();
                            } else {
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
        Personnel personnel = new Personnel(id, name.getText(), changeBox.getValue(), description.getText());

        //工具类SpringUtil获取personService,调用业务层方法
        PersonnelService personnelService = SpringUtil.getBean("personnelService", PersonnelService.class);
        PageBean<Personnel> personnelPageBean = personnelService.selectPageCondition(personnel,
                Integer.valueOf(currentPage.getText()), pageSize.getValue());
        //设置页数和总记录数
        pagination.setPageCount((int) Math.ceil(personnelPageBean.getTotalCount() / (double) pageSize.getValue()));
        totalCount.setText(String.valueOf(personnelPageBean.getTotalCount()));
        //获取数据
        List<Personnel> personnels = personnelPageBean.getRows();

        //加载数据到表格
        LoadData(personnels);

    }

    public void refresh() {
        selectPageCondition();
    }


}


