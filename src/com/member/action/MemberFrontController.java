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
		

		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		

		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

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
			
		case "/memberDelete1.com" :
			action = new MemberDelete1Action();
			break;
			
		case "/memberFind.com" :
			action = new MemberFindAction();
			break;
			
		case "/memberFindProcess.com" :
			action = new MemberFindProcessAction();
			break;
			

		case "/memberFindProcess1.com" :
			action = new MemberFindProcess1Action();
			break;
		
    case "/chkemail.com" :
			action = new MemberCheckEmailAction();
			break;
			
    case "/MemberReport.com" :
			action = new MemberReportAction();
			break;
			
    case "/MemberReportProcessAction.com" :
			action = new MemberReportProcessAction();
			break;
			
	

		} //switch end
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
       

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
