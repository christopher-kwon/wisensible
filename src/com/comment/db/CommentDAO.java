package com.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public int getListCount(int comment_board_ref) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from comm where comment_board_ref = ?";
		int x = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_board_ref);
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

		if (state == 2) {
			sort = "desc";
		}

		String sql = "select * from ( select b.*, rownum rnum from(select comment_num, comment_id, comment_content, comment_date, "
				+ "comment_re_lev, comment_re_seq, comment_re_ref, member_file from comm join member "
				+ "on comment_id=member_id where comment_board_ref = ? order by comment_re_ref " + sort + ", "
				+ "comment_re_seq asc)b" + ")";

		JsonArray array = new JsonArray();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment_board_ref);
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO 개체에 담습니다.
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("comment_num", rs.getInt("comment_num"));
				object.addProperty("comment_id", rs.getString("comment_id"));
				object.addProperty("comment_content", rs.getString("comment_content"));
				object.addProperty("comment_date", rs.getString("comment_date"));
				object.addProperty("comment_re_ref", rs.getInt("comment_re_ref"));
				object.addProperty("comment_re_lev", rs.getInt("comment_re_lev"));
				object.addProperty("comment_re_seq", rs.getInt("comment_re_seq"));
				object.addProperty("member_file", rs.getString("member_file"));
				array.add(object);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getCommentList() 에러 : " + ex);
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

	public int commentsInsert(CommentBean comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String SQL = "INSERT INTO comm VALUES(comm_seq.nextval, ?, ?, ?, " + "SYSDATE, comm_seq.nextval, ?, ?)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, comment.getComment_board_ref());
			pstmt.setString(2, comment.getComment_id());
			pstmt.setString(3, comment.getComment_content());
			pstmt.setInt(4, comment.getComment_re_lev());
			pstmt.setInt(5, comment.getComment_re_seq());

			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			} else {
				System.out.println("데이터 삽입 오류");
			}

		} catch (Exception e) {
			System.out.println("boardInsert() 에러 : " + e);
			e.printStackTrace();
		} finally {
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

		return result;
	}

	public int commentsDelete(int comment_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String select_sql = "select comment_re_ref, comment_re_lev, comment_re_seq from comm where comment_num = ?";

		String delete_sql = "delete from comm where comment_re_ref = ? and comment_re_lev >= ? "
				+ "and comment_re_seq >= ? and comment_re_seq <= (nvl((select min(comment_re_seq)-1 from comm "
				+ "where comment_re_ref = ? and comment_re_lev = ? and comment_re_seq > ?), (select max(comment_re_seq) "
				+ "from comm where comment_re_ref = ?)))";

		int result = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, comment_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt2 = conn.prepareStatement(delete_sql);
				pstmt2.setInt(1, rs.getInt("comment_re_ref"));
				pstmt2.setInt(2, rs.getInt("comment_re_lev"));
				pstmt2.setInt(3, rs.getInt("comment_re_seq"));
				pstmt2.setInt(4, rs.getInt("comment_re_ref"));
				pstmt2.setInt(5, rs.getInt("comment_re_lev"));
				pstmt2.setInt(6, rs.getInt("comment_re_seq"));
				pstmt2.setInt(7, rs.getInt("comment_re_ref"));

				result = pstmt2.executeUpdate();

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("commentsDelete() 에러 : " + ex);

		} finally {
			if (rs != null)
				try {
					rs.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}

		return result;

	}

	public int commentsReply(CommentBean comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String update_sql = "UPDATE comm SET comment_re_seq = comment_re_seq + 1 where comment_re_ref = ? and comment_re_seq > ?";

			pstmt = conn.prepareStatement(update_sql);
			pstmt.setInt(1, comment.getComment_re_ref());
			pstmt.setInt(2, comment.getComment_re_seq());
			pstmt.executeUpdate();
			pstmt.close();

			String sql = "INSERT INTO comm VALUES(comm_seq.nextval, ?, ?, ?, " + "SYSDATE, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment.getComment_board_ref());
			pstmt.setString(2, comment.getComment_id());
			pstmt.setString(3, comment.getComment_content());
			pstmt.setInt(4, comment.getComment_re_ref());
			pstmt.setInt(5, comment.getComment_re_lev() + 1);
			pstmt.setInt(6, comment.getComment_re_seq() + 1);

			result = pstmt.executeUpdate();

			if (result == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("boardReply() 에러 : " + ex);
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}

		return result;

	}

	public int commentsUpdate(CommentBean comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update comm set comment_content = ? where comment_num = ?";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getComment_content());
			pstmt.setInt(2, comment.getComment_num());

			result = pstmt.executeUpdate();

			if (result == 1) {
				return result;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("boardModify() 에러 : " + ex);

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}

		return result;

	}

}