package com.auction.action;

import java.io.File;
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
			boardBean.setBoard_subject(multi.getParameter("board_subject").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_price(Integer.parseInt(multi.getParameter("board_price")));
			boardBean.setBoard_bank(multi.getParameter("board_bank"));
			boardBean.setBoard_account(multi.getParameter("board_account"));
			boardBean.setBoard_tel(multi.getParameter("board_tel"));
			boardBean.setBoard_product(multi.getParameter("board_product").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_amount(multi.getParameter("board_amount").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_producer(multi.getParameter("board_producer").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_expirydate(multi.getParameter("board_expirydate").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_origin(multi.getParameter("board_origin").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			boardBean.setBoard_deliverycost(Integer.parseInt(multi.getParameter("board_deliverycost")));
			boardBean.setBoard_content(multi.getParameter("board_content").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			
			String[] board_storages = multi.getParameterValues("board_storage");
			String storage_result="";
			for(int i = 0; i < board_storages.length; i++){
				storage_result += board_storages[i]+ "   ";
			}
			
			boardBean.setBoard_storage(storage_result);
		
			String[] board_deliverys = multi.getParameterValues("board_delivery");
			String delivery_result="";
			for(int num = 0; num < board_deliverys.length; num++){
				delivery_result += board_deliverys[num]+ "   ";
			}
			
			
			boardBean.setBoard_delivery(delivery_result);
			
			String check0 = multi.getParameter("check0");
			if (check0 != null) {
				boardBean.setBoard_thumbnail(check0);
				System.out.println("check0 = " + check0);

			} else {
				String fileName = multi.getFilesystemName("board_thumbnail");
				if(!fileName.toUpperCase().endsWith(".JPG") && !fileName.toUpperCase().endsWith(".PNG") && !fileName.toUpperCase().endsWith(".GIF") && !fileName.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName);
					file.delete();
				} else {
				boardBean.setBoard_thumbnail(fileName);
			}
			
			}
	
			String check1 = multi.getParameter("check1");
			if (check1 != null) {
				boardBean.setBoard_file1(check1);

			} else {
				String fileName1 = multi.getFilesystemName("board_file1");
				if(!fileName1.toUpperCase().endsWith(".JPG") && !fileName1.toUpperCase().endsWith(".PNG") && !fileName1.toUpperCase().endsWith(".GIF") && !fileName1.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName1);
					file.delete();
					System.out.println("업로드 불가 파일");
					System.out.println(realFolder + "\\" +fileName1);
					
				} else {
					
				boardBean.setBoard_file1(fileName1);
				}
				
			}
			
			String check2 = multi.getParameter("check2");
			if (check2 != null) {
				boardBean.setBoard_file2(check2);
				System.out.println("check2 = " + check2);

			} else {
				String fileName2 = multi.getFilesystemName("board_file2");
				if(!fileName2.toUpperCase().endsWith(".JPG") && !fileName2.toUpperCase().endsWith(".PNG") && !fileName2.toUpperCase().endsWith(".GIF") && !fileName2.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName2);
					file.delete();
				} else {
				boardBean.setBoard_file2(fileName2);
			}
			
			}
			
			String check3 = multi.getParameter("check3");
			if (check3 != null) {
				boardBean.setBoard_file3(check3);
				System.out.println("check3 = " + check3);

			} else {
				String fileName3 = multi.getFilesystemName("board_file3");
				if(!fileName3.toUpperCase().endsWith(".JPG") && !fileName3.toUpperCase().endsWith(".PNG") && !fileName3.toUpperCase().endsWith(".GIF") && !fileName3.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName3);
					file.delete();
				} else {
				boardBean.setBoard_file3(fileName3);
			}
				
			}
			
			String check4 = multi.getParameter("check4");
			if (check4 != null) {
				boardBean.setBoard_file4(check4);
				System.out.println("check4 = " + check4);

			} else {
				String fileName4 = multi.getFilesystemName("board_file4");
				if(!fileName4.toUpperCase().endsWith(".JPG") && !fileName4.toUpperCase().endsWith(".PNG") && !fileName4.toUpperCase().endsWith(".GIF") && !fileName4.toUpperCase().endsWith(".JPEG")) {
					File file = new File(realFolder + "\\" + fileName4);
					file.delete();
				} else {
				boardBean.setBoard_file4(fileName4);
			}
			
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
		forward.setPath("BoardDetailAction.bom?board_num=" + boardBean.getBoard_num());
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
