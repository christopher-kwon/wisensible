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
//2021.2.22 finish
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String member_id = request.getParameter("id");
		String member_name = request.getParameter("name");
		String member_password = request.getParameter("pass");
		String birth1 = request.getParameter("birth1");
		String birth2 = request.getParameter("birth2");
		String birth3 = request.getParameter("birth3");
		String member_email = request.getParameter("email");
		String member_gender = request.getParameter("gender");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		String post1 = request.getParameter("post1");
		String address = request.getParameter("address");
		String member_interest = request.getParameter("member_interest");
		String member_account = request.getParameter("account_num");
		String member_bank = request.getParameter("account_name");
		
		MemberBean memberbean = new MemberBean();
		
		memberbean.setMember_id(member_id);
		memberbean.setMember_name(member_name);
		memberbean.setMember_password(member_password);
		memberbean.setMember_birth(birth1 + birth2 + birth3);
		memberbean.setMember_email(member_email);
		memberbean.setMember_gender(member_gender);
		memberbean.setMember_tel("" + tel1 + tel2 + tel3);
		memberbean.setMember_address(post1 + address);
		memberbean.setMember_interest(member_interest);
		memberbean.setMember_account(member_account);
		memberbean.setMember_bank(member_bank);
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		MemberDAO memberdao = new MemberDAO();
		
		int result = memberdao.insert(memberbean);
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