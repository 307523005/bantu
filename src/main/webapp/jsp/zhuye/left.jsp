<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<base href="<%=basePath%>/">
<html>
  <head>
    
    <title>后台页面</title>
    
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
	<link rel="stylesheet" href="<%=basePath%>jsp/zhuye/css/style.css" type="text/css"></link>
  	<style type="text/css">
  		body{
  			text
  		}
  	</style>
  </head>
  <script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
<body style="background:#f0f9fd;">
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="jsp/zhuye/images/leftico01.png"></img></span>管理信息
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="jsp/wxuser/wxuser.jsp" target="rightFrame">用户查询</a><i></i></li>
        <li><cite></cite><a href="jsp/feedback/feedback.jsp" target="rightFrame">反馈信息查询</a><i></i></li>
        <li><cite></cite><a href="" target="rightFrame">图片数据表</a><i></i></li>
        <li><cite></cite><a href="" target="rightFrame">添加编辑</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="jsp/zhuye/images/leftico02.png" /></span>通知设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">小程序操作通知模板内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="jsp/zhuye/images/leftico03.png" /></span>编辑器</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd><div class="title"><span><img src="jsp/zhuye/images/leftico04.png" /></span>日期管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>
    
    </dd>   
    
    </dl>
</body>
</html>

