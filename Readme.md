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
                             id long primary key
                             SerialNumber varchar(12),
                             SessionID varchar(36),
                             SessionStartTime datetime,
                             SessionTerminationTime datetime
) engine=InnoDB;
create index idx_serial_number_sessionid on activity_log (serial_number, sessionid);
create table SelectionLog (
                              id long primary key,
                              SerialNumber varchar(12),
                              SessionID varchar(36),
                              SelectedWatchSeries varchar(20),
                              SelectedWatchSize varchar(10),
                              SelectedWatchCase varchar(20),
                              SelectedWatchBand varchar(30)
) engine=InnoDB;
create index idx_serial_number_sessionid on selection_log (serial_number, sessionid);
create table ErrorLog (
                          id long primary key,
                          SerialNumber varchar(12),
                          SessionID varchar(36),
                          EventTime datetime,
                          ErrorMessage varchar(100)
) engine=InnoDB;
create index idx_serial_number on error_log (serial_number);

create table AppConfigChangeLog (
                                    id long primary key,
                                    SerialNumber varchar(12),
                                    SessionID varchar(36),
                                    ParameterChangeTime datetime,
                                    ParameterToBeChanged varchar(40),
                                    ParameterValueBeforeChange varchar(40),
                                    ParameterValueAfterChange varchar(40)
) engine=InnoDB;
create index idx_serial_number on app_config_change_log (serial_number);
```
所在的database: `applewatch`

## 打包方式
在终端中进入项目所在目录，执行以下代码
```shell
mvn package -DskipTests
```

编译后的产物在target目录下，RESTful-0.0.2-SNAPSHOT.jar

## 启动方式
* 以jar包方式启动
```shell
java -jar RESTful-0.0.2-SNAPSHOT.jar
```
## 部署和运行
将RESTful-0.0.2-SNAPSHOT.jar复制到任何一个有同样版本JRE机器，以jar包方式启动即可。

## 监控
````
https://<hostname>:8099/actuator/health
````

## docker镜像打包方式
代码所在目录下执行一下指令
``docker build -t restful:v1 .``

## Docker启动方式
* 以容器方式启动 ``docker run -d -p 8080:8080 restful:v1``