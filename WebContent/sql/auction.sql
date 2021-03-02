drop table auction;

create table auction(
board_category varchar2(20) not null,
board_num number(10) primary key,
board_subject varchar2(60) not null ,
board_name varchar2(30) not null, 
board_pass varchar2(30) not null,
board_content varchar2(3000) not null,
board_date date not null,
board_end_date date not null,
board_file1 varchar2(30) not null,
board_file2 varchar2(30) not null ,
board_file3 varchar2(30) ,
board_file4 varchar2(30) ,
board_thumbnail varchar2(30) not null ,
board_read number(10),
board_max_price number(10),
board_min_price number(10),
board_bank varchar2(20),
board_account varchar2(50),
board_tel varchar2(30) not null,
board_storage varchar2(50) not null,
board_delivery varchar2(50) not null,
board_product varchar2(30) not null,
board_amount varchar2(30) not null,
board_producer varchar2(30) not null,
board_origin varchar2(30) not null,
board_deliverycost number(10) not null,
board_expirydate varchar2(30) not null,
board_hopemax_price number(10)
);


select * from auction;

drop sequence auction_seq;


create sequence auction_seq; 

truncate table board;

)
alter table auction modify(board_tel varchar2(30));
update board set board_evaluation=0 where board_num=1;
select 