drop table member;

create table member (
	MEMBER_ID			VARCHAR2(50) primary key,
	MEMBER_NAME			VARCHAR2(50) NOT NULL,
	MEMBER_PASS			VARCHAR2(50) NOT NULL,
	MEMBER_BIRTH		VARCHAR2(50) NOT NULL,
	MEMBER_EMAIL		VARCHAR2(50) NOT NULL,
	MEMBER_EMAILHASH	VARCHAR2(70),
	MEMBER_EMAILCHECKED VARCHAR2(3) ,
	MEMBER_GENDER		VARCHAR2(50) NOT NULL,
	MEMBER_TEL			VARCHAR2(50) NOT NULL,
	MEMBER_ADDRESS		VARCHAR2(300) NOT NULL,
	MEMBER_INTEREST		VARCHAR2(50),
	MEMBER_ACCOUNT		VARCHAR2(50),
	MEMBER_BANK			VARCHAR2(50),
	MEMBER_FILE			VARCHAR2(50)
);
select * from member;
truncate table member;
delete from member;