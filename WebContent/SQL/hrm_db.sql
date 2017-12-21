/*================*/
/*创建表dept_inf*/
/*================*/
create table dept_inf(
	id int(11) not null auto_increment,
	name varchar(50) not null,
	remark varchar(300) default null,
	primary key(id)
)ENGINE=INNODB AUTO_INCREMENT=10;
/*================*/
/*插入数据*/
/*================*/
insert into dept_inf(id,name,remark) values(1,'技术部','技术部'),(2,'运营部','运营部'),
(3,'财务部','财务部'),(4,'总公办','总公办'),(5,'市场部','市场部'),(6,'教学部','教学部');
/*================*/
/* 创建表job_inf */
/*================*/
create table job_inf(
	id int(11) not null auto_increment,
	name varchar(50) not null,
	remark varchar(300) default null,
	primary key(id)
)ENGINE=INNODB AUTO_INCREMENT=10;
/* 插入数据 */
insert into job_inf(id,name,remark) values(1,'职员','职员'),(2,'java开发工程师','java开发工程师'),
(3,'java中级开发工程师','java中级开发工程师'),(4,'java高级开发工程师','java高级开发工程师'),
(5,'系统管理员','系统管理员'),(6,'架构师','架构师'),(7,'主管','主管'),(8,'经理','经理'),
(9,'总经理','总经理');
/*================*/
/* 创建表user_info */
/*================*/
create table user_inf(
	id int(11) not null auto_increment,
	loginname varchar(20) not null,
	password varchar(16) not null,
	status int(11) not null default '1',
	createdate timestamp not null default current_timestamp,
	username varchar(20) default null,
	primary key(id)
)ENGINE=INNODB AUTO_INCREMENT=2;
/*================*/
/* 插入数据 */
/*================*/
insert into user_inf(id,loginname,password,status,createdate,username) values(1,'admin','123456',2,'2017-11-25 10:42:28','超级管理员');
/*================*/
/* 创建表employee_inf */
/*================*/
create table employee_inf(
	id int(11) not null auto_increment,
	dept_id int(11) not null,
	job_id int(11) not null,
	name varchar(20) not null,
	card_id varchar(18) not null,
	address varchar(50) not null,
	post_code varchar(50) not null,
	tel varchar(16) default null,
	phone varchar(11) not null,
	qq_num varchar(10) default null,
	email varchar(50) not null,
	sex int(11) not null default '1',
	party varchar(10) not null,
	birthday DATETIME default null,
	race varchar(100) default null,
	education varchar(10) default null,
	speciality varchar(20) default null,
	hobby varchar(100) default null,
	remark varchar(500) default null,
	create_date timestamp not null default current_timestamp,
	primary key(id),
	key FK_EMP_DEPT (dept_id),
	key FK_EMP_JOB (job_id),
	constraint FK_EMP_DEPT foreign key (dept_id) references dept_inf(id),
	constraint FK_EMP_JOB foreign key (job_id) references job_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=21;
/*================*/
/* 插入数据 */
/*================*/
insert into employee_inf(id,dept_id,job_id,name,card_id,address,post_code,tel,
phone,qq_num,email,sex,party,birthday,race,education,speciality,hobby,remark,create_date)
values(1,1,8,'李展强','16212869','广州天河','514400','39943320','18927753339',
'823287036','/qq.com',1,'党员','1993-12-06 00:00:00','汉','硕士','自动化','唱歌','handsome','2017-11-25 11:06:19');
/*================*/
/* 创建表 notice_inf */
/*================*/
create table notice_inf(
	id int(11) not null auto_increment,
	title varchar(50) not null,
	content TEXT not null,
	create_date TIMESTAMP not null default CURRENT_TIMESTAMP,
	user_id int(11) default null,
	primary key(id),
	KEY FK_NOTICE_USER (user_id),
	constraint FK_NOTICE_USER foreign key(user_id) references user_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=19;
/*================*/
/* 插入数据 */
/*================*/
insert into notice_inf(id,title,content,create_date,user_id) values(1,'first notice','hello everybody','2017-11-27 15:43:19',1);
insert into notice_inf(id,title,content,create_date,user_id) values(2,'会议通知','进入例会照常进行','2017-11-28 15:43:19',1);
/*================*/
/* 创建表document_inf */
/*================*/
create table document_inf(
	id int(11) not null auto_increment,
	title varchar(50) not null,
	filename varchar(300) not null,
	remark varchar(300) default null,
	create_date TIMESTAMP not null default CURRENT_TIMESTAMP,
	user_id int(11) default null,
	primary key(id),
	KEY FK_DOCUMENT_USER (user_id),
	constraint FK_DOCUMENT_USER foreign key(user_id) references user_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=7;
