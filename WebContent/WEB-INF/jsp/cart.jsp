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

            .content {
                width: 990px;
                margin: 50px auto 0;
            }

            .operateList {
                width: 990px;
                display: flex;
            }

            .check li {
                width: 200px;
                list-style: none;
            }
			.pro{
				margin-bottom: 40px;
			}
            .pro li {
                float: left;
                list-style: none;
            }

            .pro-list-item {
                width: 990px;
            }

            .pro-shopName {
                margin: 10px 0 0 20px;
            }

            .pro-show {
                border-top: 1px solid #ccc;
                border-bottom: 1px solid #ccc;
                margin: 10px 0;
            }

            .pro-info {
                width: 280px;
                display: flex;
                padding: 10px 0 10px;
            }

            .pro-info div {
                margin: 0 3px;
            }

            .pro-price {
                width: 130px;
                margin: 20px 10px 0;
                text-align: center;
            }

            .pro-number {
                width: 160px;
                height: 60px;
                margin: 20px 10px 0 0;
            }

            .pro-detailNumber {
                width: 100px;
                position: relative;
                border: 1px solid #ccc;
                left: 40px;
                overflow: hidden;
            }

            .add,
            .sub {
                width: 25px;
                height: 25px;
                border: none;
            }

            .add:focus,
            .sub:focus {
                outline: none;
            }

            .add {
                margin-left: -5px;
            }

            #num {
                width: 48px;
                height: 25px;
                border: none;
                margin-left: -4px;
                outline: none;
                text-indent: 10px;
            }

            .pro-money {
                width: 150px;
                text-align: center;
                margin: 20px 0 0;
            }

            .pro-operate {
                width: 150px;
                text-align: center;
                margin: 20px 10px 0;
            }

            a {
                text-decoration: none;
                color: #000;
            }

            a:hover {
                color: #ff2300;
            }

            .calculate {
                width: 990px;
                height: 50px;
                background-color: #e5e5e5;
                margin-top: 20px;
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .fixed {
                position: fixed;
                top: 587px;
                z-index: 99;
            }

            .deleteAll,
            .choosePro,
            .total {
                font-size: 12px;
                color: #3c3c3c;
            }
            .deleteAll{
            	margin-left: 20px;
            }
			.delAll{
				text-decoration: none;
				color: #3c3c3c;
				
			}
			.delAll:hover{
				text-decoration: none;
				color:#3c3c3c;
			}
            .delete {
                margin-left: 40px;
            }
			.choosePro p{
				margin-top: 15px;
			}
            .choosePro span {
                margin: 0 5px;
                font-size: 14px;
                color: #ff2300;
            }

            .total span {
                font-size: 16px;
                padding-left: 10px;
                color: #ff2300;
            }

            .calBtn {
                height: 50px;
                width: 120px;
                border: none;
                font-size: 19px;
                line-height: 50px;
                text-align: center;
                color: #fff;
                letter-spacing: 5px;
                background-color: #b0b0b0;
            }

            .calBtn:focus {
                outline: none;
            }
            .hidden{
            	display: none;
            }
        </style>
    </head>
    <body>
    	<%@include file="header.jsp" %>
        <div class="content">
            <div class="allPro">
                <h3>全部商品<span class="counts"></span></h3>
            </div>
            <form>
                <div class="check">
                    <ul class="operateList">
                        <li><input type="checkbox" class="selectAll">全选</li>
                        <li>商品信息</li>
                        <li>单价</li>
                        <li>数量</li>
                        <li>金额</li>
                        <li>操作</li>
                    </ul>
                </div>
                <div class="pro">
                    <ul class="pro-list"></ul>
                </div>
                <div class="calculate">
                	<div class="deleteAll"><a class="delAll" href="javascript:(0);">删除</a></div>
                    <div class="choosePro">
                        <p>已选商品<span class="count">0</span>件</p>
                    </div>
                    <div class="total">
                        合计（不含运费）：<span class="totalPrice">0.00</span>
                        <button type="button" class="calBtn">结算</button>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="./footer.jsp" %>
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script>
            window.onload = window.onresize =function () {
                let proList = document.querySelectorAll(".pro-list>li");
                let pro = document.querySelector(".pro");
                let calculate = document.querySelector(".calculate");
                pro.style.height = proList.length / 2 * 178 + "px";
                if (pro.style.height >= window.innerHeight + "px") {
                    calculate.classList.add("fixed")
                }
                
                let str = "";
                let prolist = document.querySelector(".pro-list");
                $.ajax({
                	url: "${pageContext.request.contextPath}/findShoppingCart",
                	type: "post",
                	success: function(result){
                		let object = result.data;
                		$(".counts").html(object.length);
                		for(let i = 0; i < object.length; i++){
                			str += `
                				<li class="pro-show">
                                <ul class="pro-list-item">
                                    <li>
                                        <div class="pro-info">
                                            <div>
                                                <input type="checkbox" class="goodsCheck">                                            </div>
                                            <div>
                                                <img src="/images/\${object[i].commodityPicture1}" width="100" height="100" alt="">
                                            </div>
                                            <div>
                                                \${object[i].commodityName}
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="pro-price">\${object[i].price}</div>
                                    </li>
                                    <li>
                                        <div class="pro-number">
                                            <div class="pro-detailNumber">
                                                <button type="button" class="sub">-</button>
                                                <input type="text" id="num" value="\${object[i].count}" />
                                                <button type="button" class="add">+</button>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="pro-money"></div>
                                    </li>
                                    <li>
                                        <div class="pro-operate">
	                                    	<input name="id" class="cartId hidden" value="\${object[i].id}"/>
                                            <input type="button" class="btn btn-danger delBtn" value="删除">
                                        </div>
                                    </li>
                                </ul>
                            </li>
                			`;
                		}
                		prolist.innerHTML = str;
                		$(".sub").each(function () {
                            if ($(this).next().val() == 1) {
                                $(this).attr("disabled", true);
                            }
                        });
                        let proMoney = $(".pro-money");
                        proMoney.each(function (i) {
                            $(this).html(parseFloat($(this).parent().prev().prev().children().html() * $(this).parent().prev().find("#num").val()).toFixed(2))
                        })
                        $(".calBtn").prop("disabled", true)
                		let add = $(".add");
                        let sub = $(".sub");
                        let number = $("#num");
                        $(".selectAll").click(function () {
                            let flag = $(".selectAll").prop("checked");
                            if (flag) {
                                $(".goodsCheck").each(function () {
                                    //如果没有点击 所有的checkbox为选中状态 
                                    if (!this.checked) {
                                        $(this).prop("checked", flag);
                                    }
                                });
                            } else {
                                //点击全选之后又取消全选将所有的金额清空
                                $(".goodsCheck").each(function () {
                                    $(this).prop("checked", flag);
                                });
                            }
                            if (isCheck($(this))) {
                                $(".calBtn").css("backgroundColor", "#ff2300");
                                $(".calBtn").prop("disabled", false)
                            } else {
                                $(".calBtn").css("backgroundColor", "#b0b0b0");
                                $(".calBtn").prop("disabled", true)
                            }
                            total();
                        });
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
                                $(".calBtn").css("backgroundColor", "#ff2300");
                                $(".calBtn").prop("disabled", false)
                            } else {
                                $(".calBtn").css("backgroundColor", "#b0b0b0");
                                $(".calBtn").prop("disabled", true)
                            }
                            if (!this.checked) {
                                flag = false;
                            } else {
                                flag = false;
                                if (result) {
                                    flag = true;
                                }
                            }
                            total()
                            $(".selectAll").prop("checked", flag);
                        })
                        $(".add").click(function () {
                            let sum = 0;
                            let that = $(this);
                            let value = $(this).prev().val();
                            value++;
                            if (value > 1) {
                                $(this).prev().prev().attr("disabled", false)
                            }
                            $(this).prev().val(value)
                            $(this).parents(".pro-list-item").find(".pro-money").html((value * parseFloat($(this).parents(".pro-list-item").find(".pro-price").html())).toFixed(2))
                            total();
                            $.ajax({
                            	url: "${pageContext.request.contextPath}/updateShoppingCart",
                            	data: JSON.stringify({
                            		id: that.parent().parent().parent().parent().find(".cartId").val(),
                            		count: parseInt(value)
                            	}),
                            	contentType: "application/json;charset=UTF-8",
                            	type:"post",
                            	dataType: "json",
                            	success: function(result){
                            	}
                            })
                        });
                        $(".sub").click(function () {
                                let value = $(this).next().val();
                                --value;
                                let that = $(this)
                                if (value < 2) {
                                    $(this).attr("disabled", true);
                                }
                                $(this).next().val(value);
                                $(this).parents(".pro-list-item").find(".pro-money").html((value * parseFloat($(this).parents(".pro-list-item").find(".pro-price").html())).toFixed(2))
                                total();
                                $.ajax({
                                	url: "${pageContext.request.contextPath}/updateShoppingCart",
                                	data: JSON.stringify({
                                		id: that.parent().parent().parent().parent().find(".cartId").val(),
                                		count: parseInt(value)
                                	}),
                                	contentType: "application/json;charset=UTF-8",
                                	type:"post",
                                	dataType: "json",
                                	success: function(result){
                                	}
                                })
                        });
                        number.on('keyup', function () {
                            if (isNaN(number.val())) {
                                number.val(1)
                            }
                            if (number.val() === "" || number.val() < 1) {
                                alert("最少购买一件商品")
                                number.val(1);
                            }
                            let value = $(this).val();
                            let that = $(this);
                            $(this).parents(".pro-list-item").find(".pro-money").html(($(this).val() * parseFloat($(this).parents(".pro-list-item").find(".pro-price").html())).toFixed(2))
                            total();
                            $.ajax({
                            	url: "${pageContext.request.contextPath}/updateShoppingCart",
                            	data: JSON.stringify({
                            		id: that.parent().parent().parent().parent().find(".cartId").val(),
                            		count: parseInt(value)
                            	}),
                            	contentType: "application/json;charset=UTF-8",
                            	type:"post",
                            	dataType: "json",
                            	success: function(result){
                            		console.info(result)
                            	}
                            })
                        });
                        function isCheck(el) {
                            return [].some.call(el, item => {
                                if (item.checked) {
                                    return true;
                                } else {
                                    return false;
                                }
                            });
                        }
                        function total() {
                            let sum = 0;
                            let count = 0;
                            $(".goodsCheck").each(function () {
                                if ($(this).is(":checked")) {
                                    let s = parseFloat($(this).parents(".pro-list-item").find(".pro-money").html());
                                    
                                    sum += s;
                                    count ++;
                                }
                            });
                            $(".totalPrice").html(sum.toFixed(2));
                            $(".count").html(count)
                        }
                        $(".delBtn").on("click", function(){
                        	
                        	if(confirm("确定要删除宝贝吗")){
	                        	let arr =[];
	                        	let that = $(this);
	                        	arr.push($(this).prev().val());
                        		
	                        	$.ajax({
	                        		url: "${pageContext.request.contextPath}/deleteShoppingCart",
	                        		data: JSON.stringify({
	                        			arr: arr
	                        		}),
	                        		contentType: "application/json;charset=UTF-8",
	                        		type: "post",
	                        		dataType: "json",
	                        		success: function(result){
	                        			let value = $(".counts").html();
	                        			if(result.flag){
	                        				alert("删除成功");
	                        				$(".counts").html(--value)
	                        				that.parent().parent().parent().remove();
	                        			}
	                        		}
	                        	})
                        	}
                        })
                        $(".delAll").on("click", function(){
                        	let id = [];
                        	let count = 0;
                        	let that;
                        	$(".goodsCheck").each(function(){
                        		if($(this).is(":checked")){
                        			that = $(this)
                        			count++;
                        			id.push($(this).parents(".pro-list-item").find(".pro-operate").children().val())
                        		}
                        	})
                       		if(confirm("您确定要删除"+count+"件商品吗")){
                       			$.ajax({
	                        		url: "${pageContext.request.contextPath}/deleteShoppingCart",
	                        		data: JSON.stringify({
	                        			arr: id
	                        		}),
	                        		contentType: "application/json;charset=UTF-8",
	                        		type: "post",
	                        		dataType: "json",
	                        		success: function(result){
	                        			if(result.flag){
	                        				alert("删除成功");
	                        				location.reload()
	                        			}
	                        		}
	                        	})
                       		}
                        	
                        })
                        $(".calBtn").on("click", function(){
                        	let arr = [];
                        	let goodsChecks = $(".goodsCheck");
                        	goodsChecks.each(function(){
                        		if($(this).is(":checked")){
                        			arr.push(parseInt($(this).parent().parent().parent().parent().find(".cartId").val()))
                        		}
                        	})
                        	localStorage.setItem("arr",JSON.stringify(arr));
                        	location.href = "${pageContext.request.contextPath}/purchasePro";
                        })
                	}
                })
            }
            
        </script>
    </body>
</html>