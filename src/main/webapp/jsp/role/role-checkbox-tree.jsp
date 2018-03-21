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
  	<div style="margin:20px 0;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getChecked()">GetChecked</a> 
	</div>
	 <div class="easyui-panel" style="padding:5px">
		<ul id="tt" class="easyui-tree" data-options="url:'roleControl/getRoleTree',method:'get',animate:true,checkbox:true"></ul>
	</div>
	<script type="text/javascript">
		function getChecked(){
			var nodes = $('#tt').tree('getChecked');
			var s = '',ids='';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].text;
				if(ids!='') ids+=',';
				ids += nodes[i].id;
			}
			console.log(s+"@"+ids);
			//alert(s+"@"+ids);
		}
	</script>
   </center>
  </body>
</html>
