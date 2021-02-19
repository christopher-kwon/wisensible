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
		if(result ==1) {//삽입이 된 경우
			out.println("alert('회원 가입을 축하합니다.');");
			out.println("location.href='login.com';");
		}else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요.');");
			//새로고침 되어 이전에 입력한 데이터가 나타나지 않는다.
			//out.println("location.href='join.net';");
			out.println("history.back()");//비밀번호를 제외한 다른 데이터는 유지되어 있는다.
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
