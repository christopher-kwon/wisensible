drop table board;

--테이블 생성
create table board(
board_category varchar2(20) not null,
board_num number(10) primary key,
board_subject varchar2(60) not null ,
board_name varchar2(30) not null, 
board_pass varchar2(30) not null,
board_content varchar2(3000) not null,
board_date date not null,
board_file1 varchar2(30) ,
board_file2 varchar2(30) ,
board_file3 varchar2(30) ,
board_file4 varchar2(30) ,
board_thumbnail varchar2(30) ,
board_read number(10),
board_price number(10) not null,
board_bank varchar2(20),
board_account number(20),
board_tel varchar2(20) not null,
board_storage varchar2(50) not null,
board_delivery varchar2(50) not null,
board_product varchar2(30) not null,
board_amount varchar2(30) not null,
board_producer varchar2(30) not null,
board_origin varchar2(30) not null,
board_deliverycost number(10) not null,
board_expirydate varchar2(30) not null
);

insert into board values(
'야채', 1, '야채테스트', 'master', '1234', '야채는 맛있어', SYSDATE, 'a.png', 'b.png', 'c.png', 
'd.png', 'e.png', 0, 1000, '신한', 123456789, '010-2390-7250', '실온', '직배송',
'견과류', '1박스', '(주)업드림코리아', '강원도', 3000, '1주일이내'

);

select * from board;

drop sequence board_seq;


--시퀀스 생성
create sequence board_seq; 



)


