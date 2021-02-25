<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원관리 시스템 회원수정 페이지</title>
<jsp:include page="/board/header.jsp"/>
<link href="css/join.css" type="text/css" rel="stylesheet">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
$(function(){

$(".cancelbtn").click(function(){
	history.back();
});


$('form').submit(function(){
	if(!checkid){
		alert("사용 가능한 id로 입력하세요");
		$("input:eq(0)").val('').focus();
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
})
</script>
<style>
h3{text-align: center; color: #1a92b9}
input[type=file]{display: none;}
</style>
</head>
<body>
<form name="joinform" action="updateProcess.com" method="post"
		enctype="multipart/form-data">
	<h3>회원 정보 수정</h3>
	<hr>
	<b>이름</b>
	<input type="text" name="name" value="${member_info.member_name}" readOnly>
			
	<b>아이디</b>
	<input type="text" name="id" value="${member_info.member_id}" readOnly>
	
	<b>비밀번호</b>
	<input type="password" name="pass" value="${member_info.member_password}">
	
	
	<b>생년월일</b>
	<input type="text" name="birth" value="${member_info.member_birth}" readOnly>
	
	<b>성별</b>
	<input type="text" name="gender" value="${member_info.member_gender}" readOnly>
	
	<label for="email"><b> E-Mail </b></label><br>
      <input type="text" name="email"	id="email" value="${member_info.member_email.split('@')[0]}">@
      <input type="text" name="domain" id="domain" value="${member_info.member_email.split('@')[1]}">
      <select name="sel" id="sel" >
       	<option value="">직접입력</option>
       	<option value="naver.com">naver.com</option>
        <option value="daum.net">daum.net</option>
        <option value="nate.com">nate.com</option>
        <option value="gmail.com">gmail.com</option>
      </select>
	
	<b> 휴대전화 번호 </b>
	<input type="text" name="tel1"	id="tel" value="${member_info.member_tel.split('-')[0]}">&nbsp;-&nbsp;
    <input type="text" name="tel2" value="${member_info.member_tel.split('-')[1]}">&nbsp;-&nbsp;
    <input type="text" name="tel3" value="${member_info.member_tel.split('-')[2]}">
        
	<b>계좌번호</b>
	<select name="account_name" id="sel">
        <option value="신한은행">신한은행</option>
        <option value="국민은행">국민은행</option>
        <option value="농협">농협</option>
        <option value="우리은행">우리은행</option>
        <option value="하나은행">하나은행</option>
        <option value="카카오뱅크">카카오뱅크</option>
        <option value="케이뱅크">케이뱅크</option>
    </select>
    <input type="text" name="account_num"	id="account_num" 
        	value="${member_info.member_account}"	placeholder="계좌번호 입력">
        		
	<label><b>우편번호</b></label><br>
    <input type="text" size="5" maxLength="5" name="post1" id="post1"> 
    <input type="button" value="우편검색" id="postcode">
        
    <label><b>주소</b></label><br>
    <input type="text" size="50" name="address" id="address" value="${member_info.member_address}"> 
	
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
		<img src="image/attach.png" width="10px">
		<span id="filename">${member_info.member_file}</span>
		<span id="showImage">
			<c:if test='${empty member_info.member_file}'>
				<c:set var='src' value='image/profile.png'/>
			</c:if>
			<c:if test='${!empty member_info.member_file}'>
				<c:set var='src' value='${"memberupload/"}${member_info.member_file}'/>
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

</body>
<jsp:include page="/board/footer.jsp"/>
</html>