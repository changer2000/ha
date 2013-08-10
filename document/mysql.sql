-----------  节假日出勤登记系统
drop database if exists ha;
create database ha default character set utf8;

use ha;

drop table t_user;
create table t_user(
id int auto_increment primary key,
empe_num varchar(20),
empe_name varchar(100),
pwd varchar(8),
mobile varchar(11),
tel_no varchar(50),
email varchar(50),
dept_cd varchar(20),
del_flg int
) engine=InnoDB;
alter table t_user change empe_num empe_num varchar(50);

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
hldy_year int,
hldy_name varchar(100),
hldy_start date,
hldy_end date,
start_dt date,
end_dt date,
init_flg int default 0
) engine=InnoDB;

drop table t_attendance_status;
create table t_attendance_status(
id int auto_increment primary key,
atndnc_name varchar(100)
) engine=InnoDB;

drop table t_attendance_info;
create table t_attendance_info(
id int auto_increment primary key,
user_id int,
work_date date, 
atndnc_id int,
hldy_id int
) engine=InnoDB;


-----------------------------------
insert into t_user(empe_num,empe_name,pwd,mobile,tel_no,email,dept_cd,del_flg) values ('1404','li','1234','13524656789','021-12345678',null,null,0);

insert into  t_attendance_status(atndnc_name) values('休息-在沪');
