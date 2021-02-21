package com.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.board.db.BoardBean;
import com.board.db.BoardDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		BoardBean boardBean = new BoardBean();

		int board_num = Integer.parseInt(request.getParameter("board_num"));

		boardDAO.setReadCountUpdate(board_num);

		boardBean = boardDAO.getDetail(board_num);

		if (boardBean == null) {
			System.out.println("�� �ҷ����� ����");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "�ۺ��⿡ �����Ͽ����ϴ�. ");
			forward.setPath("error/error.jsp");
			return forward;
		}

		request.setAttribute("boardBean", boardBean);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board/boardView.jsp");
		return forward;
	}

}
