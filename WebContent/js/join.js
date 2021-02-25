$(document).ready(function(){
	var checkid=false;
	var checkpass=false;
	
	$('form').submit(function(){
		
		if(!checkid){
			alert("사용 가능한 id로 입력하세요");
			$("input:eq(1)").val('').focus();
			return false;
		}
		
	});// submit

	$("input[name=id]").on('keyup', function(){
		checkid=true;
		$("#message").empty();// 처음에 pattern에 적합하지 않은 경우 메세지 출력 후 적합한 데이터를
								// 작성하면 사라짐
		// [A-Za-z0-9_]의 의미가\w이다.
		var pattern = /^\w{5,12}$/;
		var id = $("input[name=id]").val();
		if(!pattern.test(id)){
			$("#message").css('color', 'red').html("영문자 대문자 숫자_로 5~12자 가능합니다.");
			checkid=false;
			return;
		}
		
		$.ajax({
			url: 'idcheck.com',
			data: {"id" : id},// 보낼 데이터
			success: function(resp){
				if(resp==-1){// db에 해당 id가 없는 경우
					$("#message").css('color', 'green').html(
							"사용 가능한 아이디 입니다.");
					checkid=true;
				}else{
					$("#message").css('color', 'blue').html(
						"사용중인 아이디 입니다.");
					checkid=false;
				}
			}
		})
	});// id keyup end
	
	// 비밀번호 일치 확인(수정완료)
	$("input[name=passck]").on('keyup', function(){
		checkpass=true;
		$("#pass_message").empty();// 처음에 pattern에 적합하지 않은 경우 메세지 출력 후 적합한 데이터를
								// 작성하면 사라짐
		var pass = $("input[name=pass]").val();
		var passck = $("input[name=passck]").val();
		if(pass != passck){
			$("#pass_message").css('color', 'red').html("비밀번호가 서로 일치하지 않습니다.");
			checkpass=false;
			return;
		}
	});

	
	//우편번호
	function Postcode() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
	            var extraRoadAddr = ''; // 참고 항목 변수

	            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
	                extraRoadAddr += data.bname;
	            }
	            // 건물명이 있고, 공동주택일 경우 추가한다.
	            if (data.buildingName !== '' && data.apartment === 'Y') {
	                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }
	            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	            if (extraRoadAddr !== '') {
	                extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }
	            
	         // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
	            if(fullRoadAddr !== ''){
	                fullRoadAddr += extraRoadAddr;
	            }

	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            $('#post1').val(data.zonecode);
	            $("#address").val(fullRoadAddr);
	        }
	    }).open();
		}
	//우편 검색 버튼 클릭
	$("#postcode").click(function(){
		//window.open("post.html", "post","width=400, height=500, scrollbars=yes");
		Postcode();
	});
	
	$('input[type=file]').change(function(event){
		var inputfile = $(this).val().split('\\');
		var filename = inputfile[inputfile.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(filename)){
			$("#filename").text(filename);//inputfile.length - 1 = 2
			
			var reader = new FileReader();//파일을 읽기 위한 객체 생성
			//DataURL 형식으로 파일을 읽어온다.
			//읽어온 결과는 reader 객체의 result 속성에 저장된다.
			//event.target.files[0] : 선택한 그림의 파일 객체에서 첫 번째 객체를 가져온다.
			reader.readAsDataURL(event.target.files[0]);
			
			reader.onload = function(event){//읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#showImage').html('<img width="40px" src="' + event.target.result + '">')
			};
		}else{
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
		}
	
	})
});