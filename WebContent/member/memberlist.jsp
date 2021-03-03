<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../board/header.jsp" />
<title>회원 리스트</title>
<link href="css/memberlist.css" type="text/css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="js/list.js"></script>
<script>
$(function(){
	var selectedValue = '${search_field}'
		
			$("#viewcount").val(selectedValue);
})
</script>
</head>
<body>
<div class="container">
	<form action="memberList.com" method="post">
		<div class="input-group">
			<select id="viewcount" name="search_field">
				<option value="0" selected>아이디</option>
				<option value="1">이름</option>
				<option value="2">이메일</option>
			</select>
			<input name="search_word" type="text" class="form-control"
					placeholder="아이디를 입력하세요" value="${search_word}">
			<button class="btn btn-primary" type="submit">검색</button>
		</div>
	</form>
	<c:if test="${listcount > 0 }"><%-- 회원이 있는 경우 --%>
		<table class="table table-striped" id="checkboxTestTbl">
			<caption>회원 목록</caption>
			<thead>
				<tr>
					
					<th colspan="2">회원 정보 list</th>
					<th colspan="2">
						<font size=3>회원 수 : ${listcount}</font>
					</th>
				</tr>
				<tr class="id_table">
					<td><input type="checkbox"/></td><td>아이디</td><td>이름</td><td>삭제</td>
				</tr>
			</thead>
			<tbody class="sTable">
				<c:forEach var="member_info" items="${totallist}">
					<tr>
						<td><input type="checkbox"/></td>
						<td>${member_info.member_id}</td>
						<td>${member_info.member_name}</td>
						<td>
							<a href="javascript:del('${member_info.member_id}')" style="color:red">삭제</a>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" id="removeButton_sel" name="removeButton_sel" 
			value="삭제" class="btn btn-danger" style="float: right">
	</c:if>
</div>
<div>
	<ul class="pagination justify-content-center">
		<c:if test="${page <= 1}">
			<li class="page-item">
				<a class="page-link current" href="#">이전&nbsp;</a>
			</li>
		</c:if>
		<c:if test="${page > 1}">
			<li class="page-item">
				<a href="memberList.com?page=${page-1}&search_file=${search_field}&search_word=${search_word}" 
					class="page-link">이전</a>&nbsp;
			</li>
		</c:if>
		
		<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:if test="${a == page}">
				<li class="page-item">
					<a class="page-link current" href="#">${a}</a>
				</li>
			</c:if>
			<c:if test="${a != page}">
				<li class="page-item">
					<a href="memberList.com?page=${a}&search_file=${search_field}&search_word=${search_word}"
						class="page-link">${a}</a>
				</li>
			</c:if>
		</c:forEach>
		
		<c:if test="${page >= maxpage}">
			<li class="page-item">
				<a class="page-link current" href="#">&nbsp;다음</a>
			</li>
		</c:if>
		<c:if test="${page < maxpage}">
			<li class="page-item">
				<a href="memberList.com?page=${page+1}&search_file=${search_field}&search_word=${search_word}" 
					class="page-link">&nbsp;다음</a>
			</li>
		</c:if>
	</ul>
</div>

<%-- 회원이 없는 경우 --%>
<c:if test="${listcount == 0 && empty search_word}">
	<font>회원이 없습니다.</font>
</c:if>

<c:if test="${listcount == 0 && !empty search_word}">
	<font>검색 결과가 없습니다.</font>
</c:if>
</body>
<jsp:include page="/board/footer.jsp"/>
</html>