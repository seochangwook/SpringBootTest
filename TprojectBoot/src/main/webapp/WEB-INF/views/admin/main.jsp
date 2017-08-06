<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
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
</head>
<body>
<h1>Admin page (${sessionid})</h1>
<p>* logout</p>
<input type="button" id="logoutbutton" value="logout">
</body>
<script type="text/javascript">
$(function(){
	$('#logoutbutton').click(function(){
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