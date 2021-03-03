<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <style>
            *{
                margin: 0;
                padding: 0;
            }
            .content{
                width: 600px;
                margin: 100px auto 0;
            }
            .modify{
                margin-left: 420px;
            }
            .hidden{
            	display: none;
            }
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    </head>
    <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <body>
    	<%@include file="header.jsp" %>
        <div class="content">
            <form>
            	<input type="text" value="<%=id %>" class="hidden">
                <div class="form-group row">
                    <label for="userName" class="col-sm-3 form-label">用户名：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="userName">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-3 form-label">绑定手机号：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="phone">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="passWord" class="col-sm-3 form-label">密码：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="passWord">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="idCard" class="form-label col-sm-3">身份证号：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="idCard">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="sex" class="form-label col-sm-3">性别：</label>
                    <div class="col-sm-2">
                        <input type="radio" name="sex" value="男">男
                    </div>
                    <div class="col-sm-2">
                        <input type="radio" name="sex" value="女">女
                    </div>
                </div>
                <div class="form-group row">
                    <input type="button" onclick="updateUser()" class="btn btn-danger modify" value="修改">
                </div>
            </form>
        </div>   
    <script src="${pageContext.request.contextPath }/js/canvas-nest.js"></script>
    <script>
    	//获取验证码
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
			let tel = $("#phone").val();
			if(tel == ""){
				alert("手机号不能为空")
			}else{				
				 //设置button效果，开始计时
	            $("#sendCode").attr("disabled", "true");
	            $("#sendCode").val("请在" + curCount + "秒内输入验证码");
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
	            $("#sendCode").val("重新发送验证码");
	            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
	        }
	        else {
	            --curCount;
	            $("#sendCode").val("请在" + curCount + "秒内输入验证码");
	        }
	    }
		window.onload = function(){
		    $("#userName").val( "<%=username%>");
	        $("#phone").val("<%=phoneNumber%>");
	        $("#passWord").val("<%=password%>");
	        $("#idCard").val("<%=idCard%>");
	        let sex = "<%=sex%>";
			$("input[name='sex']").each(function(){
				if($(this).val() == sex){
					$(this).prop("checked", true);
				}
			});
		}
		function updateUser(){
			let id = $(".hidden").val();
			let phone = $("#phone").val();
			let password = $("#passWord").val();
			let idCard = $("#idCard").val();
			let username = $("#userName").val();
			let sex;
			$("input[name='sex']").each(function(){
				if(this.checked){
					sex = $(this).val();
				}
			});
			$.ajax({
				url: "${pageContext.request.contextPath}/updateUser",
				type: "post",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				data: JSON.stringify({
					id: id,
					phoneNumber: phone,
					passWord: password,
					userName: username,
					idCard: idCard,
				}),
				success: function(result){
					if(result.flag){
						alert("修改成功");
						window.location.replace("${pageContext.request.contextPath}/modifyUserInfo");
					}
				}
			})
		}
    </script>
    </body>
</html>