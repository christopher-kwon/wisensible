<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/19
  Time: 11:59 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<h1 style="margin: 20px;">${category}</h1>

<table class="table table-hover table-borderless">
    <thead class="table-dark">
    <tr scope="row">
        <th>#</th>
        <th>Image</th>
        <th>Subject</th>
        <th>Price</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boardList}">
    <tr>
        <th scope="row">${board.board_num}</th>
        <td>
            <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                <img class="" src="boardupload/${board.board_thumbnail}" alt="" width="70px" height="70px">
            </a>
        </td>
        <td>
            <h4 class=".col-4"><a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_subject}</a></h4>
        </td>
        <td>
            <h5><fmt:formatNumber value="${board.board_price}" pattern="###,###,###"/></h5>
        </td>
        <td>
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
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>


<div>
    <ul class="pagination justify-content-center">
        <c:if test="${page <= 1}">
            <li class="page-item">
                <a class="page-link current" href="#">이전&nbsp;</a>
            </li>
        </c:if>
        <c:if test="${page > 1}">
            <li class="page-item">
                <a href="BoardCategoryListAction.bo?category=${category}&page=${page-1}"
                   class="page-link">이전</a>&nbsp;
            </li>
        </c:if>

        <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <c:if test="${a == page}">
                <li class="page-item">
                    <a href="BoardCategoryListAction.bo?category=${category}&page=${page-1}"
                       class="page-link">${a}</a>
                </li>
            </c:if>
            <c:if test="${a != page}">
                <li class="page-item">
                    <a href="BoardCategoryListAction.bo?category=${category}&page=${a}"
                       class="page-link">${a}</a>
                </li>
            </c:if>
        </c:forEach>

        <c:if test="${page >= maxpage}">
            <li class="page-item">
                <a class="page-link current" href="#">&nbsp;다음</a>
            </li>
        </c:if>
        <c:if test="${page < maxpage}">
            <li class="page-item">
                <a href="BoardCategoryListAction.bo?category=${category}&page=${page+1}"
                   class="page-link">&nbsp;다음</a>
            </li>
        </c:if>
    </ul>
</div>

<jsp:include page="footer.jsp"/>
