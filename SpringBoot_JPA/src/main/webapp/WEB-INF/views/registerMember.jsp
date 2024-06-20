<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>Registration</title>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 8pt;
}

.fieldset-auto-width {
	display: inline-block;
}
</style>


</head>
<body>
	<div align='center'>
		<h3>輸入會員資料</h3>
		<font color='darkgreen'>&nbsp;${insertSuccess}</font>
		<form:form method='POST' modelAttribute="member" >
		
		<fieldset style='display: inline-block; width: 660px;'>
			<legend>填寫下列資料</legend>
			<table border='1'>
				<tr height='60'>
					<td width='400'>&nbsp;帳號: <form:input type="text" path="memberId" id='memberId' />
					                            <form:errors path="memberId" cssClass="error" />
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;姓名: <form:input type="text" path="name" id='name' />
					                            <form:errors path="name" cssClass="error" />
					</td>
					
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;餘額: <form:input type="text" path="balance" id='balance' />
					            <form:errors path="balance" cssClass="error" />
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;生日: <form:input type="text" path="birthday" 
						                             id='birthday' size='24' />
					<form:errors path="birthday" cssClass="error" />
					</td>
				</tr>
				<tr height='50'>
					<td align='center'><button id='sendData'>送出</button></td>
				</tr>
			</table>
		</fieldset>
		</form:form>
		<hr>
		<p>
			<a href="<c:url value='/' />">回前頁</a>
			
		<hr>
		
	</div>

</body>
</html>