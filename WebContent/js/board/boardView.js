		function getListev() {
		$.ajax({
			type : "post",
			url : "EvaluationGet.ev",
			data : {
				"board_num" : $("#board_num").val(),
				
			},
			
			success : function(rdata) {
				console.log(rdata)
				switch (rdata) {
				case "1" :
					$(".star_result > label:nth-child(2)").prevAll("label").addClass("on")
					break;
				case "2" :
					$(".star_result > label:nth-child(3)").prevAll("label").addClass("on")
					break;

				case "3":
					$(".star_result > label:nth-child(4)").prevAll("label").addClass("on")
					break;

				case "4":
					$(".star_result > label:nth-child(5)").prevAll("label").addClass("on")
					break;

				case "5":
					$(".star_result > label:nth-child(6)").prevAll("label").addClass("on")
					break;

				
				} //switch
				
				$("#res").text(rdata+ " 점 / 5 점");

			} //success
		}) //ajax
	} //function


function getList(state) {
	option=state; //현재 선택한 댓글 정렬방식을 저장합니다. 1=> 등록순, 2=> 최신순
	
	$.ajax({
		type :"post",
		url :"CommentList.co",
		data : {"page" : 1, "comment_board_ref" : $("#board_num").val(), state : state},
		dataType : "json",
		success : function(rdata){
			$('#count').text(rdata.listCount).css('font-family', 'arial,sans-serif')
			var red1='red';
			var red2='red';
			if(option==1){
				red2='gray';
			}
			if(option==2){
				red1='gray';
			}
			var output="";
			
		if(rdata.boardList.length > 0) {
			output += '<li class="comment_tab_item ' + red1 + '" >'
			  	+ ' 	<a href="javascript:getList(1)" class="comment_tab_button">등록순</a>'
			  	+ '</li>'
			  	+ '<li class="comment_tab_item ' + red2 + '" >'
			  	+ ' 	<a href="javascript:getList(2)" class="comment_tab_button">최신순</a>'
			  	+ '</li>';
			$('.comment_tab_list').html(output);
			output='';
			$(rdata.boardList).each(function() {
				var lev = this.comment_re_lev;
				var comment_reply = '';
				if(lev==1) {
					comment_reply = ' CommentItem--reply lev1'; //댓글의 여백
				} else if(lev==2){
					comment_reply = ' CommentItem--reply lev2';
				}
				var profile=this.member_file;
				var src='image/profile.png';
				if(profile){
					src='memberupload/'+profile;
				}
				
				output += '<li id="' + this.comment_num + '" class="CommentItem' + comment_reply + '">'
						+ '<div class="comment_area">'
						+ '	<img src="' + src + '" alt="프로필 사진" width="36" height="36">'
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
							+ this.comment_re_ref +')" class="comment_info_button" >답글쓰기</a>'
				}
				output += '</div>' //comment_info_box
					
					if($("#loginid").val()==this.comment_id){
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

//더보기 - 수정 클릭한 경우 수정 폼을 보여 줍니다.
function updateForm(comment_num) {
	//선택한 내용을 구합니다.
	var content=$('#'+comment_num).find('.text_comment').text();
	var countNum=content.length;
	
	var selector = '#'+comment_num+ ' .comment_area'
	var obj=$(selector).hide(); //selector 영역 숨겨요 - 수정에서 취소를 선택하면 보여줄 예정입니다. 
	
	//$('.comment_list+.CommentWriter').clone() : 기본 글쓰기 영역 복사합니다.
	//글이 있던 영역에 글을 수정할 수 있는 폼으로 바꿉니다.
	$('#'+comment_num).append($('.comment_list+.CommentWriter').clone());
	
	//수정 폼의 textarea에 내용을 나타냅니다.
	$('#'+comment_num).find('textarea').val(content);
	
	//수정 폼의 글자수
	$('#'+comment_num).find('.comment_inbox_count').text(countNum+"/200");

	
	//수정할 글 번호를 속성 date-id에 나타내고 클래스 udpate를 추가합니다.
	$('#'+comment_num).find('.btn_register').attr('data-id', comment_num).addClass('update');
	
	//폼에서 취소를 사용할 수 있도록 보이게 합니다.
	$('#'+comment_num).find('.btn_cancel').css('display','block')
} //function end

//더보기 -> 삭제
function del(comment_num) {
	if(!confirm('정말 삭제하시겠습니까'))
		//$('#commentItem' + comment_num).hide(); //'수정 삭제' 영역 숨겨요.< 이거하니깐 안됨.>

		return;
	
	$.ajax({
		url : 'CommentDelete.co',
		data : {comment_num:comment_num},
		success : function(rdata) {
			if(rdata==1){
				getList(option);
			}
		}
	})
} //function(del) end

//답글 달기 폼
function replyform(comment_num, lev, seq, ref){
	var output = '<li class="CommentItem CommentItem--reply lev'
		+ lev + ' CommentItem-form"></li>'
		
		//선택한 글 뒤에 답글 폼을 추가합니다.
		$('#'+comment_num).after(output).attr('onclick', 'return false');
	
	
	//글쓰기 영역 복사합니다.
	output=$('.comment_list+.CommentWriter').clone();
	
	//선택한 글 뒤에 답글 폼 생성합니다.
	$('#'+comment_num).next().html(output);
	
	//답글 폼의 <textarea>의 속성 'placeholder'를 '답글을 남겨보세요'로 바꾸어 줍니다.
	$('#'+comment_num).next().find('textarea').attr('placeholder', '답글을 남겨보세요');
	
	//답글 폼의 '.btn_cancle'을 보여주고 클래스 'reply_cancel'를 추가합니다.
	$('#'+comment_num).next().find('.btn_cancel').css('display', 'block').addClass('reply_cancel');
	
	//답글 폼의 '.btn_register'에 클래스 'reply' 추가합니다.
	//속성 'data-ref'에 ref, 'data-lev'에 lev, 'data-seq'에 seq값을 설정합니다.
	$('#'+comment_num).next().find('.btn_register').addClass('reply').attr('data-ref', ref).attr('data-lev', lev).attr('data-seq', seq);
	

	
	
} //function(replyform) end


$(function() {
	getListev();
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
	
	
	//댓글 등록을 클릭하면 데이터베이스에 저장 -> 저장 
	$('ul+.CommentWriter .btn_register').click(function() {
		var content=$('.comment_inbox_text').val();
		if(!content){ //내용없이 등록 클릭한 경우
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			url : 'CommentAdd.co', //원문 등록
			data : {
				comment_id : $('#loginid').val(),
				comment_content : $('.comment_inbox_text').val(),
				comment_board_ref : $("#board_num").val(),
				comment_re_lev : 0, //원문인 경우 comment_re_seq는 0, comment_re_ref는 댓글의 원문 글번호
				comment_re_seq : 0
			},
			type : 'post',
			success : function(rdata){
				if(rdata == 1) {
					getList(option);
				}
				
			}
		}) //ajax
		
		$('.comment_inbox_text').val(''); //textarea 초기화
		$('.comment_inbox_count').text(''); //입력한 글 카운트 초기화
		
		
	}) //$('.btn_register').click(function() {
	
	//더보기를 클릭하면 수정과 삭제 영역이 나타나고 다시 클릭하면 사라져요-toggle() 이용
	$(".comment_list").on('click', '.comment_tool_button',  function() {
		$(this).next().toggle();
	})
	
	//수정 후 등록 버튼을 클릭한 경우
	$('.CommentBox').on('click', '.update', function() {
		var num = $(this).attr('data-id');
		var content = $(this).parent().parent().find('textarea').val();
		$.ajax({
			url : 'CommentUpdate.co',
			data : {comment_num:num, comment_content:content},
			success : function(rdata){
				if(rdata==1){
					getList(option);
				} //if
			} //success
		}); //ajax
	}) //수정 후 등록 버튼을 클릭한 경우
	
	//수정 후 취소 버튼을 클릭한 경우
		$('.CommentBox').on('click', '.btn_cancel', function() {
			//댓글 번호를 구합니다. 
		var comment_num = $(this).next().attr('data-id');
		var selector='#' +comment_num;
		
		//$('#'+comment_num).find('.CommentWriter').remove();
		//.CommentWriter 영역 삭제 합니다. 
		$(selector + ' .CommentWriter').remove();

		
		//숨겨 두었던 .comment_area영역 보여줍니다. 
		//$('#'+comment_num).find('.comment_area').attr({"style":"display:block"});
		$(selector + '>.comment_area').css('display', 'block');
		
		console.log($('#commentItem' + comment_num).css('display')) //'block'
		$('#commentItem' + comment_num).hide(); //수정 -> 삭제 영역 숨겨요
		
	}) //수정 후 취소 버튼을 클릭한 경우
	
	//답변 달기 등록 버튼을 클릭한 경우
	$('.CommentBox').on('click', '.reply', function() {
		var comment_re_ref = $(this).attr('data-ref');
		var content=$(this).parent().parent().find('.comment_inbox_text').val();
		var lev = $(this).attr('data-lev');
		var seq = $(this).attr('data-seq');
		
		$.ajax({
			url : 'CommentReply.co', //답글 등록
			data : {
				comment_id : $("#loginid").val(),
				comment_content : content,
				comment_board_ref: $("#board_num").val(),
				comment_re_lev : lev,
				comment_re_ref : comment_re_ref,
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
	
ind = -1;
   $(".star_box > a").on('click', function() {

      $(this).parent().children("a").removeClass("on");
      $(this).addClass("on").prevAll("a").addClass("on")

      ind = $(this).index() + 1
      console.log(ind);
      $("#lev").text(ind + " 점 / 5 점");

   }) //.star_box

   $("#starsub").click(function() {
      if($("#loginid").val()==""){
         alert("로그인후에 평점을 등록해주세요")
         return;
      }
      
      
      
      
      if (ind == -1) {
         alert("평점을 선택해주세요")
         return;
      }


      $.ajax({
         url : "EvaluationAdd.ev",
         data : {
            evaluation_name : $("#loginid").val(),
            evaluation : ind ,
            board_num : $("#board_num").val()
         },
         type : "post",
         success : function(rdata) {
            if (rdata.length > 0) {
               $("star_box > a").text("")
               $(".on").removeClass("on")
               $("#lev").text("");
               
               getListev();
            }
         },
         error :function(request, status, error){
               $("body").append("<div id ='error'>code : " + request.status + "<br>" +"받은데이터 : " + request.responseText + "<br>"
                       +"error status :" +status +"<br>"
                       +"error 메시지 : " + error +"</div>");
              },

              complete :function(){//요청의 실패, 성공과 상관없이 완료될 경우 호출
                 $("body").append("");

              }

      }) //ajax

   }) //function();
   if($("#board_writer").val() == $("#loginsession").val()){
      $("#star_box").html("")
      
   }
   $("#remstar").click(function(){
      $("div.star_box > .on").removeClass("on")
      $("#lev").text("");
   })
   
   $('#previous').click(function(){
      var g = $("#board_num").val()-1
      location.href='BoardDetailAction.bo?board_num='+ g
      
   })
      $('#nextp').click(function(){
      var g = Number($("#board_num").val())+1
      
      
      location.href='BoardDetailAction.bo?board_num='+ g
   })


}) //function 


   


	