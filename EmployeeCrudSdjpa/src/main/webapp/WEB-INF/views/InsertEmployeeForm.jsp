<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新建員工資料</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
    <script>
       let inputFileToLoad = null;
       let image = null;
       let img = null;
       window.addEventListener('load', function(){
	        inputFileToLoad = document.getElementById("inputFileToLoad");
	        image = document.getElementById("image");
	        img = document.getElementById("img");
	        // 當檔案挑選完畢，促發change事件後，將執行loadImageFileAsURL()函數
	        inputFileToLoad.addEventListener('change', loadImageFileAsURL);
       }
       );
       function loadImageFileAsURL(){
          let filesSelected = inputFileToLoad.files;
          if (filesSelected.length > 0)
          {   
        	  // FileReader的readAsDataURL()函數可以讀取指定的檔案，
        	  // 當檔案讀取完畢後會觸發load事件。
              let fileReader = new FileReader();
              
              let fileToLoad = filesSelected[0];
              // 註冊fileReader之load事件。
              // 
              fileReader.onload = function(fileLoadedEvent) 
              {
                  //image.value = fileLoadedEvent.target.result;
                  image.value = fileReader.result;
                  console.log("image.value=" + image.value );
                  img.src 		= fileReader.result;
              };
              // 讀入指定的檔案
              fileReader.readAsDataURL(fileToLoad);
          }
     }
       
    </script>
</head>
<body>
    <div class='container'>
    <div class='alert alert-success'><h2 align='center'>新建員工資料</h2></div>
    <c:url var='insertUrl' value='/employee' />
    <form:form method='POST' modelAttribute="employee" action="${insertUrl}">
        <div class='row mb-3'>
            <label class='col-sm-2'>員工編號</label>
            <div class='col-sm-6'>
                <form:input type='text' class='form-control' path='employeeId'  
                       placeholder="請輸入員工編號" />
                                       
            </div>
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='employeeId' />
            </div>
        </div> 
        <div class='row mb-3'>
            <label class='col-sm-2'>姓名</label>
            <div class='col-sm-6'>
                <form:input type='text' class='form-control' path='name' 
                       placeholder="請輸入姓名"   />
            </div>
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='name' />
            </div>
            
        </div>
        <div class='row mb-3'>
            <label class='col-sm-2'>生日</label>
            <div class='col-sm-6'>
                <form:input type='text' class='form-control' path='birthday' 
                       placeholder="請輸入生日 (yyyy-mm-dd)"   />
            </div>
             <div class='col-sm-4'>
            	<form:errors cssClass="error" path='birthday' />
            </div>
        </div>
        <div class='row mb-3'>
            <label class='col-sm-2'>電郵地址</label>
            <div class='col-sm-6'>
                <form:input type='email' class='form-control' path='email' 
                       placeholder="請輸入電郵地址"  />
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
		                <img id='img' width='60' height='80'>
		                <input type='hidden' id='image' name='image' >
                   </c:when>
                   <c:otherwise>
                        <img id='img' width='60' height='80' src='${image}' >
                        <input type='hidden' name='image' value='${image}'>
                   </c:otherwise>
                </c:choose>
            </div>    
            <div class='col-sm-4'>
            	<form:errors cssClass="error" path='filename' />
            </div>
        </div>
        <c:if test="${not empty exception}">
	        ${exception}
        </c:if>
        <div class='row mb-3'>
            <div class='offset-sm-3 col-sm-3 d-grid'>
                <button type='submit' class='btn btn-primary'>提交</button>
            </div>
            <div class='col-sm-3 d-grid'>
                <a class='btn btn-outline-primary' href="<c:url value='/' />" role='button'>放棄新增</a>
            </div>
        </div>
        </form:form>
        <hr>
        
    </div>
</body>
</html>
