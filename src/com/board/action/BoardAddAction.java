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
		int fileSize = 50 * 1024 * 1024;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(savaFolder);

		boolean result = false;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			boardbean.setBoard_name(multi.getParameter("id"));
			boardbean.setBoard_pass(multi.getParameter("board_passward"));
			boardbean.setBoard_subject(multi.getParameter("board_subject"));
			boardbean.setBoard_category(multi.getParameter("board_category"));
			boardbean.setBoard_content(multi.getParameter("board_content"));
			boardbean.setBoard_price(Integer.parseInt(multi.getParameter("board_price")));
			boardbean.setBoard_bank(multi.getParameter("board_bank"));
			boardbean.setBoard_tel(multi.getParameter("board_tel"));
			boardbean.setBoard_account(Integer.parseInt(multi.getParameter("board_account")));
			
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

			// �����߰� 1-4

			result = boarddao.boardInsert(boardbean);

			if (result == false) {
				System.out.println("�Ǹű� ��Ͻ���");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setRedirect(false);
				return forward;

			}
			System.out.println("�Խ��� ��ϿϷ�");

			// �۵���� �Ϸ�Ǹ� �� ����� �����ֱ����� "BoardList.bo"���̵��մϴ�.
			// Redirect���θ� true�� �����մϴ�.
			forward.setRedirect(true);
			forward.setPath("BoardList.bo");// �̵��� ��θ� �����մϴ�.
			return forward;

		} catch (IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "�Խ��� ���ε�����Դϴ�.");
			forward.setRedirect(false);
			return forward;
		} // catch end

	}

}
