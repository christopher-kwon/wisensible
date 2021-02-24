package com.board.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private DataSource ds;

    // �����ڿ��� JNDI ���ҽ��� �����Ͽ� Connection ��ü�� ���ɴϴ�.
    public BoardDAO() {
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            System.out.println("DB���� ���� : " + e);
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
            preparedStatement = connection.prepareStatement("select count(*) from BOARD_TEST");
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

        String board_list_sql = "select * from (select * from board_test order by ROWNUM desc) where ROWNUM <= 12";
//        String board_list_sql = "select * from BOARD_TEST";

        List<BoardBean> list = new ArrayList<BoardBean>();
        int startRow = (page -1) * limit +1;
        int endRow = startRow + limit -1;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(board_list_sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BoardBean boardBean = new BoardBean();
                boardBean.setBoard_num(resultSet.getInt("board_num"));
                boardBean.setBoard_name(resultSet.getString("board_name"));
                boardBean.setBoard_subject(resultSet.getString("board_subject"));
                boardBean.setBoard_content(resultSet.getString("board_content"));
                boardBean.setBoard_price(resultSet.getString("board_price"));
                list.add(boardBean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getBoardList() 에러 : " + ex);
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
