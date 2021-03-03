<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>   
<%@page import="po.User"%>
<%
	User user =(User) request.getSession().getAttribute("user");
	String a = "";
	String username = "";
	String password = "";
	String sex = "";
	String idCard = "";
	String phoneNumber = "";
	boolean flag = false;
	int id = 0;
	if(user == null){
		a = "我要开店";
		flag = false;
	}else{
		flag = true;
		username = user.getUserName();
		password = user.getPassWord();
		sex = user.getSex();
		idCard = user.getIdCard();
		id = user.getId();
		phoneNumber = user.getPhoneNumber();
		if(user.getCustomerType()!=null && user.getCustomerType().equals("卖家")){
			a = "我的店铺";
		}else{
			a = "我要开店";
		}
	}
%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
	margin: 0;
	padding: 0;
}
/*头部样式*/

header {
    width: 100%;
    background-color:#f0f3f4;
}

header .head-nav {
    max-width: 1190px;
    margin: 0px auto;
    height: 42px;
    min-width: 1190px;
}

.head-nav li {
    margin: 1px 50px;
}
.nav-item a{
    text-decoration: none;
    font-size: 14px;
    color: #6c6c6c;
}
.nav-item a:hover{
    color: #6c6c6c;
}
</style>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<header>
		<div class="head-nav">
			<ul class="nav justify-content-center">
				<li class="nav-item"><a class="nav-link active"
					href="javascript:mine();">我的</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath }/index">首页</a></li>
				<li class="nav-item"><a class="nav-link" href="javascript:cart();">购物车</a></li>
				<li class="nav-item"><a class="nav-link customerType" href="javascript:(0);">我要开店</a></li>
				<li class="nav-item"><a class="nav-link information" href="javascript:(0);">消息</a></li>
				<li class="nav-item ">
					<a href="javascript:(0);" class="nav-link distory">注销</a>
				</li>
			</ul>
		</div>
	</header>
	<script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
	<script>
		let type = "<%=a%>";
		let flag = "<%=flag%>";
		$(".distory").html(flag === "false"?"亲，请登录":"注销");
		
		$(".customerType").html(type);
		$(".customerType").click(function(){
			if($(this).html() === "我的店铺"){
				$(".customerType").attr("href", "${pageContext.request.contextPath}/editPro")
				$(".information").css("display","inline-block")
			}else{
				$(".information").css("display","none")
				if(flag === "true"){					
					location.href = "${pageContext.request.contextPath}/sellerRegist";
				}else{
					alert("亲，请登录后操作")
					location.href = "${pageContext.request.contextPath}/login";
				}
			}
		})
		$(".information").click(function(){
			if(login()){
				location.href = "${pageContext.request.contextPath}/information";
			}else{
				alert("亲，请登录后操作");
				location.href = "${pageContext.request.contextPath}/login";
			}
		})
		function mine(){
			if(login()){				
				window.location.href = "${pageContext.request.contextPath}/userinfo";		
			}else{
				alert("请登录后操作");
				window.location.href = "${pageContext.request.contextPath}/login";
			}
		}
		$(".distory").click(function(){
			let that = $(this)
			if($(this).html() === "亲，请登录"){
				location.href = "${pageContext.request.contextPath}/login";
			}
			if(login() && $(this).html() == "注销"){
					$.ajax({
						url: "${pageContext.request.contextPath}/signout",
						type: "post",
						success: function(result){
							if(result.flag){
								alert("注销成功");
								if('false' === '<%=user==null%>'){
									location.href = "${pageContext.request.contextPath}/index";
								}
							}
							that.html("亲，请登录");
							that.attr("href","${pageContext.request.contextPath}/login");
						}
					})
				}else{
					alter("亲，请登录");
					location.href = "${pageContext.request.contextPath}/login"
				}
			})
			
		function cart(){	
			if(login()){
				window.location.href = "${pageContext.request.contextPath}/cart";
			} else{
				alert("请登录后操作");
				location.href = "${pageContext.request.contextPath}/login";
			}
		}
		function login(){
			if(flag === "false"){
				return false;
			}else{
				return true;
			}
		}
		
	</script>
</body>
</html>