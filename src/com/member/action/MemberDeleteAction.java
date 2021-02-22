package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;

public class MemberDeleteAction implements Action {
//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		String member_id = request.getParameter("member_id");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		int result = memberdao.delete(member_id);
		if (result == 1) {
			out.println("<script>");
			out.println("alert('���� �����Դϴ�.');");
			out.println("location.href='BoardCategoryListAction.bo'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('ȸ�� ���� �����Դϴ�.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}
}
