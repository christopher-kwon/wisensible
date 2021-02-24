package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Action;
import com.ActionForward;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberUpdateAction implements Action {
//2021.2.24
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("id");

		MemberDAO memberdao = new MemberDAO();
		MemberBean memberbean = memberdao.member_info(member_id);

		forward.setPath("member/memberupdate.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", memberbean);
		return forward;

	}

}
