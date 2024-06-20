<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<div align='center'>
		<table border='1'>
		
		<tr>
			<th>學號</th>
			<th>姓名</th>
			<th>生日</th>
			<th>電郵</th>	
		</tr>
		<c:forEach var ='student' items = '${studentList }'>
		<tr>
			
			
			<td>${student.studentId}</td>
			<td>${student.name}</td>
			<td>${student.birthday}</td>
			<td>${student.email}</td>
				
		</tr>	
			
		</c:forEach>
		</table>
		
		
		</div>
	</body>
</html>