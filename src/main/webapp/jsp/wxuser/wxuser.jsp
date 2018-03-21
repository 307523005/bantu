<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<base href="<%=basePath%>/">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>反馈	信息查询</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="jsp/zhuye/css/style.css" type="text/css"></link>

<script>
/* <-查询 */ 
	function doSearch() {
		$('#tt').datagrid('load', {
			paramc : $('#user_system').val(),
			parama : $('#begintime').val(),
			paramb : $('#endtime').val(),
			paramd : $('#feedback_isdelete').val()
		});
				$("#abegintime").val($("#begintime").val());//获取开始时间参数 
				$("#aendtime").val($("#endtime").val());
				$("#auser_system").val($("#user_system").val());
				$("#afeedback_isdelete").val($("#feedback_isdelete").val());
		
		clears();
	}
	function clears(){
	$("#begintime").textbox("setValue", "");//清除输入框内容
	$("#endtime").textbox("setValue", "");//清除输入框内容
		$("#user_system").textbox("setValue", "");//清除输入框内容
		$("#feedback_isdelete").textbox("setValue", "");//清除输入框内容
		
	}

</script>

<body>


	 <table id="tone"   class="easyui-datagrid"	style=" width:100%; height:65px"  title="条件查询" iconCls="icon-search" toolbar="#ta"	 collapsible="true"   >
		
		<div id="ta" align="left" style="  height:30px;width: 100%; margin-top: 2px" >
		<input id="abegintime"type="hidden"	 >
		<input id="aendtime"type="hidden"	>
		<input id="auser_system"	 type="hidden" >
		<input id="afeedback_isdelete"	 type="hidden" >
		<!-- editable="false"禁止输入 -->
		时间: <input id="begintime" editable="false" class="easyui-datebox" style="width:120px;"> &emsp;至: &emsp;
		<input editable="false" id="endtime" class="easyui-datebox" style="width:120px;"> 

		&emsp; 平台: <select id="user_system" class="easyui-combobox" name="dept" style="width:200px;" editable="false">
			    <option value=""></option>
			    <option value="Android">Android</option>
			    <option value="iOS">iOS</option>
			</select>
			
			
			&emsp;是否处理：<select id="feedback_isdelete" class="easyui-combobox" name="dept" style="width:200px;" editable="false">
			    <option value=""></option>
			    <option value="1">已处理</option>
			    <option value="0">未处理</option>
			</select>
			<a	class="easyui-linkbutton" plain="true"  iconCls="icon-search" onclick="doSearch()">&emsp;查询</a>
	 	<!-- 	<a  class="easyui-linkbutton" plain="true"  onclick="clears()" iconCls="icon-cancel">&emsp;清除</a> -->
				</div>
				</table>
		<!-- singleSelect:true  单行选择 -->
		<!-- collapsible:true  是否展开 -->
		<!-- pagination='true' 设置 'pagination' 属性为 true，它将在数据网格（datagrid）的底部生成一个分页（pagination）工具栏 -->
		<!-- sortable="true" 默认的，列是不能排序的，除非您设置 sortable 属性为 true。-->
		<!-- rownumbers="true" 是否显示行号 -->
		<div id="tb" align="left">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="javascript:$('#tt').datagrid('reload')"
				iconCls="icon-reload">刷新</a>
		</div>
				
				
		<table id="tt" singleSelect ="true" class="easyui-datagrid"	style=" width:100%; height:700px" url="wxuser/getList" title="反馈信息列表" iconCls="icon-save" idField="reid" toolbar="#tb"	fitColumns="true" collapsible="true" rownumbers="true" pagination="true" checkOnSelect="false" selectOnCheck="false" >

			<thead>
<!--private Integer feedback_id;//自增id
private Integer feedback_isdelete;
private String feedback_uid;//用户id
private String feedback_text;//反馈内容
private String feedback_addtime;//添加时间  -->
				<tr>
				<!--user_openid,user_gzhopenid,user_unionid,user_uid
		,user_nickname,
		user_gender,user_province,user_city,user_country,user_deptpic,user_registeredtime,user_logindtime,user_phonebrand,user_phonemodel,user_wxversion,
		user_pixelRatio, user_screenWidth, user_screenHeight,
		user_windowWidth, user_windowHeight, user_language
		, user_system,
		user_platform, user_fontSizeSetting, user_SDKVersion,user_form_id  -->
					<th data-options="field:'user_uid'" sortable="true" style="width: 15px;">用户uid</th>
					<th data-options="field:'user_openid'" style="width: 32px;">小程序openid</th>
					<th data-options="field:'user_gzhopenid'" sortable="true" style="width: 30px;">公众号openid</th>
					<th data-options="field:'user_unionid'" sortable="true"style="width: 30px;" >unionid</th>
					 <th align="center" field="user_gender" width="10px" data-options="formatter:function(user_gender,row,index){if(row.user_gender=='1'){return '男'}else{ return '女'}}">性别</th>
					<th data-options="field:'user_province'" sortable="true"style="width: 20px;" >省份</th>
					<th data-options="field:'user_city'" sortable="true"style="width: 20px;" >城市</th>
					<th data-options="field:'user_country'" sortable="true"style="width: 20px;" >国家</th>
					<th align="center" field="cao" width="30px" data-options="formatter:function(user_deptpic,row,index){ return '<img src=\'row.user_deptpic\'>'}">头像</th>
					<th data-options="field:'user_registeredtime'" sortable="true"style="width: 22px;" >注册时间</th>
					<th data-options="field:'user_logindtime'" sortable="true"style="width: 22px;" >最后登陆时间</th>
					<th data-options="field:'user_phonebrand'" sortable="true"style="width: 20px;" >手机品牌</th>
					<th data-options="field:'user_phonemodel'" sortable="true"style="width: 20px;" >手机型号</th>
					<th data-options="field:'user_wxversion'" sortable="true"style="width: 20px;" >微信版本</th>
					<th data-options="field:'user_pixelRatio'" sortable="true"style="width: 15px;" >设备像素比</th>
					<th data-options="field:'user_screenWidth'" sortable="true"style="width: 15px;" >屏幕宽度</th>
					<th data-options="field:'user_screenHeight'" sortable="true"style="width: 15px;" >屏幕高度</th>
					<th data-options="field:'user_windowWidth'" sortable="true"style="width: 15px;" >可用窗口宽度</th>
					<th data-options="field:'user_windowHeight'" sortable="true"style="width: 15px;" >可用窗口高度</th>
					<th data-options="field:'user_language'" sortable="true"style="width: 20px;" >语言</th>
					<th data-options="field:'user_system'" sortable="true"style="width: 25px;" >操作系统版本</th>
					<th data-options="field:'user_platform'" sortable="true"style="width: 25px;" >客户端平台</th>
					<th data-options="field:'user_fontSizeSetting'" sortable="true"style="width: 10px;" >字体大小</th>
					<th data-options="field:'user_SDKVersion'" sortable="true"style="width: 20px;" >客户端基础库版本</th>
				</tr>
			</thead>
		</table>
		
	
</body>

</html>
