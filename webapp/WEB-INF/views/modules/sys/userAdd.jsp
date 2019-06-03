<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<legend>添加用户</legend>
		<form:form action="${APP_PATH}/sys/user/insert" modelAttribute="user"
			method="post" class="form-horizontal" >
			<div class="form-group">
				<label class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<form:input path="name" id="name" htmlEscape="false" class="form-control" 
						placeholder="请输入名称" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<form:input path="pwd" class="form-control" placeholder="请输入密码" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<form:input path="sex" class="form-control" placeholder="请输入性别" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">地址</label>
				<div class="col-sm-10">
					<form:textarea path="home" class="form-control" rows="3" placeholder="请输入地址"></form:textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">信息</label>
				<div class="col-sm-10">
					<form:textarea path="info" class="form-control" rows="3" placeholder="请输入信息"></form:textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<button class="btn btn-info btn-sm" id="but_save">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						添加
					</button>
					<button type="button" onclick="javascript:history.go(-1)" class="btn btn-warning">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						返回
					</button>
				</div>
			</div>
		</form:form>

	</div>
</body>
</html>