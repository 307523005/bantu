<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <center>
    <h1>修改</h1>
    <form action="dept/update.do" method="post"   enctype="multipart/form-data" >
<table border="1">
   
    <c:forEach var="dept" items="${deptList}">
    
 <tr>  <td>编号</td><td><input type="text" name="deptNo" value="${dept.deptNo }"></td></tr>
 <tr>  <td>名字</td><td><input type="text" name="dname" value="${dept.dname }"></td></tr>
  <tr>  <td>位置</td><td><input type="text" name="loc" value="${dept.loc }"></td></tr>
   <tr>  <td>图片</td><td><input type="file" name="pictwo" value=""></td></tr>
 	<input type="hidden" name="deptpic" value="${dept.deptpic }"> 
   
   
      </c:forEach>

     
     <tr><td colspan="2"><input type="submit" value="提交修改"></td></tr>
 
    </table>
       </form>
    </center>
  </body>
</html>
