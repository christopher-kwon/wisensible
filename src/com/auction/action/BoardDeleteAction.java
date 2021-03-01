package com.auction.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;
		boolean userCheck = false;

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_pass = request.getParameter("board_pass");

		BoardDAO boardDAO = new BoardDAO();

		userCheck = boardDAO.isBoardWriter(board_num, board_pass);

		if (userCheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		result = boardDAO.boardDelete(board_num);

		if (result == false) {
			System.out.println("게시판 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정이 되지 않았습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='BoardList.bom';");
		out.println("</script>");
		out.close();
		return null;
	}

}
