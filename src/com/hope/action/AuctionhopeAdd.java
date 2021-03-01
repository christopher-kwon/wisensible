package com.hope.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.hope.db.HopeBean;
import com.hope.db.HopeDAO;


public class AuctionhopeAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HopeBean hope = new HopeBean();
		
		String hope_id =  request.getParameter("hope_id");
		int hope_price =Integer.parseInt(request.getParameter("hope_price"));
		int board_num =  Integer.parseInt(request.getParameter("board_num"));
		

		
		hope.setHope_id(hope_id);
		hope.setHope_price(hope_price);
		hope.setBoard_num(board_num);
		
		
		
		
		PrintWriter out = response.getWriter();
		HopeDAO hdao = new HopeDAO();
		
			
			int ok = hdao.insert(hope);
			response.getWriter().print(ok);
			out.println("<script>");
			out.println("alert('경매 참여가 완료되었습니다.');");
			out.println("location.href=history.back()");
			out.println("</script>");
		
			
			
		
		out.close();
		return null;

		
		
	}

}
