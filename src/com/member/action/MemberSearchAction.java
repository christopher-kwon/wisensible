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
		
		int page = 1; //������ page
		int limit = 10; //�� �������� ������ �Խ��� ����� ��
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " + page);
		
		List<MemberBean> list = null;
		int listcount = 0;
		int index = 0;//search_field�� �������� �ʴ� ������ �ʱ�ȭ
		if(request.getParameter("search_field")!= null) {
			index = Integer.parseInt(request.getParameter("search_field"));
		}
		String search_word = "";
		//����-������-ȸ������ Ŭ���� ���(member_list.net)
		//�Ǵ� �޴�-������-ȸ������ ũ�� �� ������ Ŭ���� ���
		//(member_list.net?page=2&search_field=-1&search_word=)
		if(request.getParameter("search_word")==null
				|| request.getParameter("search_word").equals("")) {
			//�� ����Ʈ ���� �޾ƿ´�.
			listcount = mdao.getListCount();
			list = mdao.getList(page, limit);
		}else {//�˻��� Ŭ���� ���
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"member_id", "member_name", "member_email"};
			search_word = request.getParameter("search_word");
			listcount = mdao.getListCount(search_field[index], search_word);
			list = mdao.getList(search_field[index], search_word, page, limit);
		}
		
		int maxpage = (listcount + limit - 1)/limit;
		System.out.println("�� ��������= " + maxpage);
		
		int startpage = ((page-1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("���� �������� ������ ������ ������ �� = " + endpage);
		System.out.println("���� �������� ������ ���� ������ �� = " + startpage);
		
		if(endpage > maxpage) endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if(state==null) {
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		
		//�� �������� ǥ���� ù ������ ��
		request.setAttribute("startpage", startpage);
		
		//�� �������� ǥ���� ù ������ ��
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
			
			//������ request�� ��Ҵ� ���� JsonObject�� ��´�.
			JsonObject object = new JsonObject();
			object.addProperty("page", page);//{"page": ���� page�� ��} �������� ����
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("search_field", index);
			
			/*
			 * JsonObject�� List ������ ���� �� �ִ� addProperty() �������� �ʴ´�.
			 * void com.google.gson.JsonObject.add(String property, JsonElement value)
			 * �޼ҵ带 ���ؼ� �����Ѵ�.
			 * List ������ JsonElement�� �ٲپ� �־�� object�� ������ �� �ִ�.
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
