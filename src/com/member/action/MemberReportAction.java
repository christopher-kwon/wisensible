package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

public class MemberReportAction implements Action {
//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����� report");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//�ּҺ������ jsp�������� ������ �����ش�.
		forward.setPath("member/report.jsp");
		return forward;
	}

}