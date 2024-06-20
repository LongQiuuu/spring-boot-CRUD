<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>Show Customer Data</title>
</head>
<body>
	<div align='center'>
		<h3>客戶資料</h3>
		<hr>
		<table border='1'>
			<tr>
				<th width='60'>編輯</th>
				<th width='160'>客戶姓名</th>
			</tr>
			<c:choose>
				<c:when test="${not empty customerBeanList}">
					<c:forEach var='customer' items="${customerBeanList}">
						<tr> 
							<td align='center'><a
								href="<c:url value='/_01_customer/customers/${customer.customerId}' />">${customer.customerId}</a></td>
							<td>${customer.name}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				查無Customer資料
			</c:otherwise>
			</c:choose>
		</table>
		<br> <a href="<c:url value='/_01_customer/index' />">回前頁</a>
	</div>
	<pre>
			必須在application.properties中定義 HiddenHttpMethodFilter:
			    spring.mvc.hiddenmethod.filter.enabled=true
			瀏覽器才能間接送出PUT與DELETE請求
	</pre>
</body>
</html>