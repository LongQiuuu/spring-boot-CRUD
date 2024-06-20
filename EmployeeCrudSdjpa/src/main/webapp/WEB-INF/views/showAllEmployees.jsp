<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Employees</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<link rel='stylesheet' href="<c:url value='/css/styles.css' />" />
    <script>
		 function deleteEmployee(ids, empno){
			if (confirm('確定要刪除員工編號為: ' + empno +' 這筆紀錄?')) {
				let url = "<c:url value='/employee' />" + "/" + ids;
				let input1 = document.createElement("input");
				let input2 = document.createElement("input");
				
				let container = document.forms[0];
                container.appendChild(input1);
                container.appendChild(input2);
                
                input1.type = "text";
                input1.name = "empNo";
                input1.value = empno;
                
                input2.type = "text";
                input2.name = "_method";
                input2.value = "DELETE";
                
                document.forms[0].action=url;
				document.forms[0].method="POST";
				document.forms[0].submit();
                return true;
			 } else {
                exit;
             }
		  }
	</script>
</head>
<body>
  <div class='container my-5'>
      <div class='alert alert-success'><h2 align='center'>員工基本資料表</h2>
      </div>
   <h4 align='center'>${message}&nbsp;</h4>
   <table class='table table-bordered' style='width:100%'>
        <thead>
            <tr>
                <th>員編</th>
                <th>姓名</th>
                <th>電郵地址</th>
                <th>生日</th>
                <th>阿凡達</th>
                <th>建檔日期</th>
                <th>編輯</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var='employee' items='${employeeList}' >
            
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.birthday}</td>
                <td><img width='50' height='60' src='${employee.dataUri}'></td>
                <td>${employee.created_at}</td>
                <td>
                    
                  
                  <a class='btn btn-primary btn-sm' href="<c:url value='/employee/findById/${employee.id}' />">修改</a>&nbsp;
                  <a class='btn btn-danger btn-sm'  onclick="return deleteEmployee('${employee.id}', '${employee.employeeId}');">刪除</a>
                  
                  
                </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <c:if test="${empty employeeList}" >
         <div align='center'>
             <a href="<c:url value='/employeeInit' />">按這裡建立樣本資料</a>
         </div>
         <br>
      </c:if>
      <div align='center'>
  			<a class='btn btn-outline-primary' href="<c:url value='/' />" role='button'>回首頁</a>
      </div>
    </div>
    <form action="#" method='POST'>
    </form> 
</body>
</html>