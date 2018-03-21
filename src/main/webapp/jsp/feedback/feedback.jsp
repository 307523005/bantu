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


<script type="text/javascript">
 $(function(){
	$("#input04").change(function() {
	});
 }); 

</script>
<script>

	function deleteorder() {
		if (confirm("确认处理这条息信吗？")) {
			var feedback_id ="";
			var rows = $('#tt').datagrid('getChecked');
			if (rows.length>0) {
			
			for ( var i = 0; i < rows.length; i++) {
				/* ids.push(rows[i].reid); */
				if(i<(rows.length-1)){
					feedback_id+=rows[i].feedback_id+",";
				}else{
					feedback_id+=rows[i].feedback_id;
				}
				
			}
			
			
			$.ajax({
				type : "POST",
				url : "Feedback/batchDelete",
				data :{
				feedback_id:feedback_id},
				success : function(data) {
					feedback_id = "";
						rows = "";
   		 		  $("#tt").datagrid("uncheckAll");
   		 	  $("#tt").datagrid("reload");//刷新当前页
   		 	   remind();
				},
				error : function() {
					confirm("方法执行不成功!");
				}
			}, "JSON");
		}
		else{
        $.messager.confirm("操作提示", "请勾选要处理的行！","error");
  			
		}
		}
		
	}
	/*單個刪除  */
	function del(t) {
		if (confirm("确认处理这条息信吗？")) {
			$.ajax({
				type : "POST",
				url : "Feedback/delete",
				data : {
					feedback_id : t
				},
				success : function(data) {
   		  $("#tt").datagrid("reload");//刷新当前页
   		   remind();
				},
				error : function() {
					confirm("方法执行不成功!");
				}
			}, "JSON");
				
		}
	}

</script>


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
	/* 导出 */
	function derive(t) {
	var a = $("#abegintime").val();
	var b =  $("#aendtime").val();
	var c =  $("#auser_system").val();
	var d =  $("#afeedback_isdelete").val();
	location.href = "Feedback/exportExcelEmployee?user_system=" + c+"&begintime="+a+"&endtime="+b+"&feedback_isdelete="+d;
 remind();
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
	 		<a  class="easyui-linkbutton" plain="true" iconCls="icon-print" onclick="derive()">&emsp;导出数据</a> 
				</div>
				</table>
		<!-- singleSelect:true  单行选择 -->
		<!-- collapsible:true  是否展开 -->
		<!-- pagination='true' 设置 'pagination' 属性为 true，它将在数据网格（datagrid）的底部生成一个分页（pagination）工具栏 -->
		<!-- sortable="true" 默认的，列是不能排序的，除非您设置 sortable 属性为 true。-->
		<!-- rownumbers="true" 是否显示行号 -->
		<div id="tb" align="left">
			<a href="javascript:add()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增</a>
			<!--         <a href="jsp/dept/add.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true" >新增</a> -->

			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteorder()">批量删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="javascript:$('#tt').datagrid('reload')"
				iconCls="icon-reload">刷新</a>
		</div>
				
				
		<table id="tt" singleSelect ="true" checkbox="true" class="easyui-datagrid"	style=" width:100%; height:700px" url="Feedback/getList" title="反馈信息列表" iconCls="icon-save" idField="reid" toolbar="#tb"	fitColumns="true" collapsible="true" rownumbers="true" pagination="true" checkOnSelect="false" selectOnCheck="false" >

			<thead>
<!--private Integer feedback_id;//自增id
private Integer feedback_isdelete;
private String feedback_uid;//用户id
private String feedback_text;//反馈内容
private String feedback_addtime;//添加时间  -->
				<tr>
					<th data-options="field:'ck',checkbox:true" ></th>
					<th data-options="field:'feedback_id'" sortable="true" style="width: 15px;">自增id</th>
					<th data-options="field:'feedback_uid'" style="width: 32px;">用户id</th>
					<th data-options="field:'feedback_text'" sortable="true" style="width: 100px;">反馈内容</th>
					<th data-options="field:'feedback_addtime'" sortable="true"style="width: 30px;" >添加时间</th>
					<th data-options="field:'user_phonebrand'" sortable="true"style="width: 20px;" >手机品牌</th>
					<th data-options="field:'user_phonemodel'" sortable="true"style="width: 20px;" >手机型号</th>
					<th data-options="field:'user_wxversion'" sortable="true"style="width: 20px;" >微信版本</th>
					<th data-options="field:'user_pixelRatio'" sortable="true"style="width: 20px;" >设备像素比</th>
					<th data-options="field:'user_screenWidth'" sortable="true"style="width: 20px;" >屏幕宽度</th>
					<th data-options="field:'user_screenHeight'" sortable="true"style="width: 20px;" >屏幕高度</th>
					<th data-options="field:'user_windowWidth'" sortable="true"style="width: 20px;" >可用窗口宽度</th>
					<th data-options="field:'user_windowHeight'" sortable="true"style="width: 20px;" >可用窗口高度</th>
					<th data-options="field:'user_language'" sortable="true"style="width: 20px;" >语言</th>
					<th data-options="field:'user_system'" sortable="true"style="width: 25px;" >操作系统版本</th>
					<th data-options="field:'user_platform'" sortable="true"style="width: 25px;" >客户端平台</th>
					<th data-options="field:'user_fontSizeSetting'" sortable="true"style="width: 10px;" >字体大小</th>
					<th data-options="field:'user_SDKVersion'" sortable="true"style="width: 20px;" >客户端基础库版本</th>
					<!-- <th data-options="field:'feedback_isdelete'" sortable="true"style="width: 10px;" >是否处理</th> -->
 <th align="center" field="c" width="70" data-options="formatter:function(feedback_isdelete,row,index){if(row.feedback_isdelete=='0'){return '未处理'}else{ return '已处理'}}">操作</th>
					<th align="center" field="cao" width="30px" data-options="formatter:function(feedback_id,row,index){ return '<a href=\'javascript:del('+row.feedback_id+')\'><img src=\'easyui/themes/icons/edit_remove.png\'></a>'}">操作</th>
					<!-- <th align="center" field="c" width="70" data-options="formatter:function(reid,row,index){if(row.reid=='22'){return 'A'}else{ return '<a href=\'dept/toUpdate.do?reid='+row.reid+'\' ><img src=\'easyui/themes/icons/pencil.png\'></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\'javascript:del('+row.reid+')\'><img src=\'easyui/themes/icons/edit_remove.png\'></a>'}}">操作</th> -->
				</tr>
			</thead>
		</table>
		
	
</body>

</html>
