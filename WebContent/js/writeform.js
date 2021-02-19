$(document).ready(function() {

	
	
	
	
	
	
	
	
	
	
	
	$("form").submit(function() {

		if ($('select').eq(0).val() == "----") {
			alert("카테고리를 선택해주세요");
			$('select:eq(0)').focus();
			return false;
		}
		if ($.trim($("#board_subject").val()) == "") {
			alert("제목을 입력해주세요")
			$('input:eq(0)').focus();
			return false;
		}
		if ($.trim($("#board_price").val()) == "") {
			alert("가격을 입력해주세요")
			$("#board_price").focus();
			return false;

		}
		if ($('select').eq(1).val() == "----") {
			alert("은행명을 선택해주세요");
			$('select:eq(1)').focus();
			return false;
		}
		
		if ($.trim($("#board_account").val()) == "") {
			alert("계좌번호를 입력해주세요")
			$("#board_account").focus();
			return false;

		}

		if ($.trim($("#board_tel").val()) == "") {
			alert("연락처를 입력해주세요")
			$("#board_tel").focus();
			return false;

		}
		
		$("#board_tel").keyup(function(){
			pattern= /^\d{2,3}-\d{3,4}-\d{3,4}$/;
				if(pattern.test($("#board_tel").val())){
					$("#board_storage").focus();
				
				}else{
					alert("전화번호를 확인하세요(000-0000-0000)")
					$("#board_tel").val('')
					$("#board_tel").focus();
				}
		
		})

		if ($.trim($("#board_storage").val()) == "") {
			alert("보관방법을 입력해주세요")
			$("#board_storage").focus();
			return false;

		}

		if ($.trim($("#board_delivery").val()) == "") {
			alert("배송방법을 입력해주세요")
			$("#board_delivery").focus();
			return false;
		}
			if ($.trim($(" tr:nth-child(n) > td > input[type=text]").val()) == "") {
				alert("상품상세정보를 확인하세요")
				$(" tr:nth-child(1) > td > input[type=text]").focus();
				return false;	
			
			
		}

	})

})