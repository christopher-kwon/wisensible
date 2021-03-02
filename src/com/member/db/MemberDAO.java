package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
					+ " MEMBER_EMAIL, MEMBER_EMAILHASH, MEMBER_EMAILCHECKED, MEMBER_TEL, MEMBER_BANK, MEMBER_ACCOUNT,"
					+ " MEMBER_ADDRESS, MEMBER_INTEREST, MEMBER_FILE) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(insert_sql);

			pstmt.setString(1, m.getMember_name());
			pstmt.setString(2, m.getMember_id());
			pstmt.setString(3, m.getMember_password());
			pstmt.setString(4, m.getMember_birth());
			pstmt.setString(5, m.getMember_gender());
			pstmt.setString(6, m.getMember_email());
			pstmt.setString(7, m.getMember_emailhash());
			pstmt.setString(8, m.getMember_emailchecked());
			pstmt.setString(9, m.getMember_tel());
			pstmt.setString(10, m.getMember_bank());
			pstmt.setString(11, m.getMember_account());
			pstmt.setString(12, m.getMember_address());
			pstmt.setString(13, m.getMember_interest());
			pstmt.setString(14, m.getMember_file());
			
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
				memberbean.setMember_id(rs.getString("member_id"));
				memberbean.setMember_name(rs.getString("member_name"));
				memberbean.setMember_password(rs.getString("member_pass"));
				memberbean.setMember_birth(rs.getString("member_birth"));
				memberbean.setMember_email(rs.getString("member_email"));
				memberbean.setMember_emailhash(rs.getString("member_emailhash"));
				memberbean.setMember_emailchecked(rs.getString("member_emailchecked"));
				memberbean.setMember_gender(rs.getString("member_gender"));
				memberbean.setMember_tel(rs.getString("member_tel"));
				memberbean.setMember_address(rs.getString("member_address"));
				memberbean.setMember_interest(rs.getString("member_interest"));
				memberbean.setMember_account(rs.getString("member_account"));
				memberbean.setMember_bank(rs.getString("member_bank"));
				memberbean.setMember_file(rs.getString("member_file"));
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


	

	public int update(MemberBean memberbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "update member set member_pass = ?, member_email = ?, "
						+ " member_tel = ?, member_bank = ? , member_account = ?,"
						+ " member_address =? , member_interest = ?, member_file =?  "
						+ " where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberbean.getMember_password());
			pstmt.setString(2, memberbean.getMember_email());
			pstmt.setString(3, memberbean.getMember_tel());
			pstmt.setString(4, memberbean.getMember_bank());
			pstmt.setString(5, memberbean.getMember_account());
			pstmt.setString(6, memberbean.getMember_address());
			pstmt.setString(7, memberbean.getMember_interest());
			pstmt.setString(8, memberbean.getMember_file());
			pstmt.setString(9, memberbean.getMember_id());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member_update()에러");
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

	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("select count(*) from member where member_id != 'admin'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount() 에러: " + e);
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
		return x;
	}

	public List<MemberBean> getList(int page, int limit) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
						+ " from (select b.*, rownum rnum "
						+ "			from(select * from member "
						+ "					where member_id != 'admin' "
						+ "					order by member_id) b "
						+ "			)"
						+ " where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			//한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지....
			int startrow = (page-1)*limit + 1;
							//읽기 시작할 row번호(1 11 21...
			int endrow = startrow + limit - 1;
							//읽을 마지막 row번호(10 20 30...
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberBean memberbean = new MemberBean();
				memberbean.setMember_id(rs.getString("member_id"));
				memberbean.setMember_name(rs.getString("member_name"));
				memberbean.setMember_password(rs.getString("member_pass"));
				memberbean.setMember_birth(rs.getString("member_birth"));
				memberbean.setMember_email(rs.getString("member_email"));
				memberbean.setMember_gender(rs.getString("member_gender"));
				memberbean.setMember_tel(rs.getString("member_tel"));
				memberbean.setMember_address(rs.getString("member_address"));
				memberbean.setMember_interest(rs.getString("member_interest"));
				memberbean.setMember_account(rs.getString("member_account"));
				memberbean.setMember_bank(rs.getString("member_bank"));
				memberbean.setMember_file(rs.getString("member_file"));
				list.add(memberbean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getList_2개() 에러: " + e);
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
		return list;
	}

	public int getListCount(String field, String value) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from member "
						+ " where member_id != 'admin' "
						+ " and " + field + " like ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount_2() 에러: " + e);
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
		return x;
	}

	public List<MemberBean> getList(String field, String value, int page, int limit) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
						+ " from (select b.*, rownum rnum "
						+ "			from(select * from member "
						+ "					where member_id != 'admin' "
						+ "					and " + field + " like ? "
						+ "					order by member_id) b "
						+ "			)"
						+ " where rnum between ? and ?";
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			//한 페이지 당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지....
			int startrow = (page-1)*limit + 1;
							//읽기 시작할 row번호(1 11 21...
			int endrow = startrow + limit - 1;
							//읽을 마지막 row번호(10 20 30...
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberBean memberbean = new MemberBean();
				memberbean.setMember_id(rs.getString("member_id"));
				memberbean.setMember_name(rs.getString("member_name"));
				//memberbean.setMember_password(rs.getString(3));
				//memberbean.setMember_birth(rs.getString(4));
				memberbean.setMember_email(rs.getString("member_email"));
				//memberbean.setMember_gender(rs.getString(6));
				//memberbean.setMember_tel(rs.getString(7));
				//memberbean.setMember_address(rs.getString(8));
				//memberbean.setMember_interest(rs.getString(9));
				//memberbean.setMember_account(rs.getString(10));
				//memberbean.setMember_bank(rs.getString(11));
				//memberbean.setMember_file(rs.getString(12));
				list.add(memberbean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getList_4개() 에러: " + e);
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
		return list;
	}

	public String isFindName(String member_name, String member_tel) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";//아이디가 존재하지 않습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select member_name, member_tel, member_id from member where member_name = ? and member_tel = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_name);
			pstmt.setString(2, member_tel);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("member_id=" + rs.getString(3));
				System.out.println(rs.getString(2));
				System.out.println("member_name=" + rs.getString(1));
				if(rs.getString(2).equals(member_tel)) {
					result = rs.getString(3);
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

	public String isFindId(String member_id, String member_email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";//아이디가 존재하지 않습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select member_id, member_email, member_pass from member where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();
			/*
			
			if(rs.next()) {
				System.out.println("member_id=" + rs.getString(3));
				System.out.println(rs.getString(2));
				System.out.println("member_name=" + rs.getString(1));
				if(rs.getString(2).equals(member_tel)) {
					result = rs.getString(3);
				}
			}
			 */
			if(rs.next()) {
				System.out.println("member_id=" + rs.getString(1));
				System.out.println("member_email=" + rs.getString(2));
				System.out.println("member_pass=" + rs.getString(3));
				if(rs.getString(1).equals(member_id)) {
					result= rs.getString(3);//아이디와 비밀번호가 일치하는 경우
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

	
	public int isHash(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; 
		try {
			con = ds.getConnection();
			
			String sql = "select member_id from member where member_emailhash = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
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
	
	public int checkedUp(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "update member set member_emailchecked = 1 where member_emailhash = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
		
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member_update()에러");
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
	
	public int auction(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; 
		try {
			con = ds.getConnection();
			
			String sql = "select * from member where member_id = ? and member_emailchecked = 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
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
	
}