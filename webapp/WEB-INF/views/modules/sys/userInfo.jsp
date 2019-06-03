<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用戶列表</title>
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
	<style type="text/css">
	/* table表格内容垂直居中 */
	.table th, .table td { 
	text-align: center;
	vertical-align: middle!important;
	}
	</style>
</head>
<body>
<br>
<div class="container">
<legend style="margin-left:20px">用户详情</legend>
<div style="margin-left: 20px"> 编号：${user.id }</div>
<div style="margin-left: 20px"> 用户：${user.name }</div>
<div style="margin-left: 20px"> 密码：${user.pwd }</div>
<div style="margin-left: 20px"> 性别：${user.sex }</div>
<div style="margin-left: 8px"> <a href="javascript:history.go(-1)" class="btn btn-link">返回上一步</a></div>

</div>
</body>
</html>