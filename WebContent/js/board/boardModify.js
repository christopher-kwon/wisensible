
$('#board_category').val('${boardBean.board_category}').prop("selected", true);
$('#board_bank').val('${boardBean.board_bank}').prop("selected", true);


$(document).ready(function() {

	var check = 0;
	
	$("form").submit(function() {


		if ($.trim($("#board_subject").val()) == "") {
			alert("제목을 입력하세요")
			$("#board_subject").focus();
			return false;
		}

		if ($.trim($("#board_content").val()) == "") {
			alert("내용을 입력하세요");
			$("#board_content").focus();
			return false;
		}
		
		
		if(check == 0) {
			value = $('#filevalue').text();
			html = "<input type='text' value='" + value + "'name='check'>";
			$(this).append(html);
		}
		
	
	}); // submit end
	
	function show() {
		if($('#filevalue').text() == '') {
			$(".remove").css('display', 'none');
		} else {
			$(".remove").css({'display':'inline', 'position':'relative', 'top':'-5px'});
		}
	}
	
	show();
	
	$("#upfile").change(function() {
		check++;
		var inputfile = $(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length - 1]);
		show();
	});
	
		$(".remove").click(function() {
		$('#filevalue').text('');
		$(this).css('display', 'none')
	})


}); // ready() end
