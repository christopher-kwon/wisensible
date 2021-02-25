package com.comment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.comment.db.CommentDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CommentList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentDAO commentDAO = new CommentDAO();
		
		int comment_board_ref = Integer.parseInt(request.getParameter("comment_board_ref"));
		System.out.println(comment_board_ref);
		int state = Integer.parseInt(request.getParameter("state"));
		int listCount = commentDAO.getListCount(comment_board_ref);
		System.out.println(listCount + "listcount");
		JsonObject object = new JsonObject();
		JsonArray jarray = commentDAO.getCommentList(comment_board_ref, state);
		object.addProperty("listCount", listCount);
		JsonElement je = new Gson().toJsonTree(jarray);
		object.add("boardList", je);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		return null;
	}

}
