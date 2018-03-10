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
<h1>Spring Boot test page</h1><br>
<label>* Dialog Test</label><br>
<input type="button" id="btntest1" value="click">
<br><br>
<label>* Ajax Test</label><br>
<p>value 1:</p>
<input type="text" id="value1" placeholder="input value1"><br>
<p>value 2:</p>
<input type="text" id="value2" placeholder="input value2"><br><br>
<input type="button" id="btntest2" value="click">
<br><br>
<label>* MongoDB Test</label><br>
<p>Search name:</p>
<input type="text" id="searchname" placeholder="input username"><br><br>
<input type="button" id="btntest3" value="search">
<br><br>
<label>* Mybatis(Oracle) Test</label><br>
<input type="button" id="btntest4" value="member load">
<div id="memberlistdiv">
</div>
<br><br>
<label>* Spring Security Test(Form, Basic)</label><br>
<form name='TransTest' id='tForm' method='get' action="${serveraddress}">
<p><button name='subject' type='submit'>관리자 페이지 이동(form)</button></p>
</form>
<form name='TransTest' id='tForm' method='get' action='http://localhost:8080/admin/user'>
<p><button name='subject' type='submit'>일반 페이지 이동</button></p>
</form>
<form name='TransTest' id='tForm' method='get' action='http://localhost:8080/admin/mainbasic'>
<p><button name='subject' type='submit'>관리자 페이지 이동(basic)</button></p>
</form>
<<br>
	<div>
		<label>* Spring JPA, Hibernate Test</label><br>
		<input type='button' value='load all user' id='btntest5'>
		<br>
		<input type="text" placeholder="input search id" id="searchid">&nbsp
		<input type="button" value="search address" id="searchidbtn">&nbsp
		<input type="button" value="delete user" id="deleteuserid">&nbsp
		<br><br>
		<input type="text" placeholder="input search address" id="searchemail">&nbsp
		<input type="text" placeholder="input search id" id="searcheid">&nbsp
		<input type="button" value="search phonenumber" id="searchemailbtn">&nbsp
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('#btntest1').click(function(){
		var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>test</p>',{
			title: 'Boot Test',
			type: 'confirmation',
			print: false,
			width: 760,
			position: ['right - 20', 'top + 20'],
			buttons: ['닫기'],
			onClose: function(caption){
				if(caption == '닫기'){
					//alert('yes click');
				}
			}
		});
	});
	$('#btntest2').click(function(){
		var value1text = $('#value1').val();
		var value2text = $('#value2').val();
		
		var trans_objeect = 
    	{
        	'value1':value1text,
        	'value2':value2text
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: "http://localhost:8080/ajaxtest",
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>ajax call success</p>',{
					title: 'Boot Test',
					type: 'confirmation',
					print: false,
					width: 760,
					position: ['right - 20', 'top + 20'],
					buttons: ['닫기'],
					onClose: function(caption){
						if(caption == '닫기'){
							//alert('yes click');
						}
					}
				});
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#btntest3').click(function(){
		var searchname = $('#searchname').val();
		
		var trans_objeect = 
    	{
        	'name':searchname
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: "http://localhost:8080/memberinfocall",
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				var memberlist = [];
				memberlist = retVal.memberlist;
				
				if(memberlist.length == 0){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>'+searchname+' 은 존재하지 않은 유저입니다.</p>',{
						title: 'Boot Test',
						type: 'warning',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}
				
				else if(memberlist.length > 0){
					var userinfo = memberlist[0].username + '/' + memberlist[0].password + '/' + memberlist[0].role;
					
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>검색성공 : ' + userinfo + '</p>',{
						title: 'Boot Test',
						type: 'confirmation',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#btntest4').click(function(){
		var searchname = $('#searchname2').val();
		
		var trans_objeect = 
    	{
        	'name':searchname
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: "http://localhost:8080/memberinfocalloracle",
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				console.log('ajax succcess');
				var memberlist = [];
				var printStr = '';
				memberlist = retVal.memberlist;
				
				$('#memberlistdiv').empty(); //다시 테이블을 보여주기 위해서 HTML코드 적용//
				
				if(memberlist.length == 0){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>등록된 멤버가 없습니다.</p>',{
						title: 'Boot Test',
						type: 'warning',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}
				
				else if(memberlist.length > 0){
					printStr = "<div id='listmember'>";
		        	printStr += "<table class='table table-hover'>";
		        	printStr += "<thead>";
		        	printStr += "<tr>";
		        	printStr += "<th>구분</th>";
		        	printStr += "<th>이름</th>";
		        	printStr += "<th>나이</th>";
		        	printStr += "<th>주소</th>";
		        	printStr += "<th>사진</th>";
		        	printStr += "</tr>";
		        	printStr += "</thead>"; 
		        	printStr += "<tbody>";
		        	
		        	for(var i=0; i<memberlist.length; i++){
		           		printStr += "<tr>";
		            	printStr += "<td>"+(i+1)+"</td>";
		            	printStr += "<td>"+memberlist[i].userName+"</td>";
		            	printStr += "<td>"+memberlist[i].userAge+"</td>";
		            	printStr += "<td>"+memberlist[i].userAddress+"</td>";
		            	printStr += "<td><img src='/images/"+memberlist[i].userImage+"' width='30' height='30'></td>";
	                	printStr += "</tr>"; 
		           	}
		        	
		        	printStr += "</tbody>";
		            printStr += "</table>";
		            printStr += "</div>";
		            
		            $('#memberlistdiv').append(printStr); //다시 테이블을 보여주기 위해서 HTML코드 적용//
				}
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#btntest5').click(function(){
		var trans_objeect = 
		{
	    	'':''
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: '<c:url value="/jpatest.do"/>',
			type: 'POST',
			dataType: 'json',
			data:trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>ajax call success</p>',{
					title: 'jqGrid Test',
					type: 'confirmation',
					print: false,
					width: 760,
					position: ['right - 20', 'top + 20'],
					buttons: ['닫기'],
					onClose: function(caption){
						if(caption == '닫기'){
							//alert('yes click');
						}
					}
				});
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#searchidbtn').click(function(){
		var search_id = $('#searchid').val();
		
		var trans_objeect = 
		{
	    	'userid':search_id
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: '<c:url value="/jpatestsearch.do"/>',
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				if(retVal.result == 'success'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>검색성공</p>',{
						title: 'jqGrid Test',
						type: 'confirmation',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				} else if(retVal.result == 'fail'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>존재하지 않는 유저입니다.</p>',{
						title: 'jqGrid Test',
						type: 'warning',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}	
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#searchemailbtn').click(function(){
		var search_email = $('#searchemail').val();
		var search_id = $('#searcheid').val();
		
		var trans_objeect = 
		{
	    	'useremail':search_email,
	    	'userid': search_id
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: '<c:url value="/jpatestsearchemail.do"/>',
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				if(retVal.result == 'success'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>검색성공</p>',{
						title: 'jqGrid Test',
						type: 'confirmation',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				} else if(retVal.result == 'fail'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>존재하지 않는 유저입니다.</p>',{
						title: 'jqGrid Test',
						type: 'warning',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}	
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
	$('#deleteuserid').click(function(){
		var search_id = $('#searchid').val();
		
		var trans_objeect = 
		{
	    	'userid': search_id
	    }
		var trans_json = JSON.stringify(trans_objeect); //json으로 반환//
		
		$.ajax({
			url: '<c:url value="/jpatestdeleteuser.do"/>',
			type: 'POST',
			dataType: 'json',
			data: trans_json,
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(retVal){
				if(retVal.result == 'success'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>제거성공</p>',{
						title: 'jqGrid Test',
						type: 'confirmation',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				} else if(retVal.result == 'fail'){
					var infodialog = new $.Zebra_Dialog('<strong>Message:</strong><br><br><p>존재하지 않는 유저입니다.</p>',{
						title: 'jqGrid Test',
						type: 'warning',
						print: false,
						width: 760,
						position: ['right - 20', 'top + 20'],
						buttons: ['닫기'],
						onClose: function(caption){
							if(caption == '닫기'){
								//alert('yes click');
							}
						}
					});
				}	
			},
			error: function(retVal, status, er){
				alert("error: "+retVal+" status: "+status+" er:"+er);
			}
		});
	});
});
</script>
</html>