function getList(state) {
	option = state; 
	
	$.ajax({
		type : "post",
		url : "CommentList.co",
		data : {"page" : 1, "comment_board_ref" : $("#board_num").val(), state : state},
		dataType : "json",
		success : function(rdata) {
			$('#count').text(rdata.listCount).css('font-family', 'arial, sans-serif')
			var red1 = 'red';
			var red2 = 'red';
			if(option == 1) {
				red2 = 'gray';
			}
			if(option == 2) {
				red1 = 'gray';
			}
			var output = "";
			
			if(rdata.boardList.length > 0) {
				output += '<li class="comment_tab_item ' + red1 + '" >'
						+ ' <a href="javascript:getList(1)" class="comment_tab_button">등록순</a>'
						+ '</li>'
						+ '<li class="comment_tab_item' + red2 + '" >'
						+ '</li>';
				$('.comment_tab_list').html(output);
				
				output = '';
				$(rdata.boardList).each(function() {
					var lev = this.comment_re_lev;
					var comment_reply = '';
					if(lev == 1) {
						comment_reply = ' CommentItem--reply lev1';
						
					} else if(lev == 2) {
						comment_reply = ' CommentItem--reply lev2';
					}
					var profile = this.memberfile;
					var src = 'image/profile.png';
					if(profile) {
						src = 'memberupload/' + profile;
					}
					
					
					output += '<li id="' + this.comment_num + '" class=CommentItem' + comment_reply + '" >'
							+ '<div class="comment_area">'
							+ ' <img src="' + src + '" alt="프로필 사진" width="36" height="36">'
								+ '		<div class="comment_box">'
						+ '			<div class="comment_nick_box">'
						+ '				<div class="comment_nick_info">'
						+ '					<div class="comment_nickname">' + this.comment_id + '</div>'
						+ '			</div>' //comment_nick_box
						+ '		</div>' //comment_box
						+ '</div>' //comment_area
						+ '<div class="comment_text_box">'
						+ '<p class="comment_text_view">'
						+ ' <span class="text_comment">' + this.comment_content + '</span>'
						+ '</p>'
						+ '</div>' //comment_text_box
						+ '<div class="comment_info_box">'
						+ '<span class="comment_info_date">' + this.comment_date + '</span>';
				if(lev<2) {
					output += ' <a href="javascript:replyform(' + this.comment_num + ','
							+ lev + ',' + this.comment_re_seq + ','
							+ this.comment_re_ref +')" class="comment_info_button">답글쓰기</a>'
				}
				output += '</div>' //comment_info_box
					
					if($("#loginid").val() == this.comment_id){
						output += '	<div class="comment_tool">'
								+ '<div title="더보기" class="comment_tool_button">'
								+ '		<div>&#46;&#46;&#46;</div>'
								+ '</div>'
								+ '<div id="commentItem' + this.comment_num + '" class="LayerMore">'
								+ '<ul class="layer_list">'
								+ '	<li class="layer_item">'
								+ '		<a href="javascript:updateForm(' + this.comment_num + ')"'
								+ ' 		class="layer_button">수정</a>&nbsp;&nbsp;'
								+ ' 	<a href="javascript:del(' + this.comment_num + ')"'
								+ ' 		class="layer_button">삭제</a></li></ul>'
								+ ' </div>'
								+ '</div>' //comment_tool
								
					} 
				
				output += '</div>'; //comment_box
				output += '</div>' //comment_area
						+ '</li>' //CommentItem
			}) //each
			$('.comment_list').html(output);
			
		} //if(rdata.boardList.length>0)
			
		} //success end
	}); //ajax end
			
	

} //function(getList) end

function updateForm(comment_num) {
	var content=$('#'+comment_num).find('.text_comment').text();
	var countNum=content.length;
	
	var selector = '#'+comment_num+ ' .comment_area'
	var obj=$(selector).hide(); 
	
	$('#'+comment_num).append($('.comment_list+.CommentWriter').clone());
	$('#'+comment_num).find('textarea').val(content);
	$('#'+comment_num).find('.comment_inbox_count').text(countNum+"/200");

	$('#'+comment_num).find('.btn_register').attr('data-id', comment_num).addClass('update');
	
	$('#'+comment_num).find('.btn_cancel').css('display','block')
} //function end

function del(comment_num) {
	if(!confirm('정말 삭제하시겠습니까'))
		return;
	
	$.ajax({
		url : 'CommentDelete.bo',
		data : {comment_num : comment_num},
		success : function(rdata) {
			if(rdata == 1){
				getList(option);
			}
		}
	})
} //function(del) end

function replyform(comment_num, lev, seq, ref){
	var output = '<li class="CommentItem CommentItem--reply lev'
		+ lev + ' CommentItem-form"></li>'
		
		$('#'+comment_num).after(output);
	
	output = $('.comment_list+.CommentWriter').clone();
	
	$('#'+comment_num).next().html(output);
	
	$('#'+comment_num).next().find('textarea').attr('placeholder', '답글을 남겨보세요');
	
	$('#'+comment_num).next().find('.btn_cancel').css('display', 'block').addClass('reply_cancel');
	
	$('#'+comment_num).next().find('.btn_register').addClass('reply').attr('data-ref', ref).attr('data-lev', lev).attr('data-seq',seq);
	
	
	
} //function(replyform) end

$(function() {
	option=1;
	getList(option); //처음 로드 될때는 등록순 정렬
	
		$("form").submit(function() {
		if($("#board_pass").val()=='') {
			alert("비밀번호를 입력하세요");
			$("#board_pass").focus();
			return false;
		}
	}) //form
	


	$('.CommentBox').on('keyup','.comment_inbox_text', function() {
		var length=$(this).val().length;
		$(this).prev().text(length+'/200');
		
	}); //keyup','.comment_inbox_text',function() {
	
	$('ul+.CommentWriter .btn_register').click(function() {
		var content=$('.comment_inbox_text').val();
		if(!content){ 
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			url : 'CommentAdd.bo', //원문 등록
			data : {
				comment_id : $("#loginid").val(),
				comment_content : $('.comment_inbox_text').val(),
				comment_board_ref : $("#board_num").val(),
				comment_re_lev : 0, 
				comment_re_seq : 0
			},
			type : 'post',
			success : function(rdata){
				if(rdata == 1) {
					getList(option);
				}
				
			}
		}) //ajax
		
		$('.comment_inbox_text').val(''); 
		$('.comment_inbox_count').text(''); 
		
		
	}) //$('.btn_register').click(function() {
	
	$(".comment_list").on('click', '.comment_tool_button',  function() {
		$(this).next().toggle();
	})
	
	$('.CommentBox').on('click', '.update', function() {
		var num = $(this).attr('data-id');
		var content = $(this).parent().parent().find('textarea').val();
		$.ajax({
			url : 'CommentUpdate.bo',
			data : {comment_num : comment_num, comment_content : comment_content},
			success : function(rdata){
				if(rdata == 1){
					getList(option);
				} //if
			} //success
		}); //ajax
	}) //수정 후 등록 버튼을 클릭한 경우
	
	//수정 후 취소 버튼을 클릭한 경우
		$('.CommentBox').on('click', '.btn_cancel', function() {
		var comment_num = $(this).next().attr('data-id');
		var selector = '#' + comment_num;
		
		$(selector + ' .CommentWriter').remove();
		$(selector + '>.comment_area').css('display', 'block');
		$('#commentItem' + num).hide(); 
		
	}) //수정 후 취소 버튼을 클릭한 경우
	
	//답글 쓰기 등록 버튼을 클릭한 경우
	$('.CommentBox').on('click', '.reply', function() {
		var ref = $(this).attr('data-ref');
		var content = $(this).parent().parent().find('.comment_inbox_text').val();
		var lev = $(this).attr('data-lev');
		var seq = $(this).attr('data-seq');
		
		$.ajax({
			url : 'CommentReply.bo', //원문 등록
			data : {
				id : $("#loginid").val(),
				comment_content : content,
				comment_board_num : $("#board_re_ref").val(),
				comment_re_lev : lev,
				comment_re_ref : ref,
				comment_re_seq : seq
			}, 
			type : 'post',
			success : function(rdata) {
				if(rdata==1){
					getList(option);
				}
			} 
		}) //ajax end
	}) //답변달기 등록
	
	//답변 달기 후 취소 버튼을 클릭한 경우
	$('.CommentBox').on('click', '.reply_cancel', function() {
		$(this).parent().parent().parent().remove();
	})
	
}) //function 
