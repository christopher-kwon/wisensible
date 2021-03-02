package com.auction.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.Action;
import com.ActionForward;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
	
		response.setContentType("text/html; charset=utf-8"); 

		if(id == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 사용 가능합니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			MemberDAO memberdao = new MemberDAO();
			int auction = memberdao.auction(id);
			if(auction == -1) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이메일 인증을 하셔야 사용 가능합니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			MemberBean memberbean = memberdao.member_info(id);
			request.setAttribute("memberinfo", memberbean);
			ActionForward actionforward = new ActionForward();
			actionforward.setRedirect(false);
			actionforward.setPath("auction/BoardWrite.jsp");
			return actionforward;
		}
		
		}
		return null;
		
		
	}

}
