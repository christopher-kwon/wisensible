package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;

public class MemberCheckEmailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String code = request.getParameter("code");
		System.out.println(code);
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.isHash(code);
		
		if (result == 1) {
			int checkResult = memberDAO.checkedUp(code);
			System.out.println(checkResult);
			if(checkResult == 1) {
			String message = "이메일 인증이 완료되었습니다. 지금부터 경매시스템을 이용할 수 있습니다.";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();
			forward.setRedirect(true);
			forward.setPath("index.jsp");
			return forward;
			} else {
				String message = "이메일 인증에 실패하였습니다. 관리자에게 문의하세요11.";
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('" + message + "');");
				out.println("location.href='login.com';");
				out.println("</script>");
				out.close();
				return null;
			}
		} else {
			String message = "이메일 인증에 실패하였습니다. 관리자에게 문의하세요22.";
			if (result == -1)
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}

}
