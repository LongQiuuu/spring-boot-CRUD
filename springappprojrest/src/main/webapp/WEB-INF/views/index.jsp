<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<link  rel='stylesheet' 
	 href='${pageContext.request.contextPath}/css/styles.css' />
<meta charset="UTF-8">
<title>Spring Boot</title>
</head>
<body>
<div align='center'>
    <h2>Spring Boot 範例 - REST 版</h2>
    <h4>採用Spring Data JPA技術存取資料庫</h4>
    
    <hr>
    <a href="<c:url value='/insertMemberForm' />"> 輸入會員資料 </a><br> 
    <a href="<c:url value='/showAllMembers' />"> 顯示會員資料 </a><br> 
    <hr>
    Context Path: ${pageContext.request.contextPath}<br>
 </div>   
</body>
</body>
</html>
