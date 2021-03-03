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

public class MemberDeleteAction implements Action {
//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		String[] members = request.getParameterValues("member_id");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		//member_id=A1234&member_id=B1234&member_id=Q1234
		// 'A1234', 'B1234','Q1234'
		String member_id = "";
		for(String member:members) {
			member_id += "'" + member + "',"  ;
		}
		if(member_id.length() > 0){
			member_id = member_id.substring(0, member_id.length() - 1);	
    	}
		System.out.print(member_id);
		int result = memberdao.delete(member_id);
		out.print(result);
		
		if(session != null) 
			session.invalidate();
		
		return null;
	}
}
