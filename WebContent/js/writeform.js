






$(document).ready(
		function() {
			


			$("#board_price").change(function() {
				if ($.isNumeric($("#board_price").val())) {
					$("#board_bank").focus();

				} else {
					alert("가격 정보는 숫자로 입력해주세요")
					$("#board_price").val('')
					$("#board_price").focus();
				}
			})

			$("#board_tel").change(function() {
				pattern = /^\d{2,3}-\d{3,4}-\d{3,4}$/;
				if (pattern.test($("#board_tel").val())) {
					$("#board_delivery").focus();

				} else {
					alert("전화번호를 확인하세요(000-0000-0000)")
					$("#board_tel").val('')
					$("#board_tel").focus();
				}

			})

			$("form").submit(
					function() {
						
					      var formData = new FormData();
					      for(var i = 0; i < $fileListArr.length ; i++){
					         formData.append("uploadFile" , $fileListArr[i]);
					      }
					      
					      $.ajax({
					         url:"/file/fileUpload.do",
					         processData : false,
					         contentType : false,
					         data: formData ,
					         type : 'POST',
					         success : function(res){
					            location.href = '/file/List.do';
					         },
					         error: function(){
					            alert('에러');
					         }
					      });
					         
					         


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

					

						

					})

		})