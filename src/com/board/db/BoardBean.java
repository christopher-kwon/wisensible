package com.board.db;

public class BoardBean {
    private int board_num;
    private String board_name;
    private String board_subject;
    private String board_content;
    private String board_price;

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getBoard_subject() {
        return board_subject;
    }

    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_price() {
        return board_price;
    }

    public void setBoard_price(String board_price) {
        this.board_price = board_price;
    }
}