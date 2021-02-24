
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
			
			$("#board_account").change(function() {
				if ($.isNumeric($("#board_account").val())) {
					$("#board_tel").focus();

				} else {
					alert("계좌 번호는 숫자로 입력해주세요")
					$("#board_account").val('')
					$("#board_account").focus();
				}
			})
			
				$("#board_thumbnail").change(function() {
		var inputfile = $(this).val().split('\\');
		$('#board_thumbnail_name').text(inputfile[inputfile.length - 1]).css("display","none");
		var thumbnail_name = $('#board_thumbnail_name').text();
		console.log(thumbnail_name);
		
		if(!(thumbnail_name.endsWith('.png') || thumbnail_name.endsWith('.PNG') ||
		thumbnail_name.endsWith('.jpg') || thumbnail_name.endsWith('.JPG')))
		 {
		 					alert("파일은 jpg, png파일만 업로드 가능합니다.")
		 }
	 
	 });
	 				$("#upfile1").change(function() {
		var inputfile1 = $(this).val().split('\\');
		$('#filevalue1').text(inputfile1[0][inputfile1.length - 1]).css("display","none");
		var filename1 = $('#filevalue1').text();
		console.log(filename1);
	});
 				$("#upfile2").change(function() {
		var inputfile2 = $(this).val().split('\\');
		$('#filevalue2').text(inputfile2[inputfile2.length - 1]).css("display","none");
		var filename2 = $('#filevalue2').text();
		console.log(filename2);
			});
			
		 				$("#upfile3").change(function() {
		var inputfile3 = $(this).val().split('\\');
		$('#filevalue3').text(inputfile3[inputfile3.length - 1]).css("display","none");
		var filename3 = $('#filevalue3').text();
		console.log(filename3);
		});
		 				$("#upfile4").change(function() {
		var inputfile4 = $(this).val().split('\\');
		$('#filevalue4').text(inputfile4[inputfile4.length - 1]).css("display","none");
		var filename4 = $('#filevalue4').text();
		console.log(filename4);
				});
	
			
				
				
		
			
			

			$("#board_tel").change(function() {
				pattern = /^\d{2,3}-\d{3,4}-\d{3,4}$/;
				if (pattern.test($("#board_tel").val())) {
					$("#board_delivery").focus();

				} else {
					alert("전화번호를 확인하세요(000-0000-0000)")
					$("#board_tel").val('')
					$("#board_tel").focus();
				}

			}); 

			$("form").submit(
					function() {
		
					  
					         


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


					}) //end form.submit

		});
		
