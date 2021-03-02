package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberDAO;

public class MemberFindProcess1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		String member_id = request.getParameter("id");
		String member_email = request.getParameter("email");
		
		
		MemberDAO memberdao = new MemberDAO();
		String result2 = memberdao.isFindId(member_id, member_email);
		System.out.println("�����" + result2);
		
		//Name�� �������
		if(!result2.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ������ ��й�ȣ��  " + result2 + "  �Դϴ�.');");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();			
		}else {
			String message = "ȸ���� �������� �ʽ��ϴ�.";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='memberFind.com';");
			out.println("</script>");
			out.close();
			
		}
		return null;
	}

}
