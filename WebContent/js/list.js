$(function(){
		
		//검색 버튼 클릭
		$("button").click(function(){
			//검색어 공백 유효성 검사
			if($("input").val()==''){
				alert("검색어를 입력하세요");
				$("input").focus();
				return false;
			}
			
			word=$(".input-group input").val();
			if(selectedValue==2){
				pattern = /^\w+@\w+[.]\w{3}$/;
				if(!pattern.test(word)){
					alert("이메일 형식에 맞게 입력하세요(@포함)");
					return false;
				}
			}
		});//button click end
		
		//검색어 입력창에 placeholder가 나타나도록 한다.
		$("#viewcount").change(function(){
			selectedValue= $(this).val();
			$("input").val('');
			message=["아이디", "이름", "이메일"]
			$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
		})//$("#viewcount").change end
		
		
		
		var tbl = $("#checkboxTestTbl");
        
        // 테이블 헤더에 있는 checkbox 클릭시
        $(":checkbox:first", tbl).click(function(){
            // 클릭한 체크박스가 체크상태인지 체크해제상태인지 판단
            if( $(this).is(":checked") ){
                $(":checkbox", tbl).attr("checked", "checked");
            }
            else{
                $(":checkbox", tbl).removeAttr("checked");
            }

            // 모든 체크박스에 change 이벤트 발생시키기               
            $(":checkbox", tbl).trigger("change");
        });
         
        // 헤더에 있는 체크박스외 다른 체크박스 클릭시
        $(":checkbox:not(:first)", tbl).click(function(){
            var allCnt = $(":checkbox:not(:first)", tbl).length;
            var checkedCnt = $(":checkbox:not(:first)", tbl).filter(":checked").length;
             
            // 전체 체크박스 갯수와 현재 체크된 체크박스 갯수를 비교해서 헤더에 있는 체크박스 체크할지 말지 판단
            if( allCnt==checkedCnt ){
                $(":checkbox:first", tbl).attr("checked", "checked");
            }
            else{
                $(":checkbox:first", tbl).removeAttr("checked");
            }
        }).change(function(){
            if( $(this).is(":checked") ){
                // 체크박스의 부모 > 부모 니까 tr 이 되고 tr 에 selected 라는 class 를 추가한다.
                $(this).parent().parent().addClass("selected");
            }
            else{
                $(this).parent().parent().removeClass("selected");
            }
        });
        
        $("#removeButton_sel").click(function(){
        	var sdata =""; 
        	var length=$("tbody input:checkbox:checked").length;
        	$("tbody input:checkbox:checked").each(function(index,item){
        		sdata += "member_id="+$(this).parent().next().text() + "&"
        	});
        	
        	console.log(sdata);
        	
        	if(length > 0){
        		sdata = sdata.substring(0, sdata.length - 1);	
        	}
        	delALL(sdata);
        })
	});
function del(member_id){
	delALL("member_id="+member_id);
	
}

function delALL(member_id){
	if(!confirm('정말 삭제하시겠습니까?')){
		return;
	}
	
	console.log("최종:"+member_id);
	
	$.ajax({
		url: "memberDelete.com",
		data: member_id,
		success:function(rdata){
			if(rdata >= 1){
				go(1);
			}
		}
	})
}

function go(page) {
	var limit = $('#viewcount').val();
	var data = "limit=" + limit + "&state=ajax&page=" + page;
	getList(data);
}

function setPaging(href, digit) {
	output += "<li class=page-item>";
	gray = "";
	if (href == "") {
		gray = " gray";// 한 칸 띄우기(유의)
	}
	anchor = "<a class='page-link" + gray + "'" + href + ">" + digit
			+ "</a></li>";
	output += anchor;
}

function getList(sdata) {
	console.log(sdata)
	output = "";
	$.ajax({
		type : "POST",
		data : sdata,
		url : "memberList.com",
		dataType : "json",
		cache : false,
		success : function(data) {
			$("#viewcount").val(data.search_field);
			$("table").find("font").text("글 개수: " + data.listcount);

			if (data.listcount > 0) {// 총 갯수가 0보다 큰 경우
				$("tbody").remove();
				var num = data.listcount - (data.page - 1) * data.limit;
				console.log(num)
				output = "<tbody>";
				$(data.totallist).each(
						function(index, item) {
							
							output += '<tr><td><input type="checkbox"/></td>'
							output += '<td>'+item.member_id + '</td>'
							output += '<td>'+item.member_name + '</td>'
							var id = "'" + item.member_id +"'"
							output += '<td><a href = "javascript:del('+ id + ')" style="color:red">삭제</a>'
							output += '</tr>'
						})
				output += "</tbody>"
				$('table').append(output)// table 완성

				$(".pagination").empty();// 페이지 처리 영역 내용 제거
				output = "";

				digit = '이전&nbsp;'
				href = "";
				if (data.page > 1) {
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				setPaging(href, digit);

				for (var i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href = "";
					if (i != data.page) {
						href = 'href=javascript:go(' + i + ')';
					}
					setPaging(href, digit);
				}
				digit = "&nbsp;다음&nbsp;";
				href="";
				if(data.page < data.maxpage){
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				setPaging(href, digit);
				
				$(".pagination").append(output)
			}//if(data.listcount) end
			else{
				$("table").hide();
			}
		},//success end
		error: function(){
			console.log('에러')
		}
	})
}