<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>
<script>
var pageNo = 0;
var totalPage  = 0;
// 本網頁一開始時先向後端發出非同步請求：/misc/_09/pagingEmployeeData.json，要求第一頁
var xhr = new XMLHttpRequest();
xhr.open("GET", "<c:url value='/pagingEmployeeData.json' />", true);
xhr.send();
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 ) {
		if (xhr.status == 200){
			var responseData = xhr.responseText;
			displayPageEmployees(responseData);   // 顯示讀取到的非文字性資料
		} else {
			alert(xhr.status);
		}
	}
}
// 當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
function asynRequest(id) {
	var xhr = new XMLHttpRequest();
	var no = 0;
    var queryString = "";     		// queryString紀錄查詢字串
	    if (id == "first") {		// 算出查詢字串中，要送出的pageNo為何?
	    	no = 1;
	    } else if (id == "prev") {
	    	no = pageNo - 1;
	    } else if (id == "next") {
	    	no = pageNo + 1;
	    } else if (id == "last") {
	    	no = totalPage;	    	
	    }
	    // 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
	    // 注意，查詢字串的前面有問號
	    queryString = "?pageNo=" + no + "&totalPage=" + 	totalPage;

		xhr.open("GET", "<c:url value='/pagingEmployeeData.json'/>" + queryString , true);
		xhr.send();
		xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var responseData = xhr.responseText;
			displayPageEmployees(responseData);
		}
	}
}

function displayPageEmployees(responseData){
  var content = "<table width='960' border='1'><tr height='42' bgcolor='#fbdb98'>";
      content +=  "<th style='text-align:center; width:80px;'>員工編號</th><th style='text-align:center; width:80px;'>姓名</th>";
      content +=  "<th style='text-align:center; width:300px;'>電郵地址</th><th style='text-align:center; width:180px;'>生日</th>";
      content +=  "<th style='text-align:center; width:80px;'>阿凡達</th>";
      content +=  "<th style='text-align:center; width:200px;'>建檔日期</th>";
	  content +=  "</tr>";
 	  console.log(content);
	var mapData = JSON.parse(responseData);
	pageNo = mapData.currPage;
	totalPage  = mapData.totalPage;
	
	var employees = mapData.employees;		// 傳回一個陣列
	var bgColor = "";                       // 每一項商品的背影 
	for(var i=0; i < employees.length; i++){
		bgColor = (i % 2 == 0 ? "#d4f5b2" : "#b2f5e5");
		content += "<tr height='80' bgcolor='" + bgColor + "'>" + 
		           "<td style='text-align:center; width:80px;' >" + employees[i].employeeId + "</td>" + 
	               "<td style='text-align:center; width:80px;' >" + employees[i].name + "</td>" +
	               "<td style='text-align:left; width:300px;' >" + employees[i].email + "</td>" +
	               "<td style='text-align:center; width:180px;'>" + employees[i].birthday + "</td>" +
	               "<td style='text-align:center; width:80px;' ><img width='50' height='60' src='" + employees[i].dataUri + "'></td>" + 
	               "<td style='text-align:center; width:200px;' >" + employees[i].created_at+ "</td>" + 
	               
		           "</tr>";    
	}
	content += "</table>";
	document.getElementById("somedivS").innerHTML = content;
	
	
	var navContent = "<table border='1' ><tr height='36' bgcolor='#fbdb98'>" ;
	if (pageNo != 1){
		navContent += "<td width='80' align='center'><button id='first'>第一頁</button></td>";
		navContent += "<td width='80' align='center'><button id='prev'>前一頁</button></td>";
	}  else {
		navContent += "<td width='80' align='center'>&nbsp;</td>";
		navContent += "<td width='80' align='center'>&nbsp;</td>";
	}
	navContent += "<td width='200' align='center'>第" + pageNo + "頁 / 共" + totalPage + "頁</td>";
	if (pageNo != totalPage){
		navContent += "<td width='80' align='center'><button id='next'>下一頁</button></td>";
		navContent += "<td width='80' align='center'><button id='last'>最末頁</button></td>";
	}  else {
		navContent += "<td width='80' align='center'>&nbsp;</td>"
		navContent += "<td width='80' align='center'>&nbsp;</td>";
	}
	document.getElementById("navigation").innerHTML = navContent;
	var firstBtn = document.getElementById("first");
	var prevBtn  = document.getElementById("prev");
	var nextBtn  = document.getElementById("next");
	var lastBtn  = document.getElementById("last");
	if (firstBtn != null) {
		firstBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (prevBtn != null) {
		prevBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (nextBtn != null) {
		nextBtn.onclick=function(){
			asynRequest(this.id);
		}
	}
	
	if (lastBtn != null) {
		lastBtn.onclick=function(){
			asynRequest(this.id);				
		}
	}	
}
</script>
</head>
<body>
<div align='center'>
	<h3>分頁顯示員工資料(JSON)</h3>
	<hr>
	<div id='somedivS'  style='height:260px;'> </div>
	<div id='navigation' style='height:60px;'></div>
	<hr>
		<a href="<c:url value='/' />">回前頁</a>
	</div>
</body>
</html>