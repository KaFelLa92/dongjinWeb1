drop database if exists waitingList;
create database waitingList;
use waitingList;

create table wait(
	wno int auto_increment,
    constraint primary key (wno),
    wnumber varchar(20) not null,
    wcount int not null
);

# 샘플
INSERT INTO wait (wnumber, wcount) VALUES ('010-1023-9841', 3);
INSERT INTO wait (wnumber, wcount) VALUES ('010-2934-1234', 2);
INSERT INTO wait (wnumber, wcount) VALUES ('010-8372-5521', 4);
INSERT INTO wait (wnumber, wcount) VALUES ('010-4571-9090', 1);
INSERT INTO wait (wnumber, wcount) VALUES ('010-6573-2211', 5);
INSERT INTO wait (wnumber, wcount) VALUES ('010-7102-8888', 2);
INSERT INTO wait (wnumber, wcount) VALUES ('010-3345-1100', 3);
INSERT INTO wait (wnumber, wcount) VALUES ('010-9981-4412', 1);
INSERT INTO wait (wnumber, wcount) VALUES ('010-1203-7841', 2);
INSERT INTO wait (wnumber, wcount) VALUES ('010-8523-6753', 5);
INSERT INTO wait (wnumber, wcount) VALUES ('010-3388-2266', 3);
INSERT INTO wait (wnumber, wcount) VALUES ('010-5522-4499', 4);
INSERT INTO wait (wnumber, wcount) VALUES ('010-1293-6789', 1);
INSERT INTO wait (wnumber, wcount) VALUES ('010-7771-3333', 2);
INSERT INTO wait (wnumber, wcount) VALUES ('010-2311-9821', 3);
INSERT INTO wait (wnumber, wcount) VALUES ('010-8432-1144', 5);
INSERT INTO wait (wnumber, wcount) VALUES ('010-3901-7612', 2);
INSERT INTO wait (wnumber, wcount) VALUES ('010-6214-3543', 3);
INSERT INTO wait (wnumber, wcount) VALUES ('010-1122-3344', 4);
INSERT INTO wait (wnumber, wcount) VALUES ('010-7812-1290', 1);

select * from wait;