<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

.form {
	width: 800px;
	height: 300px;
	margin: 10px auto;
}

.find {
	display: block;
	background-color: #007DFF;
	position: absolute;
	color: #fff;
	text-decoration: none;
	width: 40px;
	height: 38px;
	line-height: 38px;
	text-indent: 3px;
	right: -24px;
	top: 0px;
}

.find:hover {
	text-decoration: none;
	color: #fff;
}

.form {
	width: 800px;
	height: 300px;
}

.goodsList {
	width: 1060px;
	position: relative;
	margin-left: -100px;
}

.edit {
	position: absolute;
	width: 40px;
	margin: 20px 10px 0 180px;
	z-index: 2;
	display: none;
}

.edit button {
	width: 36px;
	background-color: #ff2300;
	margin: 3px;
	border: none;
	outline: none;
	color: #fff;
}

.goodsList li {
	margin: 10px;
	width: 240px;
	height: 320px;
	border: 1px solid #ccc;
	list-style: none;
	float: left;
}

.good {
	position: absolute;
	width: 200px;
	height: 200px;
	margin: 20px 20px 10px;
}

.price span {
	font-size: 20px;
	color: #ff2300;
	font-weight: bold;
}

.count {
	position: absolute;
	right: 0px;
	bottom: -90px;
}

.counts {
	color: #76624c;
}

.goodsLi:hover {
	border: 1px solid #ff2300;
}

.goodsLi:hover .edit {
	display: block;
}

.goodsName {
	width: 200px;
	height: 47px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.id{
	display: none;
}
</style>
</head>
<body>
	<div class="form">
		<div class="showPro">
			<form method="post">
				<ul class="goodsList">
				</ul>	
			</form>
		</div>
	</div>
<script>
 window.onload = function(){
   	let str = "";
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath}/findSellerCommodity",
		success: function(result){
			//商品数组
			let ul = document.querySelector(".goodsList");
			let arr = result.data;
			
			for(let i = 0; i < arr.length; i++){
				var price = arr[i].commodityDetailsList[0]['price'];
				let imageList = [];
				
				for(let j = 1;j<=15;j++){
				   if(arr[i][`commodityPicture\${j}`]!=null){
					   imageList.push(arr[i][`commodityPicture\${j}`]);
				   }
			   }
				
				str += `
				<li class="goodsLi">
	              <div class="edit">
					<input type="text" value="\${arr[i].id}" name="id" class="id"/>
                    <button type="button" class="modify">修改</button>
                    <button type="button" class="delete">删除</button>
                  </div>
                  <div class="good">
                    <a href="#"><img src="/images/\${imageList[0]}" width="200" height="200" alt=""></a>
                    <div class="goodsName">        	                    
	                    <a href="#" class="goodsName">\${arr[i].commodityName}</a>
                    </div>
                    <div class="price">
                      <span>\${price}￥</span>
                    </div>
                    <div class="count">
                      <p class="counts">月销<span class="salesCount">\${arr[i].commodityCount}</span>笔</p>
                    </div>
                  </div>
                </li>`;
			}
			ul.innerHTML += str;
			
  			$(".modify").click(function(){
  				let id = $(this).prev().val();
  				$.ajax({
  					url: "${pageContext.request.contextPath}/singleCommodity/"+id,
  					type: "get",
  					contentType: "application/json;charset=UTF-8",
  					dataType: "json",
  					success: function(result) {
  						if(result.flag){
  							let id = JSON.stringify(result.data.id);
  							localStorage.setItem("id", id);
  							window.location.href = "${pageContext.request.contextPath}/modifyPro";
  						}
  					}
  				});
  			})
  	
  	
  			$(".delete").click(function(){
  				let id = $(this).prev().prev().val();
  				let that = $(this)
  				if(confirm("确定删除吗")){
  					$.ajax({
  						url: "${pageContext.request.contextPath}/deleteCommodity/"+id,
  						type: "get",
  						success: function(result){
  							if(result.flag){
  								that.parent().parent().remove();
  							}
  						}
  					})
  				}
 		 	})
		}
	});
};
</script>
</body>
</html>