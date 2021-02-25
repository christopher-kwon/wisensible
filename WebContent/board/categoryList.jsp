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

<table class="table table-warning table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Image</th>
        <th scope="col">Subject</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <h1> <%=request.getParameter("category")%></h1>
    <c:forEach var="board" items="${boardList}">
    <tr>
        <th scope="row">1</th>
        <td>
            <a href="BoardDetailAction.bo?board_num=${board.board_num}"><img class="" src="boardupload/${board.board_thumbnail}" alt="" width="70px" height="70px"></a>
        </td>
        <td>
            <h4 class=".col-4"><a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_num}. ${board.board_subject}</a></h4>
        </td>
        <td>
            <h5>${board.board_price}</h5>
        </td>
        <td>
            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>
