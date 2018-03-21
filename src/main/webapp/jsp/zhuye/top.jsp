<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>/">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'top.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jsp/zhuye/js/jquery.js"></script>
<link rel="stylesheet" href="jsp/zhuye/css/style.css" type="text/css"></link>
<script>
	var getnowtimes;
	function showtm() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		var day = now.getDate();
		if (day < 10) {
			day = "0" + day;
		}
		var hour = now.getHours();
		if (hour < 10) {
			hour = "0" + hour;
		}
		var minute = now.getMinutes();
		if (minute < 10) {
			minute = "0" + minute;
		}
		var second = now.getSeconds();
		if (second < 10) {
			second = "0" + second;
		}
		var week = now.getDay();
		if (week == 1) {
			var weeks = "一";
		}
		if (week == 2) {
			var weeks = "二";
		}
		if (week == 3) {
			var weeks = "三";
		}
		if (week == 4) {
			var weeks = "四";
		}
		if (week == 5) {
			var weeks = "五";
		}
		if (week == 6) {
			var weeks = "六";
		}
		if (week == 0) {
			var weeks = "日";
		}
		var dtime = document.getElementById("sj");
		dtime.innerHTML = year + "年" + month + "月" + day + "日&nbsp&nbsp" + "星期"
				+ weeks + "&nbsp&nbsp" + hour + ":" + minute + ":" + second;
		getnowtimes = setTimeout("showtm()", 1000);
	}

	window.onload = showtm;
	function stoptm() {
		clearTimeout(getnowtimes);
	}
</script>
<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		});
	});

	function gename() {
		//加载时获取用户姓名
		$.ajax({
			url : "LoginController/getUserList",
			type : "post",
			//上传参数
			//data:{empNo:$("#userName").val(),passWord:$("#passWord").val()},
			success : function(data) {
				$("#empName").text(data.empName);

			},
			error : function() {

			}
		}, "JSON");
	}
</script>
<body onload="gename()"
	style="background:url(jsp/zhuye/images/topbg.gif) repeat-x;">

	<div class="topleft">
		<a target="_parent"><img src="jsp/zhuye/images/logo.png" title="系统首页" />
		</a>
	</div>

	<ul class="nav">
		<div
			style=" font-family:'微软雅黑';width: 330px;margin-top:25px; margin-left:20px; font-size: 20px; "
			id="sj"></div>
	</ul>

	<div class="topright">
		<ul>
			<li><span><img src="jsp/zhuye/images/help.png" title="帮助"
					class="helpimg" />
			</span><a>帮助</a>
			</li>
			<li><a>关于</a>
			</li>
			<li><a href="LoginController/loginOut" target="_blank">安全退出</a>
			</li>
		</ul>
		<div class="user">
			<span><div id="empName"></div>
			</span>

		</div>

	</div>
</body>
</html>
