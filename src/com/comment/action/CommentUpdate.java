package com.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.comment.db.CommentBean;
import com.comment.db.CommentDAO;

public class CommentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentDAO CommentDAO = new CommentDAO();
		CommentBean comment = new CommentBean();
		comment.setComment_content(request.getParameter("comment_content"));
		comment.setComment_num(Integer.parseInt(request.getParameter("comment_num")));

		int rdata = CommentDAO.commentsUpdate(comment);
		response.getWriter().print(rdata);
		return null;
	}

}
