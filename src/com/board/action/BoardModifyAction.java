package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.board.db.BoardBean;
import com.board.db.BoardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		BoardDAO boardDAO = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		String realFolder = "";
		
		String saveFolder = "boardupload";
		int fileSize = 50 * 1024 * 1024;
		
		ServletContext servletContext = request.getServletContext();
		realFolder = servletContext.getRealPath(saveFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			int board_num = Integer.parseInt(multi.getParameter("board_num"));
			String board_pass = multi.getParameter("board_pass");
			
			boolean userCheck = boardDAO.isBoardWriter(board_num, board_pass);
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
			
			boardBean.setBoard_num(board_num);
			boardBean.setBoard_subject(multi.getParameter("board_subject"));
			boardBean.setBoard_price(Integer.parseInt(multi.getParameter("board_price")));
			boardBean.setBoard_bank(multi.getParameter("board_bank"));
			boardBean.setBoard_account(Integer.parseInt(multi.getParameter("board_account")));
			boardBean.setBoard_tel(multi.getParameter("board_tel"));
			boardBean.setBoard_delivery(multi.getParameter("board_delivery"));
			boardBean.setBoard_product(multi.getParameter("board_product"));
			boardBean.setBoard_amount(multi.getParameter("board_amount"));
			boardBean.setBoard_producer(multi.getParameter("board_producer"));
			boardBean.setBoard_expirydate(multi.getParameter("board_epirydate"));
			boardBean.setBoard_origin(multi.getParameter("board_origin"));
			boardBean.setBoard_storage(multi.getParameter("board_storage"));
			boardBean.setBoard_deliverycost(Integer.parseInt(multi.getParameter("board_deliverycost")));
			boardBean.setBoard_content(multi.getParameter("board_content"));
			
			String check = multi.getParameter("check");
			if (check != null) {
				boardBean.setBoard_thumbnail("check");
				System.out.println("check = " + check);

			} else {
				String fileName = multi.getFilesystemName("board_thumbnail");
				boardBean.setBoard_thumbnail(fileName);
			}
			
			result = boardDAO.boardModify(boardBean);
		
		
		if (result == false) {
			System.out.println("게시판 수정 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정이 되지 않았습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("BoardDetailAction.bo?board_num=" + boardBean.getBoard_num());
		return forward;
		
	} catch (IOException e) {
		e.printStackTrace();
		forward = new ActionForward();
		forward.setPath("error/error.jsp");
		request.setAttribute("message", "게시판 업로드 중 실패입니다.");
		forward.setRedirect(false);
		return forward;

	}
		
	}

}
