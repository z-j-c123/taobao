<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/font/iconfont.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/login.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body style="background-color: #f6f5ec;">
	<%@include file="./header.jsp"%>
	<section class="bg">
	<div class="form">
		<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
			<li class="nav-item" role="presentation"><a
				class="nav-link active" id="count" data-toggle="pill"
				href="#loginCount" role="tab" aria-controls="loginCount"
				aria-selected="true">账号登录</a></li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="phone" data-toggle="pill" href="#loginPhone" role="tab"
				aria-controls="loginPhone" aria-selected="false">验证码登录</a></li>
		</ul>
		<div class="tab-content" id="pills-tabContent">
			<div class="tab-pane fade show active" id="loginCount"
				role="tabpanel" aria-labelledby="count">
				<form action="#">
					<div class="form-group row">
						<label for="username"><i class="iconfont iconyonghu"></i></label>
						<div class="col-sm-10">
							<input type="text" id="username" class="form-control"
								placeholder="请输入您的手机号"> <span id="userMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row top">
						<label for="password"><i class="iconfont iconmima"></i></label>
						<div class="col-sm-10">
							<input type="password" id="password" class="form-control"
								placeholder="请输入登录密码"> <span id="passMsg">&nbsp;</span>
						</div>
					</div>
					
					<div class="form-group row top">
						<input type="button" class="btn btn-secondary col-sm-9 " onclick="passLogin()"
							value="登&nbsp;&nbsp;&nbsp;&nbsp;录">
					</div>
					<ul class="liList top">
						
						<li><a href="${pageContext.request.contextPath }/register">免费注册</a></li>
					</ul>
				</form>
			</div>
			<div class="tab-pane fade" id="loginPhone" role="tabpanel"
				aria-labelledby="phone">
				<form action="#">
					<div class="form-group row">
						<label for="tel"><i class="iconfont iconyonghu"></i></label>
						<div class="col-sm-10">
							<input type="text" id="tel" class="form-control"
								placeholder="请输入您的手机号"> <span id="telMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row top check">
						<label for="check"><i class="iconfont iconyanzhengma"></i></label>
						<div class="col-sm-10">
							<input type="text" id="check" class="form-control"
								placeholder="请输入验证码">
							<button type="button" onclick="getCode()" id="sendCode">获取验证码</button>
						</div>
					</div>
					<div class="form-group row">
						<input type="button" class="btn btn-secondary col-sm-9" onclick="checkLogin()"
							value="登&nbsp;&nbsp;&nbsp;&nbsp;录">
					</div>
					<ul class="liList">
						<li><a
							href="${pageContext.request.contextPath }/register">免费注册</a></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	</section>
	<%@include file="./footer.jsp" %>
	<script src="${pageContext.request.contextPath }/js/canvas-nest.js"></script>
	<script>
		let bg = document.querySelector(".bg");
		let form = document.querySelector(".form");
		let userMsg = $("#userMsg");
		let passMsg = $("#passMsg");
		let telMsg = $("#telMsg");
		let btn = $(".btn");
		let checkCode = $(".code");
		let span = document.getElementsByTagName("span");
		window.onload = window.onresize = function() {
			bg.style.height = window.innerHeight + "px";
			bg.style.width = window.innerWidth;
			form.style.left = (window.innerWidth - form.clientWidth) / 2 + "px";
			form.style.top = (window.innerHeight - form.clientHeight) / 2 + "px";
		}

		//验证用户是否符合要求
		function checkUser() {
			let user = $("#username").val();
			//手机号为常规的
			let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
			if (user === "") {
				userMsg.text("手机号不能为空！！！")
				userMsg.addClass("msg");
				return false;
			} else if (!regex.test(user)) {
				userMsg.text("手机号格式不正确，请重新输入！！！");
				userMsg.addClass("msg");
				return false;
			}
			userMsg.html("&nbsp;")
			userMsg.removeClass("msg");
			return true;
		}
		//验证手机号码是否符合要求
		function checkPhone() {
			let tel = $("#tel").val();
			//手机号为常规的
			let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
			if (tel === "") {
				telMsg.text("手机号不能为空！！！")
				telMsg.addClass("msg");
				return false;
			} else if (!regex.test(tel)) {
				telMsg.text("手机号格式不正确，请重新输入！！！");
				telMsg.addClass("msg");
				return false;
			}
			telMsg.html("&nbsp;")
			telMsg.removeClass("msg");
			return true;
		}
		//验证密码
		function checkPass() {
			let pass = $("#password").val();
			//密码由数字和字母组成
			let regex = /^(?=.*[a-z])(?=.*\d)[^]{6,12}$/;
			if (pass == "") {
				passMsg.text("密码不能为空！！！");
				passMsg.addClass("msg")
				return false;
			} else if (!regex.test(pass)) {
				passMsg.text("密码格式不正确!!!");
				passMsg.addClass("msg");
				return false;
			}
			passMsg.html("&nbsp;");
			passMsg.removeClass("msg");
			return true;
		}
		$("#username").blur(function() {
			checkUser();
		});
		$("#password").blur(function() {
			checkPass();
		})
		$("#tel").blur(function() {
			checkPhone();
		})
		//获取验证码
		var InterValObj; //timer变量，控制时间
    	var count = 120; 
		//间隔函数，1秒执行
		var curCount;//当前剩余秒数
		var code = ""; //验证码
		var codeLength = 6;//验证码长度
		function getCode() {
			curCount = count;
			//获取手机号
			let tel = $("#tel").val();
			 //设置button效果，开始计时
            $("#sendCode").attr("disabled", "true");
            $("#sendCode").html("请在" + curCount + "秒内输入验证码");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        	//向后台发送处理数据
			$.ajax({
				url : "${pageContext.request.contextPath}/logingetcode",
				type : "post",
				contentType : "application/json;charset=UTF-8",
				dataType : "json",
				data : JSON.stringify({
					phoneNumber : tel
				}),
				success : function(data) {
					if(data.flag === true){
						alert(data.message)
					} else{
						alert(data.message);
			            window.clearInterval(InterValObj);//停止计时器
					 	$("#sendCode").removeAttr("disabled");//启用按钮
			            $("#sendCode").html("重新发送验证码");
					}
				}
			})
		}
		//timer处理函数
	    function SetRemainTime() {
	        if (curCount == 0) {                
	            window.clearInterval(InterValObj);//停止计时器
	            $("#sendCode").removeAttr("disabled");//启用按钮
	            $("#sendCode").html("重新发送验证码");
	            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
	        }
	        else {
	            curCount--;
	            $("#sendCode").html("请在" + curCount + "秒内输入验证码");
	        }
	    }
		//账号密码登录
		function passLogin(){
			if(checkUser() && checkPass()){
				let username = $("#username").val();
				let password = $("#password").val();
				$.ajax({
					url: "${pageContext.request.contextPath}/userLogin",
					type: "post",
					dataType: "json",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({phoneNumber: username,passWord: password}),
					success: function(data){
						if(data.flag){
							alert("登录成功");
							window.location.href="${pageContext.request.contextPath}/index";
						}else{
							alert(data.message);
							$("#password").val("");
						}
					}
				});
			}
		}
		document.addEventListener("keydown", keydown);
		function keydown(e){
			switch(e.keyCode){
			 case 13:
				 passLogin();
				 break;
			}
		}
		//验证码登录
		function checkLogin(){
			let tel = $("#tel").val();
			let checkCode = $("#check").val();
			$.ajax({
				url: "${pageContext.request.contextPath}/userloginverificationcodecontroller",
				type: "post",
				dataType: "json",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify({phoneNumber: tel,passWord: checkCode}),
				success: function(data){
					if(data.flag){
						alert("登录成功");
						window.location.href="${pageContext.request.contextPath}/index";
					}else{
						alert(data.message);
					}
				}
			});
		}
	</script>
</body>
</html>