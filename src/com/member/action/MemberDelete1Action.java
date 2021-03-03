package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;

public class MemberDelete1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		String member_id = request.getParameter("id");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String answer = "관리자(admin) 비밀번호를 입력하세요.";
		String member_password = request.getParameter("pass");
		
		int result = memberdao.delete(member_id);
		if (result == 1) {
			out.println("<script>");
			out.println("confirm('정말 삭제하시겠습니까?');");
			out.println("alert('삭제 성공입니다.');");
			out.println("location.href='memberList.com'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패입니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
