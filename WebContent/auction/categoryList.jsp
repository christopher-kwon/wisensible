<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/19
  Time: 11:59 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

<h1 style="margin: 20px;"><%=request.getParameter("category")%></h1>

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
        <th scope="row">1</th>
        <td>
            <a href="BoardDetailAction.bom?board_num=${board.board_num}"><img class="" src="boardupload/${board.board_thumbnail}" alt="" width="70px" height="70px"></a>
        </td>
        <td>
            <h4 class=".col-4"><a href="BoardDetailAction.bom?board_num=${board.board_num}">${board.board_num}. ${board.board_subject}</a></h4>
        </td>
        <td>
            <h5>${board.board_min_price}</h5>
        </td>
        <td>
            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>
