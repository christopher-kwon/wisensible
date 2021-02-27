package com.board.action;

import java.io.IOException;


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

public class BoardAddAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();
		BoardBean boardbean = new BoardBean();

		String realFolder = "";
		String savaFolder = "boardupload";
		int fileSize = 5 * 1024 * 1024;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(savaFolder);

		boolean result = false;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			boardbean.setBoard_name(multi.getParameter("board_name"));
			boardbean.setBoard_pass(multi.getParameter("board_passward"));
			boardbean.setBoard_subject(multi.getParameter("board_subject"));
			boardbean.setBoard_category(multi.getParameter("board_category"));
			boardbean.setBoard_content(multi.getParameter("board_content"));
			boardbean.setBoard_price(Integer.parseInt(multi.getParameter("board_price")));
			boardbean.setBoard_bank(multi.getParameter("board_bank"));
			boardbean.setBoard_tel(multi.getParameter("board_tel"));
			boardbean.setBoard_account(multi.getParameter("board_account"));
			
			String[] board_storages = multi.getParameterValues("board_storage");
			String storage_result="";
			for(int i = 0; i < board_storages.length; i++){
				storage_result += board_storages[i]+ "   ";
			}
			
			boardbean.setBoard_storage(storage_result);
		
			String[] board_deliverys = multi.getParameterValues("board_delivery");
			String delivery_result="";
			for(int num = 0; num < board_deliverys.length; num++){
				delivery_result += board_deliverys[num]+ "   ";
			}
			
			
			boardbean.setBoard_delivery(delivery_result);
			
			boardbean.setBoard_product(multi.getParameter("board_product"));
			boardbean.setBoard_amount(multi.getParameter("board_amount"));
			boardbean.setBoard_producer(multi.getParameter("board_producer"));
			boardbean.setBoard_origin(multi.getParameter("board_origin"));
			boardbean.setBoard_expirydate(multi.getParameter("board_expirydate"));
			boardbean.setBoard_deliverycost(Integer.parseInt(multi.getParameter("board_deliverycost")));
			boardbean.setBoard_thumbnail(multi.getFilesystemName("board_thumbnail"));
			boardbean.setBoard_file1(multi.getFilesystemName("board_file1"));
			boardbean.setBoard_file2(multi.getFilesystemName("board_file2"));
			boardbean.setBoard_file3(multi.getFilesystemName("board_file3"));
			boardbean.setBoard_file4(multi.getFilesystemName("board_file4"));

			// 파일추가 1-4

			result = boarddao.boardInsert(boardbean);

			if (result == false) {
				System.out.println("판매글 등록실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;

			}
			System.out.println("게시판 등록완료");

			// 글등록이 완료되면 글 목록을 보여주기위해 "BoardList.bo"로이동합니다.
			// Redirect여부를 true로 설정합니다.
			forward.setRedirect(true);
			forward.setPath("BoardList.bo");// 이동할 경로를 지정합니다.
			return forward;

		} catch (IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드실패입니다.");
			forward.setRedirect(false);
			return forward;
		} // catch end

	}

}
