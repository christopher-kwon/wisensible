package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����� login");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//�ּҺ������ jsp�������� ������ �����ش�.
		forward.setPath("member/loginform.jsp");
		return forward;
	}

}
