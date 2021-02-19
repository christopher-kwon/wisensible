<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
<jsp:include page="/board/header.jsp"/>
<link href="css/join.css" type="text/css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
.col-lg-3{display: none}
</style>
<script>
$(function(){
	var checkid=false;
	var checkemail=false;
	var checkpass=false;
	
	$('form').submit(function(){
		
		if(!checkid){
			alert("사용 가능한 id로 입력하세요");
			$("input:eq(1)").val('').focus();
			return false;
		}
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(6)").focus();
			return false;
		}
	});//submit
	
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

	$("input:eq(1)").on('keyup', function(){
		checkid=true;
		$("#message").empty();//처음에 pattern에 적합하지 않은 경우 메세지 출력 후 적합한 데이터를 작성하면 사라짐
		//[A-Za-z0-9_]의 의미가\w이다.
		var pattern = /^\w{5,12}$/;
		var id = $("input:eq(1)").val();
		if(!pattern.test(id)){
			$("#message").css('color', 'red').html("영문자 숫자_로 5~12자 가능합니다.");
			checkid=false;
			return;
		}
		
		$.ajax({
			url: 'idcheck.com',
			data: {"id" : id},//보낼 데이터
			success: function(resp){
				if(resp==-1){//db에 해당 id가 없는 경우
					$("#message").css('color', 'green').html(
							"사용 가능한 아이디 입니다.");
					checkid=true;
				}else{
					$("#message").css('color', 'blue').html(
						"사용중인 아이디 입니다.");
					checkid=false;
				}
			}
		})
	});//id keyup end
	
	//비밀번호 일치 확인(2021-02-18 최초. 테스트 후 수정 요망)
	$('#passck').blur(function(){
		if($('#pass').val() != $('#passck').val()){
			if($('#passck').val()!=''){
				$("#pass_message").css('color', 'red').html(
					"비밀번호가 일치하지 않습니다.");
				checkpass=false;
			}
		}
	})
	
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
	
	
});
</script>
</head>
<body>
<h1>회원가입</h1>
<form name="joinform" action="joinProcess.com" method="post">
	
	<div class="container">
	  <fieldset>
	  
	   <b>이름</b>
		<input type="text" name="name" placeholder="Enter name" required maxlength="15">
	  
       <b>아이디</b>
		<div>
       		<input type="text" placeholder="Enter id" name="id" id="id">
       		<input type="button" value="ID중복검사	" id="idcheck">
       	</div>
		<span id="message"></span>
		
	   <b>비밀번호</b>
		<input type="password" name="pass" placeholder="Enter password" required>
		
	   <b>비밀번호 확인</b>
       	<input type="password" placeholder="Enter Password Check" 
       		name="passck" onblur="passck()" required>
       	<span id="pass_message"></span>
	
	
	   	<b>생년월일</b>
	   	<span>&nbsp;년&nbsp;</span><span>&nbsp;월&nbsp;</span><span>&nbsp;일&nbsp;</span>
	   	<div id="sel1">
       		<select name="birth1">
       			<%for(int i=2030; i>=1930; i--){ %>
       			<option value="<%=i %>"><%=i %></option>
      			<%} %>
			</select>
       		<select name="birth2" >
       			<%for(int i=1; i<=12; i++){ %>
       			<option value="<%=i %>"><%=i %></option>
       			<%} %>
     		</select>
    		<select name="birth3">
       			<%for(int i=1; i<=31; i++){ %>
       			<option value="<%=i %>"><%=i %></option>
       			<%} %>
     		</select><br>
        </div>
        
        <b>성별</b>
		 <div id="gen">
			<input type="radio" name="gender" value="남" checked><span>남자</span>
			<input type="radio" name="gender" value="여"><span>여자</span>
		 </div><br>
	
	   <label for="email"><b> E-Mail </b></label><br>
        <input type="text" name="email"	id="email">@
        <input type="text" name="domain" id="domain">
        <select name="sel" id="sel" >
        	<option value="">직접입력</option>
        	<option value="naver.com">naver.com</option>
        	<option value="daum.net">daum.net</option>
        	<option value="nate.com">nate.com</option>
        	<option value="gmail.com">gmail.com</option>
        </select>
        
        <b> 휴대전화 번호 </b>
        <input type="text" name="tel1"	id="tel">&nbsp;-&nbsp;
        <input type="text" name="tel2">&nbsp;-&nbsp;
        <input type="text" name="tel3">
        <span id="tel_message"></span><br>
        
        <label for="account"><b>계좌번호</b></label><br>
        <select name="account_name" id="sel">
        	<option value="농협">농협</option>
        	<option value="우리은행">우리은행</option>
        	<option value="국민은행">국민은행</option>
        </select>
        <input type="text" name="account_num"	id="account_num"
        		placeholder="계좌번호 입력"><br>
        
        <label><b>우편번호</b></label><br>
        <input type="text" size="5" maxLength="5" name="post1" id="post1"> 
        <input type="button" value="우편검색" id="postcode">
        
        <label><b>주소</b></label><br>
        <input type="text" size="50" name="address" id="address"> 
        
        <label><b> 흥미있는 카테고리 </b></label><br>
        <div class="container2">
        	<input type="checkbox" name="interest" id="hobby1" value="채소">채소&nbsp;&nbsp;&nbsp;
        	<input type="checkbox" name="interest" id="hobby2" value="곡물">곡물&nbsp;&nbsp;&nbsp;
        	<input type="checkbox" name="interest" id="hobby3" value="과일">과일&nbsp;&nbsp;&nbsp;
        	<input type="checkbox" name="interest" id="hobby4" value="해산물">해산물&nbsp;&nbsp;&nbsp;
        	<input type="checkbox" name="interest" id="hobby5" value="축산물">축산물&nbsp;&nbsp;&nbsp;
        </div>
        <br>
        
        <b>프로필 사진</b>
	<label>
		<img src="/image/attach.png" width="10px">
		<span id="filename">${memberinfo.memberfile}</span>
		<span id="showImage">
			<c:if test='${empty memberinfo.memberfile}'>
				<c:set var='src' value='/image/profile.png'/>
			</c:if>
			<c:if test='${!empty memberinfo.memberfile}'>
				<c:set var='src' value='${"memberupload/"}${memberinfo.memberfile}'/>
			</c:if>
			<img src="${src}" width="20px" alt="profile">
		</span>
		<input type="file" name="memberfile" accept="/image/*">
	</label>
	
	<div class="clearfix">
		<button type="submit" class="submitbtn">수정완료</button>
		<button type="reset" class="cancelbtn">취소</button>
	</div>
      </fieldset>
	</div>
</form>
<jsp:include page="/board/footer.jsp"/>
</body>
</html>