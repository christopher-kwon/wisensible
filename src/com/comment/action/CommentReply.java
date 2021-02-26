package com.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.comment.db.CommentBean;
import com.comment.db.CommentDAO;

public class CommentReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommentDAO commentDAO = new CommentDAO();
		CommentBean comment = new CommentBean();

		comment.setComment_id(request.getParameter("comment_id"));
		comment.setComment_content(request.getParameter("comment_content"));
		comment.setComment_board_ref(Integer.parseInt(request.getParameter("comment_board_ref")));
		comment.setComment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		comment.setComment_re_ref(Integer.parseInt(request.getParameter("comment_re_ref")));
		comment.setComment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));

		int rdata = commentDAO.commentsReply(comment);

		// response.getWriter().append(Integer.toString(result));
		response.getWriter().print(rdata);
		return null;
	}

}
