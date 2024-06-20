<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style type="text/css">
fieldset {
    border: 1px solid rgb(255, 232, 57);
    width: 400px;
    margin: auto;
}
</style>
<title>Products</title>
</head>
<body>
    <section>
        <div class="container">
            <h1 style="text-align: center">
            <!-- 系統常數外部化-->
            	<spring:message code='spring.addProduct.form.addProductData.label' />
            </h1>
        </div>
    </section>
    <div align='center'>
        <a href="<c:url value='/' />">
       			<spring:message code='spring.addProduct.form.backToHomepage.label' />
        </a>
    </div> 
    <hr style="height: 1px; border: none; color: #333; background-color: #333;">
    <section class="container">
        <!--       三個地方要完全一樣 -->
        <form:form method='POST' modelAttribute="bookBean" class='form-horizontal'>
            <fieldset >
                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for='title'>
                        <spring:message code='spring.addProduct.form.title.label' />
                        </label>
                    <div class="col-lg-10">
                         <form:input id="title" path="title" type='text'
                            class='form:input-large' />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2"  for='author'>
                        <spring:message code='spring.addProduct.form.author.label' />   
                        </label>
                    <div class="col-lg-10">
                        <form:input id="author" path="author" type='text'
                            class='form:input-large' />
                    </div>
                </div>

                <div class="form-group">
                    <label class='control-label col-lg-2 col-lg-2' for="category">
                        <spring:message code='spring.addProduct.form.category.label' />  
                        </label>
                    <div class='col-lg-10'>
                        <form:select path="category">
                            <form:option value="-1"  >
                            	<spring:message code='spring.addProduct.form.select.label' />
                            </form:option>
                            <form:options items="${categoryList}" />
                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <label class='control-label col-lg-2 col-lg-2' for="price">
                       		<spring:message code='spring.addProduct.form.price.label' />
                        </label>
                    <div class='col-lg-10'>
                        <form:input id="price" path="price" type='text'
                            class='form:input-large' />
                    </div>
                </div>
                <div class="form-group">
                    <label class='control-label col-lg-2 col-lg-2' for="bookNo">
                       		<spring:message code='spring.addProduct.form.bookNo.label' /> 
                        </label>
                    <div class='col-lg-10'>
                        <form:input id="bookNo" path="bookNo" type='text'
                            class='form:input-large' />
                    </div>
                </div>

                <div class="form-group">
                    <label class='control-label col-lg-2 col-lg-2' for="companyId">
                       		 <spring:message code='spring.addProduct.form.bookCompany.label' /> 
                        </label>
                        <!--自動抓取資料並放入下拉式選單第一種寫法  -->
                    <%-- <div class='col-lg-10'>
                        <form:select path="companyId">
                            <form:option value="-1" label="請挑選" />
                            <form:options items="${companyList}" />
                        </form:select>
                    </div> --%>
                        <!--自動抓取資料並放入下拉式選單第二種寫法  -->
                    <div class='col-lg-10'>
                        <form:select path="companyId">
                            <form:option value="-1"  >
                            	<spring:message code='spring.addProduct.form.select.label' />
                            </form:option>
                            <form:options itemLabel="name" itemValue="id" items="${companyList2}" />
                        </form:select>
                    </div>
                </div>
                
                <!--  因為系統常數外部化所以不需要這些了 先將他註解 -->
              <%--   <div class="form-group">
						<label class='control-label col-lg-2 col-lg-2' for="stock">
								庫存 </label>
					<div class='col-lg-10'>
						<form:input id="stock" path="stock" type='text'
							class='form:input-large' />
						</div>
				</div> --%>
                
                
                <div class="form-group">
                    <div class='col-lg-offset-2 col-lg-10'>
                        <input id="btnAdd" type='submit' class='btn btn-primary'
                            value="<spring:message code='spring.addProduct.form.submit.label' />" />
                    </div>
                </div>
            </fieldset>
        </form:form>
    </section>
</body>
</html>
