package com.evaluation.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class EvaluationDAO {

	private DataSource ds;

	public EvaluationDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
			return;
		}
	}
	
}
