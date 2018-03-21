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
	<table id="tt" >
	</table>
	<div class="easyui-panel">
		<div class="easyui-pagination" data-options="total:114"></div>
	</div>
	 <script type="text/javascript">
	     var json;
	     $.ajax({url:'dept/jsonPageList',
		            type:'post',
		            async:"false",
		            success:function(data){
		               alert(data);
		           		 json=data;
		             }
	            },"json");
	           alert(json);
		 $('#tt').datagrid({
			title:'Custom Sort',
			iconCls:'icon-ok',
			width:520,
			height:250,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			columns:[[
				{field:'DEPTNO',title:'Item ID',width:60,sortable:true},
				{field:'DNAME',title:'List Price',width:70,align:'right',sortable:true},
				{field:'LOC',title:'Unit Cost',width:70,align:'right',sortable:true,
					sorter:function(a,b){
						a = a.split('/');
						b = b.split('/');
						if (a[2] == b[2]){
							if (a[0] == b[0]){
								return (a[1]>b[1]?1:-1);
							} else {
								return (a[0]>b[0]?1:-1);
							}
						} else {
							return (a[2]>b[2]?1:-1);
						}
					}},
			]]
		}).datagrid('loadData',json);
	 </script>
   </center>
  </body>
</html>
