package com.board.action;

import java.io.File;
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
		int fileSize = 50 * 1024 * 1024;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(savaFolder);

		boolean result = false;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			boardbean.setBoard_name(multi.getParameter("board_name"));
			boardbean.setBoard_pass(multi.getParameter("board_passward"));
			
			boardbean.setBoard_subject(multi.getParameter("board_product").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_category(multi.getParameter("board_category"));
			boardbean.setBoard_content(multi.getParameter("board_content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
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
			
			boardbean.setBoard_product(multi.getParameter("board_product").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_amount(multi.getParameter("board_amount").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_producer(multi.getParameter("board_producer").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_origin(multi.getParameter("board_origin").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_expirydate(multi.getParameter("board_expirydate").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardbean.setBoard_deliverycost(Integer.parseInt(multi.getParameter("board_deliverycost")));
			

			
			String check0 = multi.getParameter("board_thumbnail");
			if (check0 != null) {
				boardbean.setBoard_thumbnail(check0);
				System.out.println("check0 = " + check0);

			} else {
				String fileName = multi.getFilesystemName("board_thumbnail");
				if(!fileName.toUpperCase().endsWith(".JPG") && !fileName.toUpperCase().endsWith(".PNG") && !fileName.toUpperCase().endsWith(".GIF") && !fileName.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName);
					file.delete();
				} else {
				boardbean.setBoard_thumbnail(fileName);
			}
			
			}
	
			String check1 = multi.getParameter("board_file1");
			if (check1 != null) {
				boardbean.setBoard_file1(check1);

			} else {
				String fileName1 = multi.getFilesystemName("board_file1");
				if(!fileName1.toUpperCase().endsWith(".JPG") && !fileName1.toUpperCase().endsWith(".PNG") && !fileName1.toUpperCase().endsWith(".GIF") && !fileName1.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName1);
					file.delete();
					System.out.println("업로드 불가 파일");
					System.out.println(realFolder + "\\" +fileName1);
					
				} else {
					
				boardbean.setBoard_file1(fileName1);
				}
				
			}
			
			String check2 = multi.getParameter("board_file2");
			if (check2 != null) {
				boardbean.setBoard_file2(check2);
				System.out.println("check2 = " + check2);

			} else {
				String fileName2 = multi.getFilesystemName("board_file2");
				if(!fileName2.toUpperCase().endsWith(".JPG") && !fileName2.toUpperCase().endsWith(".PNG") && !fileName2.toUpperCase().endsWith(".GIF") && !fileName2.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName2);
					file.delete();
				} else {
				boardbean.setBoard_file2(fileName2);
			}
			
			}
			
			String check3 = multi.getParameter("board_file3");
			if (check3 != null) {
				boardbean.setBoard_file3(check3);
				System.out.println("check3 = " + check3);

			} else {
				String fileName3 = multi.getFilesystemName("board_file3");
				if(!fileName3.toUpperCase().endsWith(".JPG") && !fileName3.toUpperCase().endsWith(".PNG") && !fileName3.toUpperCase().endsWith(".GIF") && !fileName3.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName3);
					file.delete();
				} else {
				boardbean.setBoard_file3(fileName3);
			}
				
			}
			
			String check4 = multi.getParameter("board_file4");
			if (check4 != null) {
				boardbean.setBoard_file4(check4);
				System.out.println("check4 = " + check4);

			} else {
				String fileName4 = multi.getFilesystemName("board_file4");
				if(!fileName4.toUpperCase().endsWith(".JPG") && !fileName4.toUpperCase().endsWith(".PNG") && !fileName4.toUpperCase().endsWith(".GIF") && !fileName4.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName4);
					file.delete();
				} else {
				boardbean.setBoard_file4(fileName4);
			}
			
			}
			
	

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
