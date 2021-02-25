<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />
<script src="js/writeform.js"></script>

<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Nanum+Myeongjo&display=swap&subset=korean" rel="stylesheet">
<link href="css/write.css" type="text/css" rel="stylesheet">
<div class="container">

	<form action="BoardAddAction.bo" method="post"
		enctype="multipart/form-data" name=board-form>
<hr>
		<h2>판매 글 작성</h2>
		<hr>



		<div class="form-group">
			<label for="board_title"> Category :&nbsp; </label> <select
				name="board_category">
				<option>----</option>
				<option>과일</option>
				<option>채소</option>
				<option>곡물</option>
				<option>축산물</option>
				<option>해산물</option>
			</select>
			
			
		 <label for="board_subject">&nbsp; 제 &nbsp;&nbsp;목&nbsp; :&emsp; </label><input type="text" name="board_subject"  placeholder="제목을 입력하세요"
				id="board_subject" maxlength="30" class="form-input">
			
		</div>
		<hr>
			
<div class="section_view">
				<label for="board_file"></label> <label for="board_thumbnail"> <img
					src="image/mainadd.jpg" width="400px" height="400px" id="board_thumbnail_view">
						
				</label> <input type="file" id="board_thumbnail" name="board_thumbnail"
					style="display: none">  <span id="board_thumbnail_name"></span>
					

		


<div class="form-info">
				<label for ="board_name"><input name="board_name" type="hidden" value="${id}"
					></label>


			<div class="form-group">
			
				<label for="board_ price">가&emsp; &nbsp;&emsp;격 &emsp;:&emsp; </label> <input name="board_price"
					id="board_price" type="text" maxlength="100" class="form-input"
					placeholder="Enter Price">&nbsp;원

			</div>

<hr>

			<div class="form-group">

				<label for="board_account"> 은 행 정 보&emsp;:&emsp; </label> 
				<input type="hidden" value="${memberinfo.member_bank}">
				<select name="board_bank">

					<option>----</option>

					<option>신한</option>

					<option>국민</option>

					<option>농협</option>

					<option>하나</option>

					<option>카카오뱅크</option>

					<option>케이뱅크</option>

				</select> <br>
				계 좌 번 호&emsp;:&emsp; <input name="board_account" id="board_account" type="text"
					maxlength="100" class="form-input" placeholder="Enter account"value="${memberinfo.member_account}">

			</div>
<hr>


			<div class="form-group">

				<label for="board_tel">연&emsp;락&emsp;처&ensp; :&emsp; </label> <input name="board_tel"
					id="board_tel" type="text" maxlength="100" class="form-input" value="${memberinfo.member_tel}"
					placeholder="Enter Tel">

			</div>
<hr>


			<div class="form-group">

				<label for="board_storage">결 제 방 법 &emsp;:&emsp;&emsp;계 좌 이 체 </label> 

			</div>

<hr>

			<div class="form-group">

				<label for="board_delivery">배 송 방 법&emsp; :&emsp;</label> 
				<input name="board_delivery" id="board_delivery" type="checkbox" value="택배">택배
        	    <input name="board_delivery" id="board_delivery" type="checkbox" value="직거래">직거래
        		<input name="board_delivery" id="board_delivery" type="checkbox" value="퀵서비스">퀵서비스

			</div>
			+ 버튼을 눌러 대표사진을 업로드해주세요.
		</div>
		</div>

<hr>
<div class="section_content">
<div class="section_info">
		<h4>상품 상세정보</h4>
			<div>
				<table class="table table-borderd">
					<tr>
						<th>상 품 명</th>
						<td><input type="text" name="board_product" id="board_product"  placeholder="Enter Product"></td>
					</tr>
					<tr>
						<th>수 량</th>
						<td><input type="text" name= "board_amount"id="board_amount" placeholder="Enter Amount" ></td>
					</tr>
					<tr>
						<th>생 산 자</th>
						<td><input type="text" name="board_producer" id="board_producer" placeholder="Enter Producer" ></td>
					</tr>
					<tr>
						<th>유 통 기 한</th>
						<td><input type="text" name="board_expirydate" id="board_expirydate" placeholder="Enter epirydate" ></td>
					</tr>
					<tr>
						<th>원 산 지</th>
						<td><input type="text" name="board_origin" id="board_origin" placeholder="Enter Origin" ></td>
					</tr>
					<tr>
						<th>보 관 방 법</th>
						<td><input name="board_storage" id="board_storage" type="checkbox" value="상온보관">상온 보관
        	    <input name="board_storage" id="board_storage" type="checkbox" value="냉장 보관">냉장 보관
        		<input name="board_storage" id="board_storage" type="checkbox" value="냉동 보관">냉동 보관</td>
					</tr>
					<tr>
						<th>배 송 비 용</th>
						<td><input type="text" name="board_deliverycost" id="board_deliverycost"  placeholder="Enter 배송비용" >원</td>
					</tr>
				</table>
			</div>
			</div>
			<hr>
			<h2>Content </h2>
			<div class="form-group">
				<textarea class="form-control" id="board_content" name="board_content"></textarea>


			</div>

<div class="form-group">
<label for="board_file"></label><br>
<label for="upfile">
</label>

<input type="file" id="upfile1" name="board_file1">
<span id="filevalue1"></span>
<input type="file" id="upfile2" name="board_file2">
<span id="filevalue2"></span>



</div>
</div>
<div class="form-group">

				<label for="board_pass">Password : </label> <input name="board_passward"
					id="board_passward" type="text" maxlength="100" class="form-input"
					placeholder="Enter passward">

			</div>


		<div class="form-button">

			<button type=submit class="btn btn-primary">등록</button>

			<button type=reset class="btn btn-danger">취소</button>

		</div>



	</form>



</div>

<jsp:include page="footer.jsp" />

