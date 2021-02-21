<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<Style>
.form-input {
	display: inline;
	width: 50%;
	height: calc(1.5em + 0.75rem + 2px);
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: 0.25rem;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.form-info {
	float: right;
	font-weight:bold;
	padding-top:15px
}
.form-info input{
width: 60%
}
.form-info select{
width: 60%;
text-align-last:center;
text-align: center;
-ms-text-align-last:center;
-moz-text-align-last:center;
}
body > div > div > div.col-lg-9 > div.container > form > div:nth-child(3) > select{
width: 10%;
text-align-last:center;
text-align: center;
-ms-text-align-last:center;
-moz-text-align-last:center;
}

img{
float:left;
padding:20px 0 10px 0

}
#board_subject{
width: 68%;

}

h2{
text-align:center;
padding:15px 0 15px 0
}

h4{
text-align:center;
padding:10px 0 30px 0
}

table {
text-align:center;
}
table input{
width:70%;


}

th, td{
border:1px dashed #ccc;
padding:10px;
}

</Style>




<jsp:include page="header.jsp" />
<script src="js/writeform.js"></script>


<div class="container">

	<form action="BoardAddaction.bo" method="post"
		enctype="multipart/form-data" name=board-form>

		<h2>판매 글 수정</h2>


<hr>
		<div class="form-group">
			<label for="board_title"> Category : </label> <select
				name="board_category">
				<option>----</option>
				<option>과 일</option>
				<option>채 소</option>
				<option>곡 물</option>
				<option>축 산</option>
				<option>해 산</option>
			</select> &emsp;Subject : <input type="text" name="board_subject"  placeholder="제목을 입력하세요"
				id="board_subject" maxlength="30" class="form-input">

		</div>
		<hr>
			

				<label for="board_file"></label> <label for="main_file"> <img
					src="image/mainadd.jpg" width="300px" height="300px">

				</label> <input type="file" id="main_file" name="board_file"
					style="display: none">

		


<div class="form-info">
			<div class="form-group">
			
				<label for="board_ price">가&emsp; &nbsp;&emsp;격 &emsp;:&emsp; </label> <input name="board_price"
					id="board_price" type="text" maxlength="100" class="form-input"
					placeholder="Enter Price">

			</div>



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



			<div class="form-group">

				<label for="board_tel">연&emsp;락&emsp;처&ensp; :&emsp; </label> <input name="board_tel"
					id="board_tel" type="text" maxlength="100" class="form-input"
					placeholder="Enter Tel">

			</div>



			<div class="form-group">

				<label for="board_storage">결 제 방 법 &emsp;:&emsp;&emsp;계 좌 이 체 </label> 

			</div>



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
						<td><input type="text" id="board_product" required placeholder="Enter Product"></td>
					</tr>
					<tr>
						<th>수 량</th>
						<td><input type="text" id="board_amount" placeholder="Enter Amount" required></td>
					</tr>
					<tr>
						<th>생 산 자</th>
						<td><input type="text" id="board_producer" placeholder="Enter Producer" required></td>
					</tr>
					<tr>
						<th>유 통 기 한</th>
						<td><input type="text" id="board_epirydate" placeholder="Enter epirydate" required></td>
					</tr>
					<tr>
						<th>원 산 지</th>
						<td><input type="text" id="board_origin" placeholder="Enter Origin" required></td>
					</tr>
					<tr>
						<th>보 관 방 법</th>
						<td><input type="text" id="board_storage"  placeholder="Enter Storage" required></td>
					</tr>
					<tr>
						<th>배 송 비 용</th>
						<td><input type="text" id="board_deliverycost"  placeholder="Enter 배송비용" required></td>
					</tr>
				</table>
			</div>
			<hr>
			<h2>Content </h2>
			<div class="form-group">
				<textarea class="form-control"></textarea>


			</div>

			<div class="form-group">

				<label for="board_file">파일첨부</label> <label for="upfile"> </label> <input
					type="file" multiple id="upfile" name="board_file"> <span
					id="filevalue"></span>

			</div>



		<div class="form-group">

			<button type=submit class="btn btn-info">수정</button>

			<button type=reset class="btn btn-danger">취소</button>

		</div>



	</form>



</div>

<jsp:include page="footer.jsp" />

