<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
.all {
    margin: 10px auto;
    max-width: 1190px;
}

.allList{
    width: 1190px;
    float: left;
   	background-color : #fff;
}
.allPro-title h3{
    padding: 20px;
    background-color: #fff;
}
.allpro{	
    width: 197px;
    margin: 19px;
    position: relative;
}
.allList-li{
    list-style: none;
    border: 1px solid #ccc;
    float: left;
}
.allList-li:hover{
    border:none;
    border: 1px solid #fe2300;
}
.all-price{
    font-size: 17px;
    font-weight: bold;
    color: #fe2300;
    margin-top: 20px;
}
.all-count{
	position: absolute;
    right: 10px;
    bottom: -12px;
    color: #999;
    font-size: 12px;
}
.comment{
    width: 180px;
    font-size: 12px;
    color: #999;
    margin:11px 0;
}
.proName{
	margin-top: 10px;
	color:#000;
	display:block;
	height:30px;
	text-decoration: none;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
.hidden{
	display: none;
}
</style>
</head>
<body>
	<section class="all">
            <div class="allPro-title">
                <h3>全部商品</h3>
            </div>
            <div class="allPro">
                <ul class="allList"></ul>
            </div>
        </section>
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script>
        window.onload = function(){
            $.ajax({
            	url: "${pageContext.request.contextPath}/getAllCommodity",
            	type: "post",
            	success: function(result){
            		let object = result.data;
            		let idList = [];
            		if(result.flag){
            			for(let i = 0; i < object.length; i++){
            				idList.push(object[i].id);
            			}
            			let str = "";
                  	   for(let i = 0; i < idList.length; i++){
                  		   $.ajax({
                  			   url: "${pageContext.request.contextPath}/singleCommodity/"+idList[i],
                  			   type: "post",
                  			   success: function(result){
                  				   let object1 = result.data;
                  				   if(result.flag){
                  					   let imageList = [];
                  					   for(let j = 1;j<=15;j++){
                  						   if(object1[`commodityPicture\${j}`]!=null){
                  							   imageList.push(object1[`commodityPicture\${j}`]);
                  						   }
                  					   }
                  					   str += `<li class="allList-li">
                  					   		<input type="text" class="hidden" value="\${idList[i]}"/>
                  	                        <div class="allpro">
                                             <a href="#"><img src="/images/\${imageList[0]}" width="197" height="197" alt=""></a>
                                             <a href="#" class="proName">\${object1.commodityName}</a>
                                             <div class="comment">
                                                 <span class="comm">20评价</span>
                                                 <span class="coll">收藏</span>
                                             </div>
                                             <div class="all-price">
                                                 <span>\${object1.commodityDetailsList[0].price}￥</span>
                                             </div>
                                             <div class="all-count">
                                                 <p>月销<span>\${object1.commodityCount}</span>笔</p>
                                             </div>
                                         </div>
                                     </li>`
                  				   }else{
                  				   }
				            		let allList = document.querySelector(".allList");
                  				   allList.innerHTML = str;
                  				   if(i === idList.length-1){
                  					   document.querySelector(".allPro").style.height = allList.clientHeight+"px"
                  				   }
                  				   $(".allList-li").on("click", function(){
                  					   let id = JSON.stringify($(this).find(".hidden").val());
                  					   localStorage.setItem("id", id);
               						   window.location.href = "${pageContext.request.contextPath}/proDetails";
                  				   })
                  			   }
                  		   })
                  	   }
            		}
            	}
            })
        }
        </script>
</body>
</html>