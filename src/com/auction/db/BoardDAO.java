package com.auction.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 에러: " + e);
			return;
		}
	}
	
	public int getListcount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int result = 0;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement("select count(*) from auction");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("getListcount() 에러 : " + ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public List<BoardBean> getBoardList(int page, int limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String board_list_sql = "select * from (select rownum rn, board_num, board_name, board_subject, board_content, board_min_price, board_thumbnail from (select * from auction order by board_num desc)) where rn between ? and ?";
//        String board_list_sql = "select * from BOARD order by board_num desc";

        List<BoardBean> list = new ArrayList<BoardBean>();
        int startRow = (page - 1) * limit + 1;
        int endRow = startRow + limit - 1;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(board_list_sql);
            preparedStatement.setInt(1, startRow);
            preparedStatement.setInt(2, endRow);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardBean boardBean = new BoardBean();
                boardBean.setBoard_num(resultSet.getInt("board_num"));
                boardBean.setBoard_name(resultSet.getString("board_name"));
                boardBean.setBoard_subject(resultSet.getString("board_subject"));
                boardBean.setBoard_content(resultSet.getString("board_content"));
                boardBean.setBoard_min_price(resultSet.getInt("board_min_price"));
                boardBean.setBoard_thumbnail(resultSet.getString("Board_thumbnail"));
                list.add(boardBean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getBoardList() : " + ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public void setReadCountUpdate(int board_num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update auction set board_read = board_read + 1 where board_num = ?";

        try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setReadCountUpdate()  : " + ex);
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
        String sql = "select * from auction where board_num = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                boardBean = new BoardBean();
                boardBean.setBoard_category(rs.getString("board_category"));
                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_name(rs.getString("board_name"));
                boardBean.setBoard_pass(rs.getString("board_pass"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_date(rs.getString("board_date"));
                boardBean.setBoard_end_date(rs.getString("board_end_date"));
                boardBean.setBoard_file1(rs.getString("board_file1"));
                boardBean.setBoard_file2(rs.getString("board_file2"));
                boardBean.setBoard_file3(rs.getString("board_file3"));
                boardBean.setBoard_file4(rs.getString("board_file4"));
                boardBean.setBoard_thumbnail(rs.getString("board_thumbnail"));
                boardBean.setBoard_read(rs.getInt("board_read"));
                boardBean.setBoard_min_price(rs.getInt("board_min_price"));
                boardBean.setBoard_bank(rs.getString("board_bank"));
                boardBean.setBoard_account(rs.getString("board_account"));
                boardBean.setBoard_tel(rs.getString("board_tel"));
                boardBean.setBoard_storage(rs.getString("board_storage"));
                boardBean.setBoard_delivery(rs.getString("board_delivery"));
                boardBean.setBoard_product(rs.getString("board_product"));
                boardBean.setBoard_amount(rs.getString("board_amount"));
                boardBean.setBoard_producer(rs.getString("board_producer"));
                boardBean.setBoard_origin(rs.getString("board_origin"));
                boardBean.setBoard_deliverycost(rs.getInt("board_deliverycost"));
                boardBean.setBoard_expirydate(rs.getString("board_expirydate"));
            }
            
		} catch (Exception e) {
			System.out.println("getDetail() 에러 : " + e);
			e.printStackTrace();
            
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return boardBean;

    }


    public boolean boardModify(BoardBean boardBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update board set board_subject = ?, board_price= ?, board_bank = ?, "
                + "board_account = ?, board_tel = ?, board_delivery = ?,"
                + "board_product = ?, board_amount = ?, board_producer = ?,"
                + "board_expirydate = ?, board_origin = ?, board_storage = ?,"
                + "board_deliverycost = ?, board_content = ?, board_file1 = ?,"
                + "board_file2 = ?, board_file3 = ?, board_file4 = ?, board_thumbnail = ? where board_num = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardBean.getBoard_subject());
            pstmt.setInt(2, boardBean.getBoard_min_price());
            pstmt.setString(3, boardBean.getBoard_bank());
            pstmt.setString(4, boardBean.getBoard_account());
            pstmt.setString(5, boardBean.getBoard_tel());
            pstmt.setString(6, boardBean.getBoard_delivery());
            pstmt.setString(7, boardBean.getBoard_product());
            pstmt.setString(8, boardBean.getBoard_amount());
            pstmt.setString(9, boardBean.getBoard_producer());
            pstmt.setString(10, boardBean.getBoard_expirydate());
            pstmt.setString(11, boardBean.getBoard_origin());
            pstmt.setString(12, boardBean.getBoard_storage());
            pstmt.setInt(13, boardBean.getBoard_deliverycost());
            pstmt.setString(14, boardBean.getBoard_content());
            pstmt.setString(15, boardBean.getBoard_file1());
            pstmt.setString(16, boardBean.getBoard_file2());
            pstmt.setString(17, boardBean.getBoard_file3());
            pstmt.setString(18, boardBean.getBoard_file4());
            pstmt.setString(19, boardBean.getBoard_thumbnail());
            pstmt.setInt(20, boardBean.getBoard_num());

            int result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("게시판 수정 성공");
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("boardModify() : " + ex);

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

        return false;

    }

    public boolean isBoardWriter(int board_num, String board_pass) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        String sql = "SELECT board_pass FROM board WHERE board_num = ?";


        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(board_pass)) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("isBoardWriter()  : " + e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

            } catch (Exception xe) {
                xe.printStackTrace();

            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception qe) {
                qe.printStackTrace();

            }

        }
        return result;

    }

    public boolean boardDelete(int board_num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from board where board_num = ?";

        try {

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board_num);


            int result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("");
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("boardDelete()  : " + ex);

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

        return false;

    }

    public boolean boardInsert(BoardBean boardbean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {

			conn = ds.getConnection();

			String max_sql = "(select nvl(max(board_num),0)+1 from auction)";

			String sql = "insert into auction " + " values( ? ," + max_sql + ",?,?,?,?,sysdate, sysdate+5/(24*60), ?,?,?,?,?,?,?, "
					+ " ?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardbean.getBoard_category());
			pstmt.setString(2, boardbean.getBoard_subject());
			pstmt.setString(3, boardbean.getBoard_name());
			pstmt.setString(4, boardbean.getBoard_pass());
			pstmt.setString(5, boardbean.getBoard_content());
			pstmt.setString(6, boardbean.getBoard_file1());
			pstmt.setString(7, boardbean.getBoard_file2());
			pstmt.setString(8, boardbean.getBoard_file3());
			pstmt.setString(9, boardbean.getBoard_file4());
			pstmt.setString(10, boardbean.getBoard_thumbnail());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			pstmt.setInt(13, boardbean.getBoard_min_price());
			pstmt.setString(14, boardbean.getBoard_bank());
			pstmt.setString(15, boardbean.getBoard_account());
			pstmt.setString(16, boardbean.getBoard_tel());
			pstmt.setString(17, boardbean.getBoard_storage());
			pstmt.setString(18, boardbean.getBoard_delivery());
			pstmt.setString(19, boardbean.getBoard_product());
			pstmt.setString(20, boardbean.getBoard_amount());
			pstmt.setString(21, boardbean.getBoard_producer());
			pstmt.setString(22, boardbean.getBoard_origin());
			pstmt.setInt(23, boardbean.getBoard_deliverycost());
			pstmt.setString(24, boardbean.getBoard_expirydate());
			pstmt.setInt(25, 0);
			
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("게시판 작성 성공.");
				return true;
			}

		} catch (Exception se) {
			System.out.println("Insert() 에러 : " + se);
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
                    conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return false;

    }

    public List<BoardBean> getBoardCategoryList(String categoryValue) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String board_category_list_sql = "select * from auction where board_category = ? order by BOARD_NUM desc ";

		List<BoardBean> list = new ArrayList<BoardBean>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(board_category_list_sql);
			preparedStatement.setString(1, categoryValue);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				BoardBean boardBean = new BoardBean();
				boardBean.setBoard_num(resultSet.getInt("board_num"));
				boardBean.setBoard_name(resultSet.getString("board_name"));
				boardBean.setBoard_subject(resultSet.getString("board_subject"));
				boardBean.setBoard_content(resultSet.getString("board_content"));
				boardBean.setBoard_min_price(resultSet.getInt("board_min_price"));
				boardBean.setBoard_thumbnail(resultSet.getString("board_thumbnail"));

				list.add(boardBean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardCategoryList() : " + ex);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}

}


