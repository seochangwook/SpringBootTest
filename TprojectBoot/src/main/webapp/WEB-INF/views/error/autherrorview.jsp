<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- jQuery, bootstrap CDN -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script> <!-- msie 문제 해결 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<title>Access Denied Page</title>
</head>
<body>
	<h1>Login Error (Access Denied)</h1>
	<input type="button" id="backbutton" value="back login">
</body>
<script type="text/javascript">
$(function(){
	$('#backbutton').click(function(){
		$.ajax({
			url: "http://localhost:8080/adminlogoutajax",
			type: 'POST',
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				var url = "http://localhost:8080/login.do";
		        $(location).attr("href", url);
			},
			error: function(retVal, status, er){
				console.log('logout fail...');
			}
		});
	});
});
</script>
</html>