<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

  </head>
  
  <body>
  <center>
	     <!-- singleSelect:true  单行选择 -->
	     <!-- collapsible:true  是否展开 -->
	     <!-- pagination='true' 设置 'pagination' 属性为 true，它将在数据网格（datagrid）的底部生成一个分页（pagination）工具栏 -->
	     <!-- sortable="true" 默认的，列是不能排序的，除非您设置 sortable 属性为 true。-->
	     <!-- rownumbers="true" 是否显示行号 -->
	<table id="tt" class="easyui-datagrid" style="width:600px;height:250px"
		url="deptEasyUi/jsonPageList"  toolbar="#tb"
		title="部门分页列表" iconCls="icon-save"
		rownumbers="true" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'DEPTNO',width:80" sortable="true">部门ID</th>
				<th data-options="field:'DEPTNAME',width:100" sortable="true">部门名称</th>
				<th data-options="field:'LOC',width:250" sortable="true">部门位置</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		<span>部门名称:</span>
		<input id="itemid" style="line-height:26px;border:1px solid #ccc">
		<span>部门位置:</span>
		<input id="productid" style="line-height:26px;border:1px solid #ccc">
		<a  class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
	</div>
	<div class="easyui-panel">
		<div class="easyui-pagination" data-options="total:114"></div>
	</div>
	 <script type="text/javascript">
	    function doSearch(){
			$('#tt').datagrid('load',{
				parama: $('#itemid').val(),
				paramb: $('#productid').val()
			});
		}
	 </script>
   </center>
  </body>
</html>
