
package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;


@WebServlet("*.com")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		/*
		 * 요청된 전체 URL 중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
		 * 예) http://localhost:8088/Jsp/login.com인 경우
		 * "/Jsp/login.com" 반환됩니다.
		 */
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		//getContextPath() : 컨텍스트 경로가 반환됩니다.
		//contextPath()는 "/Jsp"가 반환됩니다. 
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		//마지막 위치 문자까지 추출합니다.
		//command는 "/login.com" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		//초기화
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		case "/join.com" :
			action = new MemberJoinAction();
			break;
			
		case "/idcheck.com" :
			action = new MemberIdCheckAction();
			break;
			
		case "/login.com" :
			action = new MemberLoginAction();
			break;
			
		case "/joinProcess.com" :
			action = new MemberJoinProcessAction();
			break;
			
		case "/loginProcess.com" :
			action = new MemberLoginProcessAction();
			break;
			
		case "/logout.com" :
			action = new MemberLogoutAction();
			break;
			
		case "/memberUpdate.com" :
			action = new MemberUpdateAction();
			break;
			
		case "/updateProcess.com" :
			action = new MemberUpdateProcessAction();
			break;
			
		case "/memberList.com" :
			action = new MemberSearchAction();
			break;
			
		case "/memberInfo.com" :
			action = new MemberInfoAction();
			break;
			
		case "/memberDelete.com" :
			action = new MemberDeleteAction();
			break;
			
		} //switch end
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { //리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { //포워딩 됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
       

    // doProcess(request, response) 메서드를 구현하여 요청이 GET방식이든
    //POST 방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다. 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
