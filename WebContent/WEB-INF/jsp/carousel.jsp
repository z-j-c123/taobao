<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*轮播图*/

.banner {
    max-width: 1190px;
    height: 560px;
    background-color: purple;
    position: relative;
    margin: 0 auto;
}

.big-banner{
    height: 560px;
    width: 1190px;
}
</style>
</head>
<body>
	<section class="banner">
            <div class="big-banner">
                <div id="carousel1" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators"></ol>
                    <div class="carousel-inner"></div>
                    <a class="carousel-control-prev" href="#carousel1" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel1" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </section>
        <script>
        		let imageList = [];
            	let image = "";
            	let indicators = "";
            	$.ajax({
            		url: "${pageContext.request.contextPath}/loginGetRotationTableCommodity",
            		type: "post",
            		success: function(result){
            			let arr = result.data;
            			for(let i = 0; i < arr.length; i++){
            				if(i === 0){
            					image += `<div class="carousel-item active" id="\${arr[i].id}">
                                    <img src="/images/\${arr[i].commodityPicture1}"  height="560" width="1190" class="d-block w-100" alt="...">
                                    </div>`;
            					indicators += `<li data-target="#carousel1" data-slide-to="\${i}" class="active"></li>`;
            				}else{
            					image += `<div class="carousel-item" id="\${arr[i].id}">
                                    <img src="/images/\${arr[i].commodityPicture1}" height="560" width="1190" class="d-block w-100" alt="...">
                                    </div>`;
            					indicators += `<li data-target="#carousel1" data-slide-to="\${i}"></li>`;
            				}
            			}
            			$(".carousel-inner").html(image);
            			$(".carousel-indicators").html(indicators);
            			$(".carousel-item").on("click", function(){
            				for(let i = 0; i < arr.length; i++){
            					if(arr[i].id == $(this).attr("id")){
            						let id = arr[i].id;
            						localStorage.setItem("id", id);
            						window.location.href = "${pageContext.request.contextPath}/proDetails";
            					}
            				}
            			})
            		}
            	})
        	
        	
        </script>
</body>
</html>