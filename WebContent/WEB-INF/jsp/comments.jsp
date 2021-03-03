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
            .comment{
                width: 990px;
                margin: 50px auto;
            }
            .public-comment{
                width: 988px;
                border: 1px solid #ccc;
                height: 540px;
                background-color: #f6f6f6;
                margin-top: 20px;
                position: relative;
            }
            .goodsInfo{
                width: 990px;
                display: flex;
            }
            .goodsSpecs{
                margin:50px 0 0 20px;
            }
            .goodsSpecs .goodsName{
                font-size: 16px;
                margin: 8px 0;
                color: #000;
                font-weight: bold;
            }
            .goodsSpecs .price{
                font-size: 12px;
                color: #999;
                margin: 14px 0;
            }
            .goodsSpecs .price span{
                font-size: 25px;
                font-weight: bolder;
                color: #E30000;   
                margin: 0 5px 0 60px;
            }
            .commentSum,.commentSpecs{
                font-size: 12px;
                color: #999;
                margin: 14px 0;
            }
            .commentSum span{
                margin: 0 5px 0 60px;
            }
            .commentSum .count{
                margin: 0;
            }
            .commentSpecs span{
                margin: 0 5px 0 35px;
            }
            .check{
                display: block;
                width: 300px;
                height: 40px;
                background-color: bisque;
                font-size: 12px;
                color: #999;
                margin: 20px 0;
                line-height: 40px;
                text-indent: 20px;
            }
            .comment-info{
                position: absolute;
                left: 60px;
                top: 80px;
            }
            .suggest{
                font-size: 18px;
                font-weight: bold;
                margin: 20px 0 0 40px;
            }
            .comment-info label{
                position: absolute;
                width: 180px;
            }
            #comment-info:focus{
                outline: none;
            }
            #comment-info{
                margin-left: 160px;
            }
            .submit{
                position: absolute;
                bottom: 10px;
                left: 400px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .submitBtn{
                width: 100px;
                height: 40px;
                background-color: #eff3f4;
                border: none;
                border-radius: 3px;
            }
            .submitBtn:focus{
                outline: none;
            }
            .commentAll{
                margin-top: 20px;
            }
            .comment-user span,.goods-info span,.comment-details span{
                font-size: 12px;
                color: #121a2a;
                line-height: 80px;
            }
            .radio{
            	position: absolute;
            	left: 220px;
            	top: 200px;
            }
            .uploadPic{
            	position: absolute;
            	left: 220px;
            	top:240px;
            }
            .showImage{
            	position: absolute;
            	left: 220px;
            	top: 310px;
            }
            .box{
            	display: flex;
            }
            .box li{
            	margin: 5px;
            }
            .hidden{
            	display: none;
            }
 .uploadPic{
 	display: flex;
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
.imageList {
	display: flex;
}
.imageList li{
	width: 80px;
	height: 80px;
	margin: 4px;
}
.imageList img{
	display: none;
}
        </style>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    </head>
    <body>
    	<%@include file="header.jsp" %>
        <div class="comment">
            <div class="goodsInfo">
                <div class="goodsImage">
                    <img  class="image" width="460" height="460">
                </div>
                <div class="goodsSpecs">
                    <p class="goodsName"></p>
                    <p class="price">价格<span class="mon"></span>元</p>
                    <p class="commentSum">评价<span>累计评价（<span class="count"></span>）</span></p>
                    <p class="commentSpecs">商品规格<span class="specs"></span></p>
                    <p class="check">您现在查看的是&nbsp;&nbsp;您于<span class="time"></span>日下单购买的商品</p>
                </div>
            </div>
            <div class="public-comment">
                <p class="suggest">其他买家，需要你的建议哦！</p>
                <form id="commentForm" enctype="multipart/form-data">                
	                <div class="comment-info">
	                    <label for="comment-info">请输入您的使用感受：</label>
	                    <textarea name="message" id="comment-info" cols="80" rows="4"></textarea>
	                </div>  
	                <div class="radio">
	                	<input type="radio" name="comment" value="好评" checked/>好评
	                	<input type="radio" name="comment" value="中评"/>中评
	                	<input type="radio" name="comment" value="差评"/>差评
	                	<input type="text" name="commentGrade" class="commentGrade hidden"/>
	                </div>
	                <div class="uploadPic">
						<div class="input-file-box">
							<span>上传图片</span> <input type="file" class="uploadfile" name="commodityImage" multiple>
						</div>
					</div>
					<div class="showImage">
						<ul class="box"></ul>
					</div>
	                <div class="submit"> 
	                	<input type="text" class="commodityOrderId hidden" name="commodityOrderId"/>
	                    <button type="button" class="submitBtn">提交评价</button>
	                </div>
                </form>
            </div>
            <div class="commentAll">
                <table class="table table-border">
                    <ul class="commentList"></ul>
                </table>
            </div>
        </div>   
        <%@include file="./footer.jsp" %>
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script>
            window.onload = function(){
            	let name = JSON.parse(localStorage.getItem("name"));
            	let time = JSON.parse(localStorage.getItem("time"));
            	let specs = JSON.parse(localStorage.getItem("specs"));
            	let price = JSON.parse(localStorage.getItem("price"));
            	let image = JSON.parse(localStorage.getItem("image"));
            	let id = JSON.parse(localStorage.getItem("id"));
            	let orderId = JSON.parse(localStorage.getItem("orderId"));
            	$(".goodsName").html(name);
            	$(".mon").html(price);
            	$(".specs").html(specs);
            	$(".time").html(time.split(" ")[0]);
            	$(".image").attr("src",image)
            	$(".commodityOrderId").val(orderId);
            	$.ajax({
            		url: "${pageContext.request.contextPath}/findCommodityAllComment/"+id,
            		type: "get",
            		success: function(result){
            			console.info(result)
            			let commentList = document.querySelector(".commentList");
            			if(result.flag){
            				let object = result.data;
            				let commentList = document.querySelector(".commentList");
            				$(".count").html(object.length)
            				let str = "";
            				let imageList = [];
            				for(let i = 0; i < object.length; i++){
            					str += `<li>
            						<table class="table table-border">
	            						<tr>
	            							<td>
	            								<div>\${object[i].commentGrade}</div>
	            							</td>
	            							<td>
	            								<div>\${object[i].message}</div>
	            							</td>
	            							<td>
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
            			}
            		}
            	})
            	
            	let str = '';
                for(let i = 0; i < 5; i++){
                    str += `<div class="input-file-box">
                        <span>上传图片</span>
                        <input type="file" name="commodityImage" class="uploadfile" >
                    </div>`;
                }
                $(".uploadPic").html(str)
                let input = document.getElementsByClassName("uploadfile");
                let box = document.querySelector(".box");
            	for(let i = 0; i < input.length; i++){
                    input[i].onchange = function(){
                    	$(this).css("display","none")
                    	$(this).prev().html("已添加");
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
    		
            	$(".commentGrade").val($("input[type='radio']:checked").val())
            	$("input[type='radio']").each(function(){
            		$(this).click(function(){
            			if(this.checked){
            				$(".commentGrade").val($(this).val())
            			}
            		})
            	})
            	$(".submitBtn").on("click", function(){
            		$.ajax({
            			url: "${pageContext.request.contextPath}/comment",
            			type: "post",
            			contentType: false,
            			processData: false,
            			dataType: "json",
            			data: new FormData($("#commentForm")[0]),
            			success: function(result) {
            				if(result.flag){
            					alert("评价成功");
            					location.reload();
            				}else{
            					alert("评价失败,可能是因为您已经对本商品进行过评价了哦");
            					location.reload();
            				}
            			}
            		});
            	})
            } 
        </script>
    </body>
</html>