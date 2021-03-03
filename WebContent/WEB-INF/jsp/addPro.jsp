<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	height: 400px;
	margin: 10px auto 0;
}

.input-file-box {
	border: 1px solid gray;
	width: 80px;
	height: 60px;
	position: relative;
	text-align: center;
	border-radius: 8px;
}

/*文字描述*/
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

.add {
	border: none;
	margin: 5px 0 5px 0;
	padding: 5px;
}

.add:focus {
	outline: none;
}

</style>
</head>
<body>
	<div class="form" >
		<form enctype="multipart/form-data" method="post" id="uploadCommodityForm">
			<div class="form-group row">
				<label for="proType" class="col-sm-2 col-form-label">商品类别：</label>
				<div class="col-sm-3">
					<input type="text" name="commodityType" class="form-control" id="proType">
				</div>
				<label for="proName" class="col-sm-2 col-form-label">商品名：</label>
				<div class="col-sm-3">
					<input type="text" name="commodityName" class="form-control" id="proName">
				</div>
			</div>
			<div class="form-group row">
				<button type="button" class="add">新增一行</button>
				<table class="table table-striped table-hover table-bordered"
					id="form-table">
					<tr>
						<td>商品规格</td>
						<td>商品价格</td>
						<td>商品库存</td>
						<td>操作</td>
					</tr>
					<tr>
						<td><input type="text" name="specifications" class="form-control col-sm-12 specs"></td>
						<td><input type="text" name="price" class="form-control col-sm-12 price"></td>
						<td><input type="text" name="stock" class="form-control col-sm-12 count"></td>
						<td><input type="button" class="btn btn-danger del" value="删除"/></td>
					</tr>

				</table>
			</div>
			<div class="form-group row uploadPic">
				<div class="input-file-box">
					<span>上传图片</span> <input type="file" class="uploadfile" name="commodityImage" multiple>
				</div>
			</div>
			<div class="form-group row">			
				<div id="img-box">
					<ul class="box"></ul>
				</div>
			</div>
			<div class="form-group row">
				<input type="button" id="addpro" onclick="addPro()" class="btn btn-secondary col-sm-12 " value="提&nbsp;&nbsp;&nbsp;&nbsp;交">
			</div>
		</form>
	</div>
	<script>
		window.onload = function() {
			let input = document.getElementsByClassName("uploadfile");
			
			var box = document.querySelector(".box");
			// 当用户上传时触发事件
			let filebox = $(".uploadPic");
            // 当用户上传时触发事件
            let str = '';
            for(let i = 0; i < 15; i++){
                str += `<div class="input-file-box">
                    <span>上传图片</span>
                    <input type="file" name="commodityImage" class="uploadfile" >
                </div>
                `;
            }
            filebox.html(str)
            for(let i = 0; i < input.length; i++){
                input[i].onchange = function(){
                	$(this).prev().css("color","#1d953f");
                    readFile(input[i]);
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
						box.innerHTML += '<li class="image"><img src="' + this.result + '" width="140" height="140" /></li>';
					}
				}
			}
		}
		
		//验证价格和库存
		let regex = /(?!^0*(\.0{1,2})?$)^\d{1,13}(\.\d{1,2})?$/;
		let add = document.querySelector(".add");
		let table = document.querySelector(".table");
		add.onclick = function() {
			let tr = document.createElement("tr");
			let flag = document.createDocumentFragment();
			for (let i = 0; i <= 3; i++) {
				let td = document.createElement("td");
				let input = document.createElement("input");
				input.type = "text";
				if(i === 0){
					input.setAttribute("name", "specifications");
				}
				if(i === 1){
					input.setAttribute("name", "price");
				}
				if(i === 2){
					input.setAttribute("name", "stock");
				}
				if(i === 3){
					input.setAttribute("type","button")
					input.classList.add("btn");
					input.classList.add("btn-danger")
					input.classList.add("del")
					input.value = "删除"
				}
				input.classList.add("form-control");
				input.classList.add("col-sm-12");
				tr.appendChild(td);
				td.appendChild(input);
				flag.appendChild(tr);
			}
			table.appendChild(flag);
			$(".del").click(function(){
				$(this).parent().parent().remove();
			})
		}
		//将新增的数据发送到后台
		function addPro(){
			if(checkPriceAndCount()){
				$.ajax({
					url: "${pageContext.request.contextPath}/uploadCommodity",
					type: "post",
					contentType: false,
					processData: false,
					dataType: "json",
					data: new FormData($("#uploadCommodityForm")[0]),
					success: function(data) {
						if(data.flag){
							alert("新增成功");
							location.reload();
						}
					}
				});
			}else{
				alert("商品信息不能为空")
			}
		}
		//验证价格
		$(".price").on("blur", function(){
			if(!regex.test($(this).val())){
				alert("商品价格输入不合理，请重新输入！");
				$(this).val("");
			}
		})
		$(".count").on("blur", function(){
			if(!regex.test($(this).val())){
				alert("商品库存输入不合法，请重新输入！")
				$(this).val("");
			}
		})
		$("#proType").on("blur", function(){
			if($(this).val() === ""){
				alert("商品类别不能为空");
			}
		})
		$("#proName").on("blur", function(){
			if($(this).val() === ""){
				alert("商品名不能为空");
			}
		})
		//验证价格和库存是否合理
		function checkPriceAndCount(){
			let price = $(".price").val();
			let count = $(".count").val();
			let proType = $("#proType").val();
			let proName = $("#proName").val();
			if(price === "" || count === "" || proType === "" || proName === ""){
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>