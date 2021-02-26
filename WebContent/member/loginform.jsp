<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>로그인</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script"
	rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link href="css/login.css" type="text/css" rel="stylesheet">
<script>
	$(function() {
		$(".join").click(function() {
			location.href = "join.com";
		});
		var id = '${id}';
		if (id) {
			$("#id").val(id);
			$("remember").prop('checked', true);
		}
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 mx-auto">
				<div id="first">
					<div class="myform form ">
						<div class="logo mb-3">
							<div class="col-md-12 text-center">
								<h1>Login</h1>
							</div>
						</div>
						<form action="loginProcess.com" method="post" name="login">
							<div class="form-group">
								<label for="exampleInputEmail1">아이디</label> <input type="text"
									name="id" class="form-control" id="id"
									aria-describedby="emailHelp" placeholder="Enter ID">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">비밀번호</label> <input
									type="password" name="pass" id="password"
									class="form-control" aria-describedby="emailHelp"
									placeholder="Enter Password">
							</div>
							<br>
							<div class="form-group">
								<%-- join으로 가게끔 수정요망 --%>
								<p class="text-center">
									계정이 없나요? <a href="join.com" id="signup">회원가입 바로가기</a> /
								</p>
								<p class="text-center">
									 <a href="BoardList.bo" id="signup">메인 페이지로 가기</a>
								</p>
							</div>
							<div class="col-md-12 text-center ">
								<button type="submit"
									class=" btn btn-block mybtn btn-primary tx-tfm">로그인</button>
							</div>
							<div class="col-md-12 text-center ">
								<button type="reset"
									class=" btn btn-block mybtn btn-danger tx-tfm">취소</button>
							</div>
							<br> <br>
							<div class="col-md-12 ">
								<div class="login-or">
									<hr class="hr-or">
								</div>
							</div>
							<div class="form-group">
								<%-- join으로 가게끔 수정요망 --%>
								<p class="text-center">
									아이디와 비밀번호를 잊으셨나요?<br>
									<a href="join.com" id="signup">아이디/비밀번호 찾기</a>
								</p>
							</div>
							
						</form>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>