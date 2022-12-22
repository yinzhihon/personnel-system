# 数据库系统课程设计personnel-system

#### 介绍

JavaFX+Spring+Mybatis人事管理系统

JDK 1.8

Maven 3.8.5

Mysql 5.5.36

JavaFX 8

#### 软件架构

软件架构说明

JavaFX+Spring+Mybatis

#### 使用说明

1. 在目录src/main/resources下新建jdbc.properties文件

2. 修改文件内容如下,改成你的数据库账号和密码

   > jdbc.driver=com.mysql.jdbc.Driver
   >
   > jdbc.url=jdbc:mysql://localhost:3306/person_db
   >
   > jdbc.username=${your username}
   >
   > jdbc.password=${your password}

3. 在数据库软件中执行目录src/main/resources下的sql脚本person.sql