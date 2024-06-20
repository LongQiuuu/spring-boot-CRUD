<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
</head>
<body>
<div align='center'>
	<h2>Spring Boot 範例</h2>
	<h3>使用Spring Data JPA技術</h3>
    <hr>
    <a href="<c:url value='/employee' />">員工資料列表</a><p>
    <a href="<c:url value='/ShowEmployeesByPage' />">員工資料列表(分頁顯示)</a><p>
    <a href="<c:url value='/employee/InsertEmployeeForm' />">新增員工資料</a><p>
</div>
</body>
</html>