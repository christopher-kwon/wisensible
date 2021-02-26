<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그아웃</title>
</head>
<body>
	<%
		session.invalidate();
	%><!-- 꼭 집어서 하나만=remove, 전체=invalidate -->
	<script>
		alert("로그아웃 되었습니다.");
		location.href = "BoardList.bo"
	</script>
</body>
</html>