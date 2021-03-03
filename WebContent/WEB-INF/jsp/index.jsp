<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    </head>
	
    <body style="background-color: #f4f4f4;">
        <!--头部-->
        <%@include file="header.jsp" %>
        <!--搜索框-->
        <%@include file="searchBar.jsp" %>
        <!-- 轮播图 -->
        <%@include file="carousel.jsp" %>
        
        
        <!--所有商品-->
        <div style><%@include file="allProduct.jsp" %>
        <!--底部-->
        <%@include file="./footer.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
        <script>
            $(".carousel").carousel({
                interval: 2500,
            });
            
        </script>
    </body>
</html>