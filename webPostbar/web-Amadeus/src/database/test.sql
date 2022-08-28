create table lab(id varchar(20) primary key,user varchar(20) not null,
pwd varchar(20) not null, gender int,salary int)engine=innoDB charset=utf8;
insert into lab values ('001','okarin','001',1,5000);
insert into lab values ('002','mayoshi','002',0,2000);
insert into lab values ('003','daru','003',1,4000);
insert into lab values ('004','kurisu','004',0,7000);
insert into lab values ('005','moe','005',0,3000);
insert into lab values ('006','ryoka','006',1,2000);
insert into lab values ('007','ferisu','007',0,4000);
insert into lab values ('008','sutsuha','008',0,3000);
insert into lab values ('009','maho','009',0,5000);
insert into lab values ('010','kagari','010',0,2000);
insert into lab values ('011','amadeus kurisu','011',0,0000);
insert into lab values ('012','amadeus maho','012',0,0000);
insert into lab values ('013','何意 maho','012',0,0000);
show variables like '%char%' 
alter database 1x character set utf8;

drop table emp
delete from lab where id='013';
insert into emp values ('001','okarin','男',5000);
set names gbk;
create table emp(id varchar(20) primary key,user varchar(20) not null,
gender varchar(4),salary int)engine=innoDB charset=utf8;
insert into emp values ('001','okarin','男',5000);
insert into emp values ('002','mayoshi','女',2000);
insert into emp values ('003','daru','男',4000);
insert into emp values ('004','kurisu','女',7000);
insert into emp values ('005','moe','女',3000);
insert into emp values ('006','ryoka','男',2000);
delete from emp where id='009'
select * from emp