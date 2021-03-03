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
.allPro{
	
}
.allList{
    width: 1190px;
    float: left;
   	background-color : #fff;
}
.allPro-title{
    padding-top:15px;
    padding-left:15px;
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
display:none;}
.meiyou{
	text-align: center;
	font-size: 40px;
}
</style>
</head>
<body>
	<%@include file="header.jsp" %>
	<%@include file="searchBar.jsp" %>
	<section class="all">
           <div class="allPro">
                <ul class="allList"></ul>
            </div>
       </section>
       <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
       <script>
           window.onload = function(){
        	   let allList = document.querySelector(".allList");
        	   let allLength = document.querySelector(".allList").clientHeight;
               let allPro = document.querySelector(".allPro");
               allPro.style.height = allLength+"px";
        	   let value = JSON.parse(localStorage.getItem("value"));
        	   let id = JSON.parse(localStorage.getItem("id"));
        	   let sellerId = JSON.parse(localStorage.getItem("sellerId"));
        	   $(".find").val(value);
        	   let str = "";
        	   if(id == ""){
        		  	let div = document.createElement("div");
        		  	div.classList.add("meiyou");
        		  	div.innerHTML = `为您找到0个   \${value}   商品`;
        		  	allPro.appendChild(div)
        	   }else{
        		   for(let i = 0; i < id.length; i++){
            		   $.ajax({
            			   url: "${pageContext.request.contextPath}/singleCommodity/"+id[i],
            			   type: "post",
            			   success: function(result){
            				   let object = result.data;
            				   if(result.flag){
            					   str += `<li class="allList-li">
            					   		<input type="text" value="\${id[i]}" class="hidden">
            	                        <div class="allpro">
                                       <a href="#"><img src="/images/\${object.commodityPicture1}" width="197" height="197" alt=""></a>
                                       <a href="#" class="proName">\${object.commodityName}</a>
                                       <div class="comment">
                                           <span class="comm">20评价</span>
                                           <span class="coll">收藏</span>
                                       </div>
                                       <div class="all-price">
                                           <span>\${object.commodityDetailsList[0].price}￥</span>
                                       </div>
                                       <div class="all-count">
                                           <p>月销<span>\${object.commodityCount}</span>笔</p>
                                       </div>
                                   </div>
                               </li>`
            				   }
            				   allList.innerHTML = str;
            				   $(".allList-li").on("click", function(){
            					   let id = JSON.stringify($(this).find(".hidden").val());
            					   localStorage.setItem("id", id);
           						   window.open("${pageContext.request.contextPath}/proDetails");
            				   })
            			   }
            		   })
            	   }   
        	   }
           }
       </script>
</body>
</html>