package com.member.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	private DataSource ds;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
			return;
		}
	}

	public int isId(String id, String pass) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(MemberBean m) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
