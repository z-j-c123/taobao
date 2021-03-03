<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		marign: 0;
		padding: 0;
	}
	.allSoldPro{
		max-width: 1090px;
		margin: 0 auto;
	}
	.proName{
		width: 180px;
	}
	.table td{
		text-align:center;
	}
	
	.operate input{
		display: none;
	}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="allSoldPro">
		<table class="table table-border table-hover">
			<tr>
				<td width="20%">商品详情</td>
				<td width="15%">商品规格</td>
				<td width="10%">购买数量</td>
				<td width="8%">总价</td>
				<td width="13%">订单状态</td>
				<td width="18%">订单编号</td>
				<td>操作</td>
			</tr>
		</table>
		<ul class="tableList">
		</ul>
	</div>
	<script>
		window.onload = function(){
			
			$.ajax({
				url: "${pageContext.request.contextPath}/getListSellOrder",
				type: "post",
				success: function(result){
					if(result.flag){
						let object = result.data.data;
						let tableList = document.querySelector(".tableList");
						let str = "";
						for(let i = 0; i　< object.length; i++){
							str += `
								<table class="table table-border table-hover">
									<tr>
										<td width="20%">
											<img src="/images/\${object[i].commodityPicture}" width="50" height="50"/>
											<div class="proName">\${object[i].commodityName}</div>
										</td>
										<td width="15%">\${object[i].specifications}</td>
										<td width="10%">\${object[i].orderCount}</td>
										<td width="8%">\${object[i].orderPrice}</td>
										<td width="13%"><div class="orderStatus">\${object[i].orderStatus}</div></td>
										<td width="18%">\${object[i].orderNumber}</td>
										<td class="operate">
											<input type="text" value="\${object[i].id}" class="hidden"/>
											<input type="button" class="btn btn-primary delive" value="发货"/>
											<input type="button" class="btn btn-primary agree" value="同意"/>
											<input type="button" class="btn btn-primary reject" value="拒绝"/>
										</td>
									</tr>
								</table>
							`;
						}
						tableList.innerHTML = str;
						let tr = document.getElementsByTagName("tr");
						let operate = document.getElementsByClassName("operate");
						for(let i = 0; i < operate.length; i++){
							hidden(operate[i])
						}
						function hidden(el){
							let status = $(el).prev().prev().find(".orderStatus").html();
							if(status === "申请退款"){
								$(el).find(".agree").css("display", "inline-block");
								$(el).find(".reject").css("display", "inline-block")
							}else if(status === "待发货"){
								$(el).find(".delive").css("display", "inline-block")
							}
						}
						for(let i = 0; i < tr.length; i++){
							if(i === 0){
								continue;
							}
							center(tr[i])
						}
						function center(el){
							let add = $(el).children()[0];
							add.style.textAlign = "left";
						}
						//发货
						$(".delive").on("click", function(){
							let id = $(this).prev().val();
							$.ajax({
								url: "${pageContext.request.contextPath}/deliver/"+id,
								type: "post",
								success: function(result){
									if(result.flag){
										alert("操作成功");
										location.reload();
									}
								}
							})
						})
						//同意退款
						$(".agree").on("click", function(){
							let id = $(this).prev().prev().val();
							let message = $(this).val();
							$.ajax({
								url: "${pageContext.request.contextPath}/refund",
								type: "post",
								dataType: "json",
								contentType: "application/json;charset=UTF-8",
								data: JSON.stringify({
									id: id,
									message: message
								}),
								success: function(result){
									console.info(result)
								}
							})
						})
						//拒绝退款
						$(".reject").on("click", function(){
							let id = $(this).prev().prev().prev().val();
							let message = $(this).val();
							$.ajax({
								url: "${pageContext.request.contextPath}/refund",
								type: "post",
								dataType: "json",
								contentType: "application/json;charset=UTF-8",
								data: JSON.stringify({
									id: id,
									message: message
								}),
								success: function(result){
									if(result.flag){
										alert("操作成功");
										location.reload();
									}
								}
							})
						})
					}
				}
			})	
		}
	</script>
</body>
</html>