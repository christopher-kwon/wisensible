package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;


public class MemberLoginProcessAction implements Action {
	//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		String member_id = request.getParameter("member_id");
		String member_password = request.getParameter("member_password");

		MemberDAO memberdao = new MemberDAO();
		int result = memberdao.isId(member_id, member_password);
		System.out.println("결과는" + result);

		// 로그인 성공
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("member_id", member_id);

			forward.setRedirect(true);
			forward.setPath("BoardList.bo");
			//forward.setPath("BoardWrite.bo");
			return forward;
		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			if (result == -1)
				message = "아이디가 존재하지 않습니다.";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');'");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
