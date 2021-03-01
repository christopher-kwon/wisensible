drop table hope;

--테이블 생성
create table hope(
hope_num number(5) primary key,
board_num number(10) references auction(board_num) on delete cascade,
hope_id varchar2(30) not null,
hope_price number(20) not null,
hope_date date
);
select nvl(max(hope_num),0)+1 from hope



truncate table info;
insert into info values(
1, 1, 'master', '테스트중입니다', SYSDATE, 0, 0, 1
);
--시퀀스 생성
drop sequence info_seq;

select * from hope;
create sequence info_seq; 

select * from (select b.*, rownum rnum from(select infoent_num, infoent_id, infoent_content, infoent_date, 
				infoent_re_lev, infoent_re_seq, infoent_re_ref, member_file from info join member 
				on infoent_id=member_id where infoent_board_ref = 3 order by infoent_re_ref asc, 
				infoent_re_seq asc)b);