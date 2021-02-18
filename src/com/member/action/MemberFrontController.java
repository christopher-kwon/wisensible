
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
		 * ��û�� ��ü URL �߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�.
		 * ��) http://localhost:8088/Jsp/login.com�� ���
		 * "/Jsp/login.com" ��ȯ�˴ϴ�.
		 */
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		//getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		//contextPath()�� "/Jsp"�� ��ȯ�˴ϴ�. 
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		//������ ��ġ ���ڱ��� �����մϴ�.
		//command�� "/login.com" ��ȯ�˴ϴ�.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		//�ʱ�ȭ
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
			if(forward.isRedirect()) { //�����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());
			} else { //������ �˴ϴ�.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
       

    // doProcess(request, response) �޼��带 �����Ͽ� ��û�� GET����̵�
    //POST ������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�. 
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
