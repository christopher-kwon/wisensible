<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />
<script src="js/writeform.js"></script>
<link href="css/write.css" type="text/css" rel="stylesheet">

<div class="container">

	<form action="BoardAddAction.bo" method="post"
		enctype="multipart/form-data" name=board-form>

		<h2>판매 글 작성</h2>


<hr>
		<div class="form-group">
			<label for="board_title"> Category : </label> <select
				name="board_category">
				<option>----</option>
				<option>과 일</option>
				<option>채 소</option>
				<option>곡 물</option>
				<option>축 산 물</option>
				<option>해 산 물</option>
			</select> &emsp;Subject : <input type="text" name="board_subject"  placeholder="제목을 입력하세요"
				id="board_subject" maxlength="30" class="form-input">

		</div>
		<hr>
			

				<label for="board_file"></label> <label for="board_thumbnail"> <img
					src="image/mainadd.jpg" width="400px" height="400px" id="board_thumbnail_view">

				</label> <input type="file" id="board_thumbnail" name="board_thumbnail"
					style="display: none">  <span id="board_thumbnail_name"></span>

		


<div class="form-info">
				<label for ="board_name"><input name="board_name" type="text" value="${id}"
					style="display:none"></label>


			<div class="form-group">
			
				<label for="board_ price">가&emsp; &nbsp;&emsp;격 &emsp;:&emsp; </label> <input name="board_price"
					id="board_price" type="text" maxlength="100" class="form-input"
					placeholder="Enter Price">&nbsp;원

			</div>

<hr>

			<div class="form-group">

				<label for="board_account"> 은 행 정 보&emsp;:&emsp; </label> <select name="board_bank">

					<option>----</option>

					<option>신 한</option>

					<option>국 민</option>

					<option>농 협</option>

					<option>하 나</option>

					<option>카 카 오 뱅 크</option>

					<option>케 이 뱅 크</option>

				</select> <br>
				계 좌 번 호&emsp;:&emsp; <input name="board_account" id="board_account" type="text"
					maxlength="100" class="form-input" placeholder="Enter account">

			</div>
<hr>


			<div class="form-group">

				<label for="board_tel">연&emsp;락&emsp;처&ensp; :&emsp; </label> <input name="board_tel"
					id="board_tel" type="text" maxlength="100" class="form-input"
					placeholder="Enter Tel">

			</div>
<hr>


			<div class="form-group">

				<label for="board_storage">결 제 방 법 &emsp;:&emsp;&emsp;계 좌 이 체 </label> 

			</div>

<hr>

			<div class="form-group">

				<label for="board_delivery">배 송 방 법&emsp; :&emsp;</label> <input
					name="board_delivery" id="board_delivery" type="text"
					maxlength="100" class="form-input" placeholder="Enter delivery">


			</div>
		</div>
<hr>

		

		<h4>상품 상세정보</h4>
			<div>
				<table class="table table-borderd">
					<tr>
						<th>상 품 명</th>
						<td><input type="text" name="board_product" id="board_product" required placeholder="Enter Product"></td>
					</tr>
					<tr>
						<th>수 량</th>
						<td><input type="text" name= "board_amount"id="board_amount" placeholder="Enter Amount" required></td>
					</tr>
					<tr>
						<th>생 산 자</th>
						<td><input type="text" name="board_producer" id="board_producer" placeholder="Enter Producer" required></td>
					</tr>
					<tr>
						<th>유 통 기 한</th>
						<td><input type="text" name="board_expirydate" id="board_expirydate" placeholder="Enter epirydate" required></td>
					</tr>
					<tr>
						<th>원 산 지</th>
						<td><input type="text" name="board_origin" id="board_origin" placeholder="Enter Origin" required></td>
					</tr>
					<tr>
						<th>보 관 방 법</th>
						<td><input type="text" name="board_storage" id="board_storage"  placeholder="Enter Storage" required></td>
					</tr>
					<tr>
						<th>배 송 비 용</th>
						<td><input type="text" name="board_deliverycost" id="board_deliverycost"  placeholder="Enter 배송비용" required></td>
					</tr>
				</table>
			</div>
			<hr>
			<h2>Content </h2>
			<div class="form-group">
				<textarea class="form-control" id="board_content" name="board_content"></textarea>


			</div>

<div class="form-group">
<label for="board_file">파일첨부</label><br>
<label for="upfile">
</label>
<input multiple="multiple" type="file" id="upfile1" name="board_file1">
<span id="filevalue1"></span><br>
<input type="file" id="upfile2" name="board_file2">
<span id="filevalue2"></span><br>
<input type="file" id="upfile3" name="board_file3">
<span id="filevalue3"></span><br>
<input type="file" id="upfile4" name="board_file4">
<span id="filevalue4"></span><br>
</div>


<div class="form-group">

				<label for="board_pass">Password : </label> <input name="board_passward"
					id="board_passward" type="text" maxlength="100" class="form-input"
					placeholder="Enter passward">

			</div>


		<div class="form-group">

			<button type=submit class="btn btn-primary">등록</button>

			<button type=reset class="btn btn-danger">취소</button>

		</div>



	</form>



</div>

<jsp:include page="footer.jsp" />

