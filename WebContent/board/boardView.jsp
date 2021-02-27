<meta name="viewport" content="width=device-width, initial-scale=1">



<%@ include file="header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>







<link href="css/board/boardView.css" rel="stylesheet">
<script src="js/board/boardView.js"></script>


<div class="container">
<input type="hidden" id="loginid" value="${id}" name="loginid">


<!-- table -->
<div class="board_view_table">
  <h2>${boardBean.board_subject }</h2>
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
<td><strong>평점</strong> 
<input type="hidden" id = "board_writer" value="${boardBean.board_name}">
<input type="hidden" id= "loginsession" value="${id}">
<input type="hidden" value="${boardBean.board_num}">
	<div class="star_result">
	<label for="1">★</label><label for="2">★</label><label for="3">★</label><label for="4">★</label>
	<label for="5">★</label>
	<p id="res" style="text-align:;"></p>
	</div>	<hr>

	<div class="star_box">
		<a id="1">★</a> <a id="1">★</a> <a id="1">★</a> <a id="1">★</a>
		<a id="1">★</a>

	
	<p id="lev" style="text-align:;"></p>

<div class="register_box2">

	<button type=submit class="btn btn-info">평점등록</button>

	<button type=reset class="btn btn-warning">초기화</button>

</div>
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

<div class="board_content_view">
<div class="board_content">${boardBean.board_content } </div>
<div class="board_view_image1">
  <img src="boardupload/${boardBean.board_file1 }" class="board_file1" alt="상품이미지1" width="795" height="500"> 
</div><br>

<div class="board_view_image2">
  <img src="boardupload/${boardBean.board_file2 }" class="board_file2" alt="상품이미지2" width="795" height="500"> 
</div><br>

<div class="board_view_image3">
  <img src="boardupload/${boardBean.board_file3 }" class="board_file3" alt="상품이미지3" width="795" height="500"> 
</div><br>

<div class="board_view_image4">
  <img src="boardupload/${boardBean.board_file4 }" class="board_file4" alt="상품이미지4" width="795" height="500"> 
</div><br>
</div>
<input type="hidden" name="board_num" value="${boardBean.board_num }" id="board_num">

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
  <button type="button" class="btn btn-primary" onclick="location.href='BoardModifyView.bo?board_num=${boardBean.board_num}'" id="Modify">Modify</button>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="Delete">Delete</button>
  <button type="button" class="btn btn-primary" onclick="location.href='BoardList.bo'">List</button>

   </c:when>
   
   <c:otherwise>
  <button type="button" class="btn btn-primary" onclick="location.href='BoardModifyView.bo?board_num=${boardBean.board_num}'" id="Modify">Modify</button>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="Delete">Delete</button>
  <button type="button" class="btn btn-primary" onclick="location.href='BoardList.bo'">List</button>

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
<input type="hidden" name="board_num" value="${boardBean.board_num }" id="board_num">

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

