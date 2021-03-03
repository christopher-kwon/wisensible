package com.evaluation.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.evaluation.db.EvaluationBean;
import com.evaluation.db.EvaluationDAO;

public class EvaluationAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EvaluationBean evaluationbean = new EvaluationBean();
		
		String evaluation_name =  request.getParameter("evaluation_name");
		int evaluation =Integer.parseInt(request.getParameter("evaluation"));
		int board_num =  Integer.parseInt(request.getParameter("board_num"));
		

		
		evaluationbean.setEvalueation_name(evaluation_name);
		evaluationbean.setEvaluation(evaluation);
		evaluationbean.setBoard_num(board_num);
		
		
		
		
		
		EvaluationDAO edao = new EvaluationDAO();
		int idcheck = edao.idCheck(evaluationbean);
		
		
			
		if(idcheck==1) {
			
			idcheck = edao.insert(evaluationbean);
		
			System.out.println("idcheck = " + idcheck +"평점등록완료");
			
			
			
			
		}
		response.getWriter().print(idcheck);

		return null;

		
		
	}

}
