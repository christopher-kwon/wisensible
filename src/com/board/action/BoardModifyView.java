package com.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.board.db.BoardBean;
import com.board.db.BoardDAO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		BoardDAO boardDAO = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		
		int board_num = Integer.parseInt(request.getParameter("num"));
		
		boardBean = boardDAO.getDetail(board_num);
		
		if (boardBean == null) {
			System.out.println("�� ������ �����߽��ϴ�.");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		request.setAttribute("boardBean", boardBean);
		forward.setRedirect(false);
		
		forward.setPath("board/boardModify.jsp");
		return forward;
		
		
	}

}
