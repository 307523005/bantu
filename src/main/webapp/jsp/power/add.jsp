<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  </head>
  	
  <body  style="background-color:#DBEBFA; ">
        	<div >
				<form  method="post" class="form-horizontal" action="power/add.do">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									
									<h4 class="modal-title" id="myModalLabel">权限添加</h4>
								</div>
	
								<div class="form-group" style="margin-top: 10px;">
									<input type="hidden" class="form-control" name="parentId" value="${powerId}" readonly="readonly" />
								</div>									
									<div class="form-group">
										<label class="col-lg-3 control-label">权限名称</label>
										<div class="col-lg-4" style=" width:237px; ">
											<input type="text" class="form-control" name="text"
												placeholder="--请输入权限名称--" required
												data-bv-notempty-message="不能是空的" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-lg-3 control-label">权限链接</label>
										<div class="col-lg-4" style=" width:300px; ">
											<input type="text" class="form-control" name="url" 
												placeholder="--请输入权限链接--" />
										</div>
									</div>
									<!-- <div class="form-group">
										
										<label class="col-lg-3 control-label">是否关闭</label>
										<div style="margin-top: 8px;">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="state" value="closed" >关闭
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="state" value=" ">不关闭
										</div>
	         					    </div> -->
	         					    
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:window.history.go(-1)" >返回</button>
									<button type="submit" class="btn btn-primary">添加</button>
								</div>
						</div>
					 </div>
					 
					
				</form>
			</div>
			
 </body>
</html>
