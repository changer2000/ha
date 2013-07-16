-----------  节假日出勤登记系统
drop database if exists hldy_atdns;
create database hldy_atdns default character set utf8;

use hldy_atdns;

drop table t_user;
create table t_user(
id int auto_increment primary key,
user_id varchar(20),
user_name varchar(100),
pwd varchar(8),
mobile varchar(11),
tel_no varchar(20),
email varchar(50),
dept_cd varchar(20),
del_flg int
) engine=InnoDB;

drop table t_role;
create table t_role(
id int auto_increment primary key,
role_id varchar(20),
role_name varchar(100)
) engine=InnoDB;

drop table t_permission;
create table t_permission(
id int auto_increment primary key,
p_id varchar(20),
p_name varchar(100)
) engine=InnoDB;

drop table t_role_permission;
create table t_role_permission(
id int auto_increment primary key,
role_id varchar(20),
p_id varchar(100)
) engine=InnoDB;

drop table t_holiday;
create table t_holiday(
id int auto_increment primary key,
holiday_name varchar(100),
year varchar(4),
start_dt date,
end_dt date,
holiday_start date,
holiday_end date
) engine=InnoDB;

drop table t_attendance_status;
create table t_attendance_status(
id int auto_increment primary key,
attendance_cd varchar(20),
attendance_name varchar(100)
) engine=InnoDB;

drop table t_attendance_info;
create table t_attendance_info(
id int auto_increment primary key,
user_id varchar(20),
hldy_date date, 
attendance_cd varchar(20)
) engine=InnoDB;