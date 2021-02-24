package com.evaluation.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			System.out.println("DB���� ���� : " + e);
			return;
		}
	}

	public int insert(EvaluationBean evaluationbean) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	      int result = 0;
	      String max_sql = "(select nvl(max(evaluation_id),0)+1 from evaluation)";
	      String sql = "insert into evaluation " 
	            + "    values ("
	            + max_sql +", ?, ?, ?, sysdate )";
	      try {

	         // context.xml�� ���ҽ��� �����س��� (JNDI�� �����س���) jdbc/OracleDB��
	         // �����Ͽ� Connection ��ü�� ���ɴϴ�.
	      
	         conn = ds.getConnection();
	   

	         

	         // PreparedStatemint
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, evaluationbean.getBoard_num());
	         pstmt.setString(2, evaluationbean.getEvalueation_name());
	         pstmt.setInt(3,evaluationbean.getEvaluation());
	      
	         
	      
	         result = pstmt.executeUpdate();

	         // primarykey�� �������� ��� �߻��ϴ� ����
	   
	      } catch (Exception se) {
	         System.out.println("insert()���� :" + se);
	         se.printStackTrace();
	      } finally {

	         try {
	            if (pstmt != null)
	               pstmt.close();
	         } catch (SQLException e) {
	            System.out.println(e.getMessage());

	         }
	         try {
	            if (conn != null)
	               conn.close();// 4�ܰ� Db�������
	         } catch (Exception e) {
	            System.out.println(e.getMessage());

	         }
	      }
	      return result;

	   }

	public int getAvg(int board_num) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     int x =0;
	      String avg_sql = "select avg(evaluation) from evaluation where board_num= ? ";
	      try {

	         conn = ds.getConnection();
	   

	         

	  
	         pstmt = conn.prepareStatement(avg_sql);
	         pstmt.setInt(1, board_num);
	         rs= pstmt.executeQuery();
	         if(rs.next()) {
	        	 x =rs.getInt(1);
	         }
	      
	         

	     
	   
	      } catch (Exception se) {
	         System.out.println("insert()���� :" + se);
	         se.printStackTrace();
	      } finally {

	         try {
	            if (pstmt != null)
	               pstmt.close();
	         } catch (SQLException e) {
	            System.out.println(e.getMessage());

	         }
	         try {
	            if (conn != null)
	               conn.close();// 4�ܰ� Db�������
	         } catch (Exception e) {
	            System.out.println(e.getMessage());

	         }
	      }
		return x;
	      

	   }
}
