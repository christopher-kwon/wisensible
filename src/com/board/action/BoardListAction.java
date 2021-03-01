package com.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Action;
import com.ActionForward;
import com.board.db.BoardBean;
import com.board.db.BoardDAO;

public class BoardListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BoardDAO boardDAO = new BoardDAO();
        List<BoardBean> boardList = new ArrayList<BoardBean>();

        int page = 1;
        int limit = 6;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("�Ѿ�� ������ = " + page);
        if(request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }
        System.out.println("�Ѿ�� limit = " + limit);

        int listCount = boardDAO.getListcount();

        boardList = boardDAO.getBoardList(page, limit);

        String searchWord = request.getParameter("searchWord");
        System.out.println("입력한 검색어 ======== " + searchWord);
        if (searchWord != null) {
            listCount = boardDAO.getListcount(searchWord);
            boardList = boardDAO.getBoardList(page, limit, searchWord);
        }

        int maxPage = (listCount + limit -1) / limit;
        System.out.println("�� ������ �� = " + maxPage);
        int startPage = ((page -1) / limit) * limit + 1;
        System.out.println("���� �������� ������ ���� ������ �� = " + startPage);
        int endPage = startPage + limit - 1;
        System.out.println("���� �������� ������ ������ ������ �� = " + endPage);

        if(endPage > maxPage) {
            endPage = maxPage;
        }

        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);

        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCount", listCount);
        request.setAttribute("boardList", boardList);
        request.setAttribute("limit", limit);

        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/board/mainPage.jsp");

        return actionForward;
    }

}