package com.auction.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.auction.db.BoardBean;
import com.auction.db.BoardDAO;
import com.hope.db.HopeDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		HopeDAO hopeDAO = new HopeDAO();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int max_price = hopeDAO.getMax(board_num);

		boardDAO.setReadCountUpdate(board_num);

		boardBean = boardDAO.getDetail(board_num);

		if (boardBean == null) {
			System.out.println("글 불러오기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "글보기에 실패하였습니다. ");
			forward.setPath("error/error.jsp");
			return forward;
		}

		request.setAttribute("boardBean", boardBean);
		request.setAttribute("max_price", max_price);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("auction/boardView.jsp");
		return forward;
	}

}
