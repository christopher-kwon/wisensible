<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>아이디/비밀번호 찾기</title>
<script src="js/jquery-3.5.1.js"></script>
<jsp:include page="/board/header.jsp" />
<link href="css/find.css" type="text/css" rel="stylesheet">
<style>
</style>
<script>
	$(function(){
		
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- 입력폼 그리드 -->
			<div class="col-lg-4">
				<form action="memberFindProcess.com" method="post" name="find">
					<fieldset>
						<legend align="center">아이디 찾기</legend>
						<div class="input-group mt-3 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1"> &nbsp;&nbsp;이&nbsp;&nbsp;름&nbsp;&nbsp; </span>
							</div>
							
							<input type="text" name="name" class="form-control"
								placeholder="이름을 입력하세요">
							
						</div><br>
						<!-- PW입력 -->
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">전화번호</span>
							</div>
							<input type="text" name="tel1"	id="tel" maxlength="3" class="form-control">&nbsp;-&nbsp;
        					<input type="text" name="tel2" maxlength="4" class="form-control">&nbsp;-&nbsp;
        					<input type="text" name="tel3" maxlength="4" class="form-control">
						</div><br>
						<!-- 회원가입 성공, 로그인 실패 등 메세지가 있으면 경고창 출력 -->
				
						<!-- 로그인 버튼 -->
						<button type="submit" class="btn btn-success btn-sm btn-block mybtn">
							찾기</button>
						<button type="button" class="btn btn-danger btn-sm btn-block my-1 mybtn"
							onclick="location.href='login.com'">취소</button>
					</fieldset>
				</form>

				<form>
					<fieldset>
						<legend align="center">비밀번호 찾기</legend>
						<div class="input-group mt-3 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">아이디</span>
							</div>
							<input type="text" name="id" class="form-control"
								placeholder="아이디를 입력하세요">
						</div><br>
						<!-- PW입력 -->
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon1">이메일</span>
							</div>
							<input type="text" name="email" class="form-control"
								placeholder="@포함 입력하세요">
						</div><br>

						<!-- 로그인 버튼 -->
						<button type="submit" class="btn btn-success btn-sm btn-block mybtn">찾기</button>
						<button type="button" class="btn btn-danger btn-sm btn-block my-1 mybtn">취소</button>
					</fieldset>
				</form>
		</div>
	</div>
</div>
</body>
<jsp:include page="/board/footer.jsp" />
</html>