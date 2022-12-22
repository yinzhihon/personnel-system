package com.personnel.controller;

import com.personnel.pojo.Person;
import com.personnel.service.PersonService;
import com.personnel.utils.SpringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;

public class StatsController {

    @FXML
    public Pane areaChartPane;
    @FXML
    public Pane lineChartPane;
    @FXML
    public Pane barChartPane;
    @FXML
    public PieChart pieChart;

    public PieChart pieChart2;
    public BarChart<String,Number> barChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    public LineChart<String,Number> lineChart;
    public NumberAxis yAxis1;
    public CategoryAxis xAxis1;


    @FXML
    public void initialize(){
        pieChart2 = new PieChart();
        setLineChartData();
    }

    public void refresh(){
        //区域图
        /*setAreaChartData();*/
        setPieChartData2();
        //饼状图
        setPieChartData();
        //柱状图
        setBarChartData();
        //折线图
        setLineChartData();
        lineChart.getData().remove(0);
    }

    private void setAreaChartData() {
        //Configuring Xaxis and Yaxis
        NumberAxis xaxis = new NumberAxis(1,12,1);
        NumberAxis yaxis = new NumberAxis(10,510,50);
        xaxis.setLabel("Month of the year");
        yaxis.setLabel("Sales value (lacs)");

        //Creating AreaChart
        AreaChart<Number,Number> area = new AreaChart<>(xaxis,yaxis);

        //Configuring series for North sales
        Series<Number, Number> seriesNorth = new Series<>();
        seriesNorth.setName("North");
        seriesNorth.getData().add(new XYChart.Data<>(1,120));
        seriesNorth.getData().add(new XYChart.Data<>(2,140));
        seriesNorth.getData().add(new XYChart.Data<>(3,50));
        seriesNorth.getData().add(new XYChart.Data<>(4,80));
        seriesNorth.getData().add(new XYChart.Data<>(5,150));
        seriesNorth.getData().add(new XYChart.Data<>(6,130));
        seriesNorth.getData().add(new XYChart.Data<>(7,110));
        seriesNorth.getData().add(new XYChart.Data<>(8,90));
        seriesNorth.getData().add(new XYChart.Data<>(9,200));
        seriesNorth.getData().add(new XYChart.Data<>(10,120));
        seriesNorth.getData().add(new XYChart.Data<>(11,100));
        seriesNorth.getData().add(new XYChart.Data<>(12,120));

        //Configuring series for East sales
        Series<Number, Number> seriesEast = new Series<>();
        seriesEast.setName("East");
        seriesEast.getData().add(new XYChart.Data<>(1,500));
        seriesEast.getData().add(new XYChart.Data<>(2,200));
        seriesEast.getData().add(new XYChart.Data<>(3,300));
        seriesEast.getData().add(new XYChart.Data<>(4,290));
        seriesEast.getData().add(new XYChart.Data<>(5,150));
        seriesEast.getData().add(new XYChart.Data<>(6,120));
        seriesEast.getData().add(new XYChart.Data<>(7,190));
        seriesEast.getData().add(new XYChart.Data<>(8,80));
        seriesEast.getData().add(new XYChart.Data<>(9,100));
        seriesEast.getData().add(new XYChart.Data<>(10,220));
        seriesEast.getData().add(new XYChart.Data<>(11,300));
        seriesEast.getData().add(new XYChart.Data<>(12,220));

        //Configuring series for west sales
        XYChart.Series<Number, Number> seriesWest = new Series<>();
        seriesWest.setName("West");
        seriesWest.getData().add(new XYChart.Data<>(1,320));
        seriesWest.getData().add(new XYChart.Data<>(2,40));
        seriesWest.getData().add(new XYChart.Data<>(3,250));
        seriesWest.getData().add(new XYChart.Data<>(4,10));
        seriesWest.getData().add(new XYChart.Data<>(5,190));
        seriesWest.getData().add(new XYChart.Data<>(6,140));
        seriesWest.getData().add(new XYChart.Data<>(7,200));
        seriesWest.getData().add(new XYChart.Data<>(8,190));
        seriesWest.getData().add(new XYChart.Data<>(9,300));
        seriesWest.getData().add(new XYChart.Data<>(10,130));
        seriesWest.getData().add(new XYChart.Data<>(11,400));
        seriesWest.getData().add(new XYChart.Data<>(12,220));

        //Configuring series for south sales
        XYChart.Series<Number, Number> seriesSouth = new Series<>();
        seriesSouth.setName("South");
        seriesSouth.getData().add(new XYChart.Data<>(1,220));
        seriesSouth.getData().add(new XYChart.Data<>(2,40));
        seriesSouth.getData().add(new XYChart.Data<>(3,250));
        seriesSouth.getData().add(new XYChart.Data<>(4,180));
        seriesSouth.getData().add(new XYChart.Data<>(5,450));
        seriesSouth.getData().add(new XYChart.Data<>(6,230));
        seriesSouth.getData().add(new XYChart.Data<>(7,110));
        seriesSouth.getData().add(new XYChart.Data<>(8,100));
        seriesSouth.getData().add(new XYChart.Data<>(9,123));
        seriesSouth.getData().add(new XYChart.Data<>(10,125));
        seriesSouth.getData().add(new XYChart.Data<>(11,225));
        seriesSouth.getData().add(new XYChart.Data<>(12,127));

        //adding series to the Area chart
        area.getData().add(seriesNorth);
        area.getData().add(seriesWest);
        area.getData().add(seriesEast);
        area.getData().add(seriesSouth);
        area.setTitle("RegionWise Sales");

        area.setLayoutX(11);
        area.setLayoutY(9);
        area.setPrefWidth(737);
        area.setPrefHeight(385);

        areaChartPane.getChildren().add(area);

    }

    private void setPieChartData2() {

        PersonService personService = SpringUtil.getBean(PersonService.class);
        Person person = new Person();
        Double totalCount = Double.valueOf(personService.selectCount(person));
        person.setJobName("实习员工"); //当实习员工为零时,由于是连表查询,查出总数为totalCount,因此使t_department中的名称查询
        Double _0Count = Double.valueOf(personService.selectCount(person));
        person.setJobName("");
        person.setJobId(1);
        Double _1Count = Double.valueOf(personService.selectCount(person));
        person.setJobId(2);
        Double _2Count = Double.valueOf(personService.selectCount(person));
        person.setJobId(3);
        Double _3Count = Double.valueOf(personService.selectCount(person));
        person.setJobId(4);
        Double _4Count = Double.valueOf(personService.selectCount(person));
        person.setJobId(5);
        Double _5Count = Double.valueOf(personService.selectCount(person));
        person.setJobId(6);
        Double _6Count = Double.valueOf(personService.selectCount(person));

        System.out.println("job数量:"+_0Count+" "+_1Count+" "+_2Count+" "+_3Count+" "+_4Count+" "+_5Count+" "+_6Count+" "+totalCount);

        double _0Value = (_0Count/totalCount) * 100;
        double _1Value = (_1Count/totalCount) * 100;
        double _2Value = (_2Count/totalCount) * 100;
        double _3Value = (_3Count/totalCount) * 100;
        double _4Value = (_4Count/totalCount) * 100;
        double _5Value = (_5Count/totalCount) * 100;
        double _6Value = (_6Count/totalCount) * 100;

        System.out.println("job占比:"+_0Value+" "+_1Value+" "+_2Value+" "+_3Value+" "+_4Value+" "+_5Value+" "+_6Value);

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.addAll(new PieChart.Data("实习员工", _0Value),
                    new PieChart.Data("普通员工", _1Value),
                    new PieChart.Data("主管", _2Value),
                    new PieChart.Data("经理", _3Value),
                    new PieChart.Data("部门总监", _4Value),
                    new PieChart.Data("副总经理", _5Value),
                    new PieChart.Data("总经理", _6Value)
        );

        pieChart2.setLayoutX(11);
        pieChart2.setLayoutY(9);
        pieChart2.setPrefWidth(737);
        pieChart2.setPrefHeight(385);

        pieChart2.setData(list);
        areaChartPane.getChildren().clear();
        areaChartPane.getChildren().add(pieChart2);

    }

    private void setPieChartData() {

        PersonService personService = SpringUtil.getBean(PersonService.class);
        Person person = new Person();
        Double totalCount = Double.valueOf(personService.selectCount(person));
        person.setSex("男");
        Double manCount = Double.valueOf(personService.selectCount(person));
        person.setSex("女");
        Double womanCount = Double.valueOf(personService.selectCount(person));
        double othersCount = totalCount - manCount - womanCount;

        double manValue = (manCount/totalCount) * 100;
        double womanValue = (womanCount/totalCount) * 100;
        double othersValue = 0.0;
        if(othersCount != 0){
            othersValue = othersCount/totalCount * 100;
        }

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.addAll(new PieChart.Data("男", manValue),
                    new PieChart.Data("女", womanValue),
                    new PieChart.Data("其他", othersValue)
        );

        pieChart.setData(list);
    }

    private void setBarChartData() {
        String dept1 = "研发部";
        String dept2 = "运营部";
        String dept3 = "财务部";
        String dept4 = "人事部";


        //Configuring Series for XY chart
        XYChart.Series<String,Number> series1 = new XYChart.Series<>();
        series1.setName("研发部");
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series2.setName("运营部");
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series3.setName("财务部");
        XYChart.Series<String,Number> series4 = new XYChart.Series<>();
        series4.setName("人事部");
        series1.getData().add(new XYChart.Data<>(dept1, 6));
        series2.getData().add(new XYChart.Data<>(dept2,4));
        series3.getData().add(new XYChart.Data<>(dept3,5));
        series4.getData().add(new XYChart.Data<>(dept4,3));

        //Adding series to the barchart
        barChart.getData().clear();
        ObservableList<XYChart.Series<String,Number>> list = FXCollections.observableArrayList();
        list.add(series1);
        list.add(series2);
        list.add(series3);
        list.add(series4);

        barChart.getData().addAll(list);
    }

    private void setLineChartData() {
        //creating the series
        XYChart.Series<String,Number> series = new XYChart.Series<>();

        //setting name and the date to the series
        series.setName("Number Analysis");
        series.getData().add(new XYChart.Data<>("2009",25));
        series.getData().add(new XYChart.Data<>("2010",15));
        series.getData().add(new XYChart.Data<>("2011",68));
        series.getData().add(new XYChart.Data<>("2012",60));
        series.getData().add(new XYChart.Data<>("2013",35));
        series.getData().add(new XYChart.Data<>("2014",55));
        series.getData().add(new XYChart.Data<>("2015",45));
        series.getData().add(new XYChart.Data<>("2016",67));
        series.getData().add(new XYChart.Data<>("2017",78));

        //adding series to the lineChart
        lineChart.getData().add(series);
    }

}
