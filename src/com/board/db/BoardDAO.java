package com.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패: " + e);
			return;
		}
	}

	public void setReadCountUpdate(int board_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board set board_read = board_read + 1 where board_num = ?";

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setReadCountUpdate() 에러 : " + ex);
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ce) {
					ce.printStackTrace();
				}
		}
	}

	public BoardBean getDetail(int board_num) {
		
		BoardBean boardBean = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where board_num = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoard_category(rs.getString(1));
				boardBean.setBoard_num(rs.getInt(2));
				boardBean.setBoard_subject(rs.getString(3));
				boardBean.setBoard_name(rs.getString(4));
				boardBean.setBoard_pass(rs.getString(5));
				boardBean.setBoard_content(rs.getString(6));
				boardBean.setBoard_date(rs.getString(7));
				boardBean.setBoard_file1(rs.getString(8));
				boardBean.setBoard_file2(rs.getString(9));
				boardBean.setBoard_file3(rs.getString(10));
				boardBean.setBoard_file4(rs.getString(11));
				boardBean.setBoard_read(rs.getInt(12));
				boardBean.setBoard_price(rs.getInt(13));
				boardBean.setBoard_bank(rs.getString(14));
				boardBean.setBoard_account(rs.getInt(15));
				boardBean.setBoard_tel(rs.getInt(16));
				boardBean.setBoard_storage(rs.getString(17));
				boardBean.setBoard_delivery(rs.getString(18));
				boardBean.setBoard_product(rs.getString(19));
				boardBean.setBoard_amount(rs.getString(20));
				boardBean.setBoard_producer(rs.getString(21));
				boardBean.setBoard_origin(rs.getString(22));
				boardBean.setBoard_deliverycost(rs.getInt(23));
				boardBean.setBoard_expirydate(rs.getString(24));
				boardBean.setBoard_sort(rs.getString(25));
				boardBean.setBoard_domestic(rs.getString(26));
			}
			
		} catch(Exception e) {
			System.out.println("getDetail() ���� : " + e);
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			if(pstmt != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			if(conn != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		return boardBean;
		
	}
	
}
	
		
		
	
