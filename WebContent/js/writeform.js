
$(document).ready(
		function() {
			  $('input[type=file]').change(function(event){
			      var inputfile =$(this).val().split('\\');
			      var filename=inputfile[inputfile.length -1];
			      var pattern = /(gif|jpg|jpeg|png)$/i;
			      if(pattern.test(filename)){
			         $('#filename').text(filename);
			         
			         var reader =new FileReader();
			      reader.readAsDataURL(event.target.files[0])
			      reader.onload = function(event){
			            $("#showImage").html('<img width="400px" height="400px" src="' + event.target.result + '">');
			      
			            
			         };
			      }else{
			         alser('확장자는 gif, jpg, jpeg, png 가능합니다.');
			      }
			   })
			
			$("#file_add").click(function(){
				var output ="<input type='file' id='upfile3' name='board_file3'>"
					output += "<span id='filevalue3'></span>&nbsp;"
					output +="<input type='file' id='upfile4' name='board_file4'>"
					output += "<span id='filevalue4'></span><br>"	
					output += "사진 업로드는 최대 4개까지 가능합니다."
				$("body > div > div > div > div.col-lg-9 > div > form > div.section_content > div.form-file > label").append(output+"<br>");
				$("#file_add").attr("style","display:none")
				
			})
		
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
      
         $(".remove").click(function() {
         $('#filevalue').text('');
         $(this).css('display', 'none')
      })
		});
		
