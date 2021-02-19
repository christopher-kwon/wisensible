drop table board;

--테이블 생성
create table board(
board_category varchar2(4) not null,
board_num number(10) primary key,
board_subject varchar2(60) not null ,
board_name varchar2(30) not null, 
board_pass varchar2(30) not null,
board_content varchar2(3000) not null,
board_date date not null,
board_file1 varchar2(30) not null,
board_file2 varchar2(30) not null,
board_file3 varchar2(30) not null,
board_file4 varchar2(30) not null,
board_read number(10),
board_price number(10) not null,
board_bank varchar2(20),
board_account number(20),
board_tel number(20) not null,
board_storage varchar2(50) not null,
board_delivery varchar2(50) not null,
board_product varchar2(30) not null,
board_amount varchar2(30) not null,
board_producer varchar2(30) not null,
board_origin varchar2(30) not null,
board_deliverycost number(10) not null,
board_expirydate varchar2(30) not null,
board_sort varchar2(30) not null,
board_domestic varchar2(10) not null
);

select * from board;

drop sequence board_seq;

--시퀀스 생성
create sequence board_seq; 


)
