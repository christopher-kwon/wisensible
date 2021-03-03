package com.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;


public class MemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		int page = 1; //보여줄 page
		int limit = 10; //한 페이지에 보여줄 게시판 목록의 수
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		List<MemberBean> list = null;
		int listcount = 0;
		int index = 0;//search_field에 존재하지 않는 값으로 초기화
		if(request.getParameter("search_field")!= null) {
			index = Integer.parseInt(request.getParameter("search_field"));
		}
		String search_word = "";
		//메유-관리자-회원정보 클릭한 경우(member_list.net)
		//또는 메뉴-관리자-회원정보 크릭 후 페이지 클릭한 경우
		//(member_list.net?page=2&search_field=-1&search_word=)
		if(request.getParameter("search_word")==null
				|| request.getParameter("search_word").equals("")) {
			//총 리스트 수를 받아온다.
			listcount = mdao.getListCount();
			list = mdao.getList(page, limit);
		}else {//검색을 클릭한 경우
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"member_id", "member_name", "member_email"};
			search_word = request.getParameter("search_word");
			listcount = mdao.getListCount(search_field[index], search_word);
			list = mdao.getList(search_field[index], search_word, page, limit);
		}
		
		int maxpage = (listcount + limit - 1)/limit;
		System.out.println("총 페이지수= " + maxpage);
		
		int startpage = ((page-1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		if(endpage > maxpage) endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if(state==null) {
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		
		//한 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		//한 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount);
		request.setAttribute("totallist", list);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		
		forward.setPath("member/memberlist.jsp");
		forward.setRedirect(false);
		return forward;
		}else {
			System.out.println("state=ajax");
			
			//위에서 request로 담았던 것을 JsonObject에 담는다.
			JsonObject object = new JsonObject();
			object.addProperty("page", page);//{"page": 변수 page의 값} 형식으로 저장
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("search_field", index);
			
			/*
			 * JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않는다.
			 * void com.google.gson.JsonObject.add(String property, JsonElement value)
			 * 메소드를 통해서 저장한다.
			 * List 형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있다.
			 * List => JsonElement
			 */
			JsonElement je = new Gson().toJsonTree(list);
			System.out.println("totallist=" + je.toString());
			object.add("totallist", je);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println(object.toString());
			return null;
		}
	}

}
