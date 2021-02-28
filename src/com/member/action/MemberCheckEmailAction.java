package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

public class MemberCheckEmailAction implements Action {
	private EmailSender emailSender;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");
		
		String userEmail1 = "";
		String subject = "";
		String content ="";
		String receiver ="";
		String sender = "";
		
		int authCode = 0;
		String authCodes = "";
		boolean bool = false;
		
		if(email != null && !email.isEmpty() && domain != null && !domain.isEmpty()) {
			userEmail1 = email + "@" + domain;
			for(int i=0; i<6; i++) {
				authCode = (int)(Math.random()*9 + 1); //1~9사이의 난수
				authCodes += Integer.toString(authCode);
			}
			subject = "안녕하세요 Wisensible의 관리자 입니다. 회원가입 인증번호 입니다.";
			content = DM.dmCertification(authCodes);
			receiver = userEmail1;
			sender = "admin@wisensible.com";
		}try {
			emailSender.sendMail(subject, content, receiver, sender);
			System.out.println("이메일 발송 성공");
		}catch(Exception e) {
			System.out.println("이메일 발송 false");
			e.printStackTrace();
		}
		
		return forward;
	}

}
