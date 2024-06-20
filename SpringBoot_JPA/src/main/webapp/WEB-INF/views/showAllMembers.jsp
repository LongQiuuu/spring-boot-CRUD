<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage='true'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript">
function delfun(pk, name){
	if (confirm("確定刪除此筆紀錄(會員姓名: " + name + ") ?")){
		var form = document.forms[0];
		form.action = "<c:url value='/members/' />" + pk;
		form.submit();
	}
	
}

</script>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顯示所有會員</title>
</head>
<body>
<div align="center">
<h3>會員清單</h3>
<hr>

<p>
   <c:if test='${empty memberList}'>
		查無會員資料<br>
  </c:if> 
   <c:forEach var="member"  varStatus="statusX" items="${memberList}">
      <c:if test="${statusX.first}" >
         <c:out value="<table border='1' cellspacing='5' cellpadding='5' >" escapeXml="false"/>
    	 <tr bgcolor="CCCC00">
            <th colspan='7'>會員基本資料</th>
         </tr>
         
         <c:out value="<tr bgcolor='#ff9333'><th width='90px'>帳號</th><th  width='100px'>姓名</th><th  width='140px'>餘額</th><th  width='120px'>生日</th><th  width='260px'>註冊時間</th><th width='80px'>保留欄</th><th width='48px'>刪除</th></tr>" escapeXml='false'/>
      </c:if>		         
      <c:choose>
         <c:when test="${ statusX.count % 2 == 0 }">
            <c:set var="colorVar" value="99ddff" />
         </c:when>
         <c:otherwise>
            <c:set var="colorVar" value="88dd00" />
         </c:otherwise>
      </c:choose>
                    
        <tr bgcolor="${colorVar}">
            <td><a href="<c:url value='/members' />/${member.pk}">${member.memberId}</a></td>
            <td>${member.name} </td>
            <td style="text-align:right"> <fmt:formatNumber pattern="#,###.00" type="number" value='${member.balance}' />&nbsp;</td>
            <td>${member.birthday} </td>
            <td>${member.registerTime}</td>
            <td>${member.extra} </td>
            <td><button id="delbtn" onclick="delfun('${member.pk}', '${member.name}')">刪除</button></td>
        </tr>
        <c:if test="${statusX.last}" >
             <c:out value="</table>" escapeXml="false" />
        </c:if>		                      
	</c:forEach>
</div>
<p>
<div class='center'>
<hr>
<small><a href="<c:url value='/' />">回首頁</a></small>
</div>
<form method='POST'>
  <input type='hidden' name='_method'  value='DELETE' >
</form>
</body>
</html>