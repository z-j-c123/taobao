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
                list-style: none;
            }
            .content{
                max-width: 1090px;
                margin: 50px auto;
            }
            table ul{
                display: flex;
                justify-content: space-between;
            }
            .distpicker{
                display: flex;
            }
            .distpicker div{
                margin-right: 20px;
            }
            .detail{
                margin: 20px 0 0 0;
            }
            #userinfoContent{
                margin-top: 40px;
            }
            .reciveAddress{
                text-indent: 15px;
            }
            .modify{
                position: absolute;
                right: 20px;
            }
            .hidden{
            	display: none;
            }
            .form-group{
            	margin:5px 0;
            }
            #btn{
            	margin-left:20px;
            }
            .address{
				width: 800px;
				margin:100px 0 0;
			}
			.addressList{
				width: 900px;
			}
			.addressList .address-item{
				width: 900px;
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
			
			.modal-dialog .modal-content{
				width: 550px;
			}
			.modal-body{
				width: 550px;
			}
			.allAddress{
				width: 900px;
			}
			.quantity{
				font-size: 14px;
				width: 900px;
				height: 40px;
				background-color: #DEF3FD;
				margin-top: 80px;
				line-height: 40px;
			}
			.icontishi::before{
				font-size: 20px;
				margin:10px 10px 0 10px;
			}
			.refund, .confirm{
				font-size: 12px;
				margin:0 2px 0 0;
			}
			.proName{
				width: 160px;
			}
			td{
				text-align: center;
			}
			.operate input{
				display: none;
			}
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/font/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/distpicker.data.js"></script>
        <script src="${pageContext.request.contextPath}/js/distpicker.js"></script>
    </head>
    <body>
    	<%@include file="header.jsp" %>
    	<div>
        <div class="content">
            <div class="row">
                <div class="col-2">
                    <div class="nav flex-column nav-pills" id="userinfo" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="info" data-toggle="pill" href="#info-detail"
                            role="tab" aria-controls="info-detail" aria-selected="true">个人资料</a>
                        <a class="nav-link" id="address" data-toggle="pill" href="#address-detail"
                            role="tab" aria-controls="address-detail" aria-selected="false">收货地址</a>
                        <a class="nav-link" id="purchased" data-toggle="pill" href="#bought"
                            role="tab" aria-contorls="bought" aria-selected="false">我的订单</a>  
                    </div>
                </div>
                <div class="col-10">
                    <div class="tab-content" id="userinfoContent">
                        <div class="tab-pane fade show active" id="info-detail" role="tabpanel" aria-labelledby="info">
                            <table class="table table-border table-hover table-striped">
                                <tr>
                                    <td>
                                        <ul>
                                            <li>用户名：</li>
                                            <li class="username"></li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <ul>
                                            <li>绑定手机号：</li>
                                            <li class="phoneNumber"></li>
                                        </ul>
                                    </td>
                                </tr> 
                                <tr>
                                    <td>
                                        <ul>
                                            <li>密码：</li>
                                            <li class="password"></li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <ul>
                                            <li>身份证号：</li>
                                            <li class="idCard"></li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <ul>
                                            <li>性别：</li>
                                            <li class="sex"></li>
                                        </ul>
                                    </td>
                                </tr>
                            </table>
                            <div class="form-group">
                                <input type="button" class="btn btn-outline-danger modify"  value="修改">
                            </div>
                        </div>
                        <div class="tab-pane fade" id="address-detail" role="tabpanel" aria-labelledby="address">
                            <div class="reciveAddress">
                                <div>
                                    <span>新增收货地址：</span>
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
	                                                <label for="details-address" class="col-sm-5 form-label">请输入详细地址：</label>
	                                                <div class="col-sm-6">
	                                                    <input type="text" id="details-address" class="form-control" >
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="recName" class="col-sm-5 form-label">收货人：</label>
	                                                <div class="col-sm-6">
	                                                    <input type="text" id="recName" name="userName" class="form-control">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="recTel" class="col-sm-5 form-label">联系电话：</label>
	                                                <div class="col-sm-6">
	                                                    <input type="text" class="form-control" name="phoneNumber" id="recTel">
	                                                </div>
	                                                <input type="text" name="detailedAddress" class="detailedAddress hidden">
	                                            </div>
	                                            <div class="form-group row btnContainer">                                         	
	                                                <input type="button" class="btn btn-outline-secondary btn-lg" id="btn" value="保存">    
	                                            </div>
	                                         </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- 已有的收货地址条数 -->
                            <div class="quantity">
                            	<p><span class="iconfont icontishi"></span>已保存了<span class="addressCount"></span>条地址</p>
                            </div>
                            <div class="allAddress">
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
								                                <select class="form-control" id="upProvince" data-province="---- 选择省 ----"></select>
								                            </div>
								                            <div class="form-group">
								                                <label class="sr-only" for="city">City</label>
								                                <select class="form-control" id="upCity" data-city="---- 选择市 ----"></select>
								                            </div>
								                            <div class="form-group">
								                                <label class="sr-only" for="district">District</label>
								                                <select class="form-control" id="upDistrict" data-district="---- 选择区 ----"></select>
								                            </div>
								                        </div>
								                		<div class="detail">
								                         <div class="form-group row">
								                             <label for="details-address" class="col-sm-4 form-label">请输入详细地址：</label>
								                             <div class="col-sm-6">
								                                 <input type="text" id="upDetails-address" class="form-control" >
								                             </div>
								                         </div>
								                         <div class="form-group row">
								                             <label for="recName" class="col-sm-4 form-label">收货人：</label>
								                             <div class="col-sm-6">
								                                 <input type="text" id="upRecName" name="userName" class="form-control">
								                             </div>
								                         </div>
								                         <div class="form-group row">
								                             <label for="recTel" class="col-sm-4 form-label">联系电话：</label>
								                             <div class="col-sm-6">
								                                 <input type="text" class="form-control" name="phoneNumber" id="upRecTel">
								                             </div>
								                             <input type="text" name="upDetailedAddress" id="upDetailedAddress" class="upDetailedAddress hidden">
								                         </div>
							                        </form>
							                    </div>
							                </div>
							                <div class="modal-footer">
							                  <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
							                  <button type="button" class="btn btn-primary modifyAddress">保存修改</button>
							                </div>
							              </div>
							            </div>
							          </div>
								</form>
							</div>
                        </div>
                        
                    </div>
                    <div class="tab-pane fade bought" id="bought" role="tabpanel" aria-labelledby="purchased">
                         <table class="table table-border table-striped table-hover header">
                             <tr>
                                 <td width="20%">宝贝</td>
                                 <td width="13%">宝贝详情</td>
                                 <td width="10%">数量</td>
                                 <td width="10%">实付款</td>
                                 <td width="13%">交易状态</td>
                                 <td>操作</td>
                             </tr>
                          </table>
                            <ul class="boughtGoods"></ul>
                      </div>
                </div>
            </div>
        </div></div>
        <%@include file="./footer.jsp" %>
        
        <script>
            let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; 
            let recAddress = "";
            let recName ="";
            let phone ;
            $("#details-address").on("blur", function(){
            	$(".detailedAddress").val($("#province option:selected").html()+$("#city option:selected").html()+$("#district option:selected").html()+$(this).val())
            })
            $("#btn").on("click", function(){
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
                }
                else{
                	let username = $("#recName").val();
                	let phone = $("#recTel").val();
                	let address = $(".detailedAddress").val();
                    $.ajax({
                    	url: "${pageContext.request.contextPath}/addAddress",
                    	type: "post",
                    	contentType: "application/json;charset=UTF-8",
                    	dataType: "json",
                    	data: JSON.stringify({
                    		userName: username,
                    		phoneNumber: phone,
                    		detailedAddress: address
                    	}),
                    	success: function(result){
                    		let addressList = document.querySelector(".addressList");
                    		if(result.flag){
                    			alert("新增成功");
                    			location.reload();
                    		}
                    	}
                    })
                }
            });
            $(".modify").on("click", function(){
                window.location.href = '${pageContext.request.contextPath}/modifyUserInfo';
            });
            //身份证号和手机号中间部分为*
            function change(str) {
                let arr = str.split("");
                let brr = [];
                if (arr.length > 11) {
                    for (let i = 0; i < arr.length; i++) {
                        if (i > 5 && i < arr.length - 4) {
                            brr[i] = arr[i].replace(arr[i], "*");
                        } else {
                            brr[i] = arr[i];
                        }
                    }
                } else {
                    for (let i = 0; i < arr.length; i++) {
                        if (i >= 3 && i < arr.length - 4) {
                            brr[i] = arr[i].replace(arr[i], "*");
                        } else {
                            brr[i] = arr[i];
                        }
                    }
                }
                return brr.join("")
            }
            //密码都显示为*
            function pass(str){
                let arr = str.split("");
                let brr = [];
                arr.map((item, index) => {
                    brr[index] = item.replace(item, "*");
                });
                return brr.join("")
            }
            $(".username").html( "<%=username%>");
            $(".phoneNumber").html(change("<%=phoneNumber%>"));
            $(".password").html(pass("<%=password%>"));
            $(".idCard").html(change("<%=idCard%>"));
            $(".sex").html("<%=sex%>");
            
            window.onload = function(){
    			let str = "";
    			let addressList = document.querySelector(".addressList")
    			$.ajax({
    				url: "${pageContext.request.contextPath}/findAddress",
    				type: "post",
    				success: function(result){
    					let object = result.data;
    					if(result.flag){
    						$(".addressCount").html(object.length);
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
												let count = parseInt($(".addressCount").html());
												$(".addressCount").html(count - 1);
    											that.parent().parent().remove();
    										}
    									}
    								})
    							}
    						})
    			            let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; 
    			            $(".update").on("click", function(){
    			            	let that = $(this);
    			            	$("#upDetails-address").on("blur", function(){
    					            $("#upDetailedAddress").val($("#upProvince option:selected").html()+$("#city option:selected").html()+$("#district option:selected").html()+$(this).val())    
    							})
    			            	$(".modifyAddress").on("click", function(){
    			            		let that2 = $(this);
    								if(confirm("确定修改吗")){
    									
    									if($("#upProvince option:selected").html() === "---- 选择省 ----"){
    					                    alert("请选择省份");
    					                }
    					                if($("#upCity option:selected").html() === "---- 选择市 ----"){
    					                    alert("请选择市");
    					                }
    					                if($("#upDistrict option:selected").html() === "---- 选择区 ----"){
    					                    alert("请选择区");
    					                }
    					                if($("#upDetails-address").val() === ""){
    					                    alert("请输入详细地址");
    					                }
    					                if($("#upRecName").val() === ""){
    					                    alert("请输入收货人的名字");
    					                }
    					                if($("#upRecTel") === ""){
    					                    alert("联系电话不能为空");
    					                }
    					                if(!regex.test($("#upRecTel").val())){
    					                    alert("联系电话不合法，请重新输入");
    					                    $("#upRecTel").val("");
    					                }else{
    					                	let id = that.parent().prev().prev().val();
    					                	let username = that2.parent().parent().find("#upRecName").val();
    					                	let phone = that2.parent().parent().find("#upRecTel").val();
    					                	let address = that2.parent().parent().find("#upDetailedAddress").val();
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
    			$.ajax({
    				url: "${pageContext.request.contextPath}/findBuyerAllCommodityOrder",
    				type: "post",
    				success: function(result){
    					let object = result.data;
    					let status = [];
    					
    					for(let i = 0; i < object.length; i++){
    						status.push(object[i].orderStatus)
    					}
		    			$.ajax({
		    				url: "${pageContext.request.contextPath}/getListOrderController",
		    				type: "post",
		    				success: function(result){
		    					let obj = result.data;
		    	    			let boughtGoods = document.querySelector(".boughtGoods");
		    					let str = "";
		    					for(let i = 0; i < obj.length; i++){
		    						str += `<li class="boughtGoods-item">
		    						<table class="table table-border table-hover"><tr>
                                        <td width="20%"><div><div class="proName">\${obj[i].commodityName}</div><img src="/images/\${obj[i].commodityPicture}" class="image" width="40" height="40"></div></td>
                                        <td width="13%"><div class="proSpecs">\${obj[i].specifications}</div></td>
                                        <td width="10%">\${obj[i].orderCount}</td>
                                        <td width="10%"><div class="proPrice">\${obj[i].orderPrice}</div>￥</td>
                                        <td width="13%"><span class="orderStatus">\${object[i].orderStatus}</span></td>
                                        <td class="operate">
                                        	<input type="text" value="\${object[i].cancelTime}" class="cancelTime"/>
                                        	<input type="text" value="\${obj[i].commodityId}" class="commodityId"/>
                                        	<input type="text" value="\${obj[i].id}" class="orderId"/>
                                        	<input type="button" class="btn btn-primary refund" value="申请退款"/>
                                        	<input type="button" class="btn btn-primary goPay" value="去支付"/>
                                        	<input type="button" class="btn btn-primary cancel" value="取消订单"/>
                                        	<input type="button" class="btn btn-primary confirm" value="确认收货"/>
                                        	<input type="button" class="btn btn-primary comment" value="评价"/>
                                        </td>
                                    </tr></table>
                                  </li>`;
		    					}
		    					boughtGoods.innerHTML = str;
		    					let operate = document.getElementsByClassName("operate");
		    					for(let i = 0; i < operate.length; i++){
		    						hidden(operate[i])
		    					}
		    					function hidden(el){
		    						let status = $(el).prev().find(".orderStatus").html();
		    						if( status === "待发货" || status === "待收货"  || status === "拒绝退款"){
		    							$(el).find(".refund").css("display", "inline-block");
		    						}else if(status === "待付款"){
		    							$(el).find(".cancel").css("display", "inline-block");
		    							$(el).find(".goPay").css('display', "inline-block")
		    						}else if(status === "已收货"){
		    							$(el).find(".comment").css("display", "inline-block");
		    						}
		    						if(status === "待收货"){
		    							$(el).find(".confirm").css("display", "inline-block")
		    						}
		    					}
		    					$(".cancel").click(function(){
		    						let id = $(this).parent().find(".orderId").val();
		    						let that = $(this);
		    						$.ajax({
		    							url: "${pageContext.request.contextPath}/cancelCommodityOrder/"+id,
		    							type: "get",
		    							success: function(result){
		    								if(result.flag){
		    									alert("操作成功");
		    									that.parents(".boughtGoods-item").remove();
		    								}
		    							}
		    						})
		    					})
		    					$(".refund").click(function(){
		    						let id = $(this).parent().find(".orderId").val();
		    						$.ajax({
		    							url: "${pageContext.request.contextPath}/applyRefund/"+id,
		    							type: "get",
		    							success: function(result){
		    		    					let operate = document.getElementsByClassName("operate");
		    								if(result.flag){
		    									alert("操作成功");
		    									localtion.reload();
		    								}
		    							}
		    						})
		    					})
		    					$(".confirm").click(function(){
		    						let id = $(this).parent().find(".orderId").val();
		    						$.ajax({
		    							url: "${pageContext.request.contextPath}/receipt/"+id,
		    							type: "get",
		    							success: function(result){
		    								if(result.flag){
		    									location.reload();
		    									alert("操作成功")
		    								}
		    							}
		    						})
		    					})
		    					$(".goPay").click(function(){
		    						let orderId = $(this).parent().find(".orderId").val();
		    						$.ajax({
		    							url: "${pageContext.request.contextPath}/pay/" + orderId,
		    							type: "post",
		    							dataType: "json",
		    							success: function (data) {
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
		    					$(".comment").click(function(){
		    						let id = $(this).parent().find(".commodityId").val();
		    						let orderId = $(this).parent().find('.orderId').val();
		    						let time = $(this).parent().find(".cancelTime").val();
		    						let name = $(this).parents(".table").find(".proName").html();
		    						let specs = $(this).parents(".table").find(".proSpecs").html();
		    						let price =$(this).parents(".table").find(".proPrice").html();
		    						let image = $(this).parents(".table").find(".image").attr("src");
		    						localStorage.setItem("image", JSON.stringify(image));
		    						localStorage.setItem("name", JSON.stringify(name));
		    						localStorage.setItem("specs", JSON.stringify(specs));
		    						localStorage.setItem("time", JSON.stringify(time));
		    						localStorage.setItem("price", JSON.stringify(price));
		    						localStorage.setItem("id", JSON.stringify(id));
		    						localStorage.setItem("orderId", JSON.stringify(orderId));
		    						location.href = "${pageContext.request.contextPath}/comments";
		    					})
		    				}
		    			})
    				}
    			})
    		}
        </script>
    </body>
</html>