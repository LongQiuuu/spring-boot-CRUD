<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<style type="text/css">
fieldset {
  background-color: #F8E0C7;
}

legend {
  background-color: gray;
  color: white;
  padding: 5px 10px;
}
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
<meta charset="UTF-8">
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />	
</head>
<body>
    <div align='center'>
    <h3>更新會員資料</h3>
    <c:set var='updateurl' value='${pageContext.request.contextPath}/members/${id}' />
	<form:form method="POST" style="width:660px;" action="${updateurl}" modelAttribute="member">
		<fieldset >
			<legend>會員資料</legend>
		    <input type='hidden' name='_method' value='PUT'> <br>
		    <form:input type='hidden' path='pk' /><br>&nbsp;
			<table>
				<tr>
					<td>&nbsp;帳號：
					    <form:input style="background-color: #eeeeee;" path='memberId' readonly="true"/><br>&nbsp;
					    <form:errors path='memberId' cssClass="error" />
					</td>
				</tr>
				<tr>
					<td width='400'>&nbsp;姓名：
					    <form:input type="text"	path='name' id='name'/><br>&nbsp; 
					    <form:errors path='name' cssClass="error" />
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;餘額：
					    <form:input type="text"	path="balance" id='balance' /><br>&nbsp; 
					    <form:errors path="balance" cssClass="error" />
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;生日： 
						<form:input type="text" path="birthday" id='birthday' size='24' /> 
						<form:errors path="birthday" cssClass="error" />
					</td>
				</tr>
				<tr height='50'>
					<td align='center'><button id='sendData'>送出</button></td>
				</tr>
				
			</table>


	</fieldset>
		</form:form>
	<br>
	<a href="<c:url value='/'/> ">回前頁</a>
    </div>
</body>
</html>