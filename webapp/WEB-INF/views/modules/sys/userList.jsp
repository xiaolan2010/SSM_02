<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<div class="container">
	  <legend>用戶管理</legend>
	  
	  <!-- 查询条件 -->
	  <form:form id="searchForm" modelAttribute="user" action="${APP_PATH}/sys/user/list" method="get"> 
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<!-- 面板内容 -->
			<div class="panel-body">
	         	<div class="form-group">
	               <label for="txt_name" class="col-sm-2 control-label">姓名</label>
	               <div class="col-md-4">
	                   <form:input class="form-control" type="text" path="name" placeholder="输入姓名"/>
	               </div>
	               <label for="txt_info" class="col-sm-2 control-label">信息</label>
	               <div class="col-md-4">
	                   <form:input class="form-control" type="text" path="info" placeholder="输入信息"/>
	               </div>
	            </div>
	            <div style="text-align: right; margin-top: 50px;">
	            	<button type="submit" class="btn btn-success btn-sm" id="btnSubmit">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						查找
					</button>
	            </div>
			</div>
		</div>
	  </form:form>
		<!-- 事件操作 -->
		<div class="form-group" style="margin-bottom: 50px;">
			<div class="col-md-4" style="text-align: left; width: 50%">
				<button class="btn btn-info btn-sm" id="but_add" >
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					新增
				</button>
				<button class="btn btn-danger btn-sm" id="but_delete">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					删除
				</button>
			</div>
			<div class="col-md-4" style="text-align: right; width: 50%">
				<button type="submit" class="btn  btn-sm">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
				</button>
				<button type="submit" class="btn  btn-sm">
					<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
				</button>
				<button type="submit" class="btn  btn-sm">
					<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
				</button>
			</div>
		</div>

		<br>

		<!-- 表单 -->
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" id="checkall" value="" onclick="ckeckall();"></th>
							<th>编号</th>
							<th>用户</th>
							<th>密码</th>
							<th>性别</th>
							<th>省份</th>
							<th>信息</th>
							<th>日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageInfo.list}" var="us"  varStatus="status">
						<c:set var="now" value="<%=new java.util.Date()%>" />
						<c:if test="${status.count%2==0}">
							<tr>
							    <td><input type="checkbox" id="onebox" value="${us.id}" onclick="ckeckonebox()"></td>
								<td>${us.id}</td>
								<td><a href="${APP_PATH}/sys/user/details?userid=${us.id}">${us.name}</a></td>
								<td>${us.pwd }</td>
								<td>${us.sex }</td>
								<td>${us.home }</td>
								<td>${us.info }</td>
								<td><fmt:formatDate type="both"  dateStyle="long" timeStyle="long"   value="${now}" /></td>
								<td>
								<button class="btn btn-success" id="but_list_info"  data-target="#modal-container-ck" 
								data-whatever=${us.id}  data-whatever0=${us.name} data-whatever1=${us.pwd} data-whatever2=${us.sex} data-toggle="modal">
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
									查看
								</button>
								<button class="btn btn-primary btn-sm" id="but_list_edit" data-toggle="modal"
								onclick="window.location.href ='${APP_PATH}/sys/user/userEdit?userid=${us.id}'">
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm" id="but_list_delete" data-whatever=${us.id} data-target="#modal-container-delete" data-toggle="modal">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
								</td>
							</tr>
							</c:if>
							<c:if test="${status.count%2!=0}" >
							<tr class="success">
							   <td><input type="checkbox" id="onebox" value="${us.id}" onclick="ckeckonebox()"></td>
								<td>${us.id}</td>
								<td><a href="${APP_PATH }/sys/user/details?userid=${us.id}">${us.name}</a></td>
								<td>${us.pwd }</td>
								<td>${us.sex }</td>
								<td>${us.home }</td>
								<td>${us.info }</td>
								<td>
								<fmt:formatDate type="both"  dateStyle="long" timeStyle="long"   value="${now}" />
								</td>
								<td>
								<button class="btn btn-success" id="but_list_info" data-target="#modal-container-ck" 
								data-whatever=${us.id} data-whatever0=${us.name} data-whatever1=${us.pwd} data-whatever2=${us.sex} data-toggle="modal">
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
									查看
								</button>
								<button class="btn btn-primary btn-sm" id="but_list_edit" data-toggle="modal" 
								onclick="window.location.href ='${APP_PATH}/sys/user/userEdit?userid=${us.id}'" >
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
									编辑 
								</button>
								<button class="btn btn-danger btn-sm" id="but_list_delete" data-whatever=${us.id} data-target="#modal-container-delete" data-toggle="modal">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
								
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row ">
			<!--分页文字信息  -->
			<div class="col-md-6">
		  	 <h3>当前第 ${pageInfo.pageNum} 页,当页共 ${pageInfo.getSize()} 条,共 ${pageInfo.pages} 页,共 ${pageInfo.total} 条 </h3>
		   </div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
				    <!-- 首页 -->
					<li><a href="${APP_PATH }/sys/user/list?pageNum=1">首页</a></li>
					<!--上一页-->  
					<c:if test="${pageInfo.hasPreviousPage }">
						<li><a href="${APP_PATH}/sys/user/list?pageNum=${pageInfo.pageNum-1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接--> 
					<c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
						<c:if test="${page_Num == pageInfo.pageNum}">
							<li class="active"><a href="#">${page_Num}</a></li>
						</c:if>
						<c:if test="${page_Num != pageInfo.pageNum}">
							<li><a href="${APP_PATH}/sys/user/list?pageNum=${page_Num}">${page_Num}</a></li>
						</c:if>
					</c:forEach>
					<!--下一页-->  
					<c:if test="${pageInfo.hasNextPage}">
						<li><a href="${APP_PATH}/sys/user/list?pageNum=${pageInfo.pageNum+1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<!-- 尾页 -->
					<li><a href="${APP_PATH}/sys/user/list?pageNum=${pageInfo.pages}">末页</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>

	<!-- 模态框  详情查看 -->
	<div class="modal fade" id="modal-container-ck" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						用户详情
					</h4>
				</div>
				<div class="modal-body" id="ck_content">
				  ....
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

    <!-- 模态框   信息删除确认 -->
      <div class="modal fade" id="modal-container-delete">
          <div class="modal-dialog">
              <div class="modal-content message_align">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal"
                          aria-label="Close">
                          <span aria-hidden="true">×</span>
                      </button>
                      <h4 class="modal-title">提示</h4>
                  </div>
                  <div class="modal-body">
                      <!-- 隐藏需要删除的id -->
                      <input type="hidden" id=delete_Id />
                      <p id="p_txt"></p>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-default"
                          data-dismiss="modal">取消</button>
                      <button type="button" class="btn btn-primary"
                          id="but_list_modal_delete">确认</button>
                  </div>
              </div>
              <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
    
    <!-- 脚本 -->
    <script type="text/javascript">
    
    $(document).ready(function(){
    	
   	   /* 模态框  详情查看 */
       $('#modal-container-ck').on('show.bs.modal', function (event) {
           var button = $(event.relatedTarget) // 触发事件的按钮  
           var recipient_id   = button.data('whatever') // 解析出whatever内容   根据标签获得按钮传入的参数
           var recipient_name = button.data('whatever0')  
           var recipient_pwd  = button.data('whatever1') 
           var recipient_sex  = button.data('whatever2')   
           var str_value="<div>编号: "+recipient_id+"</div><div>名称: "+recipient_name+"</div><div>密码: "+recipient_pwd+"</div><div>性别: "+recipient_sex+"</div>";
           $("#ck_content").html(str_value); //赋值
       })
       
       /* 模态框   信息删除确认  */
       $('#modal-container-delete').on('show.bs.modal', function (event) {
           var button = $(event.relatedTarget) // 触发事件的按钮  
           var recipient_id   = button.data('whatever') // 获取删除ID
           //alert(del_id)
		   var str_value= "您确认要删除该(ID:"+recipient_id+")条信息吗？";
           $("#p_txt").html(str_value); //给P标签 赋值
           $("#delete_Id").val(recipient_id);//列表删除ID，赋值给模态内hidden隐藏控件值
       })
       
     /* 事件处理  添加、编辑、删除、等 */
     //添加页面跳转
      $("#but_add").click(function(){
   		 $(window).attr('location','${APP_PATH}/sys/user/userAdd');
   	  });
   	  //批量删除： 模态（确定）按钮处理
   	  
   	  //单一删除： 模态（确定）按钮处理
   	  $("#but_list_modal_delete").click(function(){
   		  var del_id=$("#delete_Id").val(); 
   		  $(window).attr('location','${APP_PATH}/sys/user/delete?userid='+del_id);
   	  });
   	});

  </script>
</body>
</html>