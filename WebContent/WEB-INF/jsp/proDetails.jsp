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
            }

            .shopName {
                max-width: 1190px;
                padding: 10px;
                margin: 10px auto 0;
                text-align: center;
            }

            .detail {
                max-width: 1190px;
                min-width: 1125px;
                margin: 10px auto 0;
                display: flex;
            }

            .proPic {
                width: 420px;
                height: 530px;
                border-right: 1px solid #ccc;
                overflow: hidden;
            }

            .purchase {
                border-right: 1px solid #ccc;
                width: 510px;
                height: 530px;
                margin: 0 20px;
                position: relative;
                padding-right: 20px;
            }

            .shopSuggest {
                width: 140px;
                height: 140px;
                margin: 0 10px;
            }

            .shopSuggest .shopLi {
                margin-bottom: 10px;
            }

            ul,
            li {
                list-style: none;
                margin: 0;
                padding: 0;
            }

            .bigPic {
                width: 2100px;
                display: flex;
            }

            .bigPic li {
                width: 420px;
                height: 420px;
                background-color: red;
            }

            .smallPic {
                width: 420px;
                display: flex;
            }

            .smallPic li {
                width: 56px;
                height: 56px;
                background-color: black;
                margin: 25px 5px 0 20px;
                border-width: 1px;
            }

            .smallPic li:hover {
                border: 1px solid #000;
            }

            .specs {
                position: absolute;
                width: 510px;
                height: 242px;

            }

            .proName {
                width: 480px;
                font-size: 17px;
                font-weight: bold;
                margin: 20px 0 0;
            }

            .price {
                color: #ff2300;
                background-color: #ecebeb;
                height: 60px;
                line-height: 60px;
                margin-top: 30px;
                width: 480px;
            } 

            .proPrice {
                font-size: 26px;
                font-weight: bold;
            }

            .plate {
                width: 480px;
                height: 40px;
                border-top: 1px dotted #ccc;
                border-bottom: 1px dotted #ccc;
                margin: 10px 0 0;
            }

            .plate-item {
                width: 240px;
                float: left;
                text-align: center;
                margin: 10px 0;
            }

            .plate-item-count {
                color: #ff2300;
                font-weight: bold;
            }

            .border-right {
                border-right: 1px dotted #ccc;
            }

            .proSpecs {
                width: 500px;
                margin: 10px;
                height: 150px;
                overflow-y: auto;
            }

            .proList {
                width: 460px;
            }

            .proList li {
                font-size: 16px;
                float: left;
                margin: 10px;
                font-size: 14px;
                height: 25px;
                line-height: 25px;
                border: none;
            }

            .proList label {
                padding: 5px;
                position: relative;
                border: 1px solid #000;
                user-select: none;
            }

            .proList li input {
                border: none;
                display: none;
            }

            .Price {
                width: 500px;
                margin: 10px 10px 10px 10px;
            }

            .number {
                width: 100px;
                background-color: red;
                position: relative;
                border: 1px solid #ccc;
                height: 28px;
                margin-left: 100px;
                margin-top: -22px;
            }

            .sub,
            .add {
                width: 26px;
                height: 26px;
                border: none;
                position: absolute;
                background-color: #dedede;
                font-weight: bold;
            }

            .buy,
            .addCart {
                border: none;
                font-weight: 16px;
                padding: 10px 35px;
                margin-left: 40px;
            }

            .buy {
                border: 1px solid #f9c8b5;
                background-color: #ffe3cf;
                color: #fc4300;
            }

            .addCart {
                border: 1px solid #ff2a00;
                background-color: #ff2a00;
                color: #fff;
            }

            .sub:focus,
            .add:focus,
            .buy:focus,
            .addCart:focus {
                outline: none;
            }

            .add {
                right: 0;
            }

            .priceNumber {
                width: 48px;
                height: 26px;
                border: none;
                outline: none;
                position: absolute;
                left: 26px;
                text-indent: 20px;
            }

            .button {
                margin-top: 20px;
            }

            .box {
                max-width: 1190px;
                position: relative;
                margin: 100px auto 0;
            }

            .pro-details {
                width: 1100px;
                margin: 10px auto 0;
            }

            .pro-details li {
                width: 1100px;
                margin: 10px 0;
            }

            .pro-comment {
                width: 1100px;
                margin: 10px auto 0;
            }

            .pro-comment li {
                width: 1100px;
                padding: 20px 0 0 20px;
                border-bottom: 1px solid #ccc;
            }

            .pro-comment-detail {
                position: relative;
            }

            .user {
                text-indent: 30px;
            }

            .show-detail {
                position: absolute;
                top: 0;
                left: 120px;
            }

            .time {
                color: #ccc;
                font-size: 12px;
            }

            .choose::after {
                content: "√";
                color: #fff;
                font-size: 5px;
                text-indent: 2px;
                line-height: 10px;
                display: block;
                width: 10px;
                height: 10px;
                position: absolute;
                background-color: #ff2300;
                right: 0;
                bottom: 0px;
            }

            .unchoose::after {
                content: "";
            }
            .comment-user span,.goods-info span,.comment-details span{
                font-size: 12px;
                color: #121a2a;
                line-height: 80px;
            }
            .lianxi{
            	text-decoration: none;
            }
            .hidden{
            	display:none;
            }
            
            .content{
            	width: 468px;
            }
            .sendMessage{
            	width: 468px;
            	height: 124px;
            	border: 1px solid #ccc;
            	background-color: blue;
            	position: relative;
            }
            .info:focus{
            	outline: none;
            }
            .submit{
            	position: absolute;
            	right: 0;
            	bottom: 1px;
            	width: 100px;
                height: 40px;
                background-color: aqua;
            }
            #send{
            	width: 100px;
                height: 40px;
                font-size: 18px;
                font-weight: bold;
                letter-spacing: 5px;
            }
            .chatmessage{
                height: 100px;
                overflow: auto;
            }
            .chatmessageDetail{
            	height: 230px;
            	overflow: auto;
            }
            .chatmessageDetail li{
                margin: 10px;
            }
            .right{
                margin-left: 260px;
            }
            .messageDetail{
                width: 180px;
                padding: 5px;
            }
            .messageD{
                background-color:#73b9a2;
                padding: 5px;
                border-radius: 6px;
            }
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/font/iconfont.css">
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    </head>
    <body>
    <%@include file="./header.jsp" %>
        <div class="main">
            <div class="shopName">
                <h2 class="shopname"></h2>
            </div>
            <div class="detail">
                <div class="proPic">
                    <ul class="bigPic"></ul>
                    <ul class="smallPic"></ul>
                </div>
                <div class="purchase">
                    <div class="proName"></div>
                    <div class="price">
                        <p>商品价格：￥<span class="proPrice"></span></p>
                    </div>
                    <div class="plate">
                        <ul>
                            <li class="plate-item border-right">
                                <div class="plate-item-des">
                                    <span>月销量</span>
                                    <span class="plate-item-count"></span>
                                </div>
                            </li>
                            <li class="plate-item">
                                <div class="plate-item-des">
                                    <span>累计评价</span>
                                    <span class="plate-item-count comment"></span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="specs">
                        <div class="proSpecs">
                            <span>商品规格:</span>
                            <ul class="proList"></ul>
                        </div>
                        <div class="Price">
                            <span>商品数量:</span>
                            <div class="number">
                                <button class="sub">-</button>
                                <input type="text" value="1" class="priceNumber">
                                <button class="add">+</button>
                            </div>
                            <span>(库存<span class="stock"></span>)</span>
                            <div class="button">
                                <button type="button" class="buy">立即购买</button>
                                <button type="button" class="addCart"><i class="iconfont iconicon-"></i>加入购物车</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="shopSuggest">
                    <ul class="shopList"></ul>
                    <div>
                    	<a href="javascript:(0);"data-toggle="modal" data-target="#staticBackdrop">联系卖家</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="box">
            <nav>	
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                        aria-controls="nav-home" aria-selected="true">商品详情</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                        aria-controls="nav-profile" aria-selected="false">累计评论<span class="plate-item-count comment"></span></a>
                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <ul class="pro-details"></ul>
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <ul class="commentList"></ul>
                </div>
            </div>
        </div>
        <!-- 对话模态框 -->
        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1"
            role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="content">
                            <ul class="chatmessageDetail"></ul>
                        </div>	
                        <div class="sendMessage">
                            <textarea cols="64" rows="5" class="info"></textarea>
                            <div class="submit">
                            	<input type="text" class="receiveId hidden">
                                <input type="button" value="发送" id="send">
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="./footer.jsp" %>
        <script>
            window.onload = function(){
                if($(".sub").next().val() <= 1){
                    $(".sub").attr("disabled", true)
                }
                let id = JSON.parse(localStorage.getItem("id"));
                let bigPic = document.querySelector(".bigPic");
                let smallPic = document.querySelector(".smallPic");
                let proDetails = document.querySelector(".pro-details");
                let proList = document.querySelector(".proList");
                
                $.ajax({
                	url: "${pageContext.request.contextPath}/singleCommodity/"+id,
                	type: "get",
                	success: function(result){
                		let object = result.data;
                		let sellerId = object.sellerId;
                		$(".modal-title").html(object.storeName);
                		$(".receiveId").val(sellerId);
                		let imageList = [];
                		$(".shopname").html(object.storeName)
                		setInterval(()=>{
               				$.ajax({
               					url: "${pageContext.request.contextPath}/updatechatcessage",
               					data: JSON.stringify({receiveId: sellerId}),
               					dataType:"json",
               					type: "post",
               					contentType: "application/json;charset=UTF-8",
               					success:function(result){}
               				})
               				$.ajax({
   								url: "${pageContext.request.contextPath}/getlistchatmessage",
   								data: JSON.stringify({
   									receiveId: sellerId,
   								}),
   								contentType : "application/json;charset=UTF-8",
   								dataType : "json",
   								type: "post",
   								success: function(result){
	                				let str1 = "";
	                				let chatMessageDetail = document.querySelector(".chatmessageDetail");
   									let object = result.data;
   									for(let i = 0; i < object.length; i++){							
   										if(object[i].sendOutId == parseInt("<%=id%>")){
   											str1 += `<li>
   			                                    <div class="messageDetail right">
   		                                        <div class="messageD">\${object[i].message}</div>
   		                                    </div>
   		                                </li>`;
   										}else{
   											str1 += `<li>
   			                                    <div class="messageDetail">
   		                                        <div class="messageD">\${object[i].message}</div>
   		                                    </div>
   		                                </li>`
   										}	
   									}
   									chatMessageDetail.innerHTML = str1;
   								}
   							})
           				}, 100)
                		
                		//发送信息
				       	$("#send").on('click', function(){
				       		let id = $(this).prev().val();
				       		let content = $(this).parents(".sendMessage").find(".info").val();
				       		$.ajax({
				       			url: "${pageContext.request.contextPath}/addChatMessage",
				       			dataType : "json",
								type: "post",
								data: JSON.stringify({
									receiveId: id,
									message: content,
								}),
								contentType : "application/json;charset=UTF-8",
				       			success: function(result){
				       				if(result.flag){
				       					$(".info").val("")
				       				}
				       			}
				       		})
				       	})
                		
                		let specsList = object.commodityDetailsList;
                		for(let i = 1; i <= 15; i++){
							if(object[`commodityPicture\${i}`] !== null){
								imageList.push(object[`commodityPicture\${i}`])
							}
						}
                		let str1 = "";
                		let str2 = "";
                		let str3 = "";
                		let str4 = "";
                		if(imageList.length >=5 ){
                			for(let i = 0; i < 5; i++){
                    			str1 += `<li><img src="/images/\${imageList[i]}" width="420" height="420"></li>`;
                    			str2 += `<li><img src="/images/\${imageList[i]}" width="56" height="56"></li>`;
                    		}
                		}else if(imageList.length < 5){
                			for(let i = 0; i < imageList.length; i++){
                    			str1 += `<li><img src="/images/\${imageList[i]}" width="420" height="420"></li>`;
                    			str2 += `<li><img src="/images/\${imageList[i]}" width="56" height="56"></li>`;
                    		}
                		}
                		
                		bigPic.innerHTML = str1;
                		smallPic.innerHTML = str2;
                		function move(count) {
                            bigPic.style.transform = "translate("+count * -420+"px,0)";
                        }
                        let liList = document.querySelectorAll(".smallPic li");
                        let count = 0;
                        for (let i = 0; i < liList.length; i++) {
                            liList[i].onmouseenter = function () {
                            	count = i;
                                move(count);
                            }
                        }
                        for(let i = 0; i < imageList.length; i++){
                        	str3 += `<li><img src="/images/\${imageList[i]}" width="1100" alt=""></li>`;
                        }
                        proDetails.innerHTML = str3;
                        for(let i = 0; i < specsList.length;i++){
                        	str4 += `<li>
                        		<input type="text" value="\${specsList[i].id}"/>
                                <input type="radio" id="specs\${i}" name="norms">
                                <label for="specs\${i}">\${specsList[i].specifications}</label>
                            </li>`;
                        }
                        proList.innerHTML = str4;
                		$(".proName").html(object.commodityName)
                		$(".plate-item-count").html(object.commodityCount);
                		$(".comment").html("1")
                        let radio = $("input[type='radio']");
                        $("input[type='radio']").first().next().css('borderColor', 'red');		//设置默认选项的文字为选中状态
                        $("input[type='radio']").first().next().attr("class", 'choose');
                        $("input[type='radio']").first().attr("checked", true);
                        $("input[type='radio']").first().parent().parent().parent().parent().parent().find(".proPrice").html(specsList[0].price);
                        $("input[type='radio']").first().parent().parent().parent().parent().parent().find(".stock").html(specsList[0].stock);
                        radio.on("click", function(){
                			for(let i = 0; i < radio.length; i++){
                				if(radio[i].checked){
                					$(this).parent().parent().parent().parent().parent().find(".proPrice").html(specsList[i].price);
                					$(this).parent().parent().parent().parent().parent().find(".stock").html(specsList[i].stock);
                					$(this).next().css('borderColor', 'red');
                                    $(this).next().attr("class", "choose");
                				}else {
                                    $(this).next().css('borderColor', 'black');
                                    $(this).next().attr("class", "unchoose");
                                }
                			}
                		})
                        $("input[type='radio']").click(function () {
                            $("input[type='radio']").each(function () {
                                if (this.checked) {
                                    $(this).next().css('borderColor', 'red');
                                    $(this).next().attr("class", "choose");
                                } else {
                                    $(this).next().css('borderColor', 'black');
                                    $(this).next().attr("class", "unchoose");
                                }
                            });
                        });
                      //增减数量
                        let add = $(".add");
                        let sub = $(".sub");
                        let number = $(".priceNumber");
                        let value = 1;
                        add.click(function () {
                            value++;
                            if (value > 1) {
                                sub.attr("disabled", false)
                            }
                            number.val(value);
                        });
                        sub.click(function () {
                            value--;
                            if (value <= 1) {
                                sub.attr("disabled", true)
                            }
                            
                            number.val(value);
                        });
                        number.on('keyup', function () {
                            if (isNaN(number.val())) {
                                number.val(1)
                            }
                            if (number.val() === "" || number.val()<1) {
                                alert("最少购买一件商品")
                                number.val(1);
                            }
                        })
                        let stock = parseInt($(".stock").html())
                        $(".buy").click(function(){
                            if(number.val() > stock){
                                number.val(1);
                                alert("商品输入数量超出限制")
                            }else{
                                let id = $("input[type='radio']:checked").prev().val();
                                let count = $(".priceNumber").val();
                                $.ajax({
                                	url: "${pageContext.request.contextPath}/getShoppingCartCommodity",
                                	data: JSON.stringify({
                                		id: id,
                                		count: count
                                	}),
                                	dataType: "json",
                                	contentType: "application/json;charset=UTF-8",
                                	type: "post",
                                	success: function(result){
                                		if(result.flag){
                                			let arr = localStorage.setItem("single",JSON.stringify(result.data));
                                			location.href = "${pageContext.request.contextPath}/singlePurchase";
                                		}
                                	}
                                })
                            }
                        });
                        $(".addCart").click(function(){
							let commdityId = object.id;
							let commdityCount = number.val();
							let commdityDetailId = $("input[type='radio']:checked").prev().val();
							$.ajax({
								url: "${pageContext.request.contextPath}/addShoppingCart",
								type: "post",
								data: JSON.stringify({
									commodityId: commdityId,
									commoditydetailsId: commdityDetailId,
									commodityCount: commdityCount
								}),
								contentType: "application/json;charset=UTF-8",
								dataType: "json",
								success: function(result){
									alert(result.message);
									if(result.message == "商品已加入购物车"){	
										let image = JSON.stringify(imageList[0]);
										let specs = JSON.stringify($("input[type='radio']:checked").next().html());
										let name =  JSON.stringify(object.commodityName);
										localStorage.setItem("specs", specs);
										localStorage.setItem("image", image);
										localStorage.setItem("name", name);
										localStorage.setItem("commodityId", object.id);
										window.open("${pageContext.request.contextPath}/addCartSuccess");
									}
									if(result.message == "您的购物车中已经存在该商品了哦"){
										location.reload();
									}
								}
							})
                        });
                        let id = object.sellerId;
		                $.ajax({
		                	url: "${pageContext.request.contextPath}/getListDetailsJspCommodity/"+id,
		                	type: "get",
		                	success: function(result){
		                		let object = result.data; 
		                		let str = "";
		                		let shopList = document.querySelector(".shopList");
		                		for(let i = 0; i < object.length; i++){
		                			if(`\${object[i].commodityPicture1}` === 'null'){
		                				
		                			}else{		                				
		                				str += ` <li class="shopLi">
		                					<input type="text" value="\${object[i].id}" class="hidden"/>
		                               	 	<img src="/images/\${object[i].commodityPicture1}" width="140" height="140" alt="">
		                                	</li>`;
		                			}
		                		}
		                		shopList.innerHTML = str;
		                		let shopLi = document.getElementsByClassName("shopLi");
		                		for(let i = 0; i < shopLi.length; i++){
		                			shopLi[i].onclick= function(){
		                				localStorage.setItem("id",JSON.stringify($(this).find(".hidden").val()))
		                				location.href = "${pageContext.request.contextPath}/proDetails";
		                				
		                			}
		                		}
		                	}
		                })
                	}
                })
                $.ajax({
            		url: "${pageContext.request.contextPath}/findCommodityAllComment/"+id,
            		type: "get",
            		success: function(result){
            			let commentList = document.querySelector(".commentList");
            			if(result.flag){
            				let object = result.data;
            				let commentList = document.querySelector(".commentList");
            				let length = object.length;
            				$(".comment").each(function(){
            					$(this).html(length)
            				})
            				let str = "";
            				let imageList = [];
            				for(let i = 0; i < object.length; i++){
            					str += `<li>
            						<table class="table table-border">
	            						<tr>
	            							<td width="20%">
	            								<div>\${object[i].commentGrade}</div>
	            							</td>
	            							<td width="50%">
	            								<div>\${object[i].message}</div>
	            							</td>
	            							<td width="30%">
	            								<ul class="imageList">
	            									<li><img class="img" src="/images/\${object[i].commentPicture1}" widht="80" height="80"/></li>
	            									<li><img class="img" src="/images/\${object[i].commentPicture2}" widht="80" height="80"/></li>
	            									<li><img class="img" src="/images/\${object[i].commentPicture3}" widht="80" height="80"/></li>
	            									<li><img class="img" src="/images/\${object[i].commentPicture4}" widht="80" height="80"/></li>
	            									<li><img class="img" src="/images/\${object[i].commentPicture5}" widht="80" height="80"/></li>
	            								</ul>
	            							</td>
	            						</tr>
            						</table>
	            					</li>`;
            				}
            				commentList.innerHTML = str;
            				$(".img").each(function(){
            					if($(this).attr("src").split("/")[2] != "null"){
            						$(this).css("display", "inline-block");
            					}else{
            						$(this).css("display", "none")
            					}
            				})
            			}else{
            				$(".comment").each(function(){
            					$(this).html(0)
            				})
            			}
            		}
            	})
               	
            }

            
        </script>
    </body>
</html>