package com.board.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	private DataSource ds;

	// �����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB���� ���� : " + e);
			return;
		}
	}

}
