<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>我的名片</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
        div:nth-of-type(1){
            color: aqua
        }
        div:nth-of-type(2){
            color: blue
        }
        div:nth-of-type(3){
            color: slateblue
        }
    </style>

</head>
<!--mycard_cardid;// 名片id
mycard_uid;// 用户id
mycard_name;// 姓名
mycard_position;// 职位
mycard_mobile;// 手机号
mycard_companyname;// 公司名称
mycard_phone;// 电话
mycard_mail;// 邮箱
mycard_add;// 地址
mycard_website;// 公司网址
mycard_profile;// 公司简介
mycard_scope;// 经营范围
 mycard_Isdelete;// 是否删除 0否 1 是
 mycard_addtime;// 添加时间
 mycard_templateid;// 模板类型id
 mycard_ismy ;//是否是我的 0 收藏他人的    1 我自己的
mycard_initial 首字母 -->
<body>
	<div>${res[0].mycard_name}</div>
	<div>${res[0].mycard_position}</div>


	<div id="content"></div>
</body>
<script>
	// var data =[];
	/* 封装正则获取URL地址内的地址 */
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	var content = document.querySelector('#content')
	var key = GetQueryString('k');
	var uid = GetQueryString('uid');
	var cid = GetQueryString('cid');
	console.log(content);
	console.log(key);
	content.innerHTML = "key1212:" + key + "</br>";
	content.innerHTML += "uid:" + uid + "</br>";
	content.innerHTML += "cid:" + cid + "</br>";
	// var myurl = GetQueryString('url');
	// if (myurl != null && myurl.toString().length > 1) {
	//     alert(GetQueryString("url"));
	// }
</script>
</html>
