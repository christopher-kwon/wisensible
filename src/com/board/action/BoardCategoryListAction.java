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

public class BoardCategoryListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();
        List<BoardBean> boardCategoryList = new ArrayList<BoardBean>();

        String categoryValue = request.getParameter("category");

        boardCategoryList = boardDAO.getBoardCategoryList(categoryValue);

        request.setAttribute("boardList", boardCategoryList);

        ActionForward actionForward = new ActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/board/categoryList.jsp");
        return actionForward;
    }

}
