
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<%@ include file="header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 <link href="../css/board/boardView.css" rel="stylesheet">


<%
request.setCharacterEncoding("utf-8");
%>





<div class="container">


<<<<<<< HEAD
<!-- table -->
<div class="board_view_table">
  <h2>${boardBean.board_product }</h2>
  <table class="table">

      <tr><td rowspan="6">  <img src="boardupload/${boardBean.board_file1 }" class="view_thumbnail" alt="thumbnail" width="304" height="236"> 
      </td>
        <td>가격 ${boardBean.board_price}원 </td>
      </tr>
      <tr>
        <td>계좌번호 ${boardBean.board_acoount}</td>
      </tr>
      <tr>
        <td>연락처 ${boardBean.board_tel}</td>
      </tr>
      <tr>
        <td>보관방법 ${boardBean.board_storage}</td>
      </tr>
            <tr>
        <td>배송구분 / 배송비 ${boardBean.board_delivery} / ${boardBean.board_deliverycost}</td>
      </tr>
      
                  <tr>
        <td><strong>평점</strong> ${evaluationBean.evaluation_avg} /</td>
      </tr>
  </table>
</div>




<!-- sort -->
<div class="well">
  <div class="label">
   <span class="label label-success">소분 판매</span>
   <span class="label label-warning">직배송</span>
   <span class="label label-danger">국내산</span>
  </div>
</div>


<!-- content -->
<div class="board_detail">
 <table class="table table-bordered">
      <tr>
        <td>상품명</td>
        <td>${boardBean.board_product}</td>
      </tr>
      <tr>
        <td>용량/수량</td>
        <td>${boardBean.board_amount}</td>
      </tr>
      <tr>
        <td>생산자</td>
        <td>${boardBean.board_producer}</td>
      </tr>
      <tr>
        <td>유통기한</td>
        <td>${boardBean.board_expirydate}</td>
      </tr>
            <tr>
        <td>원산지</td>
        <td>${boardBean.board_origin}</td>
      </tr>
            <tr>
        <td>보관방법</td>
        <td>${boardBean.board_storage}</td>
      </tr>
            <tr>
        <td>배송방법</td>
        <td>${boardBean.board_delivery}</td>
      </tr>
  </table>
</div>

<div class="board_content">
<div class="board_view_image2">
  <img src="boardupload/${boardBean.board_file2 }" class="상품이미지1" alt="thumbnail" width="795" height="500"> 
  <h1>국내산 과일</h1>
  <p>대한민국에서 가장 맛있는 과일입니다.</p>
</div>

<div class="board_view_image3">
  <img src="boardupload/${boardBean.board_file2 }" class="상품이미지1" alt="thumbnail" width="795" height="500"> 
  <h1>국내산 채소</h1>
  <p>대한민국에서 가장 맛있는 채소입니다.</p>
</div>

<div class="board_view_image3">
  <img src="boardupload/${boardBean.board_file2 }" class="상품이미지1" alt="thumbnail" width="795" height="500"> 
  <h1>국내산 곡물</h1>
  <p>대한민국에서 가장 맛있는 곡물입니다.</p>
</div>
</div>

<div class="board_pager">
  <ul class="pager">
    <li><a href="#">Previous</a></li>
    <li><a href="#">List</a></li>
    <li><a href="#">Next</a></li>
  </ul>
</div>



</div>


<%@ include file="footer.jsp"%>

