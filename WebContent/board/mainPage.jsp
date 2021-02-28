<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/18
  Time: 9:52 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="BoardSearchAction.bo?searchWord=${searchWord}" method="get" name="searchWord">
    <div class="input-group" style="margin: 20px;">
        <input type="text" name="searchWord" class="form-control" placeholder="아이디 입력하세요" value="${searchWord}">
        <button class="btn btn-secondary" type="submit" id="button-addon2">검   색</button>
    </div>
</form>

<div class="row" id="row">
    <c:forEach var="board" items="${boardList}">
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="BoardDetailAction.bo?board_num=${board.board_num}"><img class="card-img-top"
                                                                                 src="boardupload/${board.board_thumbnail}"
                                                                                 alt="" height="200px"></a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_num}. ${board.board_subject}</a>
                    </h4>
                    <h5>${board.board_price} <strong>원</strong></h5>
                    <p class="card-text">${board.board_content}</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="row" id="testAjax"></div>

<c:if test="${searchWord == null}">
<div class="d-grid gap-2 col-6 mx-auto">
    <button class="pageInc btn btn-dark btn-lg" style="width: 300px">더보기</button>
</div>
</c:if>

<script>
    $(document).ready(function () {
            var page = 1;
            $('.pageInc').click(function () {
                page += 1;
                $.get("/wisensible/BoardListAjax.bo", {"page": page},
                    function (data) {
                        $("#testAjax").append(data);
                    })

            })
        }
    )
</script>

<%@ include file="footer.jsp" %>