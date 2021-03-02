package com.hope.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HopeDAO {

	private DataSource ds;

	public HopeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
			return;
		}
	}

	public int insert(HopeBean hope) {
		 Connection conn = null;
		  PreparedStatement pstmt = null;
		  PreparedStatement pstmt2 = null;
		  PreparedStatement pstmt3 = null;
		  ResultSet rs = null;
		  int result = 0;
		  int result2 = 0;

	      String max_sql = "(select nvl(max(hope_num),0)+1 from hope)";
	      String sql = "insert into hope " 
	            + "    values ("
	            + max_sql +", ?, ?, ?, sysdate)";
	      String pricemax_sql = "select max(hope_price) from hope where board_num= ? ";
	      String auction_maxsql= "update auction set board_hopemax_price = ? where board_num=?";
	     
	      
	      try {

	   
	         conn = ds.getConnection();

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, hope.getBoard_num());
	         pstmt.setString(2, hope.getHope_id());
	         pstmt.setInt(3,hope.getHope_price());
	         result = pstmt.executeUpdate();
	        
	         pstmt2 = conn.prepareStatement(pricemax_sql);
	         pstmt2.setInt(1,  hope.getBoard_num());
	         rs= pstmt2.executeQuery();
	         if(rs.next()) {
	        	 result2 =rs.getInt(1);
	         }
	         

	         pstmt3 = conn.prepareStatement(auction_maxsql);
	         pstmt3.setInt(1, result2);
	         pstmt3.setInt(2, hope.getBoard_num());
	         pstmt3.executeUpdate();
	         
	         
	      
	   
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

	public int getMax(int board_num) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     int x =0;
	      String max_sql = "select max(hope_price) from hope where board_num= ? ";
	      try {

	         conn = ds.getConnection();

	         pstmt = conn.prepareStatement(max_sql);
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

	
}
