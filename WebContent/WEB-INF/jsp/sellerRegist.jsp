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
	width: 500px;
	height: 160px;
	margin:100px auto;
	background-color:#d3d7d4;
}
.storeName{
	position: relative;
	left: 40px;
	width: 400px;
	height: 40px;
	line-height: 40px;
	background-color: red;
}
.storeName input{
	border: none;
	outline: none;
	width: 400px;
	height: 40px;
	font-size: 18px;
	text-indent: 20px;
}
.btn{
	margin-left: 300px;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
</head>

<body>
	<%@include file="./header.jsp" %>
	<div class="main">
		<div class="form">
			<h3>请输入您的店铺名：</h3>
		</div>
		<div class="form-group row storeName">
			<input type="text" placeholder="请输入您的店铺名" name="storeName" class="name"/>
		</div>
		<div class="col-sm-6">
			<input type="button" class="btn btn-primary col-sm-6" value="提交"/> 
		</div>
	</div>
	<br>
	<%@include file="./footer.jsp" %>
	<script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/canvas-nest.js"></script>
	<script>
		$(".btn").on("click", function(){
			let storeName = $(".name").val();
			$.ajax({
				url: "${pageContext.request.contextPath}/becomeSeller",
				type: "post",
				dataType: "json",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify({
					storeName: storeName
				}),
				success: function(data){
					if(data.flag){
    					var payDiv = document.createElement('div');
    					payDiv.id = 'payDiv';
    					payDiv.innerHTML = data.data;
    					document.body.appendChild(payDiv);
    					document.querySelector('#payDiv').children[0].submit();
    				} else {
    					console.log(data.message);
    				}
				}
			})
		})
	</script>
</body>
</html>