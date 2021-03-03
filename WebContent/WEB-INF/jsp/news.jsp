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

            .talk {
                width: 522px;
                height: 500px;
                position: relative;
                border: 1px solid #ccc;
                margin: 100px auto;
            }
            .content{
            	width: 522px;
            }
            .content li{
            	width: 522px;
            	height: 50px;
            	background-color: #ccc;
            }
        </style>
    </head>
    <body>
        <div class="talk">
            <ul class="content"></ul>
        </div>
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script>
        	window.onload = function(){
       			let content = document.querySelector(".content");
        		$.ajax({
            		url: "${pageContext.request.contextPath}/getlistchatusers",
            		success: function(result){
            			let str = "";
            			let object = result.data;
            			console.info(object[0].user)
            			console.info(object.length)
            			for(let i = 0; i < object.length; i++){
            				str += `<li>
            						   <input type="text" value="\${object[i].user.id}" class="userId">
	                                   <div class="info">
	                                       <span class="name">\${object[i].user.userName}</span>
	                                       <span class="time">\${object[i].time}</span>
	                                       <div class="news">\${object[i].message}</div>
	                                   </div>
                                    </li>`;
            			}
						content.innerHTML = str;
						$(".content li").click(function(){
							let value = $(this).find(".userId").val();
							$.ajax({
								url: "${pageContext.request.contextPath}/getlistchatmessage",
								data: JSON.stringify({
									receiveId: value,
								}),
								contentType : "application/json;charset=UTF-8",
								dataType : "json",
								type: "post",
								success: function(result){
									console.info(result)
								}
							})
						})
            		}
            	})
        	}
        	
            //向后台请求和所有人聊天的
        	function load(){
            	$.ajax({
            		url: "${pageContext.request.contextPath}/getlistchatusers",
            		success: function(result){
            			console.info(result)
            		}
            	})
            }
            //设置定时器，每隔两秒钟向后台请求一次
            /*let timer = setInterval(()=>{
            	load();
            }, 5000)*/
        </script>
    </body>
</html>