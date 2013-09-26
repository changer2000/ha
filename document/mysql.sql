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
alter table t_user add dflt_atndnc_sts_id int;
alter table t_user add unique(email);

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
name varchar(100)
) engine=InnoDB;

drop table t_holiday_list;
create table t_holiday_list(
id int auto_increment primary key,
hldy_year int,
hldy_id int,
start_dt date,
end_dt date,
init_flg int default 0
) engine=InnoDB;
alter table t_holiday_list add unique (hldy_year, hldy_id);
alter table t_holiday_list add foreign key (hldy_id) references t_holiday(id);
alter table t_holiday_list drop column hldy_start;
alter table t_holiday_list drop column hldy_end;
--ALTER TABLE t_holiday_list DROP FOREIGN KEY t_holiday_list_ibfk_1;

drop table t_holiday_list_dtl;
create table t_holiday_list_dtl(
id int auto_increment primary key,
hldy_list_id int,
hldy_start date,
hldy_end date
) engine=InnoDB;
alter table t_holiday_list_dtl add foreign key (hldy_list_id) references t_holiday_list(id);

drop table t_attendance_status;
create table t_attendance_status(
id int primary key,
atndnc_name varchar(100),
in_shanghai int default 1
) engine=InnoDB;

drop table t_attendance_info;
create table t_attendance_info(
id int auto_increment primary key,
user_id int,
work_date date, 
atndnc_sts_id int,
hldy_list_id int
) engine=InnoDB;
alter table t_attendance_info add unique (user_id, work_date);


-----------------------------------
insert into t_user(empe_num,empe_name,pwd,mobile,tel_no,email,dept_cd,del_flg) values ('1404','li','1234','13524656789','021-12345678',null,null,0);

insert into  t_attendance_status(atndnc_name) values('休息-在沪');


----------------------------------- 以下为参考的例子
1. 加外键的例子：
CREATE TABLE UTILIZATION (
ID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY 
... 
FK_WORKCATEGORY SMALLINT,
CONSTRAINT PK_UTILIZATION PRIMARY KEY ( ID));

CREATE TABLE DB2ADMIN.WORKCATEGORY(
ID SMALLINT NOT NULL,
DESCRIPTION VARCHAR(50),
CONSTRAINT PK_WORKCATEGORY PRIMARY KEY(ID));

ALTER TABLE UTILIZATION ADD FOREIGN KEY (FK_WORKCATEGORY) REFERENCES WORKCATEGORY(ID);

//主键549830479
alter table tabelname add new_field_id int(5) unsigned default 0 not null auto_increment ,add primary key (new_field_id);

//增加一个新列549830479
alter table t2 add d timestamp;
alter table infos add ex tinyint not null default '0';

//删除列549830479
alter table t2 drop column c;

//重命名列549830479
alter table t1 change a b integer;


//改变列的类型549830479
alter table t1 change b b bigint not null;
alter table infos change list list tinyint not null default '0';

//重命名表549830479
alter table t1 rename t2;

//加索引549830479
mysql> alter table tablename change depno depno int(5) not null;
mysql> alter table tablename add index 索引名 (字段名1[，字段名2 …]);
mysql> alter table tablename add index emp_name (name);

加主关键字的索引549830479
mysql> alter table tablename add primary key(id);

加唯一限制条件的索引549830479
mysql> alter table tablename add unique emp_name2(cardnumber);

删除某个索引549830479
mysql>alter table tablename drop index emp_name;

修改表：549830479

增加字段：549830479
mysql> ALTER TABLE table_name ADD field_name field_type;

修改原字段名称及类型：549830479
mysql> ALTER TABLE table_name CHANGE old_field_name new_field_name field_type;

删除字段：549830479
mysql> ALTER TABLE table_name DROP field_name; 
