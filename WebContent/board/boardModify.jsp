<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script src="js/board/boardModify.js"></script>
<link href="css/board/boardModify.css" rel="stylesheet">

<div class="container">

	<form action="BoardModifyAction.bo" method="post"
		enctype="multipart/form-data" name=board-form>

		<h2>판매 글 수정</h2>


<hr>
		<div class="form-group">
			<label for="board_title"> Category : </label> <select
				name="board_category" id="board_category" disabled>
				<option>${boardBean.board_category }</option>
				<option value="과일">과일</option>
				<option value="채소">채소</option>
				<option value="곡물">곡물</option>
				<option value="축산물">축산물</option>
				<option value="해산물">해산물</option>
			</select> 
			
			<label for="board_subject">&nbsp; 제 &nbsp;&nbsp;목&nbsp; :&emsp;</label>
			 <input type="text" name="board_subject"  value="${boardBean.board_subject }"
				id="board_subject" maxlength="30" class="form-input">
<input name="board_num" type="text" value="${boardBean.board_num}"
					style="display:none">
		</div>
		<hr>
			

				<label for="board_file"></label> 
				<label for="board_thumbnail"> 
				<img id="thumbnailView" src="boardupload/${boardBean.board_thumbnail }" width="400px" height="400px">

				</label> <input type="file" id="board_thumbnail" name="board_thumbnail"
					style="display: none">

		


<div class="form-info">
			<div class="form-group">
			
				<label for="board_ price">가&emsp; &nbsp;&emsp;격 &emsp;:&emsp; </label> <input name="board_price"
					id="board_price" type="text" maxlength="100" class="form-input"
					value="${boardBean.board_price }">

			</div>



			<div class="form-group">

				<label for="board_account"> 은 행 정 보&emsp;:&emsp; </label> <select name="board_bank" id="board_bank">

					<option>${boardBean.board_bank }</option>

					<option value="신 한">신한</option>

					<option value="국 민">국민</option>

					<option value="농 협">농협</option>

					<option value="하 나">하나</option>

					<option value="카 카 오 뱅 크">카카오뱅크</option>

					<option value="케 이 뱅 크">케이뱅크</option>

				</select> <br>
				계 좌 번 호&emsp;:&emsp; <input name="board_account" id="board_account" type="text"
					maxlength="100" class="form-input" value="${boardBean.board_account }">

			</div>



			<div class="form-group">

				<label for="board_tel">연&emsp;락&emsp;처&ensp; :&emsp; </label> <input name="board_tel"
					id="board_tel" type="text" maxlength="100" class="form-input"
					value="${boardBean.board_tel }">

			</div>



			<div class="form-group">

				<label for="board_storage">결 제 방 법 &emsp;:&emsp;&emsp;계 좌 이 체 </label> 

			</div>



			<div class="form-group">

				<label for="board_delivery">배 송 방 법&emsp; :&emsp;</label>      
				<input name="board_delivery" id="board_delivery" type="checkbox" value="택배">택배
               <input name="board_delivery" id="board_delivery" type="checkbox" value="직거래">직거래
              <input name="board_delivery" id="board_delivery" type="checkbox" value="퀵서비스">퀵서비스


			</div>
		</div>
		<div>사진을 수정하려면 사진을 클릭하세요. <span id="board_thumbnailname">${boardBea.board_thumbnail }</span></div>
<hr>

		

		<h4>상품 상세정보</h4>
			<div>
				<table class="table table-borderd">
					<tr>
						<th>상 품 명</th>
						<td><input type="text" name="board_product" id="board_product" required value="${boardBean.board_product }"></td>
					</tr>
					<tr>
						<th>수 량</th>
						<td><input type="text" name="board_amount" id="board_amount" value="${boardBean.board_amount }" required></td>
					</tr>
					<tr>
						<th>생 산 자</th>
						<td><input type="text" name="board_producer" id="board_producer" value="${boardBean.board_producer }" required></td>
					</tr>
					<tr>
						<th>유 통 기 한</th>
						<td><input type="text" name="board_expirydate" id="board_expirydate" value="${boardBean.board_expirydate }" required></td>
					</tr>
					<tr>
						<th>원 산 지</th>
						<td><input type="text" name="board_origin" id="board_origin" value="${boardBean.board_origin }" required></td>
					</tr>
					<tr>
						<th>보 관 방 법</th>
						<td><input name="board_storage" id="board_storage" type="checkbox" value="상온보관">상온 보관
               <input name="board_storage" id="board_storage" type="checkbox" value="냉장 보관">냉장 보관
              <input name="board_storage" id="board_storage" type="checkbox" value="냉동 보관">냉동 보관</td>
					</tr>
					<tr>
						<th>배 송 비 용</th>
						<td><input type="text" name="board_deliverycost" id="board_deliverycost"  value="${boardBean.board_deliverycost }" required></td>
					</tr>
				</table>
			</div>
			<hr>
			<h2>Content </h2>
			<div class="form-group">
				<textarea class="form-control" name="board_content" id="board_content">${boardBean.board_content }</textarea>


			</div>
			
							<label for="board_file1"></label> 
				<label for="board_file1"> 
				<input type="file" id="board_file1" name="board_file1"
					style="display: none">
				<img id="board_fileView1" src="boardupload/${boardBean.board_file1 }" width="795px" height="500px">
				</label>  
				<br> 사진을 수정하려면 사진을 클릭하세요. 
				<span id="board_file1Name1">${boardBea.board_file1 }</span><br>

							<label for="board_file2"></label> 
				<label for="board_file2"> 
				<input type="file" id="board_file2" name="board_file2"
					style="display: none">
				<img id="board_fileView2" src="boardupload/${boardBean.board_file2 }" width="795px" height="500px">
				</label>  
				<br> 사진을 수정하려면 사진을 클릭하세요. 
				<span id="board_file1Name2">${boardBea.board_file2 }</span><br>
				
											<label for="board_file3"></label> 
				<label for="board_file3"> 
				<input type="file" id="board_file3" name="board_file3"
					style="display: none">
				<img id="board_fileView3" src="boardupload/${boardBean.board_file3 }" width="795px" height="500px">
				</label>  
				<br> 사진을 수정하려면 사진을 클릭하세요. 
				<span id="board_file1Name3">${boardBea.board_file3 }</span><br>
				
											<label for="board_file4"></label> 
				<label for="board_file4"> 
				<input type="file" id="board_file4" name="board_file4"
					style="display: none">
				<img id="board_fileView4" src="boardupload/${boardBean.board_file4 }" width="795px" height="500px">
				</label>  
				<br> 사진을 수정하려면 사진을 클릭하세요.
				<span id="board_file1Name4">${boardBea.board_file4 }</span> <br>
			
			<div class="form-group">
				<label for="board_pass">Password : </label> <input name="board_pass"
					id="board_pass" type="text" maxlength="100" class="form-input"
					placeholder="Enter passward">

			</div>


		<div class="form-group">

			<button type=submit class="btn btn-info">수정</button>
			<button type=reset class="btn btn-danger" onClick="history.go(-1)">취소</button>

		</div>



	</form>



</div>

<jsp:include page="footer.jsp" />

