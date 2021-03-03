<%@page import="po.User"%>
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
            .news{
                width: 1190px;
                height: 560px;
                margin: 50px auto;
                display: flex;
            }
            .message{
                width: 430px;
                height: 560px;
            }
            .mess{
            	display: flex;
            	justify-content: space-between;
            }
            .message-list{
                width: 430px;
                height: 560px;
                overflow: auto;
            }
            .message-list-item{
                width: 420px;
                height: 70px;
                font-size:14px;
                color: #6c6c6c;
                border-bottom: 1px solid #afb4db;
            }
            .message-list .message-item{
                width: 400px;
                padding: 15px 15px 0 15px;
                display: flex;
                justify-content: space-between;
            }
            .message-detail{
                text-indent: 15px;
                width: 300px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
            }
            .unread{
            	margin-right:40px;
            	background-color: red;
            	font-size: 16px;
            	width: 20px;
            	height: 20px;
            	text-align: center;
            	line-height: 20px;
            	color:#fff;
            	border-radius: 50%;
            }
            .chat{
                width: 760px;
                height: 560px;
                position: relative;
            }
            .chat-list{
                height: 560px;
            }
            .chat-list-item{
                background-color: #afb4db;
                height: 560px;
                position: relative;
            }
           
            .sendMessage{
                height: 200px;
                width: 757px;
                position: absolute;
                bottom: 1px;
            }
            .chatmessage{
                height: 375px;
                overflow: auto;
            }
            
            .chatmessageDetail li{
                margin: 10px;
            }
            .right{
                margin-left: 560px;
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
            .info{
                width: 760px;
                height: 200px;
                font-size: 16px;
            }
            .info:focus{
                outline: none;
            }
            .submit{
                position: absolute;
                right: 0px;
                bottom: 2px;
                width: 100px;
                height: 40px;
                background-color: aqua;
            }
            .submit input{
                width: 100px;
                height: 40px;
                font-size: 18px;
                font-weight: bold;
                letter-spacing: 5px;
            }
            .userId{
            	display: none;
            }
            .userName{
            	height: 38px;
            	font-size: 38px;
            	line-height: 38px;
            	margin-left: 300px;
            	z-index: 99;
            }
            
            .hidden{
            	display: none;
            }
            .receiveName{
            	font-size: 20px;
            	font-weight: bloder;
            	color: #000;
            }
            .noMessage {
                width: 300px;
                height: 200px;
                position: relative;
                margin: 200px auto;
            }

            .noMess {
                width: 300px;
                height: 150px;
                border: 2px solid #ccc;
                border-radius: 5px;
                background-color: #fff;
            }

            .noMess::before {
                content: "";
                display: block;
                position: absolute;
                bottom: 40px;
                left: 30px;
                width: 60px;
                height: 40px;
                border: 2px solid #ccc;
                transform: rotate(90deg) skew(45deg);
                z-index: -2;
            }

            .messageList {
                width: 200px;
                height: 150px;
                display: flex;
                margin: auto;
                justify-content: space-between;
                align-items: center;
            }

            .messageList li {
                width: 20px;
                height: 20px;
                border-radius: 50%;
                border: 2px solid #ccc;
            }

            .tip {
                color: #ccc;
                font-size: 20px;
                line-height: 100px;
                text-align: center;
            }
        </style>
    </head>
    <body>
    	<%@include file="header.jsp" %>
        <div class="news">
            <div class="message">
                <ul class="message-list"></ul>
            </div>
            <div class="chat">
                <ul class="chat-list">
                    <li class="chat-list-item">
                        <div class="chatmessage">
                        	<div class="userName"></div>
                            <ul class="chatmessageDetail"></ul>
                        </div>
                        <div class="sendMessage">
                            <textarea cols="106" rows="13" class="info"></textarea>
                            <div class="submit">
                            	<input type="text" class="receiveId hidden">
                                <input type="button" value="发送" id="send">
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="noMessage">
            <div class="noMess">
                <ul class="messageList">
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <div class="tip">暂时没有新消息</div>
        </div>
        <%@include file="./footer.jsp" %>
        <script src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
        <script>
        	window.onload = function(){
        		let chatList = document.querySelector(".message-list");
        		$.ajax({
            		url: "${pageContext.request.contextPath}/getlistchatusers",
            		success: function(result){
            			let str = "";
            			let object = result.data;
            			if(object.length == 0){
            				$('.news').css("display", "none");
            				$(".noMessage").css("display", "block")
            			}else{
            				$(".noMessage").css("display", "none")
            			let sellerId = JSON.parse(localStorage.getItem("sellerId"))
            			for(let i = 0; i < object.length; i++){
            					str += `<li class="message-list-item">
       								<input type="text" value="\${object[i].user.id}" class="userId">
	                                <div class="message-item">
		                                <div class="receiveName">\${object[i].user.userName}</div>
		                                <div class="lastTime">\${object[i].time}</div>
		                            </div>
		                            <div class="mess">
			                            <div class="message-detail">\${object[i].message}</div>
			                            <div class="unread">\${object[i].unread}</div>
		                            </div>
		                        </li>`;
            				
            			}
            			chatList.innerHTML = str;
            			$(".unread").each(function(){
            				if($(this).html() === "0"){
            					$(this).css("display", "none")
            				}
            			})
            			$(".message-list-item").click(function(){
            					$(".userName").html($(this).find(".receiveName").html());
                				$(".receiveId").val($(this).find(".userId").val());
                				
                				console.info($(this).find(".unread").html("0"));
                				setInterval(function(){
                				$(".unread").each(function(){
                    				if($(this).html() === "0"){
                    					$(this).css("display", "none")
                    				}
                    			})
                    			
                				let id = $(".receiveId").val();
                				$.ajax({
                					url: "${pageContext.request.contextPath}/updatechatcessage",
                					data: JSON.stringify({receiveId: id}),
                					dataType:"json",
                					type: "post",
                					contentType: "application/json;charset=UTF-8",
                					success:function(result){}
                					
                				})
                				$.ajax({
    								url: "${pageContext.request.contextPath}/getlistchatmessage",
    								data: JSON.stringify({
    									receiveId: id,
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
                				},100)
            			})
            		}}
            	})
        	}
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
        	
            //更新消息列表
            function getChatList(){
        		let chatList = document.querySelector(".message-list");
        		$.ajax({
            		url: "${pageContext.request.contextPath}/getlistchatusers",
            		success: function(result){
            			let str = "";
            			let object = result.data;
            			for(let i = 0; i < object.length; i++){
            				str += `<li class="message-list-item">
            								<input type="text" value="\${object[i].user.id}" class="userId">
			                                <div class="message-item">
				                                <div class="receiveName">\${object[i].user.userName}</div>
				                                <div class="lastTime">\${object[i].time}</div>
				                            </div>

				                            <div class="mess">
					                            <div class="message-detail">\${object[i].message}</div>
					                            <div class="unread">\${object[i].unread}</div>
				                            </div>
			                        </li>`;
            			}
            			chatList.innerHTML = str;
            			$(".unread").each(function(){
            				if($(this).html() === "0"){
            					$(this).css("display", "none")
            				}
            			})
            			$(".message-list-item").click(function(){
            				$(".userName").html($(this).find(".receiveName").html());
            				$(".receiveId").val($(this).find(".userId").val());
            				let id = $(".receiveId").val();
            				$.ajax({
            					url: "${pageContext.request.contextPath}/updatechatcessage",
            					data: JSON.stringify({receiveId: id}),
            					dataType:"json",
            					type: "post",
            					contentType: "application/json;charset=UTF-8",
            					success:function(result){}
            					
            				})
            				let str1 = "";
            				let chatMessageDetail = document.querySelector(".chatmessageDetail");
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
									let object = result.data;
									let arr = $(".userId").val();
									console.info(result);
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
            			})
            		}
            	})
        	}
        </script>
    </body>
</html>
