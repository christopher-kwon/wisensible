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
		 * �슂泥��맂 �쟾泥� URL 以묒뿉�꽌 �룷�듃 踰덊샇 �떎�쓬 遺��꽣 留덉�留� 臾몄옄�뿴源뚯� 諛섑솚�맗�땲�떎.
		 * �삁) http://localhost:8088/Jsp/login.com�씤 寃쎌슦
		 * "/Jsp/login.com" 諛섑솚�맗�땲�떎.
		 */
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		//getContextPath() : 而⑦뀓�뒪�듃 寃쎈줈媛� 諛섑솚�맗�땲�떎.
		//contextPath()�뒗 "/Jsp"媛� 諛섑솚�맗�땲�떎. 
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		//RequestURI�뿉�꽌 而⑦뀓�뒪�듃 寃쎈줈 湲몄씠 媛믪쓽 �씤�뜳�뒪 �쐞移섏쓽 臾몄옄遺��꽣
		//留덉�留� �쐞移� 臾몄옄源뚯� 異붿텧�빀�땲�떎.
		//command�뒗 "/login.com" 諛섑솚�맗�땲�떎.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		//珥덇린�솕
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
			

		} //switch end
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { //由щ떎�씠�젆�듃 �맗�땲�떎.
				response.sendRedirect(forward.getPath());
			} else { //�룷�썙�뵫 �맗�땲�떎.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
       

    // doProcess(request, response) 硫붿꽌�뱶瑜� 援ы쁽�븯�뿬 �슂泥��씠 GET諛⑹떇�씠�뱺
    //POST 諛⑹떇�쑝濡� �쟾�넚�릺�뼱 �삤�뱺 媛숈� 硫붿꽌�뱶�뿉�꽌 �슂泥��쓣 泥섎━�븷 �닔 �엳�룄濡� �븯���뒿�땲�떎. 
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
