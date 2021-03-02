
$(function() {

      const countDownTimer = function (id, date) {

var _vDate = new Date(date); // 전달 받은 일자 
var _second = 1000; 
var _minute = _second * 60; 
var _hour = _minute * 60; 
var _day = _hour * 24; 
var timer;

function showRemaining() { 
var now = new Date(); 
var distDt = _vDate - now;

if (distDt < 0) { 
clearInterval(timer);
 document.getElementById(id).textContent = '경매가 종료 되었습니다!'; 
           getmaxprice();
 $('#hope_submit').remove();
 $('#btn-warning').remove();
 $('.info_hope').remove();
 return; 
 } 
 
 var days = Math.floor(distDt / _day); 
 var hours = Math.floor((distDt % _day) / _hour); 
 var minutes = Math.floor((distDt % _hour) / _minute); 
 var seconds = Math.floor((distDt % _minute) / _second);
 
 //document.getElementById(id).textContent = date.toLocaleString() + "까지 : "; 
 document.getElementById(id).textContent = days + '일 '; 
 document.getElementById(id).textContent += hours + '시간 '; 
 document.getElementById(id).textContent += minutes + '분 '; 
 document.getElementById(id).textContent += seconds + '초'; 
 } //showRemaining
 
 timer = setInterval(showRemaining, 1000); 
 
 } //countDownTimer function
 
 var dateObj = new Date(); 
 dateObj.setDate(dateObj.getDate() + 2);


countDownTimer('sample01', $('#end_time').val()) // 시간 셋팅

		function getmaxprice() {
		$.ajax({
			type : "post",
			url : "AuctionhopeGet.ho",
			data : {
				"board_num" : $("#board_num").val(),
				
			},
			
			success : function(rdata) {
			 $('#end_text').text('최고가 : ' + rdata);
				console.log(rdata)

			} //success
		}) //ajax
	} //function


	$("#hope_submit").click(function(){
	
   if($("#hope_price").val()==""){
      alert("희망 가격을 입력해주세요")
      return;
   }
   
      if($("#hope_price").val() <= $("#start_price").val()){
      alert("시작 가격보다 높은 금액을 입력해 주세요.")
      return;
   }
   

   $.ajax({
         url : "AuctionhopeAdd.ho",
         data : {
            hope_id : $("#loginid").val(),
            hope_price: $("#hope_price").val(),
            board_num : $("#board_num").val()
         },
         type : "post",
         success : function(rdata) {
            if (rdata.length > 0) {
               
               alert("경매 참여가 완료되었습니다.")
               
               getmaxprice();
            }
         },
         error :function(request, status, error){
               $("body").append("<div id ='error'>code : " + request.status + "<br>" +"받은데이터 : " + request.responseText + "<br>"
                       +"error status :" +status +"<br>"
                       +"error 메시지 : " + error +"</div>");
              },
              complete :function(){//요청의 실패, 성공과 상관없이 완료될 경우 호출
                 $("body").append("<div id='com'>Ajax가 완료되었습니다.</div>");
              }

      }) //ajax
      

}) //submit



}) //function 


	