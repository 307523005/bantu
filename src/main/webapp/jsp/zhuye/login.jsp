<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>jsp/zhuye/css/style.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>jsp/zhuye/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>jsp/zhuye/js/cloud.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 
	<script>
	$(function(){
		$("#button").click(function(){
		/*验证是否为空  */
			if($("#userName").val()==""||$("#passWord").val()==""){
			$("#errors1").css("display","block");
			}else {
					$.ajax({
						url:"<%=basePath%>LoginController/login",
						type:"post",
						//上传参数
						data:{emp_id:$("#userName").val(),emp_password:$("#passWord").val()},
						success:function(data){
							if(data==2){
								
								$("#errors2").css("display","block");
							}else {
								
								window.location.href= "<%=basePath%>jsp/zhuye/main.html";/*/bantu/jsp-front/jsp/front.jsp  */
								
							}
						},
						error: function(){
							confirm("系统异常！"); 
						}
					});
				}
			});
					
					 
			/* 点击提示消失 */
			$("#userName").click(function(){
					$("#errors2").css("display","none");	
			});
			$("#passWord").click(function(){
					$("#errors2").css("display","none");	
			});
			$("#userName").click(function(){
					$("#errors1").css("display","none");	
			});
			$("#passWord").click(function(){
					$("#errors1").css("display","none");	
			});
		
	})
	</script>
</head>
 
<body style="background-color:#1c77ac; background-image:url(<%=basePath%>images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="">回首页</a></li>
    <li><a href="">帮助</a></li>
    <li><a href="">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
     
 
<!--      <form action="userControl/login" method="post">
   用户名：<input type="text" name="userName">
   密码：<input type="text" name="passWord">
   <input type="submit" value="提交">
   </form> -->
    <ul>
    <li>
    <div id="errors1" style="position: absolute; top: 135px; display: none; color: red;" >用户名或密码不能为空！</div>
    <input name="userName" placeholder="请输入账号" id="userName" type="text" class="loginuser" onkeyup="this.value=value.replace(/\s/g,'')"/>
</li>
    <li>
    <input name="passWord" id="passWord"placeholder="请输入密码" type="password" class="loginpwd" onkeyup="this.value=value.replace(/\s/g,'')" />
    <div id="errors2" style="position: absolute; top: 210px; display:none ; color: red;" >用户名或密码错误！</div>
    </li>
    <li><input  type="button" class="loginbtn" value="登录" id="button"   /><label><a href="#">忘记密码？</a></label></li>
    </ul>

    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  bantu  .com </div>
</body>
</html>
