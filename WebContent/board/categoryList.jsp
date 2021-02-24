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
        <th scope="col">image</th>
        <th scope="col">Item Name</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boardList}">
    <tr>
        <th scope="row">1</th>
        <td>
            <a href="#"><img class="" src="http://placehold.it/700x400" alt="" width="70px" height="70px"></a>
        </td>
        <td>
            <h4 class=".col-4"><a href="#">${board.board_name}</a></h4>
        </td>
        <td>
            <h5>$24.99</h5>
        </td>
        <td>
            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>
