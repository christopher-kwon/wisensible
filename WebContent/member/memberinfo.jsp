<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
<jsp:include page="../board/header.jsp" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="css/info.css" type="text/css" rel="stylesheet">
<script>
</script>
</head>
<body>
	<div class="container2">
		<h1>회원 정보</h1>
		<table class="table table-bordered">
			<tr>
				<td>이름</td>
				<td>${member_info.member_name}</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${member_info.member_id}</td>
				<%-- Member클래스의getId() 메소드 호출 --%>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${member_info.member_password}</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member_info.member_birth}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${member_info.member_gender}</td>
			</tr>
			<tr>
				<td>이메일 주소</td>
				<td>${member_info.member_email}</td>
			</tr>
			<tr>
				<td>휴대전화 번호</td>
				<td>${member_info.member_tel}</td>
			</tr>
			<tr>
				<td>계좌</td>
				<td>${member_info.member_bank} ${member_info.member_account}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member_info.member_address}</td>
			</tr>
			<tr>
				<td>흥미있는 카테고리</td>
				<td>${member_info.member_interest}</td>
			</tr>

		</table>
		<div class="clearfix">
			<a href="memberDelete.com?id=${member_info.member_id}">
				<button type="reset" class="cancelbtn">삭제</button>
			</a>
			<a href="memberUpdate.com?id=${member_info.member_id}">
				<button type="submit" class="submitbtn">정보 수정</button>
			</a>
		</div>
	</div>

</body>
<jsp:include page="/board/footer.jsp" />
</html>