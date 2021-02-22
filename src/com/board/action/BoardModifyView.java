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
			System.out.println("글 수정에 실패했습니다.");
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		request.setAttribute("boardBean", boardBean);
		forward.setRedirect(false);
		
		forward.setPath("board/boardModify.jsp");
		return forward;
		
		
	}

}
