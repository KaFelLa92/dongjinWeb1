drop database if exists waitingList;
create database waitingList;
use waitingList;

create table wait (
	wno int auto_increment unique,
    constraint primary key (wno),
    wnumber varchar(20) not null,
    wcount int
); 

-- 샘플 데이터
insert into wait (wnumber, wcount) values ("010-1234-5678" , 3);
insert into wait (wnumber, wcount) values ("010-1344-7238" , 1);
insert into wait (wnumber, wcount) values ("010-1234-3452" , 5);
insert into wait (wnumber, wcount) values ("010-1446-0020" , 2);
insert into wait (wnumber, wcount) values ("010-2404-6578" , 3);
insert into wait (wnumber, wcount) values ("010-0234-5613" , 4);
insert into wait (wnumber, wcount) values ("032-354-6580" , 9);
insert into wait (wnumber, wcount) values ("010-5324-5667" , 13);
insert into wait (wnumber, wcount) values ("010-4236-5778" , 20);
insert into wait (wnumber, wcount) values ("02-1253-5470" , 1);

select * from wait;