<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

	
	

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

	<jsp:include page="header.jsp"/>
	<script src ="js/writeform.js"></script>


	<div class="container">

		<form action="BoardAddaction.bo" method="post" enctype="multipart/form-data" name=board-form>

			<h1>- Writer -</h1>

 

			<div class="form-group">

				<label for="board_title"> Category : </label> <select

					name="board_category">
					
					<option>----</option>

					<option>과 일</option>

					<option>채 소</option>

					<option>곡 물</option>

					<option>축 산</option>

					<option>해 산</option>

				</select> 
				Subject : <input type="text" name="board_subject" id="board_subject"

					maxlength="30" class="form-control">

			</div>

			<div class="form-group">

				<label for="board_file"></label> <label for="upfile"> <img

					src="../image/mainadd.jpg" width="400px" height="400px">

				</label> <input type="file" id="upfile" name="board_file"

					style="display: none">

			</div>

 

			<div class="form-group">

				<label for="board_ price">가격 : </label> <input name="board_price"

					id="board_price" type="text" maxlength="100" class="form-control"

					placeholder="Enter Price">

			</div>

 

			<div class="form-group">

				<label for="board_account">계좌번호 : </label> 
				 <select name="board_bank">
					
					<option>----</option>

					<option> 신 한 </option>

					<option> 국 민 </option>

					<option> 농 협 </option>

					<option> 하 나 </option>

					<option> 카 카 오 뱅 크 </option>
					
					<option> 케 이 뱅 크 </option>

				</select> 
				
				
				<input

					name="board_account" id="board_account" type="text" maxlength="100"

					class="form-control" placeholder="Enter account">

			</div>

 

			<div class="form-group">

				<label for="board_tel">연락처 : </label> <input name="board_tel"

					id="board_tel" type="text" maxlength="100" class="form-control"

					placeholder="Enter Tel">

			</div>

 

			<div class="form-group">

				<label for="board_storage">보관방법 : </label> <input

					name="board_storage" id="board_storage" type="text" maxlength="100"

					class="form-control" placeholder="Enter storage">

			</div>

 

			<div class="form-group">

				<label for="board_delivery">배송방법 :</label> <input

					name="board_delivery" id="board_delivery" type="text"

					maxlength="100" class="form-control" placeholder="Enter delivery">

			</div>

 

			<h2>Content</h2>

			<fieldset>

				<div>

					<table border=1>
					<caption>상품 상세정보</caption>

						<tr>

							<th>상 품 명</th>

							<td><input type="text"></td>

						</tr>

						<tr>

							<th>수 량</th>

							<td><input type="text"></td>

						</tr>

						<tr>

							<th>생 산 자</th>

							<td><input type="text"></td>

						</tr>

						<tr>

							<th>제 조 년</th>

							<td><input type="text"></td>

						</tr>

						<tr>

							<th>원 산 지</th>

							<td><input type="text"></td>

						</tr>

 

						<tr>

							<th>보 관 방 법</th>

							<td><input type="text"></td>

						</tr>

 

						<tr>

							<th>배 송 비 용</th>

							<td><input type="text"></td>

						</tr>

 

 

 

					</table>

				</div>

 

 

				<div class="form-group">

 

					<textarea></textarea>

 

 

 

 

 

 

 

 

 

				</div>

				<div class="form-group">

					<label for="board_file">파일첨부</label> <label for="upfile"> 

					</label> <input type="file" id="upfile" name="board_file"> <span

						id="filevalue"></span>

				</div>

 

 

 

 

			</fieldset>

			<div class="form-group">

				<button type=submit class="btn btn-primary">등록</button>

				<button type=reset class="btn btn-danger">취소</button>

			</div>

 

		</form>

 

	</div>
	
		<jsp:include page="footer.jsp"/>

