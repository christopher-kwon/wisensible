
package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

@WebServlet("*.bom")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/BoardWrite.bom":
			action = new BoardWriteAction();
			break;

		case "/BoardList.bom":
			action = new BoardListAction();
			break;


		case "/BoardAddAction.bom":
			action = new BoardAddAction();
			break;

		case "/BoardCategoryListAction.bom":
			action = new BoardCategoryListAction();
			break;

		case "/BoardSearchAction.bom":
			action = new BoardSearchAction();
			break;

		case "/BoardDeleteAction.bom":
			action = new BoardDeleteAction();
			break;

		case "/BoardModifyView.bom":
			action = new BoardModifyView();
			break;

		case "/BoardModifyAction.bom":
			action = new BoardModifyAction();
			break;

		case "/BoardDetailAction.bom":
			action = new BoardDetailAction();
			break;

		} // switch end

		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath());
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
