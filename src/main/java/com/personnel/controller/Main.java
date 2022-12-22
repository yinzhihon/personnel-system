package com.personnel.controller;

import com.personnel.utils.DragWindowHandler;
import com.personnel.utils.SpringUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //加载fxml文件
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));

        //加载窗口移动工具类
        DragWindowHandler handler = new DragWindowHandler(primaryStage);
        root.setOnMousePressed(handler);/* 鼠标按下 */
        root.setOnMouseDragged(handler);/* 鼠标拖动 */

        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("com/personnel/images/AdobeCS.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //初始化ApplicationContext
        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //自动实现了springUtil.setApplicationContext(app)
        new SpringUtil();

        launch(args);
    }
}
