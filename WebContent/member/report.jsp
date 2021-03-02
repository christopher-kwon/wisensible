<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/board/header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>신고하기</title>
<link href="css/join.css?ver=1" type="text/css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
.col-lg-3{display: none}
#report_content{width:700px; height:800px;}
</style>
</head>
<body>
<h1>신고하기</h1>
<form name="joinform" action="MemberReportProcessAction.com" method="post">
	
	<div class="container3">
	  <fieldset>
	  <br>
       <b>아이디</b>
		<input type="text" name="report_id" placeholder="Enter id" required maxlength="12">
		<span id="message"></span>
		
		<b>이메일주소</b>
		<input type="text" name="report_email" placeholder="Enter Email" required maxlength="50">
		<span id="message"></span>
		
	   <b>신고 내용</b>
		<input type="text" name="report_content" id="report_content" placeholder="내용을 입력해주세요">
	 	
	 		<div class="clearfix">
		<button type="reset" class="cancelbtn">다시작성</button>
		<button type="submit" class="submitbtn">신고하기</button>
	</div>
      </fieldset>
	</div>
</form>
<jsp:include page="/board/footer.jsp"/>
</body>
</html>