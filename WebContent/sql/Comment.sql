drop table comm;

--테이블 생성
create table comm(
comment_num number(5) primary key,
comment_board_ref number(10) references board(board_num) on delete cascade,
comment_id varchar2(30) not null,
comment_content varchar2(400) not null,
comment_date date,
comment_re_ref number(5),
comment_re_lev number(1) check(comment_re_lev in(0,1,2)),
comment_re_seq number(5)
);

insert into comm values(
1, 1, 'master', '테스트중입니다', SYSDATE, 0, 0, 1
);
--시퀀스 생성
drop sequence comm_seq;

select * from comm;
create sequence comm_seq; 