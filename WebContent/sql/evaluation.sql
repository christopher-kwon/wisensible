drop table EVALUATION;

CREATE TABLE EVALUATION(
EVALUATION_ID	NUMBER(10) PRIMARY KEY,
BOARD_NUM 		NUMBER(18)  references board(board_num) on delete cascade,
EVALUATION_NAME	VARCHAR2(30) NOT NULL,
EVALUATION		NUMBER(10) NOT NULL,
REPLY_DATE		DATE NOT NULL
);

truncate table evaluation;
SELECT * from evaluation;

insert into EVALUATION values(1,1,'admin',1,sysdate);
insert into EVALUATION values(2,1,'admin',1,sysdate);
insert into EVALUATION values(3,1,'admin',1,sysdate);
insert into EVALUATION values(4,1,'admin',5,sysdate);
insert into EVALUATION values(5,1,'admin',4,sysdate);
insert into EVALUATION values(6,2,'admin',4,sysdate);
insert into EVALUATION values(7,3,'admin',4,sysdate);


select avg(EVALUATION) from EVALUATION where board_num=1;
