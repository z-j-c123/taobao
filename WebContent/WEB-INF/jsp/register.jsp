<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<style>
* {
	margin: 0;
	padding: 0;
}

.main {
	position: relative;
}

.form {
	width: 550px;
	height: 500px;
	position: absolute;
	opacity: .7;
	background-color: #ccc;
}

.regist {
	width: 500px;
	height: 420px;
	padding-top: 10px;
	margin-top: 20px;
}

label {
	text-align: right;
	font-size: 20px;
	color: #000;
	font-weight: bold;
}

.content {
	width: 668px;
	height: 430px;
	border: 2px solid #dedede;
	border-top: 2px solid #ff2300;
	text-align: left;
	position: absolute;
	background-color: #fff;
	z-index: 2;
}

h3 {
	font-size: 16px;
	color: #000;
	display: block;
	text-indent: 20px;
}

.href {
	color: #ddd;
	float: right;
	margin-right: 5px;
	font-size: 20px;
	text-decoration: none;
}

.href:hover {
	color: #ff2300;
}

.body {
	width: 628px;
	margin: 20px 10px 0 20px;
	font-size: 12px;
}

p {
	margin: 7px 0;
}

.button {
	width: 180px;
	font-size: 15px;
	padding: 11px 0;
	color: #fff;
	background-color: #ff2300;
	border: none;
	outline: none;
	border-radius: 3px;
	margin: 30px 0;
}

.form-group {
	margin-top: -18px;
}

.btn {
	margin-left: 100px;
}

.msg {
	color: #ff2300;
}

#sendCode {
	position: absolute;
	right: 20px;
	top: 5px;
	color: #ccc;
	border: none;
	background-color: #fff;
}

#sendCode:focus {
	outline: none;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
</head>

<body>
	<%@include file="./header.jsp" %>
	<div class="main">
		<div class="form">
			<h3>用户注册</h3>
			<div class="regist">
				<form action="#">
					<div class="form-group row">
						<label for="username" class="col-sm-4  form-label">用户名：</label>
						<div class="col-sm-7">
							<input type="text" id="username" class="form-control"> <span
								class="userMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="phone" class="col-sm-4  form-label">手机号：</label>
						<div class="col-sm-7">
							<input type="text" id="phone" class="form-control"> <span
								class="phoneMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="password" class="col-sm-4 form-label">密码：</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="password"
								placeholder="密码由6-12个数字或字母组成"> <span class="passMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="repassword" class="col-sm-4 form-label">再次输入密码：</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="repassword">
							<span class="rePassMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="sex" class="col-sm-4 form-label">性别：</label>
						<div class="col-sm-2">
							<input type="radio" checked name="sex" value="男">男
						</div>
						<div class="col-sm-2">
							<input type="radio" name="sex" value="女">女
						</div>
					</div>
					<div class="form-group row">
						<label for="idCard" class="col-sm-4 form-label">身份证号：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="idCard" id="idCard"
								placeholder="请输入您的身份证号" maxlength="18"> <span
								class="idMsg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="check" class="col-sm-4 form-label"><span
							class="check">验证码</span>：</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="check">
							<button type="button" onclick="getCode()" id="sendCode">获取验证码</button>
							<span>&nbsp;</span>
						</div>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-outline-secondary col-sm-3"
							onclick="addUser()" value="提交">
						<input type="reset"
							class="btn btn-outline-secondary col-sm-3" value="重置">
					</div>

				</form>
			</div>

		</div>
		<div class="content">
			<div class='header'>
				<h3>
					注册协议<a href="${pageContext.request.contextPath }/index"
						class="href">X</a>
				</h3>

			</div>
			<div class='body'>
				<p>
					<strong>【审慎阅读】</strong>您在申请注册流程中点击同意前，应当认真阅读以下协议。<strong
						style='text-decoration: underline'>请您务必审慎阅读、充分理解协议中相关条款内容，其中包括：</strong>
				</p>
				<p>
					1、<strong style='text-decoration: underline'>与您约定免除或限制责任的条款；</strong>
				</p>
				<p>
					2、<strong style='text-decoration: underline'>与您约定法律适用和管辖的条款；</strong>
				</p>
				<p>
					3、<strong style='text-decoration: underline'>其他以粗体下划线标识的重要条款。</strong>
				</p>
				<p>如您对协议有任何疑问，可向平台客服咨询。</p>
				<p>
					<strong>【特别提示】</strong>当您按照注册页面提示填写信息、阅读并同意协议且完成全部注册程序后，即表示您已充分阅读、理解并接受协议的全部内容。如您在使用平台服务过程中与其他用户发生争议的，依您与其他用户达成的协议处理。
				</p>
				<p>
					<strong style='text-decoration: underline'>阅读协议的过程中，如果您不同意相关协议或其中任何条款约定，您应立即停止注册程序。</strong>
				</p>
				<p style='text-align: center'>
					<button class="button" type='button'>同意协议</button>
				</p>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp" %>
	<script
		src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/canvas-nest.js"></script>
	<script>
		let main = document.querySelector(".main");
		let content = document.querySelector(".content");
		let btn = document.querySelector(".button");
		let form = document.querySelector(".form");
		window.onload = window.onresize = function() {
			main.style.width = window.innerWidth;
			main.style.height = window.innerHeight + "px";
			form.style.left = (window.innerWidth - form.offsetWidth) / 2 + "px";
			form.style.top = (window.innerHeight - form.offsetHeight) / 2
					+ "px";
			content.style.left = (window.innerWidth - 672) / 2 + "px";
			content.style.top = (window.innerHeight - 434) / 2 + "px";
		}
		btn.onclick = function() {
			main.removeChild(content)
		}
		//验证用户名是否符合要求
		function checkUser() {
			let user = $("#username").val();
			let userMsg = $('.userMsg');
			let regex = /[\u4e00-\u9fa5_a-zA-Z0-9_]{1,10}/;
			if (user === "") {
				userMsg.text("用户名不能为空")
				userMsg.addClass("msg");
				return false;
			} else if (!regex.test(user)) {
				userMsg.text("用户名格式不正确，请重新输入");
				userMsg.addClass("msg");
				return false;
			}
			userMsg.html("&nbsp;")
			userMsg.removeClass("msg");
			return true;
		}
		//验证手机号是否符合要求
		function checkPhone() {
                let phone = $("#phone").val();
                let phoneMsg = $(".phoneMsg");
                //手机号为常规的
                let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
                if (phone === "") {
                    phoneMsg.text("手机号不能为空！！！")
                    phoneMsg.addClass("msg");
                    return false;
                }
                else if (!regex.test(phone)) {
                    phoneMsg.text("手机号格式不正确，请重新输入！！！");
                    phoneMsg.addClass("msg");
                    return false;
                }
               	phoneMsg.html("&nbsp;")
                phoneMsg.removeClass("msg");
                return true;
            }
		//验证密码
		function checkPass() {
			let pass = $("#password").val();
			let passMsg = $(".passMsg");
			//密码由数字和字母组成
			let regex = /^(?=.*[a-z])(?=.*\d)[^]{6,12}$/;
			if (pass == "") {
				passMsg.text("密码不能为空");
				passMsg.addClass("msg")
				return false;
			} else if (!regex.test(pass)) {
				passMsg.text("密码格式不正确，请重新输入");
				passMsg.addClass("msg");
				return false;
			}
			passMsg.html("&nbsp;");
			passMsg.removeClass("msg");
			return true;
		}
		//验证两次密码是否一致
		function checkRepass() {
			let repass = $("#repassword").val();
			let rePassMsg = $(".rePassMsg");
			let pass = $("#password").val();
			//判断第二次输入的密码是否为空
			if (repass === "") {
				rePassMsg.text("密码不能为空")
				rePassMsg.addClass("msg");
				return false;
			}
			//判断第一次输入的密码是否和第二次输入的相同
			else if (repass !== pass) {
				rePassMsg.text("两次密码不一致");
				rePassMsg.addClass("msg");
				return false;
			}
			rePassMsg.html("&nbsp;");
			rePassMsg.removeClass("msg");
			return true;
		}
		//验证身份证号
		function checkId() {
			let idCard = $("#idCard").val();
			let idMsg = $(".idMsg");
			let regex = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
			//判断身份证号是否符合要求
			if (idCard == "") {
				idMsg.text("身份证号不能为空");
				idMsg.addClass("msg");
				return false;
			} else if (!regex.test(idCard)) {
				idMsg.text("身份证号不合法，请重新输入")
				idMsg.addClass("msg");
				return false;
			}
			idMsg.html("&nbsp;")
			idMsg.removeClass("msg");
			return true;
		}
		$("#username").blur(function() {
			checkUser();
		});
		$("#phone").blur(function(){
			checkPhone();
		});
		$("#password").blur(function() {
			checkPass();
		})
		$("#repassword").blur(function() {
			checkRepass();
		});
		$("#idCard").blur(function() {
			checkId();
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
			let phone = $("#phone").val();
			if(phone === "") {
				alert("手机号码不能为空");
			}else{
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
					phoneNumber : phone
				}),
				success : function(data) {
					alert(data.message)
				}
			})
			}
			
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
		//通过ajax发送到后台
		function addUser(){
			let username = $("#username").val();
			let phone = $("#phone").val();
			let password = $("#password").val();
			let sex = $('input[type=radio][name=sex]:checked').val();
			let idcard = $("#idCard").val();
			let codeNumber = $("#check").val();
			$.ajax({
				url: "${pageContext.request.contextPath}/adduser",
				type: "post",
				data: JSON.stringify({userName:username,phoneNumber:phone,passWord:password,sex:sex,idCard:idcard,codeNumber: codeNumber}),
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function(data){
					alert(data.message);
				}
			});
		}
	</script>
</body>
</html>