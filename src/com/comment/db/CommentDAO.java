package com.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CommentDAO {

	private DataSource ds;

	public CommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
			return;
		}
	}

	public int getListCount(int BOARD_RE_REF) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from comm where comment_board_ref = ?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BOARD_RE_REF);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount() 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close(); 
				} catch (Exception qe) {
					qe.printStackTrace();
				}

		}
		return x;
	}
	
	public JsonArray getCommentList(int comment_board_ref, int state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sort = "asc";
		
		if(state == 2) {
			sort="desc";
		}

		String sql = "select * from ( select b.*, rownum rnum from(select comment_num, comm.comment_id, comment_content, comment_date, "
				+ "comment_re_lev, comment_re_seq, comment_re_ref, member.memberfile from comm join member "
				+ "on comm.comment_id=member.id where comment_board_ref = ? order by comment_re_ref " + sort + ", " 
				+ "comment_re_seq asc)b"
				+ ")";
		
		JsonArray array = new JsonArray();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment_board_ref);
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 개체에 담습니다.
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("comment_num", rs.getInt(1));
				object.addProperty("comment_board_ref", rs.getInt(2));
				object.addProperty("comment_id", rs.getString(3));
				object.addProperty("comment_content", rs.getString(4));
				object.addProperty("comment_date", rs.getString(5));
				object.addProperty("comment_re_ref", rs.getInt(6));
				object.addProperty("comment_re_lev", rs.getInt(7));
				object.addProperty("comment_re_seq", rs.getInt(8));
				object.addProperty("memberfile", rs.getString(9));
				array.add(object);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardList() 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close(); // 4단계 : DB 연결을 끊는다.
				} catch (Exception qe) {
					qe.printStackTrace();
				}

		}

		return array;
	}
}
