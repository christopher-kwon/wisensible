<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
<jsp:include page="/board/header.jsp"/>
<link href="css/info.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container2">
	<h1>회원 정보</h1>
	<table class="table table-bordered">
		<tr>
			<td>이름</td>
			<td>${memberinfo.member_name}</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${memberinfo.member_id}</td><%-- Member클래스의getId() 메소드 호출 --%>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${memberinfo.member_password}</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>${memberinfo.member_birth}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${memberinfo.member_gender}</td>
		</tr>
		<tr>
			<td>이메일 주소</td>
			<td>${memberinfo.member_email}</td>
		</tr>
		<tr>
			<td>휴대전화 번호</td>
			<td>${memberinfo.member_tel}</td>
		</tr>
		<tr>
			<td>계좌</td>
			<td>${memberinfo.member_bank}${memberinfo.member_account}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${memberinfo.member_address}</td>
		</tr>
		<tr>
			<td>흥미있는 카테고리</td>
			<td>${memberinfo.member_interest}</td>
		</tr>
		<tr>
			<td colspan=2>
			<a href="BoardCategoryListAction.bo">메인으로 돌아가기</a>
			</td>
		</tr>
	</table>
</div>

</body>
<jsp:include page="/board/footer.jsp"/>
</html>