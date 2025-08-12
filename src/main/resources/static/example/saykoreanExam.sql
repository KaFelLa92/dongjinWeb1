drop database if exists saykorean;
create database saykorean;
use saykorean;

create table user(
	uno int auto_increment unique,
    constraint primary key (uno) ,
	uid varchar(15)
);
