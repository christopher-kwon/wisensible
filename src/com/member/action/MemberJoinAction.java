package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

public class MemberJoinAction implements Action {
//2021.02.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기는 join");

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);// 주소변경없이 jsp페이지의 내용을 보여준다.
		forward.setPath("member/joinform.jsp");
		return forward;
	}

}
