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
            * {
                margin: 0;
                padding: 0;
                list-style: none;
            }

            .confirmPro {
                width: 990px;
                height: 500px;
                margin: 50px auto 0;
                position: relative;
            }

            .confirm-order {
                font-size: 14px;
                font-weight: bold;
            }

            .attr-nav {
                display: flex;
                margin: 10px 0;
            }

            .attr-nav div {
                width: 200px;
                margin: 5px 1px;
                padding: 5px 40px;
                border-bottom: 2px solid #a7d2ff;
                text-align: center;
                font-size: 11px;
                color: #6c6c6c;
            }

            .pro-item {
                width: 960px;
                margin: 10px 10px;
                display: flex;
            }

            .pro-info {
                width: 185px;
                overflow: hidden;
                position: relative;
                margin: 0 2px;
            }

            .pro-info span {
                position: absolute;
                left: 60px;
                width: 130px;
                display: inline-block;
                font-size: 11px;
            }

            .pro-attribute {
                width: 200px;
                text-align: center;
                margin: 0 2px;
            }

            .pro-price {
                width: 200px;
                text-align: center;
                margin: 0 2px;
            }

            .pro-number {
                width: 195px;
                margin: 0 2px;
            }

            .number {
                width: 100px;
                height: 30px;
                position: relative;
            }

            .sub,
            .add {
                width: 30px;
                height: 30px;
                font-size: 18px;
                border: none;
                position: absolute;
            }

            .sub:focus,
            .add:focus {
                outline: none;
            }

            .add {
                right: 0;
            }

            .value {
                width: 40px;
                height: 30px;
                border: none;
                position: absolute;
                left: 30px;
                text-align: center;
                font-size: 16px;
                outline: none;
            }

            .subtotal {
                width: 190px;
                text-align: center;
            }

            .reciveAddress {
                width: 990px;
                margin: 30px 10px;
            }
			
            .container {
                position: relative;
                left: 130px;
                top: -30px;
            }

            .form-inline div {
                float: left;
                margin-right: 5px;
            }

            .details-address {
                margin-left: 145px;
                margin-top: -25px;
            }

            .address {
                border: 1px solid #ccd4da;
                height: 35px;
                width: 300px;
                border-radius: 5px;
                outline: none;
                text-indent: 15px;
                color: #6c6c6c;
            }
			.act{
				position: absolute;
				right: 0;
			}
            .actual-pay {
                width: 500px;
                border: 1px solid #ff2300;
                right: 30px;
                text-align: right;
            }

            .actual-pay div {
                margin-right: 20px;
                font-size: 11px;
            }

            .pay {
                margin: 11px 0;
                font-weight:bold;
            }

            .pay span {
                font-size: 19px;
                color: #999;
                margin: 0 3px;
            }

            .pay .money {
                color: #ff2300;
                font-weight: bold;
            }

            .receiver p {
                margin: 10px 0;
                font-weight:bold;
            }
			.receiver span{
				margin: 0 5px;
				font-weight: bold;
			}
            .submit {
            	position: absolute;
            	right: 0;
            }

            .submit-btn {
                border: none;
                font-size: 12px;
                padding: 14px 60px;
                background-color: #ff3d00;
                color: #fff;
            }

            .rec {
                margin-top: 20px;
            }

            .rec span {
                display: inline-block;
                width: 80px;
            }

            .rec input {
                margin-left: 60px;
                height: 35px;
                border: 1px solid #ccd4da;
                border-radius: 4px;
                outline: none;
                text-indent: 15px;
                color: #6c6c6c;
            }

            .submit-btn:focus {
                outline: none;
            }
            .selectAddress{
                margin-bottom: 10px;
            }
            .selectAddress span{
                font-size: 14px;
                font-weight: bold;
            }
            .selectAddress ul li{
                height: 30px;
                padding: 3px;
                margin: 0 0 3px 0;
                position: relative;
                overflow: hidden;
            }
            .addressList{
                width: 860px;
                height: 100px;
                margin: 15px auto 10px;
                overflow: auto;
            }
            .addressList input{
                /*border: none;
                display: none;*/
            }
            .addressList label{
                user-select: none;
                font-size: 12px;
                color: #666;
                line-height: 30px;
                position: absolute;
                top: 0;
                text-indent: 10px;
            }
			.addressList span{
				margin: 0 5px;
			}
            .choose::after {
                content: "√";
                color: #fff;
                font-size: 16px;
                text-indent: 2px;
                line-height: 10px;
                text-align: center;
                display: block;
                width: 30px;
                height: 30px;
                position: absolute;
                right: 0px;
                bottom: 0px;
                border-top: 15px solid transparent;
                border-bottom: 15px solid #ff2300;
                border-left: 15px solid transparent;
                border-right: 15px solid #ff2300;
            }

            .unchoose::after {
                content: "";
            }
            .newAddress {
                font-size: 12px;
                margin: 10px 20px 0 65px;
                background-color: transparent;
                border: 1px solid #c3c6cf;
                border-radius: 3px;
                padding: 6px 12px;
                color: #000;
                z-index: 2;
            }

            .newAddress:hover {
                
                background-color: #c3c6cf;
            }
            .newAddress:focus{
                outline: none;
            }
            .addAddress {
                width: 900px;
                height: 500px;
                background-color: white;
                z-index: 999;
                position: relative;
                border: 1px solid #ccc;
                margin: 0 auto;
                top: -1300px;
                opacity: 0;
                transition: all 0.4s;
            }

            .addAddress .container {
                width: 600px;
                margin-left: 40px;
                margin-top: 60px;
            }
            .addAddress h4{
                margin: 20px;
            }
            .addAddress .container .detail .form-group{
                margin: 20px;
            }
            .recName{
                text-indent: 15px;
            }
            .recTel{
                text-indent: 13px;
            }
            .hidden{
                display: none;
            }
            .iconcha{
                position: absolute;
                top: 20px;
                right: 20px;
            }
            .proName{
            	width: 160px;	
            }
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/font/iconfont.css">
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/distpicker.data.js"></script>
        <script src="${pageContext.request.contextPath }/js/distpicker.js"></script>
        <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    </head>
    <body>
    	<%@include file="./header.jsp" %>
        <div class="confirmPro">
        	<form  id="createCommodityOrderForm">
	            <div class="selectAddress">
	                <span>请选择收货地址：</span>
	                <ul class="addressList"></ul>
	                <button type="button" class="newAddress">使用新地址</button>
	            </div>
	            <div class="confirm-order">
	                <span>确认订单信息</span> 
	            </div>
	            <table class="table table-border thea">
	            	<tr>
	            		<td width="10%"></td>
	            		<td width="19%">宝贝</td>
	            		<td width="15%">商品属性</td>
	            		<td width="20%">单价</td>
	            		<td width="20%">数量</td>
	            		<td>小计</td>
	            	</tr>
	            </table>
	            <input type="text" name="addressId" class="addressId hidden"/>
	           	<ul class="proList"></ul>
	            <div class="act">
	            	<div class="actual-pay">
		                <div class="pay">
		                    <p>实付款:<span> ￥ <span class="money">0</span></span></p>
		                </div>
		                <div class="receiver">
		                    <p><span>寄送至：</span><span id="actualAddress"></span></p>
		                    <p>收货人:<span id="name"></span><span id="tel"></span></p>
		                </div>
		            </div>
		            <div class="submit">
		                <button class="submit-btn" type="button">提交订单</button>
		            </div>
	            </div>
            </form>
            <div class="addAddress">
                <h4>新增收货地址：</h4>
                <span class="iconfont iconcha"></span>
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
                                <label for="details-address" class="col-sm-5 form-label">请输入详细地址：</label>
                                <div class="col-sm-6">
                                    <input type="text" id="upDetails-address" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row recName">
                                <label for="recName" class="col-sm-5 form-label">收货人：</label>
                                <div class="col-sm-6">
                                    <input type="text" id="upRecName" name="userName" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row recTel">
                                <label for="recTel" class="col-sm-5 form-label">联系电话：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="phoneNumber" id="upRecTel">
                                </div>
	                            <input type="text" name="upDetailedAddress" id="upDetailedAddress" class="upDetailedAddress hidden">
                            </div>
                            <div class="form-group row">
                                <input type="button" class="btn btn-primary save" value="保存">
                            </div>
                        </div> 
                    </form>
                </div>
            </div>
        </div>
        <%@include file="./footer.jsp" %>
        <script>
            window.onload = function () {
            	//显示商品信息
            	let single = JSON.parse(localStorage.getItem("single"));
            	let proList = document.querySelector(".proList");
            	proList.innerHTML =`<li><table class="table table-border table-hover">
            			<tr><td width="5%"><input type="checkbox" class="goodsCheck">
            			<input type="text" value="\${single.shoppingCartCommodity.commoditydetailsId}" name="commodityDetailsId" class="hidden userId"></td>
        				<td width="23%">
		                    <img src="/images/\${single.shoppingCartCommodity.commodityPicture1}" width="50" height="50" alt="">
		                    <div class="proName">\${single.shoppingCartCommodity.commodityName}</div>
        				</td>
        				<td width="15%"><div>\${single.shoppingCartCommodity.specifications}</div></td>
        				<td width="18%"><span class="pro-price">\${single.shoppingCartCommodity.price}</span>￥</td>
        				<td><div class="number">
		                       <button type="button" class="sub">-</button>
		                       <input type="text" class="value" name="orderCount" id="number" value="\${single.count}">
		                       <button type="button" class="add">+</button>
		                   </div></td>
        				<td><span class="subtotal"></span></td></tr></table></li>`;
                let addressList = document.querySelector(".addressList");
                let str1 = "";
                //选择地址
                $.ajax({
    				url: "${pageContext.request.contextPath}/findAddress",
    				type: "post",
    				success: function(result){
    					let object = result.data;
    					for(let i = 0; i < object.length; i++){
    						str1 += `
    							<li>
	    	                        <input type="radio" name="address" id="\${object[i].id}"/>
	    	                        <label for="\${object[i].id}"><span class="detailAddress">\${object[i].detailedAddress}</span><span class="userName">\${object[i].userName}</span><span class="phoneNumber">\${object[i].phoneNumber}</span></label>
    	                    	</li>
    						`;
    					}
    					addressList.innerHTML = str1;
    					$("input[type='radio']").first().parent().css("border", "1px solid #ff2300").attr("class","choose");
    	                $("input[type='radio']").first().attr("checked" , true)
    	                $(".addressId").val($("input[type='radio']").first().attr("id"));
    					$("#actualAddress").html(object[0].detailedAddress);
    	                $("#name").html(object[0].userName);
    	                $("#tel").html(object[0].phoneNumber);
    	                let addressId;
    					$("input[type='radio']").click(function(){
    	                    $("input[type='radio']").each(function(){
    	                        if(this.checked){
    	                        	$(".addressId").val( $(this).attr("id"));
    	                            $(this).parent().css("border", "1px solid #ff2300");
    	                            $(this).parent().prop("class", "choose");
									$("#actualAddress").html($(this).next().find(".detailAddress").html())
    	                            $("#name").html($(this).next().find(".userName").html());
    	        	                $("#tel").html($(this).next().find(".phoneNumber").html());
    	                        } else{
    	                            $(this).parent().css("border", "none");
    	                            $(this).parent().prop("class", "unchoose")
    	                        }
    	                    });
    	                });
    				}
                })
                
	            //新增收货地址
	            let regex = /^1(3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/; 
	            $(".newAddress").on("click",function(){
	                $(".addAddress").css("top", '-440px').css("opacity", 1);
	            })
	            $(".iconcha").on("click", function(){
	                $(".addAddress").css("top", "-1300px").css("opacity", 0)
	            })
	            $('#upDetails-address').on("blur",function(){
	                    $(".upDetailedAddress").val($("#upProvince option:selected").html()+ $("#upCity option:selected").html() + $("#upDistrict option:selected").html());
	            })
	            $(".save").on("click", function(){
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
	                }
	                else{
	                	let username = $("#upRecName").val();
	                	let phone = $("#upRecTel").val();
	                	let address = $(".upDetailedAddress").val();
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
	                    		if(result.flag){
	                    			alert("新增成功");
	                    			location.reload();
	                    		}
	                    	}
	                    })
	                }
	            });
	            $(".submit-btn").css("backgroundColor", "#b0b0b0");
                $(".submit-btn").prop("disabled", true)
	            $(document).on("click", ".goodsCheck", function () {
                    let flag = true;
                    let result = [].every.call($(".goodsCheck"), item => {
                        if (item.checked) {
                            return true;
                        } else {
                            return false;
                        }
                    })
                    //判断是否有被选中的
                    if (isCheck($(".goodsCheck"))) {
                        $(".submit-btn").css("backgroundColor", "#ff2300");
                        $(".submit-btn").prop("disabled", false)
                    } else {
                        $(".submit-btn").css("backgroundColor", "#b0b0b0");
                        $(".submit-btn").prop("disabled", true)
                    }
                    total();
                })
	            $(".add").click(function () {
                    let value = $(this).prev().val();
                    value++;
                    if (value > 1) {
                        $(this).prev().prev().attr("disabled", false)
                    }
                    $(this).prev().val(value);
                    $(this).parents(".table").find(".subtotal").html(parseFloat((value * $(this).parents(".table").find(".pro-price").html()).toFixed(2))+"￥")
                    total();
                });
	            $(".sub").click(function () {
                    let value = $(this).next().val();
                    --value;
                    if (value <= 1) {
                        $(this).attr("disabled", true);
                    }
                    $(this).next().val(value);
                    $(this).parents(".table").find(".subtotal").html(parseFloat((value * $(this).parents(".table").find(".pro-price").html()).toFixed(2))+"￥")
                    total();
            	});
	            $(".subtotal").each(function(){
	            	$(this).html(parseFloat($(this).parents(".table").find(".pro-price").html() * $(this).parents(".table").find("#number").val())+"￥")
	            })
	            $("#number").on('keyup', function () {
                     if (isNaN($(this).val())) {
                         $(this).val(1)
                     }
                     if ($(this).val() === "" || $(this).val() < 1) {
                         alert("最少购买一件商品")
                         $(this).val(1);
                     }
                     let value = $(this).val();
                     $(this).parents(".table").find(".subtotal").html(parseFloat((value * $(this).parents(".table").find(".pro-price").html()).toFixed(2))+"￥")
                     total();
                 })
	            function total() {
                    let sum = 0;
                    $(".goodsCheck").each(function () {
                        if ($(this).is(":checked")) {
                            let s = parseFloat($(this).parents(".table").find(".subtotal").html());
                            sum = s;
                        }
                    });
                    $(".money").html(parseFloat(sum.toFixed(2)));
                }
	            function isCheck(el) {
                    return [].some.call(el, item => {
                        if (item.checked) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
	            $(".submit-btn").on("click", function(){
	            	console.info($(".userId").val())
	            	$.ajax({
	        			url: "${pageContext.request.contextPath}/createCommodityOrder",
	        			type: "post",
	        			data: $("#createCommodityOrderForm").serialize(),
	        			dataType: "json",
	        			success: function(data) {
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
	        		});
	            })
            }
            
        </script>
    </body>
</html>