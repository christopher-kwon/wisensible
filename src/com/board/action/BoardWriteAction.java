package com.board.action;

import java.io.IOException;

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
		MemberDAO memberdao = new MemberDAO();
		MemberBean memberbean = memberdao.member_info(id);
		request.setAttribute("memberinfo", memberbean);
		
		ActionForward actionforward = new ActionForward();
		actionforward.setRedirect(false);
		actionforward.setPath("board/BoardWrite.jsp");
		return actionforward;
		
	}

}
