<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	margin: 0;
	padding: 0;
}
/*搜索框*/
.search {
	width: 100%;
	background-color: #fff;
}

.search-box {
	max-width: 1190px;
	position: relative;
	margin: 0px auto;
	height: 120px;
}

.logo {
	position: absolute;
	width: 120px;
	height: 120px;
}

.search-bar {
	position: absolute;
	left: 300px;
	width: 503px;
	height: 44px;
	top: 35px;
	/* background-color: purple; */
	border-radius: 20px;
	border: 2px solid #fe2300;
	overflow: hidden;
}

.find {
	width: 403px;
	height: 40px;
	margin-left: 20px;
	border: none;
	outline: none;
	text-indent: 10px;
}

.search-btn {
	width: 80px;
	height: 41px;
	border: none;
	border-radius: 0 20px 20px 0;
	color: #fff;
	background-color: #ff4e00;
	font-size: 16px;
	font-weight: bold;
	position: absolute;
}

.search-btn:focus {
	outline: none;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
</head>
<body>
	<section class="search">
		<div class="search-box">
			<div class="logo">
				<h2>
					<img src="${pageContext.request.contextPath }/images/logo.jpg"
						width="120" height="120" />
				</h2>
			</div>
			<div class="search-bar">
				<input type="text" class="find" placeholder="输入要查询的商品" name="searchContent" autofocus>
				<button type="button" class="search-btn" onclick="search()">搜&nbsp;&nbsp;索</button>
			</div>
		</div>
	</section>
	<script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
	<script>
		//发送搜索信息给后台
		function search(){
			let content = $(".find").val();
			$.ajax({
				url: "${pageContext.request.contextPath}/searchCommodity",
				type: "post",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				data: JSON.stringify({
					searchContent: content,
				}),
				success: function(result){
					console.info(result)
					let id = [];
					let sellerId = [];
					if(result.flag){
						let object = result.data;
						for(let i = 0; i < object.length; i++){
							id.push(object[i].id);
							sellerId.push(object[i].sellerId);
						}
						id = JSON.stringify(id)
						sellerId = JSON.stringify(sellerId)
						let value = JSON.stringify($(".find").val())
						localStorage.setItem("id", id);
						localStorage.setItem("sellerId", sellerId);
						localStorage.setItem("value", value);
						window.open("${pageContext.request.contextPath}/proList","_self");
					}
				}
			})
		}
	</script>
</body>
</html>