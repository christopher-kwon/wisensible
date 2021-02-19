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


public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String passck = request.getParameter("passck");
		int birth = Integer.parseInt(request.getParameter("birth"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		MemberBean m = new MemberBean();
		
		m.setEmail(email);
		m.setGender(gender);
		m.setId(id);
		m.setName(name);
		m.setPassword(password);
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		MemberDAO memberdao = new MemberDAO();
		
		int result = memberdao.insert(m);
		out.println("<script>");
		if(result ==1) {//������ �� ���
			out.println("alert('ȸ�� ������ �����մϴ�.');");
			out.println("location.href='login.com';");
		}else if(result == -1) {
			out.println("alert('���̵� �ߺ��Ǿ����ϴ�. �ٽ� �Է��ϼ���.');");
			//���ΰ�ħ �Ǿ� ������ �Է��� �����Ͱ� ��Ÿ���� �ʴ´�.
			//out.println("location.href='join.net';");
			out.println("history.back()");//��й�ȣ�� ������ �ٸ� �����ʹ� �����Ǿ� �ִ´�.
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
