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
			System.out.println("DB연결 실패 : " + e);
			return;
		}
	}

	public int insert(EvaluationBean evaluationbean) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     PreparedStatement pstmt2 = null;
	     PreparedStatement pstmt3 = null;
	     ResultSet rs = null;
	      int result = 0;
	      int result2 = 0;


	      String max_sql = "(select nvl(max(evaluation_id),0)+1 from evaluation)";
	      String sql = "insert into evaluation " 
	            + "    values ("
	            + max_sql +", ?, ?, ?, sysdate)";
	      String avg = "select avg(evaluation) from evaluation where board_num= ? ";
	      String board_avgsql= "update board set board_evaluation = ? where board_num=?";
	     
	      
	      try {

	   
	         conn = ds.getConnection();

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, evaluationbean.getBoard_num());
	         pstmt.setString(2, evaluationbean.getEvalueation_name());
	         pstmt.setInt(3,evaluationbean.getEvaluation());
	         result = pstmt.executeUpdate();
	        
	         pstmt2 = conn.prepareStatement(avg);
	         pstmt2.setInt(1,  evaluationbean.getBoard_num());
	         rs= pstmt2.executeQuery();
	         if(rs.next()) {
	        	 result2 =rs.getInt(1);
	         }
	         

	         pstmt3 = conn.prepareStatement(board_avgsql);
	         pstmt3.setInt(1, result2);
	         pstmt3.setInt(2, evaluationbean.getBoard_num());
	         pstmt3.executeUpdate();
	         
	         
	      
	    
	         // primarykey를 위반했을 경우 발생하는 에러
	   
	      } catch (Exception se) {
	         System.out.println("insert()에서 :" + se);
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
	               conn.close();// 4단계 Db연결끊기
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
	         System.out.println("insert()에서 :" + se);
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
	               conn.close();// 4단계 Db연결끊기
	         } catch (Exception e) {
	            System.out.println(e.getMessage());

	         }
	      }
		return x;
	      

	   }

	public int idCheck(EvaluationBean evaluationbean) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	    
	     int result = 0;
	     
	      String idcheck_sql = "select evaluation_name from evaluation where board_num = ? AND evaluation_name = ? ";
	      try {

	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(idcheck_sql);
	         pstmt.setString(2, evaluationbean.getEvalueation_name() );
	         pstmt.setInt(1,  evaluationbean.getBoard_num());
	         
	         rs= pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 result = 0;//DB에 해당 id가 있습니다.
	         }else {
	        	 result = 1;
	         }
	         
	
	      } catch (Exception se) {
	         System.out.println("insert()에서 :" + se);
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
	               conn.close();// 4단계 Db연결끊기
	         } catch (Exception e) {
	            System.out.println(e.getMessage());

	         }
	      }
		return result;
	      

	   }
	
	
	
}
