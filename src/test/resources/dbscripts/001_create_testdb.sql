drop table if exists t_group; 
create table t_group(
group_cd varchar(20) primary key,
group_name varchar(200)
) engine=InnoDB;

drop table if exists t_user;
create table t_user(
empe_num varchar(20) primary key,
empe_name varchar(100),
pwd varchar(8),
mobile varchar(11),
tel_no varchar(50),
email varchar(50),
group_cd varchar(20),
admin_flg int default 0,
dflt_atndnc_sts_id int default 1,
del_flg int
) engine=InnoDB;
alter table t_user change empe_num empe_num varchar(50);
alter table t_user add unique(email);



drop table if exists t_role;
create table t_role(
id int auto_increment primary key,
role_id varchar(20),
role_name varchar(100)
) engine=InnoDB;

drop table if exists t_permission;
create table t_permission(
id int auto_increment primary key,
p_id varchar(20),
p_name varchar(100)
) engine=InnoDB;

drop table if exists t_role_permission;
create table t_role_permission(
id int auto_increment primary key,
role_id varchar(20),
p_id varchar(100)
) engine=InnoDB;

drop table if exists t_holiday;
create table t_holiday(
id int auto_increment primary key,
name varchar(100)
) engine=InnoDB;

drop table if exists t_holiday_list;
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


drop table if exists t_holiday_list_dtl;
create table t_holiday_list_dtl(
id int auto_increment primary key,
hldy_list_id int,
hldy_start date,
hldy_end date
) engine=InnoDB;
alter table t_holiday_list_dtl add foreign key (hldy_list_id) references t_holiday_list(id);

drop table if exists t_attendance_status;
create table t_attendance_status(
id int primary key,
atndnc_name varchar(100),
in_shanghai int default 1
) engine=InnoDB;

drop table if exists t_attendance_info;
create table t_attendance_info(
id int auto_increment primary key,
user_id int,
work_date date, 
atndnc_sts_id int,
hldy_list_id int
) engine=InnoDB;
alter table t_attendance_info add unique (user_id, work_date);

