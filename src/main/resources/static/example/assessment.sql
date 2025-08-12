drop database if exists assessment;
create database assessment;
use assessment;

create table member_tbl_02 (
	custno int auto_increment not null unique,
    constraint primary key (custno),
    custname varchar(20),
    phone varchar(13),
    address varchar(60),
    joindate date default (current_date),
    grade char(1),
	city char(2)
);

create table money_tbl_02 (
	custno int not null,
    constraint foreign key (custno) references member_tbl_02 (custno),
	salenol int auto_increment not null,
    constraint primary key (salenol),
    pcost int ,
    amount int,
    price int,
    pcode varchar(4),
    sdate date default (current_date)
);

insert into member_tbl_02 values 
(100001 , "김행복" , "010-1111-2222" , "서울 동대문구 휘경1동" , "2015-12-02" , "A" , "01") ,
(100002 , "이축복" , "010-1111-3333" , "서울 동대문구 휘경2동" , "2015-12-06" , "B" , "01") ,
(100003 , "장믿음" , "010-1111-4444" , "울릉군 울릉읍 독도1리" , "2015-10-01" , "B" , "30") ,
(100004 , "최사랑" , "010-1111-5555" , "울릉군 울릉읍 독도2리" , "2015-11-13" , "A" , "30") ,
(100005 , "진평화" , "010-1111-6666" , "제주도 제주시 외나무골" , "2015-12-25" , "B" , "60") ,
(100006 , "자공단" , "010-1111-7777" , "제주도 제주시 감나무골" , "2015-12-11" , "C" , "60") 
;

insert into money_tbl_02 values
(100001 , 20160001 , 500 , 5 , 2500, "A001" , "2026-01-01"),
(100001 , 20160002 , 1000 , 5 , 4000, "A002" , "2026-01-01"),
(100001 , 20160003 , 500 , 5 , 1500, "A008" , "2026-01-01"),
(100002 , 20160005 , 500 , 5 , 500, "A001" , "2026-01-03"),
(100003 , 20160006 , 1500 , 5 , 3000, "A003" , "2026-01-03"),
(100004 , 20160007 , 500 , 5 , 1000, "A001" , "2026-01-04"),
(100004 , 20160008 , 300 , 5 , 300, "A005" , "2026-01-04"),
(100004 , 20160009 , 600 , 5 , 600, "A006" , "2026-01-04"),
(100004 , 20160010 , 3000 , 5 , 3000, "A007" , "2026-01-06")
;
select * from member_tbl_02;
select * from money_tbl_02;

-- 조회할 것 : 회원번호, 회원성명, 고객등급, 회원번호와 일치하는 매출 sum(price).
-- 조건문 : 매출이 0 이하이면 출력하지말 것, 매출이 높은 순서대로 조회할 것(desc)
select custno , custname , grade 
from member_tbl_02 price 
from member_tbl_02 join member_tbl_02 on money_tbl_02.custno = money_tbl_02.custno;