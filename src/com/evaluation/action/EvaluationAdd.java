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
		
		
		
		
		PrintWriter out = response.getWriter();
		EvaluationDAO edao = new EvaluationDAO();
		int idcheck = edao.idCheck(evaluationbean);
		
		if(idcheck == 0) {
			
			System.out.println("idcheck = " + idcheck +"이미평점등록");
			out.println("<script>");
			out.println("alert('이미 평점등록을 완료한 게시물입니다.');");
	
			out.println("</script>");
		
			
		}else {
			
			int ok = edao.insert(evaluationbean);
			response.getWriter().print(ok);
			System.out.println("idcheck = " + idcheck +"평점등록완료");
			out.println("<script>");
			out.println("alert('평점 등록이 완료되었습니다.')");
			out.println("</script>");
			
			
			
		}
		out.close();
		return null;

		
		
	}

}
