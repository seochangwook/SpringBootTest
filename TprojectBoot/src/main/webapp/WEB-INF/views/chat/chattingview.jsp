<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" type="image/png"  href="resources/images/svnicon.png"/> <!-- favicon fix -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- jQuery, bootstrap CDN -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script> <!-- msie 문제 해결 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<!-- Zebra-Dialog CDN -->
	<script src="/js/dialog/zebra_dialog.src.js"></script>
	<link rel="stylesheet" href="/css/dialog/zebra_dialog.css" type="text/css"/>
	<!-- SocketJS CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<!-- STOMP CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<title>Chatting page</title>
</head>
<body>
	<h1>Chatting Page</h1>
	<div>
		<input type="button" id="chattinglistbtn" value="채팅 참여자 리스트">
		<input type="button" id="outroom" value="채팅방 나가기">
	</div>
	<br>
	<div>
		<textarea id="chatOutput" name="" class="chatting_history" rows="30" cols="70"></textarea>
		<div class="chatting_input">
			<input id="chatInput" type="text" class="chat">&nbsp
			<input type="button" id="sendbtn" value="전송">
		</div>
	</div>
	<input type="hidden" value='${userid}' id="sessionuserid">
	<input type="hidden" value='${chatoutaddress}' id="chatoutaddress">
</body>
<script type="text/javascript">
$(function(){
	$("#chattinglistbtn").click(function(){
		var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>채팅방 참여자 리스트</p>',{
			title: 'Chatting List',
			type: 'confirmation',
			print: false,
			width: 260,
			buttons: ['닫기'],
			onClose: function(caption){
				if(caption == '닫기'){
					//alert('yes click');
				}
			}
		});
    });
});
</script>
<script type="text/javascript">
//웹소켓을 사용할 수 있도록 페이지 로딩 완료 시 웹소켓 초기화//
document.addEventListener("DOMContentLoaded", function(){
	WebSocket.init();
});
//웹소켓 설정//
var WebSocket = (function(){
	var stompClient;
	
	var textArea = document.getElementById("chatOutput");
	var inputElm = document.getElementById("chatInput");
	var sendbtn = document.getElementById("sendbtn");
	var outroombtn = document.getElementById("outroom");
	var usersessionid = document.getElementById("sessionuserid");
	var chatoutaddress = document.getElementById("chatoutaddress");
	
	//연결//
	function connect(){
		//SockJS, STOMP관련 객체 생성//
		var socket = new SockJS("/websockethandler");
		stompClient = Stomp.over(socket);
		
		stompClient.connect({}, function(){
			//메세지를 받는다. 각각의 구독//
			stompClient.subscribe('/topic/roomId', function(msg){
				printMessage(JSON.parse(msg.body).sendMessage + '/' + JSON.parse(msg.body).senderName);
			});
			
			stompClient.subscribe('/topic/out', function(msg){
				printMessage(msg.body);
			});
			
			stompClient.subscribe('/topic/in', function(msg){
				printMessage(msg.body);
			});
			
			//입장글//
			stompClient.send("/app/in", {}, usersessionid.value + ' is in chatroom');
		});
	}
	
	//연결해제//
	function disconnect() {
	    	if (stompClient !== null) {
	    		stompClient.send("/app/out", {}, usersessionid.value + ' is out chatroom');
	    		stompClient.disconnect();
	    		
	    		window.location.href=chatoutaddress.value;
	    	}
	}
	
	//메세지 전송 버튼 이벤트//
	sendbtn.onclick = function(){
		sendMessage(inputElm.value);
		clear(inputElm);
	}
	
	//채팅방 나가기 버튼 이벤트//
	outroombtn.onclick = function(){
		disconnect();
	}
	
	function printMessage(message){
		textArea.value += message + "\n";
	}
	
	//입력창 초기화//
	function clear(input){
		input.value = "";	
	}
	
	//메세지 전송//
	function sendMessage(text){
		//send()부분에 매개변수로 MessageMapping을 입력//
		//세번째 인자로 보내고자 하는 정보를 JSON으로 설정하여 보낸다.(관련 VO존재 필요)//
		stompClient.send("/app/hello", {}, JSON.stringify({'sendMessage':text, 'senderName':''+usersessionid.value}));
	}
	
	//초기화//
	function init(){
		connect();
	}
	
	return {
		init : init
	}
})();
</script>
</html>