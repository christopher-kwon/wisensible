package com.hope.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.hope.db.HopeDAO;



public class AuctionhopeGet implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HopeDAO hdao = new HopeDAO();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("board_num="+board_num);
		
		int max = hdao.getMax(board_num);
		System.out.println("max = "+max);
		
		
		
		response.getWriter().append(Integer.toString(max));
		return null;

	}

}
