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
    
    <title>权限列表</title>
    
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
	<link rel="stylesheet" href="/im/jsp/zhuye/css/style.css" type="text/css"></link>
  </head>
  
  <body>
  	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>用户管理</li>
	    <li>权限管理</li>
	    </ul>
    </div>
	  <!-- checkbox:true 复选框 ；lines:true 线性 ； dnd:true 可拖拉节点 -->
	<div class="easyui-panel" id="tt" style="padding:0px 0px 0px 20px;border: 0px;">
		<ul class="easyui-tree" data-options="url:'power/getAllPower.do',method:'post',animate:true,lines:true, dnd: true"></ul>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#tt ul").tree({
				onClick: function(node){
				  	//<a href=node.url target="rightFrame">首页模版</a>
					window.parent.rightFrame.location.href="power/toUpdate.do?id="+node.id;
					
				},
				
				onBeforeDrop: function(targetNode, source, point){
					//source.id 获取原节点id ； targetNode 获取放置后的节点 ； point 获取放置后的位置(button...)
					var targetId = $(this).tree('getNode', targetNode).id;
					$.ajax({
						url: 'power/upParentId.do',
						type: 'post',
						dataType: 'json',
						data: {
							id: source.id,
							targetId: targetId,
							point: point
						}
					});
				}
			});
		});
		
		
		
	</script>
  </body>
</html>
