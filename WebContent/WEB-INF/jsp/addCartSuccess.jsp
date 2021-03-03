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

            .main {
                width: 990px;
                height: 400px;
                margin: 10px auto 0;
            }

            .header {
                width: 990px;
                height: 100px;
                margin: 20px 0;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
           
            .content {
                display: flex;
                justify-content: space-between;
            }
            .iconsousuo::before{
                display: inline-block;
                position: absolute;
                left: 5px;
                z-index: 222;
                line-height: 30px;
                font-size: 20px;
            }
            .proInfo {
                width: 550px;
                height: 220px;
                display: flex;
            }

            .tip {
                width: 340px;
                height: 220px;
                position: relative;
            }
            .proNameAttr div{
                margin: 40px 10px 0;
            }
            .icongou::before{
                color: #31cb2a;
                font-size: 40px;
            }
            .choose{
                position: absolute;
                bottom: 12px;
                right: 0;
            }
            .addSuccess{
                position: absolute;
                right: 0;
                top: 20px;
            }
            .money{
                position: absolute;
                bottom: 70px;
                right: 0;
            }
            .choose a{
                font-size: 17px;
                padding: 11px 20px;
                border: 1px solid #ff5e00;
            }
            .back:hover,.cal:hover{
            	text-decoration: none;
            }
            .back:hover{
            	color: #ff5e00;
            }
            .cal:hover{
            	color:#fff;
            }
            .back{
                background-color: #fff;
                color: #ff5e00;
                margin-right: 20px;
            }
            .cal{
                background-color: #ff2300;
                color:#fff;
            }
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/iconfont.css">
    </head>
    <body>
    	<%@include file="header.jsp" %>
    	<%@include file="searchBar.jsp" %>
        <div class="main">
            <div class="content">
                <div class="proInfo">
                    <div>
                        <img class="image" width="220" height="220" alt="">
                    </div>
                    <div class="proNameAttr">
                        <div class="proName">
                            已添加商品：<span class="proName">加了水电费了asdfasdf卡拉夫家里舒服</span>
                        </div>
                        <div class="proAttr">
                            商品属性：<span class="proSpecs">积分卡拉四大皆空</span>
                        </div>
                    </div>
                </div>
                <div class="tip">
                    <div class="addSuccess">
                        <span class="iconfont icongou"></span>
                        <span>已成功添加到购物车</span>
                    </div>
                    <div class="money">
                       <p>小计：<span>￥<span class="proMoney">123</span></span></p>
                    </div>
                    <div class="choose">
                        <a href="javascript:back();" class="back"> < 返回商品详情</a>
                        <a href="${pageContext.request.contextPath }/cart" class="cal">去购物车结算</a>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="./footer.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script>
        	let name = JSON.parse(localStorage.getItem("name"));
        	let image = JSON.parse(localStorage.getItem("image"));
        	let specs = JSON.parse(localStorage.getItem("specs"));
        	let commodityId = JSON.parse(localStorage.getItem("commodityId"));
        	$(".proName").html(name);
        	$(".proSpecs").html(specs);
        	$(".image").attr("src", `/images/\${image}`);
        	function back(){
        		localStorage.setItem("id", commodityId);
        		window.open("${pageContext.request.contextPath}/proDetails", "_self");
        	}
        </script>
    </body>
</html>