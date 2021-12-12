# 编译和部署流程

## 环境准备
* jdk8或者jdk11 (https://java.apple.com/applejdk.html)
* maven包管理工具（https://maven.apache.org/）
* mysql 5.7 or 8

## Mysql表结构
目前是一个非常简单的表，是通过JPA自动创建的，不需要事先创建，结构如下：
```sql
drop table if exists StoreToSNMapping;
drop table if exists ActivityLog;
drop table if exists SelectionLog;
drop table if exists ErrorLog;
drop table if exists AppConfigChangeLog;

-- drop table if exists activity_log;
-- drop table if exists selection_log;
-- drop table if exists error_log;
-- drop table if exists app_config_change_log;

create table StoreToSNMapping (
                                  SerialNumber varchar(12),
                                  StoreID int,
                                  StoreCityName varchar(30),
                                  StoreProvinceName varchar(20),
                                  ResellerName varchar(100),
                                  primary key (SerialNumber)
) engine=InnoDB;


create table ActivityLog (
                             SerialNumber varchar(12) primary key,
                             SessionID varchar(36),
                             SessionStartTime datetime,
                             SessionTerminationTime datetime
) engine=InnoDB;

create table SelectionLog (
                              SerialNumber varchar(12),
                              SessionID varchar(36),
                              SelectedWatchSeries varchar(20),
                              SelectedWatchSize varchar(10),
                              SelectedWatchCase varchar(20),
                              SelectedWatchBand varchar(30),
                              primary key (SerialNumber, SessionID)
) engine=InnoDB;

create table ErrorLog (
                          SerialNumber varchar(12),
                          SessionID varchar(36),
                          EventTime datetime,
                          ErrorMessage varchar(100),
                          primary key (SerialNumber, SessionID, EventTime)
) engine=InnoDB;

create table AppConfigChangeLog (
                                    SerialNumber varchar(12),
                                    SessionID varchar(36),
                                    ParameterChangeTime datetime,
                                    ParameterToBeChanged varchar(20),
                                    ParameterValueBeforeChange varchar(20),
                                    ParameterValueAfterChange varchar(20),
                                    primary key (SerialNumber, SessionID, ParameterChangeTime)
) engine=InnoDB;
```
所在的database: `applewatch`

## 打包方式
在终端中进入项目所在目录，执行以下代码
```shell
mvn package -DskipTests
```

编译后的产物在target目录下，RESTful-0.0.1-SNAPSHOT.jar

## 启动方式
* 以jar包方式启动
```shell
java -jar RESTful-0.0.1-SNAPSHOT.jar
```
## 部署和运行
将RESTful-0.0.1-SNAPSHOT.jar复制到任何一个有同样版本JRE机器，以jar包方式启动即可。

## 监控
````
https://<hostname>:8099/actuator/health
````