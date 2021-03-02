package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberFindProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String member_name = request.getParameter("name");
		String member_tel = request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3");
		String member_id = request.getParameter("id");
		String member_email = request.getParameter("email");
		
		
		MemberDAO memberdao = new MemberDAO();
		String result1 = memberdao.isFindName(member_name, member_tel);
		System.out.println("�����" + result1);
		
		//Name�� �������
		if(!result1.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ������ ���̵��  " + result1 + "  �Դϴ�.');");
			out.println("location.href='memberFind.com';");
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
