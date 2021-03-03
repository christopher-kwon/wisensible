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

public class MemberDelete1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		HttpSession session = request.getSession();
		String member_id = request.getParameter("id");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.print(member_id);
		int result = memberdao.delete1(member_id);
		out.print(result);
		
		if(session != null) 
			session.invalidate();
		
		return null;
	}

}
