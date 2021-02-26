package com.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.comment.db.CommentDAO;

public class CommentDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentDAO commentDAO = new CommentDAO();

		
		int rdata = commentDAO.commentsDelete(Integer.parseInt(request.getParameter("comment_num")));
		System.out.println(rdata);
		response.getWriter().print(rdata);
		return null;
	}

}
