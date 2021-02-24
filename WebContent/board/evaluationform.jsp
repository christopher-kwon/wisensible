<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery-3.5.1.js"></script>
<style>
.star_box a {
	text-decoration: none;
	color: #e3e3df;
	font-size: 3em;
}

.star_result a {
	color: #e3e3df;
	font-size: 5em;
		text-decoration: none;
}

.star_box a.on {
	text-decoration: none;
	color: #ffa630;
	font-size: 3em;
}
</style>

<td><strong>평점</strong> ${evaluationBean.evaluation_avg}

	<div class="star_result">
		<a href="#">★</a> <a href="#">★</a> <a href="#">★</a> <a href="#">★</a>
		<a href="#">★</a>

	</div>

	<div class="star_box">
		<a href="#">★</a> <a href="#">★</a> <a href="#">★</a> <a href="#">★</a>
		<a href="#">★</a>

	</div>
	<p id="lev" style="text-align:;"></p></td>

<br>
<hr>
<div class="register_box2">

	<button type=submit class="btn btn-register">평점등록</button>

	<button type=reset class="btn btn-cancel">초기화</button>

</div>



<script>
	function getList() {
		$.ajax({
			type : "post",
			url : "EvaluationGet.ev",
			data : {
				"board_num" : $("#board_re_ref").val(),
				
			},
			dataType : "json",
			success : function(rdata) {
				console.log(rdate)

			}
		})
	}

	ind = -1;
	$(".star_box a").on('click', function() {

		$(this).parent().children("a").removeClass("on");
		$(this).addClass("on").prevAll("a").addClass("on")

		ind = $(this).index() + 1
		console.log(ind);
		$("#lev").text(ind + " 점 / 5 점");

	})

	$('body > div.register_box2 > button.btn.btn-register').click(function() {
		if (ind == -1) {
			alert("평점을 선택해주세요")
			return;
		}

		$.ajax({
			url : "EvaluationAdd.ev",
			data : {
				evaluation_name : "admin",//$("#loginid").val(),
				evaluation : 1,
				board_num : 1//$('#board_re_ref').val()
			},
			type : "post",
			success : function(rdata) {
				if (rdata.length > 0) {
					alert("평점등록완료");

				}
			},
			error :function(request, status, error){
	            $("body").append("<div id ='error'>code : " + request.status + "<br>" +"받은데이터 : " + request.responseText + "<br>"
	                    +"error status :" +status +"<br>"
	                    +"error 메시지 : " + error +"</div>");
	           },
	           complete :function(){//요청의 실패, 성공과 상관없이 완료될 경우 호출
	              $("body").append("<div id='com'>Ajax가 완료되었습니다.</div>");
	           }

		})

	})
</script>