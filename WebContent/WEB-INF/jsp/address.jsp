<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		margin: 0;
		padding: 0;
		list-style: none;
	}
	.address{
		width: 800px;
		height: 60px;
		margin:20px auto;
	}
	.addressList .address-item{
		width: 800px;
		margin: 5px 0;
		position: relative;
		height: 40px;
		line-height: 40px;
	}
	.addressList .address-item .btn-group{
		position:absolute;
		right: 0;
	}
	.addressList .address-item .btn-group button:first-child{
		margin-right: 10px;
	}
	.hidden{
		display: none;
	}
	.modal-dialog .modal-content{
		width: 550px;
	}
	.modal-body{
		width: 550px;
	}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/distpicker.data.js"></script>
<script src="${pageContext.request.contextPath }/js/distpicker.js"></script>
</head>
<body>
	<%@include file="header.jsp" %>
	<form action="">
		<div class="address">	
			<ul class="addressList"></ul>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改收货地址</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <form class="form-inline" id="addressForm">
	                        <div data-toggle="distpicker" class="distpicker">
	                            <div class="form-group">
	                                <label class="sr-only" for="province">Province</label>
	                                <select class="form-control" id="province" data-province="---- 选择省 ----"></select>
	                            </div>
	                            <div class="form-group">
	                                <label class="sr-only" for="city">City</label>
	                                <select class="form-control" id="city" data-city="---- 选择市 ----"></select>
	                            </div>
	                            <div class="form-group">
	                                <label class="sr-only" for="district">District</label>
	                                <select class="form-control" id="district" data-district="---- 选择区 ----"></select>
	                            </div>
	                        </div>
	                		<div class="detail">
	                         <div class="form-group row">
	                             <label for="details-address" class="col-sm-4 form-label">请输入详细地址：</label>
	                             <div class="col-sm-6">
	                                 <input type="text" id="details-address" class="form-control" >
	                             </div>
	                         </div>
	                         <div class="form-group row">
	                             <label for="recName" class="col-sm-4 form-label">收货人：</label>
	                             <div class="col-sm-6">
	                                 <input type="text" id="recName" name="userName" class="form-control">
	                             </div>
	                         </div>
	                         <div class="form-group row">
	                             <label for="recTel" class="col-sm-4 form-label">联系电话：</label>
	                             <div class="col-sm-6">
	                                 <input type="text" class="form-control" name="phoneNumber" id="recTel">
	                             </div>
	                             <input type="text" name="detailedAddress" id="detailedAddress" class="detailedAddress hidden">
	                         </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                  <button type="button" class="btn btn-primary modify">保存修改</button>
                </div>
              </div>
            </div>
          </div>
	</form>
	<%@include file="./footer.jsp" %>
	<script>
		window.onload = function(){
			let str = "";
			let addressList = document.querySelector(".addressList")
			$.ajax({
				url: "${pageContext.request.contextPath}/findAddress",
				type: "post",
				success: function(result){
					let object = result.data;
					if(result.flag){
						for(let i = 0; i < object.length; i++){
							str += `<li class="address-item">
								<input type="text" value="\${object[i].id}" class="hidden">
								<span>\${object[i].detailedAddress}&nbsp;&nbsp;\${object[i].userName}&nbsp;&nbsp;\${object[i].phoneNumber}</span>
								<div class="btn-group" role="group"  aria-label="Basic">
									<button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#exampleModal">修改</button>
									<button type="button" class="btn btn-danger del">删除</button>
								</div>
							</li>`;
						}
						addressList.innerHTML = str;
						$(".del").on("click", function(){
							let that = $(this);
							let id = $(this).parent().prev().prev().val();
							if(confirm("确定删除吗")){								
								$.ajax({
									url: "${pageContext.request.contextPath}/deleteAddress/"+id,
									type: "post",
									success: function(result){
										if(result.flag){	
											alert("删除成功");
											that.parent().parent().remove();
										}
									}
								})
							}
						})
			            let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; 
			            $(".update").on("click", function(){
			            	let that = $(this);
			            	$("#details-address").on("blur", function(){
					            $("#detailedAddress").val($("#province option:selected").html()+$("#city option:selected").html()+$("#district option:selected").html()+$(this).val())    
							})
			            	$(".modify").on("click", function(){
			            		let that2 = $(this);
								if(confirm("确定修改吗")){
									
									if($("#province option:selected").html() === "---- 选择省 ----"){
					                    alert("请选择省份");
					                }
					                if($("#city option:selected").html() === "---- 选择市 ----"){
					                    alert("请选择市");
					                }
					                if($("#district option:selected").html() === "---- 选择区 ----"){
					                    alert("请选择区");
					                }
					                if($("#details-address").val() === ""){
					                    alert("请输入详细地址");
					                }
					                if($("#recName").val() === ""){
					                    alert("请输入收货人的名字");
					                }
					                if($("#recTel") === ""){
					                    alert("联系电话不能为空");
					                }
					                if(!regex.test($("#recTel").val())){
					                    alert("联系电话不合法，请重新输入");
					                    $("#recTel").val("");
					                }else{
					                	let id = that.parent().prev().prev().val();
					                	let username = that2.parent().parent().find("#recName").val();
					                	let phone = that2.parent().parent().find("#recTel").val();
					                	let address = that2.parent().parent().find("#detailedAddress").val();
					                	$.ajax({
					                		url: "${pageContext.request.contextPath}/updateAddress",
					                		type: "post",
					                		contentType: "application/json;charset=UTF-8",
					                		dataType: "json",
					                		data: JSON.stringify({
					                			id: id,
					                			userName: username,
					                			phoneNumber: phone,
					                			detailedAddress: address
					                		}),
					                		success: function(result){
					                			console.info(result)
					                			if(result.flag){
					                				alert("修改成功");
					                				location.reload();
					                			}
					                		}
					                	})
					                }
								}
							})
			            })
						
					}else{
						alert("请先登录");
						window.location.href = "${pageContext.request.contextPath}/login";
					}
				}
			})
		}
		
	</script>
</body>
</html>