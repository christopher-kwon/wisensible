<meta name="viewport" content="width=device-width, initial-scale=1">



<%@ include file="header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="../css/board/boardView.css" rel="stylesheet">
<script src="../js/board/boardView.js"></script>

<div class="container">
<input type="hidden" id="loginid" value="${member_id}" name="loginid">


<!-- table -->
<div class="board_view_table">
  <h2>${boardBean.board_product }</h2>
  <table class="table">

      <tr><td rowspan="6">  <img src="boardupload/${boardBean.board_thumbnail }" class="view_thumbnail" alt="thumbnail" width="304" height="236"> 
      </td>
        <td>가격 ${boardBean.board_price}원 </td>
      </tr>
      <tr>
        <td>계좌번호 ${boardBean.board_account}</td>
      </tr>
      <tr>
        <td>연락처 ${boardBean.board_tel}</td>
      </tr>
      <tr>
        <td>보관방법 ${boardBean.board_storage}</td>
      </tr>
            <tr>
        <td>배송구분 / 배송비 ${boardBean.board_delivery} / ${boardBean.board_deliverycost}</td>
      </tr>
      
                  <tr>
        <td><strong>평점</strong> ${evaluationBean.evaluation_avg} / <div class="rating"> 
			<input type="radio" name="rating" value="5" id="5"><label for="5">☆</label> <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label> <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label> <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label> 
			</div>
        			</td>
      </tr>
  </table>
</div>



<!-- content -->
<div class="board_detail">
 <table class="table table-bordered">
      <tr>
        <td>상품명</td>
        <td>${boardBean.board_product}</td>
      </tr>
      <tr>
        <td>용량/수량</td>
        <td>${boardBean.board_amount}</td>
      </tr>
      <tr>
        <td>생산자</td>
        <td>${boardBean.board_producer}</td>
      </tr>
      <tr>
        <td>유통기한</td>
        <td>${boardBean.board_expirydate}</td>
      </tr>
            <tr>
        <td>원산지</td>
        <td>${boardBean.board_origin}</td>
      </tr>
            <tr>
        <td>보관방법</td>
        <td>${boardBean.board_storage}</td>
      </tr>
            <tr>
        <td>배송방법</td>
        <td>${boardBean.board_delivery}</td>
      </tr>
  </table>
</div>

<div class="board_content">
<div class="board_view_image1">
  <img src="boardupload/${boardBean.board_file1 }" class="board_file1" alt="상품이미지1" width="795" height="500"> 
  <h1>국내산 과일</h1>
  <p>대한민국에서 가장 맛있는 과일입니다.</p>
</div>

<div class="board_view_image2">
  <img src="boardupload/${boardBean.board_file2 }" class="board_file2" alt="상품이미지2" width="795" height="500"> 
  <h1>국내산 채소</h1>
  <p>대한민국에서 가장 맛있는 채소입니다.</p>
</div>

<div class="board_view_image3">
  <img src="boardupload/${boardBean.board_file3 }" class="board_file3" alt="상품이미지3" width="795" height="500"> 
  <h1>국내산 곡물</h1>
  <p>대한민국에서 가장 맛있는 곡물입니다.</p>
</div>

<div class="board_view_image4">
  <img src="boardupload/${boardBean.board_file4 }" class="board_file4" alt="상품이미지4" width="795" height="500"> 
  <h1>국내산 곡물</h1>
  <p>대한민국에서 가장 맛있는 곡물입니다.</p>
</div>
</div>
<input type="hidden" name="board_num" value="${param.num }" id="board_num">

<!-- comment view -->
<div class="CommentBox">
			<div class="comment_option">
				<h3 class="comment_title">
					댓글 <sup id="count"></sup>
				</h3> 

				
				<div class="comment_tab">
					<ul class="comment_tab_list">
					</ul>
				</div>
			</div>
			<!-- comment option end-->
			<ul class="comment_list">
			</ul>
			<div class="CommentWriter">
				<div class="comment_inbox">

					<b class="comment_inbox_name">${id}</b> <span
						class="comment_inbox_count">0/200</span>
					<textarea placeholder="댓글을 남겨보세요" rows="1"
						class="comment_inbox_text" maxLength="200"></textarea>

				</div>
				<div class="register_box">
					<div class="button btn_cancel">취소</div>
					<div class="button btn_register">등록</div>
				</div>
			</div>
			<!--CommentWriter end-->
		</div>
		<!-- CommentBox end-->


<!-- pager -->
<div class="board_pager">
    <div class="btn-group">
  <c:choose>
  <c:when test="${boardBean.board_name == id  || id=='admin'}">
  <button type="button" class="btn btn-primary" onclick="location.href='BoardModifyView.bo?num=${boardBean.board_num}'" id="Modify">Modify</button>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="Delete">Delete</button>
  <button type="button" class="btn btn-primary" onclick="location.href='BoardList.bo'">List</button>

   </c:when>
   
   <c:otherwise>
   
  <button type="button" class="btn btn-primary">Previous</button>
  <button type="button" class="btn btn-primary">List</button>
  <button type="button" class="btn btn-primary">Next</button>
</c:otherwise>
</c:choose>
</div>
</div>

	<%--modal --%>
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<%--Modal Body --%>
					<div class="modal-body">
						<form name="deleteForm" action="BoardDeleteAction.bo"
							method="post">

							<div class="form-group">
								<label for="pwd">비밀번호</label> <input type="password"
									class="form-control" placeholder="Enter password"
									name="board_pass" id="board_pass">

							</div>
							<button type="submit" class="btn btn-primary">전송</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>

						</form>
					</div>
				</div>
			</div>
		</div>





</div>


<%@ include file="footer.jsp"%>

