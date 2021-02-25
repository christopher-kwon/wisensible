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
<script src="js/join.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
.col-lg-3{display: none}
</style>
</head>
<body>
<h1>회원가입</h1>
<form name="joinform" action="joinProcess.com" method="post" enctype="multipart/form-data">
	
	<div class="container3">
	  <fieldset>
	  <br>
	   <b>이름</b>
		<input type="text" name="name" placeholder="Enter name" required maxlength="15">
	  
       <b>아이디</b>
		<input type="text" name="id" placeholder="Enter id" required maxlength="12">
		<span id="message"></span>
		
		
	   <b>비밀번호</b>
		<input type="password" name="pass" placeholder="Enter password" required>
		
	   <b>비밀번호 확인</b>
       	<input type="password" placeholder="Enter Password Check" 
       		name="passck" required>
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
        	<option value="신한은행">신한은행</option>
        	<option value="국민은행">국민은행</option>
        	<option value="농협">농협</option>
        	<option value="우리은행">우리은행</option>
        	<option value="하나은행">하나은행</option>
        	<option value="카카오뱅크">카카오뱅크</option>
        	<option value="케이뱅크">케이뱅크</option>
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
			<img src="image/attach.png" width="10px">
			<span id="filename"></span>
			<input type="file" name="memberfile" accept="image/*">
		</label>
        
	<div class="clearfix">
		<button type="reset" class="cancelbtn">다시작성</button>
		<button type="submit" class="submitbtn">회원가입</button>
	</div>
      </fieldset>
	</div>
</form>
<jsp:include page="/board/footer.jsp"/>
</body>
</html>