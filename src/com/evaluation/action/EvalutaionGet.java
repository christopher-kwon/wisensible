package com.evaluation.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.evaluation.db.EvaluationBean;
import com.evaluation.db.EvaluationDAO;

public class EvalutaionGet implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		EvaluationDAO edao = new EvaluationDAO();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println(board_num);
		
		int avg = edao.getAvg(board_num);
		System.out.println(avg);
		return null;
		
		
		
		
		
	}

}
