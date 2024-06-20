<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage='true'
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新建員工資料</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
       let inputFileToLoad = null;
       let image = null;
       let img = null;
       window.addEventListener('load', function(){
	        inputFileToLoad = document.getElementById("inputFileToLoad");
	        image = document.getElementById("image");
	        img = document.getElementById("img");
	        inputFileToLoad.addEventListener('change', loadImageFileAsURL);
       }
       );
       function loadImageFileAsURL(){
          let filesSelected = inputFileToLoad.files;
          if (filesSelected.length > 0)
          {
              let fileReader = new FileReader();
      
              let fileToLoad = filesSelected[0];
              
              fileReader.onload = function(fileLoadedEvent) 
              {
                  image.value = fileLoadedEvent.target.result;
                  console.log("image.value=" + image.value );
                  img.src = fileLoadedEvent.target.result;
              };
              fileReader.readAsDataURL(fileToLoad);
          }
     }
    </script>
    <style type="text/css">
		span.error {
			color: red;
			display: inline-block;
			font-size: 11pt;
		}
		
		.fieldset-auto-width {
			display: inline-block;
		}
</style>
</head>

<c:set var="id"  					value = "${employee.id}" />
<c:set var="employeeId"  			value = "${employee.employeeId}" />
<c:set var="previous_employeeId"  	value = "${sessionScope.previous_employee_id}" />
<c:set var="name"  					value = "${employee.name}" />
<c:set var="birthday"  				value = "${employee.birthday}" />
<c:set var="email"  				value = "${employee.email}" />
<c:choose>
   <c:when test='${ empty requestScope.image}'>
		<c:set var="image"  value = "${employee.dataUri}" />
   </c:when>
   <c:otherwise>
   		<c:set var="image"  value = "${requestScope.image}" />
   </c:otherwise>
</c:choose>

<body>
    <div class='container my-5'>
    <div class='alert alert-success'><h2 align='center'>修改員工資料</h2></div>
     <c:url var='updateUrl' value='/employee/${id}' />
     <form:form method='POST' action="${updateUrl}" modelAttribute='employee'>
        <div class='row mb-3'>
            <label class='col-sm-2'>員工編號</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='employeeId' value="${employeeId}" >
                <input type='hidden' name='previous_employeeId' value="${previous_employeeId}" >
<%--                 <input type='hidden' name='id' value="${id}" > --%>
                <input type='hidden' name='_method' value="PUT" >
            </div>
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='employeeId' />
            </div>
        </div>
        <div class='row mb-3'>
            <label class='col-sm-2'>姓名</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='name' value="${name}" >
            </div>
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='name' />
            </div>
        </div>
         <div class='row mb-3'>
            <label class='col-sm-2'>生日</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='birthday' 
                       placeholder="請輸入生日 (yyyy-mm-dd)"  value="${birthday}" >
            </div>
             <div class='col-sm-4'>
            	<form:errors cssClass="error" path='birthday' />
            </div>
        </div>
        <div class='row mb-3'>
            <label class='col-sm-2'>電郵地址</label>
            <div class='col-sm-6'>
                <input type='text' class='form-control' name='email' value="${email}" >
            </div>
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='email' />
            </div>
        </div>
        
        <div class='row mb-3'>
            <label class='col-sm-2'>圖片</label>
            <div class='col-sm-4	'>
                <form:input type='file' id='inputFileToLoad' class='form-control' path='filename'
                       placeholder="請挑選圖片"  value='${employee.filename}'  />
            </div>
            <div class='col-sm-2'>
                <c:choose>
                   <c:when test='${empty image}' >
<!-- 		                <img id='img' width='60' height='80'> -->
<!-- 		                <input type='hidden' id='image' name='image' > -->
                   </c:when>
                   <c:otherwise>
                        <img id='img' width='60' height='80' src='${image}' >
                        <input type='hidden' name='image' id='image' value='${image}'>
                   </c:otherwise>
                </c:choose>
            </div>    
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='filename' />
            </div>
        </div>
        <div class='row mb-3'>
            <div class='offset-sm-3 col-sm-3 d-grid'>
                <button type='submit' class='btn btn-primary'>提交</button>
            </div>
            <div class='col-sm-3 d-grid'>  
                <a class='btn btn-outline-primary' href="<c:url value='/employee' />" role='button'>放棄更新	</a>
            </div>
        </div>
        </form:form>  
    </div>
</body>
</html>