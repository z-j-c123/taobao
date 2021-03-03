<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
      * {
        margin: 0;
        padding: 0;
      }

      .manage {
        border: 2px solid #ccc;
        border-bottom: none;
        position: relative;
        margin: 50px auto 0;
      }
      .goodsList {
        width: 1120px;
        position: relative;
      }

      .edit {
        position: absolute;
        width: 40px;
        margin: 20px 10px 0 180px;
        z-index: 2;
        display: none;
      }

      .edit button {
        width: 36px;
        background-color: #ff2300;
        margin: 3px;
        border: none;
        outline: none;
        color: #fff;
      }

      .goodsList li {
        margin: 10px;
        width: 240px;
        height: 320px;
        border: 1px solid #ccc;
        list-style: none;
        float: left;
      }

      .good {
        position: absolute;
        width: 200px;
        height: 200px;
        margin: 20px 20px 10px;
      }

      .price span {
        font-size: 20px;
        color: #ff2300;
        font-weight: bold;
      }

      .count {
        position: absolute;
        right: 0px;
        bottom: -90px;
        color: #dedede;
      }

      .goodsLi:hover {
        border: 1px solid #ff2300;
      }

      .goodsLi:hover .edit {
        display: block;
      }
    </style>
  </head>
  <body>
  	<%@include file="./header.jsp" %>
    <div class="manage">
      <div class="row">
        <div class="col-2">
          <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link active" id="addPro" data-toggle="pill" href="#proAdd" role="tab"
              aria-controls="v-pills-home" aria-selected="true">新增商品</a>
            <a class="nav-link findPro" id="allPro" data-toggle="pill" href="#findPro" role="tab"
              aria-controls="v-pills-profile" aria-selected="false">所有商品</a>
            <a class="nav-link sold" id="soldGoods" data-toggle="pill" href="#selledGoods" role="tab"
              aria-controls="v-pills-sold" aria-selected="false">已售商品</a>
            <a class="nav-link talk" id="talk" data-toggle="pill" href="#sellerTalk" role="tab"
              aria-controls="v-pills-talk" aria-selected="false">消息</a>
          </div>
        </div>
        <div class="col-4" id="all">
          <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="proAdd" role="tabpanel" aria-labelledby="addPro">
              <iframe src="${pageContext.request.contextPath}/addPro" id="add" width="1113" height="600"></iframe>
            </div> 	
            <div class="tab-pane" id="findPro" role="tabpanel" aria-labelledby="allPro">
              <iframe src="${pageContext.request.contextPath}/allPro" id="all" width="1113" height="600"></iframe>
            </div>
            <div class="tab-pane" id="selledGoods" role="tabpanel" aria-labelledby="soldGoods">
            	<iframe src="${pageContext.request.contextPath }/soldPro" id="sold" width="1113" height="600"></iframe>
            </div>
            <div class="tab-pane" id="sellerTalk" role="tabpanel" aria-labelledby="talk">
            	<iframe src="${pageContext.request.contextPath }/information" id="talk" width="1113" height="600"></iframe>
            </div>
          </div>
        </div>
      </div>
    </div>
    <%@include file="./footer.jsp" %>
    <script>
    	$("#allPro").click(function(){
    		$("#findPro").attr("src", $("#findPro").attr("src"))
    	})
    </script>
  </body>
</html>