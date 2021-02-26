
$(document).ready(
		function() {
		
			if($("#board_bankt").val() == "신한은행" ){
				$("#board_bank").val("신한은행").prop("selected",true);
			}
			if($("#board_bankt").val() == "하나은행" ){
				$("#board_bank").val("하나은행").prop("selected",true);
			}
			if($("#board_bankt").val() == "우리은행" ){
				$("#board_bank").val("우리은행").prop("selected",true);
			}
			if($("#board_bankt").val() == "국민은행" ){
				$("#board_bank").val("국민은행").prop("selected",true);
			}
			if($("#board_bankt").val() == "농협" ){
				$("#board_bank").val("농협").prop("selected",true);
			}
			if($("#board_bankt").val() == "카카오뱅크" ){
				$("#board_bank").val("카카오뱅크").prop("selected",true);
			}
			if($("#board_bankt").val() == "케이뱅크" ){
				$("#board_bank").val("케이뱅크").prop("selected",true);
			}
			
			
				
			
			


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
		$('#filevalue1').text(inputfile1[inputfile1.length - 1]).css("display","none");
		var filename1 = $('#filevalue1').text();
		console.log(filename1);
		
		if(!(filename1.endsWith('.png') || filename1.endsWith('.PNG') ||
		filename1.endsWith('.jpg') || filename1.endsWith('.JPG')))
		 {
		 					alert("파일은 jpg, png파일만 업로드 가능합니다.")
		 }
		 
		 
	});
 				$("#upfile2").change(function() {
		var inputfile2 = $(this).val().split('\\');
		$('#filevalue2').text(inputfile2[inputfile2.length - 1]).css("display","none");
		var filename2 = $('#filevalue2').text();
		console.log(filename2);
		
				if(!(filename2.endsWith('.png') || filename2.endsWith('.PNG') ||
		filename2.endsWith('.jpg') || filename2.endsWith('.JPG')))
		 {
		 					alert("파일은 jpg, png파일만 업로드 가능합니다.")
		 }
			});
			
		 				$("#upfile3").change(function() {
		var inputfile3 = $(this).val().split('\\');
		$('#filevalue3').text(inputfile3[inputfile3.length - 1]).css("display","none");
		var filename3 = $('#filevalue3').text();
		console.log(filename3);
		
				if(!(filename3.endsWith('.png') || filename3.endsWith('.PNG') ||
		filename3.endsWith('.jpg') || filename3.endsWith('.JPG')))
		 {
		 					alert("파일은 jpg, png파일만 업로드 가능합니다.")
		 }
		 
		 
		});
		 				$("#upfile4").change(function() {
		var inputfile4 = $(this).val().split('\\');
		$('#filevalue4').text(inputfile4[inputfile4.length - 1]).css("display","none");
		var filename4 = $('#filevalue4').text();
		console.log(filename4);
		
				if(!(filename4.endsWith('.png') || filename4.endsWith('.PNG') ||
		filename4.endsWith('.jpg') || filename4.endsWith('.JPG')))
		 {
		 					alert("파일은 jpg, png파일만 업로드 가능합니다.")
		 }
		 
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
			$("#file_add").click(function(){
				var output ="<input type='file' id='upfile3' name='board_file3'>"
				output + "<span id='filevalue2'></span>"
				$("#filevalue2").html(output);
			})
			

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
						
						if ($.trim($("#upfile1").val()) == "") {
							alert("파일은 최소 2개이상 업로드필수입니다. 1번파일을 업로드해주세요")
						
							return false;

						}
						if ($.trim($("#upfile2").val()) == "") {
							alert("파일은 최소 2개이상 업로드필수입니다. 2번파일을 업로드해주세요")
						
							return false;

						}
						if ($.trim($("#board_passward").val()) == "") {
							alert("게시물 비밀번호를 입력해주세요")
							$("#board_pass").focus();
							return false;
						}
						
						
					}) //end form.submit


					 function show() {
        
         if($('#filevalue').text() == '') {
            $(".remove").css('display', 'none');
         } else {
            $(".remove").css({'display':'inline-block', 'position':'relative', 'top':'-5px'});
         }
      }
      
      show();
      
      $("#upfile").change(function() {
         check++;
         var inputfile = $(this).val().split('\\');
         $('#filevalue').text(inputfile[inputfile.length - 1]);
         show();
         console.log(check);
      });
      
         //remove 이미지를 클릭하면 파일명을 ''로 변경하고 remove 이미지를 보이지 않게 합니다.
         $(".remove").click(function() {
         $('#filevalue').text('');
         $(this).css('display', 'none')
      })
		});
		
