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

<div class="input-group" style="margin: 20px;">
    <input type="text" class="form-control" placeholder="검색어를 입력하세요" aria-label="Recipient's username" aria-describedby="button-addon2">
    <button class="btn btn-secondary" type="button" id="button-addon2">검   색</button>
</div>

<div class="row" id="row">
    <%--    <c:if test="${listCount > 0}">--%>
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
    <%--    </c:if>--%>


</div>
<div class="row" id="testAjax"></div>
<div class="d-grid gap-2 col-6 mx-auto">
    <button class="pageInc btn btn-dark btn-lg" style="width: 300px">더보기</button>
</div>

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