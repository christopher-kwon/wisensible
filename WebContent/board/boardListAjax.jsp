<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/25
  Time: 3:15 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="board" items="${boardList}">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                <img class="card-img-top" src="boardupload/${board.board_thumbnail}" alt="" height="200px">
            </a>
            <div class="card-body">
                <h4 class="card-title">
                    <a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_subject}</a>
                </h4>
                <h5 style="text-align: right">${board.board_price} <strong>원</strong></h5>
                <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbsp${board.board_content}</p>
            </div>
            <div class="card-footer">
                <c:if test="${board.board_evaluation == 0}">
                    <small class="text-muted">평점 : </small>
                </c:if>
                <c:if test="${board.board_evaluation == 1}">
                    <small class="text-muted">평점 : &#9733; &#9734; &#9734; &#9734; &#9734;</small>
                </c:if>
                <c:if test="${board.board_evaluation == 2}">
                    <small class="text-muted">평점 : &#9733; &#9733; &#9734; &#9734; &#9734;</small>
                </c:if>
                <c:if test="${board.board_evaluation == 3}">
                    <small class="text-muted">평점 : &#9733; &#9733; &#9733; &#9734; &#9734;</small>
                </c:if>
                <c:if test="${board.board_evaluation == 4}">
                    <small class="text-muted">평점 : &#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </c:if>
                <c:if test="${board.board_evaluation == 5}">
                    <small class="text-muted">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>