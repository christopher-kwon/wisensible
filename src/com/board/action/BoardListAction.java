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
        System.out.println("넘어온 페이지 = " + page);
        if(request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }
        System.out.println("넘어온 limit = " + limit);

        int listCount = boardDAO.getListcount();

        boardList = boardDAO.getBoardList(page, limit);

        int maxPage = (listCount + limit -1) / limit;
        System.out.println("총 페이지 수 = " + maxPage);
//      int startPage = ((page -1) / 10) * 10 + 1;
        int startPage = ((page -1) / 10) * 10 + 1;
        System.out.println("현제 페이지에 보여줄 시작 페이지 수 = " + startPage);
//      int endPage = startPage + 10 - 1;
        int endPage = startPage + 10 - 1;
        System.out.println("현제 페이지에 보여줄 마지막 페이지 수 = " + endPage);
        


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