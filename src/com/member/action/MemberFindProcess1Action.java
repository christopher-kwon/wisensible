package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;

public class MemberFindProcess1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		String member_id = request.getParameter("id");
		String member_email = request.getParameter("email");
		
		
		MemberDAO memberdao = new MemberDAO();
		String result2 = memberdao.isFindId(member_id, member_email);
		System.out.println("결과는" + result2);
		
		//Name이 같은경우
		if(!result2.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원님의 비밀번호는  " + result2 + "  입니다.');");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();			
		}else {
			String message = "회원이 존재하지 않습니다.";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='memberFind.com';");
			out.println("</script>");
			out.close();
			
		}
		return null;
	}

}
