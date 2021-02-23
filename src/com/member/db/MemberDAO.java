package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	//2021.2.22 1차 완료
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

	public int isId(String id, String member_pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;//아이디가 존재하지 않습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select member_id, member_pass from member where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString(2));
				System.out.println(member_pass);
				if(rs.getString(2).equals(member_pass)) {
					result= 1;//아이디와 비밀번호가 일치하는 경우
				}else {
					result = 0; //비밀번호가 일치하지 않는 경우
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return result;
	}

	public int insert(MemberBean m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection : insert()");

			String insert_sql = "INSERT INTO member"
					+ "(MEMBER_NAME, MEMBER_ID, MEMBER_PASS, MEMBER_BIRTH, MEMBER_GENDER, "
					+ " MEMBER_EMAIL, MEMBER_TEL, MEMBER_BANK, MEMBER_ACCOUNT,"
					+ " MEMBER_ADDRESS, MEMBER_INTEREST) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(insert_sql);

			pstmt.setString(1, m.getMember_name());
			pstmt.setString(2, m.getMember_id());
			pstmt.setString(3, m.getMember_password());
			pstmt.setString(4, m.getMember_birth());
			pstmt.setString(5, m.getMember_gender());
			pstmt.setString(6, m.getMember_email());
			pstmt.setString(7, m.getMember_tel());
			pstmt.setString(8, m.getMember_bank());
			pstmt.setString(9, m.getMember_account());
			pstmt.setString(10, m.getMember_address());
			pstmt.setString(11, m.getMember_interest());
			
			result = pstmt.executeUpdate();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("멤버 아이디 중복 에러입니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return result;
	}

	public int isId(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			con = ds.getConnection();
			
			String sql = "select member_id from member where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;//DB에 해당 id가 있습니다.
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getisId() 에러: " + e);
		}finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return result;
	}

	public int delete(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			con = ds.getConnection();
			String sql = "delete from member where member_id = ? ";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete() 에러: " + e);
		}finally {
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return result;
	}

	//2021.2.23
	public MemberBean member_info(String id) {
		MemberBean memberbean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberbean = new MemberBean();
				memberbean.setMember_name(rs.getString(1));
				memberbean.setMember_id(rs.getString(2));
				memberbean.setMember_password(rs.getString(3));
				memberbean.setMember_birth(rs.getString(4));
				memberbean.setMember_gender(rs.getString(5));
				memberbean.setMember_email(rs.getString(6));
				memberbean.setMember_tel(rs.getString(7));
				memberbean.setMember_bank(rs.getString(8));
				memberbean.setMember_account(rs.getString(9));
				memberbean.setMember_address(rs.getString(10));
				memberbean.setMember_interest(rs.getString(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member_info()에러");
		}finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return memberbean;
	}
	
}
