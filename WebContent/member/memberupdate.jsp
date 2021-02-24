<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원관리 시스템 회원수정 페이지</title>
<link href="css/join.css" type="text/css" rel="stylesheet">
<style>
h3{text-align: center; color: #1a92b9}
input[type=file]{display: none;}
</style>
</head>
<body>
<jsp:include page="/board/header.jsp"/>
<form name="joinform" action="updateProcess.com" method="post"
		enctype="multipart/form-data">
	<h3>회원 정보 수정</h3>
	<hr>
	<b>아이디</b>
	<input type="text" name="id" value="${memberinfo.id}" readOnly>
	
	<b>비밀번호</b>
	<input type="password" name="pass" value="${memberinfo.password}">
	
	<b>이름</b>
	<input type="text" name="name" value="${member_info.member_name}" placeholder="Enter name"
			required>
	
	
	<b>성별</b>
	<div>
		<input type="radio" name="gender" value="남"><span>남자</span>
		<input type="radio" name="gender" value="여"><span>여자</span>
	</div><br>
	
	<b>이메일 주소</b>
	<input type="text" name="email" value="${memberinfo.email}"
			placeholder="Enter email" required>
	<span id="email_message"></span>
	
	<b>프로필 사진</b>
	<label>
		<img src="image/attach.png" width="10px">
		<span id="filename">${memberinfo.memberfile}</span>
		<span id="showImage">
			<c:if test='${empty memberinfo.memberfile}'>
				<c:set var='src' value='image/profile.png'/>
			</c:if>
			<c:if test='${!empty memberinfo.memberfile}'>
				<c:set var='src' value='${"memberupload/"}${memberinfo.memberfile}'/>
			</c:if>
			<img src="${src}" width="20px" alt="profile">
		</span>
		<input type="file" name="memberfile" accept="image/*">
	</label>
	
	<div class="clearfix">
		<button type="submit" class="submitbtn">수정완료</button>
		<button type="reset" class="cancelbtn">취소</button>
	</div>
	
</form>
<script>
//성별 체크
$("input[value='${memberinfo.gender}']").prop('checked', true);

$(".cancelbtn").click(function(){
	history.back();
});

//처음 화면 로드시 보여줄 이메일은 이미 체크 완료된 거이므로 기본 checkemail=true이다.
var checkemail=true;
$("input:eq(6)").on('keyup', function(){
	$("#email_message").empty();
	//[A-Za-z0-9_]의 의미가\w이다.
	//+는 1회 이상 반복을 의미.{1,}와 동일하다.
	//\w+는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미이다.
	var pattern = /^\w+@\w+[.]\w{3}$/;
	var email = $("input:eq(6)").val();
	if(!pattern.test(email)){
		$("#email_message").css('color', 'red').html("이메일 형식이 맞지 않습니다.");
		checkemail=false;
	}else{
		$("#email_message").css('color', 'green').html("이메일 형식에 맞습니다.");
		checkemail=true;
	}
});//email keyup end

$('form').submit(function(){
	if(!$.isNumeric($("input[name='age']").val())){
		alert("나이는 숫자로 입력하세요");
		$("input[name='age']").val('').focus();
		return false;
	}
	
	if(!checkid){
		alert("사용 가능한 id로 입력하세요");
		$("input:eq(0)").val('').focus();
		return false;
	}
	
	if(!checkemail){
		alert("email 형식을 확인하세요");
		$("input:eq(6)").focus();
		return false;
	}
});//submit

$('input[type=file]').change(function(event){
	var inputfile = $(this).val().split('\\');
	var filename = inputfile[inputfile.length - 1];
	var pattern = /(gif|jpg|jpeg|png)$/i;
	if(pattern.test(filename)){
		$("#filename").text(filename);//inputfile.length - 1 = 2
		
		var reader = new FileReader();//파일을 읽기 위한 객체 생성
		//DataURL 형식으로 파일을 읽어온다.
		//읽어온 결과는 reader 객체의 result 속성에 저장된다.
		//event.target.files[0] : 선택한 그림의 파일 객체에서 첫 번째 객체를 가져온다.
		reader.readAsDataURL(event.target.files[0]);
		
		reader.onload = function(event){//읽기에 성공했을 때 실행되는 이벤트 핸들러
			$('#showImage').html('<img width="40px" src="' + event.target.result + '">')
		};
	}else{
		alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
	}
})
</script>
</body>
</html>