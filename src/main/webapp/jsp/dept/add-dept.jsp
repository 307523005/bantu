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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
    
    $(function(){
     var arr =[];
            arr.push({"deptNo":"1..","dname":"2..","loc":"2.."});
            var arrs = JSON.stringify(arr);
            $.ajax({
                url: "dept/addDept",
                type: "post",
                data: arrs,
                contentType: "application/json",
                dataType: "json",
                success: function(data){
                
                    alert("请求成功！"+data[0].deptNo);
                },
                error: function(){
                    alert("请求失败！");
                }
            });
     });  
    </script>
  </head>
  
  <body><center>
	  <div style="padding:3px 2px;border-bottom:1px solid #ccc">Form Validation</div>
			<form id="ff" method="post">
				<div>
					<label for="name">Name:</label>
					<input class="easyui-validatebox" type="text" name="deptNo" required="true"></input>
				</div>
				<div>
					<label for="email">Email:</label>
					<input class="easyui-validatebox"   type="text" name="loc" required="true" validType="email"></input>
				</div>
				<div>
					<label for="subject">Subject:</label>
					<input class="easyui-validatebox" type="text" name="deptName" required="true"></input>
				</div>
		    <div>
		    
				
					<label for="checkbox">checkbox:</label>
					<select class="easyui-combotree" url="roleControl/getRoleTree" name="city" style="width:156px;"/>
					
				</div>
		
		  <div>
				<div style='height:20px'></div>
				<div style='height:auto'>
					<input type="submit" value="Submit">
				</div>
			</div>	
			</form>
			<script type="text/javascript">
			$('#ff').form({
				url:'dept/add',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('Info', data, 'info');
				}
			});
			</script>
     </center>
  </body>
</html>
