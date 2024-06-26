<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>會員資料</title>
<script type="text/javascript" src="<c:url value='/webjars/jquery/3.5.1/jquery.js' />" ></script>


<script>
window.onload = function() {
	var formatter = new Intl.NumberFormat('en-US', {
		  style: 'currency',
		  currency: 'NTD',
		  minimumFractionDigits: 2, 
		  maximumFractionDigits: 2, // (causes 2500.99 to be printed as $2,501)
		});
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/members' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<table border='1'>";
			content += "<tr height='48' bgcolor='CCCC00' ><th colspan='7' style='font-size: 26px'>會員基本資料</th></tr>"; 
			content += "<tr bgcolor='#ff9333' ><th width='80'>編輯</th><th width='100'>帳號</th><th width='180'>姓名</th>" + 
	           "<th width='90'>餘額</th><th width='140'>生日</th>" + 
	           "<th width='280'>註冊時間</th><th width='80'>Extra</th></tr>" ;
			var members = JSON.parse(xhr.responseText);
			for(var i=0; i < members.length; i++){
				tmp = "<c:url value='/membersEdit/' />";
				var bgcolor = "";
				if (i %2 == 0) {
					bgcolor = "'99ddff'";
				} else {
					bgcolor = "'88dd00'";
				}
				
			    content += 	"<tr bgcolor=" + bgcolor + " ><td width='70'><a href='" + tmp + members[i].pk + "'>" + 
			    			"<img width='36' height='36' src='<c:url value='/images/edit.png' />' ></a>" + 
			                "<td align='center'>" + members[i].memberId + "</td>" +
		        	       	"<td>" + members[i].name + "</td>" +
		            	   	"<td align='right'>" + formatter.format(members[i].balance) + "&nbsp;</td>" +
							"<td>" + members[i].birthday + "</td>" +
							"<td>" + members[i].registerTime + "</td>" +
							"<td>" + members[i].extra + "</td>" +
		               		"</tr>";
			}
			content += "</table>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML = content;
		}
	}
		
}
</script>
</head>
<body>
<div class='center' >
	<h2>會員資料</h2>
	<hr>
	<div class='center'  id='somedivS'></div>
	</div>
</body>
	
<p/>
<div align='center'>
<a href="<c:url value='/' />">回前頁</a>
</div>
</body>
</html>
