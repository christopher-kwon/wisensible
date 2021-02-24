<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/18
  Time: 9:52 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>==============</p>
<p>==============</p>

<div class="row">
    <c:forEach var="board" items="${boardList}">
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="/BoardDetailAction.bo?board_num=${board.board_num}"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="/BoardDetailAction.bo?board_num=${board.board_num}">${board.board_name}</a>
                    </h4>
                    <h5>${board.board_price}</h5>
                    <p class="card-text">${board.board_content}</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script>

</script>

<%@ include file="footer.jsp"%>