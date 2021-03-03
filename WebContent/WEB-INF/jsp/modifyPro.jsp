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
	margin: 10px 0 0 40px;
}

.goodsList {
	width: 1060px;
	position: relative;
	margin: 10px auto;
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
	height: 240px;
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

.goodsLi:hover {
	border: 1px solid #ff2300;
}

.goodsLi:hover .edit {
	display: block;
}

.img {
	width: 200px;
	height: 200px;
	background-color: red;
}
.input-file-box {
	border: 1px solid gray;
	width: 80px;
	height: 60px;
	position: relative;
	text-align: center;
	border-radius: 8px;
}
.input-file-box>span {
	display: block;
	width: 80px;
	height: 30px;
	position: absolute;
	top: 0px;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
	color: gray;
}

/*input框*/
.input-file-box .uploadfile {
	opacity: 0;
	width: 100%;
	height: 100%;
	cursor: pointer;
}

.box li {
	list-style: none;
	float: left;
	margin: 10px;
	border: 1px solid #ccc;
}
.hidden{
	display:none;
}
.submit{
	margin-left: 5px;
}
.goodsId{
display:none;}

</style>
</head>
<body>
	<div class="form">
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath }/updateCommodity" method="post" id="uploadCommodityForm">
			<div class="form-group row">
				<input type="button" class="btn back" value="< 返回"/>
			</div>
			<div class="form-group row">
				<input type="text" value="30" name="id" class="hidden">
				<label for="proType" class="col-sm-2 col-form-label">商品类别：</label>
				<div class="col-sm-3">
					<input type="text" name="commodityType" class="form-control dis"
						id="proType">
				</div>
				<label for="proName" class="col-sm-2 col-form-label">商品名：</label>
				<div class="col-sm-3">
					<input type="text" name="commodityName" class="form-control dis"
						id="proName">
				</div>
				<div class="col-sm-1">
					<input type="button" class="btn btn-primary modifyTN" value="修改">
				</div>
				<div class="col-sm-1">
					<input type="button" class="btn btn-primary submitTN" value="提交">
				</div>
			</div>
			<div class="form-group row">
				<table class="table table-striped table-hover table-bordered"
					id="form-table">
					<tr>
						<td>商品规格</td>
						<td>商品价格</td>
						<td>商品库存</td>
						<td>操作</td>
					</tr>

				</table>
			</div>
			</form>
			<form action="" id="uploadCommodityPictureForm">			
				<div class="form-group row uploadPic">
					<div class="input-file-box">
						<input type="text" name="id" class="goodsId">
						<span>点击上传图片</span> <input type="file" name="commodityImage" class="uploadfile">
					</div>
				</div>
				<div class="form-group row">
					<ul class="goodsList">
					</ul>
				</div>
			</form>
			
		</form>
	</div>
	<script>
		window.onload = function() {
			let ul = $("#form-table");

			let id = JSON.parse(localStorage.getItem("id"));
			let goodsList = document.querySelector(".goodsList");
			$.ajax({
				url: "${pageContext.request.contextPath}/singleCommodity/"+id,
				type: "get",
				success: function(result){
					if(result.flag){
						let object = result.data;
						let str1 = "";
						let str2 = "";
						let imageList = [];
						let length = object.commodityDetailsList.length;
						
						for(let i = 0; i < length; i++){
							str1 += `
								<tr>
								<form>
									<td>
										<input type="text" name="id" class="hidden" value="\${object.commodityDetailsList[i].id}" class="hidden">
										<input type="text" name="specifications" class="dis form-control col-sm-12 specifications" value="\${object.commodityDetailsList[i].specifications}">
									</td>
									<td>
										<input type="text" name="price" class="dis form-control col-sm-12 price" value="\${object.commodityDetailsList[i].price}">
									</td>
									<td>
										<input type="text" name="stock" class="dis form-control col-sm-12 stock" value="\${object.commodityDetailsList[i].stock}">
									</td>
									<td>
										<input type="button" class="modify btn btn-primary"  value="修改">
										<input type="button" class="submit btn btn-primary" value="提交">
									</td>
									</form>
								</tr>`;
						}
						
						$("#proType").val(object.commodityType);
						$("#proName").val(object.commodityName);
						ul.html(str1);
						$(".dis").each(function(){
							$(this).attr("disabled", true)
						})
						$(".submit").each(function(){
							$(this).attr("disabled", true)
						})
						for(let i = 1; i <= 15; i++){
							if(object[`commodityPicture\${i}`] !== null){
								imageList.push(object[`commodityPicture\${i}`])
							}
						}
						for(let i = 0; i < imageList.length; i++){
							str2 += `<li class="goodsLi">
							<div class="edit">
								<button type="button" class="del">删除</button>
							</div>
							<div class="good">
								<img src="/images/\${imageList[i]}"
									width="200" value="\${i + 1}" height="200" alt="">
							</div>
						</li>`;
						}
						goodsList.innerHTML += str2;
						$(".modify").on("click", function(){
							$(this).each(function(){					
								$(this).parent().parent().find(".dis").removeAttr("disabled");
								$(this).parent().parent().find(".submit").removeAttr("disabled")
							})
						})
						$(".submit").on("click", function(){
							let that = $(this);
							$(this).each(function(){
								let id = $(this).parent().parent().find(".hidden").val();
								let specifications = $(this).parent().parent().find(".specifications").val();
								let price = $(this).parent().parent().parent().find(".price").val();
								let stock = $(this).parent().parent().parent().find(".stock").val();
								$.ajax({
									url: "${pageContext.request.contextPath}/updateCommodityDetails",
									type: "post",
									contentType: "application/json;charset=UTF-8",
									dataType: "json",
									data: JSON.stringify({
										id: id,
										specifications: specifications,
										price: price,
										stock: stock
									}),
									success: function(result) {
										alert("修改成功");
										that.attr("disabled", true)
										that.parent().parent().find(".dis").each(function(){
											$(this).attr("disabled", true)
										})
									}
								});
							});
						})
						$(".submitTN").attr("disabled",true);
						$(".modifyTN").on("click", function(){
							$(this).parent().parent().find(".dis").removeAttr("disabled");
							$(".submitTN").removeAttr("disabled")
						})
						$(".submitTN").on("click", function(){
							let that = $(this)
							$(this).each(function(){
								let id = object.id;
								let commodityType = $(this).parent().parent().find("#proType").val();
								let commodityName = $(this).parent().parent().find("#proName").val();
								$.ajax({
									url: "${pageContext.request.contextPath}/updateCommodity",
									type: "post",
									contentType: "application/json;charset=UTF-8",
									dataType: "json",
									data: JSON.stringify({
										id: id,
										commodityType: commodityType,
										commodityName: commodityName
									}),
									success: function(result) {
										if(result.flag){
											alert("修改成功");
											that.attr("disabled", true)

											that.parent().parent().find(".dis").each(function(){
												$(this).attr("disabled", true)
											})
										}
									}
								});
							})
						})
						let input = document.getElementsByClassName("uploadfile");
						let filebox = $(".uploadPic")
						let str = `<div class="input-file-box">
								<input type="text" name="id" class="goodsId">
			                    <span>上传图片</span>
			                    <input type="file" name="commodityImage" class="uploadfile" >
			                </div>
			               `;
			            
			            filebox.html(str)
			            for(let i = 0; i < input.length; i++){
			                input[i].onchange = function(){
			                	uploadCommodityPicture();
			                    readFile(input[i])
			                }
			            }
						//处理图片并添加都dom中的函数
						var readFile = function(obj) {
							// 获取input里面的文件组
							var fileList = obj.files;
							//对文件组进行遍历，可以到控制台打印出fileList去看看
							for (var i = 0; i < fileList.length; i++) {
								var reader = new FileReader();
								reader.readAsDataURL(fileList[i]);
								// 当文件读取成功时执行的函数
								reader.onload = function(e) {
									goodsList.innerHTML += '<li class="goodsLi"><div class="edit"><button type="button" class="del">删除</button></div><div class="good"><img src="'+this.result+'" width="200" height="200" alt=""></li>';
								}
							}
						}
						let del = document.getElementsByClassName("del");
						let id = object.id;
						for(let i = 0; i < del.length; i++){
							del[i].onclick = function(){
								let pictureId = $(this).parent().next().children().attr("value");
								let that = $(this);
								$.ajax({
									url: "${pageContext.request.contextPath}/deleteCommodityPicture",
									type: "post",
									contentType: "application/json;charset=UTF-8",
									dataType: "json",
									data: JSON.stringify({
										id: id,
										pictureId: pictureId
									}),
									success: function(result){
										if(result.flag){
											that.parent().parent().remove();
										}
									}
								});
							}
						}
						$(".goodsId").each(function(){
							$(this).val(id)
						})
						
						function uploadCommodityPicture() {
							$.ajax({
								url: "${pageContext.request.contextPath}/uploadCommodityPicture",
								type: "post",
								contentType: false,
								processData: false,
								dataType: "json",
								data: new FormData($("#uploadCommodityPictureForm")[0]),
								success: function(result) {
									if(result.flag){
										alert("添加成功！")
									}
								}
							});
						}
				}
			}})
		}	
		$(".back").click(function(){
			history.go(-1)
		})
	</script>
</body>
</html>