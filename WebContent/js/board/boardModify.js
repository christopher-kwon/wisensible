
$(document).ready(function() {

$("#board_bank").val("${boardBean.board_bank}").prop("selected", true);

$('#board_thumbnail').change(function(event) {
		var thumbnail_file = $(this).val().split('\\');
		var thumbnail_filename = thumbnail_file[thumbnail_file.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(thumbnail_filename)) {
			$('#board_thumbnailname').text(thumbnail_filename); //inputfile.length - 1 = 2
			var reader = new FileReader(); //파일을 읽기 위한 개체 생성
			
			//DataURL 형식으로 파일을 읽어옵니다.
			//읽어온 결과는 reader 개체의 result 속성에 저장됩니다.
			//event.target.files[0] : 선택한 그림의 파일개체에서 첫번째 개체를 가져옵니다.
			
			reader.readAsDataURL(event.target.files[0]);
			
			reader.onload = function(event) { //읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#thumbnailView').attr('src', event.target.result);
			};
		} else {
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
		}
			
		});
		
		$('#board_file1').change(function(event1) {
		var board_file1 = $(this).val().split('\\');
		var board_filename1 = board_file1[board_file1.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(board_filename1)) {
			$('#board_file1Name1').text(board_filename1); //inputfile.length - 1 = 2
			var reader1 = new FileReader(); //파일을 읽기 위한 개체 생성
			
			//DataURL 형식으로 파일을 읽어옵니다.
			//읽어온 결과는 reader 개체의 result 속성에 저장됩니다.
			//event1.target.files[0] : 선택한 그림의 파일개체에서 첫번째 개체를 가져옵니다.
			
			reader1.readAsDataURL(event1.target.files[0]);
			
			reader1.onload = function(event1) { //읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#board_fileView1').attr('src', event1.target.result);
			};
		} else {
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
		}
			
		});
		
			$('#board_file2').change(function(event2) {
		var board_file2 = $(this).val().split('\\');
		var board_filename2 = board_file2[board_file2.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(board_filename2)) {
			$('#board_file1Name2').text(board_filename2); //inputfile.length - 1 = 2
			var reader2 = new FileReader(); //파일을 읽기 위한 개체 생성
			
			//DataURL 형식으로 파일을 읽어옵니다.
			//읽어온 결과는 reader 개체의 result 속성에 저장됩니다.
			//event2.target.files[0] : 선택한 그림의 파일개체에서 첫번째 개체를 가져옵니다.
			
			reader2.readAsDataURL(event2.target.files[0]);
			
			reader2.onload = function(event2) { //읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#board_fileView2').attr('src', event2.target.result);
			};
		} else {
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
		}
			
		});
		
		
		
		

	var check1 = 0;
	var check2 = 0;
	var check3 = 0;
	var check4 = 0;
	
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

					
					
						if ($("#board_delivery").is(":checked")==false) {
							alert("배송방법을 선택해주세요")
						
							return false;
						}

						if ($.trim($("#board_product").val()) == "") {
							alert("상품명을 입력해주세요")
							$("#board_ptoduct").focus();
							return false;

						}
						if ($.trim($("#board_amount").val()) == "") {
							alert("수량을 입력해주세요")
							$("#board_mount").focus();
							return false;

						}
						if ($.trim($("#board_producer").val()) == "") {
							alert("생산자를 입력해주세요")
							$("#board_producer").focus();
							return false;

						}
						if ($.trim($("#board_expirydate").val()) == "") {
							alert("유통기간을 입력해주세요")
							$("#board_expirydate").focus();
							return false;

						}
						if ($.trim($("#board_origin").val()) == "") {
							alert("원산지 항목을 입력해주세요")
							$("#board_origin").focus();
							return false;

						}
						if ($("#board_storage").is(":checked")==false) {
							alert("보관방법을 선택해주세요")
						
							return false;

						}
						if ($.trim($("#board_deliverycost").val()) == "") {
							alert("배송비용을 입력해주세요")
							$("#board_deliverycost").focus();
							return false;

						}
						
						if ($.trim($("#board_file1").val()) == "") {
							alert("파일은 최소 2개이상 업로드필수입니다. 1번파일을 업로드해주세요")
						
							return false;

						}
						if ($.trim($("#board_file2").val()) == "") {
							alert("파일은 최소 2개이상 업로드필수입니다. 2번파일을 업로드해주세요")
						
							return false;

						}
						if ($.trim($("#board_pass").val()) == "") {
							alert("게시물 비밀번호를 입력해주세요")
							$("#board_pass").focus();
							return false;
						}
						
								if(check1 == 0) {
			value = $('#filevalue1').text();
			html = "<input type='text' value='" + value + "'name='check1'>";
			$(this).append(html);
		}
		
				if(check2 == 0) {
			value = $('#filevalue2').text();
			html = "<input type='text' value='" + value + "'name='check2'>";
			$(this).append(html);
		}
		
				if(check3 == 0) {
			value = $('#filevalue3').text();
			html = "<input type='text' value='" + value + "'name='check3'>";
			$(this).append(html);
		}
		
				if(check4 == 0) {
			value = $('#filevalue4').text();
			html = "<input type='text' value='" + value + "'name='check4'>";
			$(this).append(html);
		}
		
		
	}); // submit end
	
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
			$("#board_deliverycost").change(function() {
				if ($.isNumeric($("#board_deliverycost").val())) {
					$("#board_bank").focus();

				} else {
					alert("배송비 정보는 숫자로 입력해주세요")
					$("#board_deliverycost").val('')
					$("#board_deliverycost").focus();
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

			}); 
			

	
	
	$("#upfile1").change(function() {
		check1++;
		var inputfile1 = $(this).val().split('\\');
		$('#filevalue1').text(inputfile1[inputfile1.length - 1]);
		show();
	});
	
		$("#upfile2").change(function() {
		check2++;
		var inputfile2 = $(this).val().split('\\');
		$('#filevalue2').text(inputfile2[inputfile2.length - 1]);
		show();
	});
	
		$("#upfile3").change(function() {
		check3++;
		var inputfile3 = $(this).val().split('\\');
		$('#filevalue3').text(inputfile3[inputfile3.length - 1]);
		show();
	});
	
		$("#upfile4").change(function() {
		check4++;
		var inputfile4 = $(this).val().split('\\');
		$('#filevalue4').text(inputfile4[inputfile4.length - 1]);
		show();
	});
	
		$(".remove1").click(function() {
		$('#filevalue1').text('');
		$(this).css('display', 'none')
	})
	
			$(".remove2").click(function() {
		$('#filevalue2').text('');
		$(this).css('display', 'none')
	})
	
			$(".remove3").click(function() {
		$('#filevalue3').text('');
		$(this).css('display', 'none')
	})
	
			$(".remove4").click(function() {
		$('#filevalue4').text('');
		$(this).css('display', 'none')
	})
	





}); // ready() end
