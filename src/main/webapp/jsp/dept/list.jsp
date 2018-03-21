<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- 	 <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>  --%>
<script>



	
</script>


<script type="text/javascript">
	$(function() {
		$("#name").on('click', function() {
			/* alert($("#t_id").val()); */
			//var  aaa="{'a':'2','c':'1'}" ;
			//  var b =JSON.stringify(aaa);
			var ganma = $("#name").val();
			alert("+++" + ganma);
			$.ajax({
				type : "POST",
				url : "dept/getLists",
				data : {
					deptno : ganma
				},
				success : function(data) {
					alert(data.LOC);
					//解析
					///var roomList = eval("(" + data + ")");	
					//    alert("--"+roomList.AA);
			
					$("#age").val(data.DNAME);
				},
				error : function() {
					alert("方法执行不成功!");
				}
			}, "JSON");
		});
	});
</script>
<!--  批量刪除-->
<script>

	function deleteorder() {
		if (confirm("确认删除这条息信吗？")) {
			var ids ="";
			var rows = $('#tt').datagrid('getChecked');
			alert(rows.length);
			if (rows.length>0) {
			
			for ( var i = 0; i < rows.length; i++) {
				/* ids.push(rows[i].deptNo); */
				if(i<(rows.length-1)){
					ids+=rows[i].deptNo+",";
				}else{
					ids+=rows[i].deptNo;
				}
				
			}
			
			
			$.ajax({
				type : "POST",
				url : "dept/delete.do",
				data :{
				deptNos:ids},
				success : function(data) {
   		 	 $("#tt").datagrid("reload");//刷新当前页
				},
				error : function() {
					alert("方法执行不成功!");
				}
			}, "JSON");
		}
		else{
        $.messager.alert("操作提示", "请勾选要删除的行！","error");
  			
		}
		}
		
	}
	/*單個刪除  */
	function del(t) {
		if (confirm("确认删除这条息信吗？")) {
			$.ajax({
				type : "POST",
				url : "dept/deletetwo.do",
				data : {
					deptNos : t
				},
				success : function(data) {
   		  $("#tt").datagrid("reload");//刷新当前页
				},
				error : function() {
					alert("方法执行不成功!");
				}
			}, "JSON");
				
		}
	}
	/*點擊修改，彈出遮罩層  */
	function upd(t) {
		$("#tt").datagrid('selectRow', t);
		var row = $("#tt").datagrid('getSelected');
		$("#dlg").dialog("open").dialog('setTitle', 'Edit User');
		$("#fm").form("load", row);
	}
/*點擊添加彈出層*/
	function add(t) {
		$("#adddlg").dialog("open").dialog('setTitle', 'Add User');
	}
</script>

<!--查询 -->
<script>
	function doSearch() {
		$('#tt').datagrid('load', {
			parama : $('#DEPTNO').val()
		});
	}

</script>

<script>
/* 保存修改 */
	function saveuser() {

 $("#fm").form("submit",{
        url:"dept/update.do",
        onSubmit: function(){
                // do some check
                // return false to prevent submit;
        },
        success:function(data){
       
                $("#dlg").dialog("close");
                $("#tt").datagrid("reload");
        }
});	
	}
	function shua() {
	 $("#tt").datagrid("reload");
	}
/* 添加提交	*/
	function addsave() {

		 $("#addfm").form("submit",{
        url:"dept/add.do",
        onSubmit: function(){
                // do some check
                // return false to prevent submit;
        },
        success:function(data){
       
                $("#adddlg").dialog("close");
                $("#tt").datagrid("reload");
        }
		});
	}
</script>
<body>

	<center>

	 <table id="tone"   class="easyui-datagrid"	style=" width:800px; height:63px"  title="条件查询" iconCls="icon-search" toolbar="#ta"	 collapsible="true"  collapsed="true" >
		<div id="ta" align="left">
		Date From: <input
				class="easyui-datebox" style="width:80px"> To: <input
				class="easyui-datebox" style="width:80px"> id: <input
				id="DEPTNO" style="line-height:26px;border:1px solid #ccc">
			位置:<input class="easyui-combobox" style="width:100px" url "dat"
				valueField="id" textField="text"> <a	class="easyui-linkbutton" plain="true"  iconCls="icon-search" onclick="doSearch()">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-print">打印</a>
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
				
				
		<table id="tt" singleSelect ="true" checkbox="true" class="easyui-datagrid"	style=" width:800px; height:500px" url="dept/jsonPageList.do" title="部门分页列表" iconCls="icon-save" idField="deptNo" toolbar="#tb"	fitColumns="true" collapsible="true" rownumbers="true" pagination="true" checkOnSelect="false" selectOnCheck="false" >

			<thead>

				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'deptNo',width:80" sortable="true">部门ID</th>
					<th data-options="field:'dname',width:100" sortable="true">部门名称</th>
				<!-- 	<th data-options="field:'loc',width:280">部门位置</th> -->
					<th align="center" field="loc" width="70" data-options="formatter:function(loc,row,index){if(row.loc=='1'){return '是'}else{ return '否' }}">是否離職</th> 
					<th field="deptpic" width="60"data-options="formatter:function(v){return '<img style=\'height: 60px;width: 60px;\' src='+v+'>'}">部门图片</th>

					<th align="center" field="cao" width="70" data-options="formatter:function(deptNo,row,index){ return '<a href=\'javascript:upd('+index+') \'plain=\'true\'><img src=\'easyui/themes/icons/pencil.png\'></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\'javascript:del('+row.deptNo+')\'><img src=\'easyui/themes/icons/edit_remove.png\'></a>'}">操作</th>
					<!-- <th align="center" field="c" width="70" data-options="formatter:function(DEPTNO,row,index){if(row.DEPTNO=='22'){return 'A'}else{ return '<a href=\'dept/toUpdate.do?deptno='+row.DEPTNO+'\' ><img src=\'easyui/themes/icons/pencil.png\'></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\'javascript:del('+row.DEPTNO+')\'><img src=\'easyui/themes/icons/edit_remove.png\'></a>'}}">操作</th> -->
				</tr>
			</thead>
		</table>
		<!-- 修改-->
		<div id="dlg" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px; top:200px; left:600px; "
			closed="true" buttons="#dlg-buttons" >
			<div class="ftitle">修改</div>
			<form id="fm"  method="post"  enctype="multipart/form-data"><!--  action="dept/update.do" -->
				<div class="fitem">
					<label> id </label> <input name="deptNo" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 姓名</label> <input name="dname" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 位置</label> <input name="loc" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 图片</label> <input class="easyui-filebox" name="pictwo"
						value="" required="true" />
				</div>

				<input class="easyui-validatebox" name="deptpic" />
				<input class="easyui-validatebox" id="page" name="page" />
			</form>
		</div>

		<div id="dlg-buttons">
	
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="saveuser()" iconcls="icon-save">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
		</div>

		<!--  添加-->
		<div id="adddlg" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px;"
			closed="true" buttons="#adddlg-b">
			<div class="ftitle">修改</div>
			<form id="addfm"  method="post"	enctype="multipart/form-data"><!--action="dept/add.do"  -->
				<div class="fitem">
					<label> id </label> <input name="deptNo" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 姓名</label> <input name="dname" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 位置</label> <input name="loc" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label> 图片</label> <input class="easyui-filebox" name="pic" multiple="multiple"
						required="true" />
				</div>
			</form>
		</div>

		<div id="adddlg-b">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addsave()" iconcls="icon-save">保存</a>
		<a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:$('#adddlg').dialog('close')" iconcls="icon-cancel">取消</a>
		
			</div>
	</center>

	-----------------------
	<br>
	<input type="text" id="name" value="10">
	<br>
	<input type="text" id="age">
</body>
<!--  -->

</html>
