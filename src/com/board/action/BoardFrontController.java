
package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ��û�� ��ü URL �߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�. ��)
		 * http://localhost:8088/Jsp/login.net�� ��� "/Jsp/login.net" ��ȯ�˴ϴ�.
		 */

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		// contextPath()�� "/Jsp"�� ��ȯ�˴ϴ�.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		// ������ ��ġ ���ڱ��� �����մϴ�.
		// command�� "/login.net" ��ȯ�˴ϴ�.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// �ʱ�ȭ
		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/BoardWrite.bo":
			action = new BoardWriteAction();
			break;

		case "/BoardList.bo":
			action = new BoardListAction();
			break;

		case "/BoardAddAction.bo":
			action = new BoardAddAction();
			break;

		case "/BoardCategoryListAction.bo":
			action = new BoardCategoryListAction();
			break;

		case "/BoardSearchAction.bo":
			action = new BoardSearchAction();
			break;

		case "/BoardDeleteAction.bo":
			action = new BoardDeleteAction();
			break;

		case "/BoardModifyView.bo":
			action = new BoardModifyView();
			break;

		case "/BoardModifyAction.bo":
			action = new BoardModifyAction();
			break;

		case "/BoardDetailAction.bo":
			action = new BoardDetailAction();
			break;

		} // switch end

		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) { // �����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());
			} else { // ������ �˴ϴ�.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	// doProcess(request, response) �޼��带 �����Ͽ� ��û�� GET����̵�
	// POST ������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
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
