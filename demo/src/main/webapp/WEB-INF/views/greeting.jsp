<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第一個網頁</title>
</head>
<body>
<div align='center'>
	<h2>${helloMessage}</h2>
	<hr>
	
	 <a href="<c:url value='/' />"> 回前頁 </a><br>
</div>
</body>
</html>