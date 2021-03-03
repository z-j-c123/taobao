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
                user-select: none;
            }
            footer{
            	width:100%;
                background-color: #1a1e21 ;
                margin-top: 150px;
            }
            .foot{
                max-width: 1190px;
                margin: 10px auto 0;
                position: relative;
                height: 240px;
            }
            .introduce{
                width: 1190px;
                display: flex;
                padding-top: 20px;
            }
            .introduce h2{
                color: #ccc;
                font-size: 40px;
            }
            .describe{
                margin-left: 40px;
            }
            .describe p{
                color: #ccc;
            }
            .describe p span{
                margin:0 5px;   
            }
            .contact{
                margin-top: 10px;
            }
            .contact ul li{
                list-style: none;
                float: left;
                margin-top: 5px;
            }
            .contact ul li span{
                color: #ccc;
                padding: 0 5px;
                font-size: 12px;
                line-height: 12px;
                height: 12px;
                display: inline-block;
            }
            .bord{
                border-right: 1px solid #ccc;
            }
        </style>
    </head>
    <body>
        <footer>
            <div class="foot">
                <div class="introduce">
                    <h2>三叶草购物商城</h2>
                    <div class="describe">
                        <p>如果您在使用本商城时，遇到任何问题请联系我们</p>
                        <p>qq:<span>978564225</span><span>2223227746</span><span>2281985285</span></p>
                    </div>
                </div>
                <div class="contact">
                    <ul class="list1"></ul>
                    <ul class="list2"></ul>
                    <ul class="list3"></ul>
                </div>
            </div>
        </footer> 	  
        <script>
            let arr1 = ["增值电信业务经营许可证：浙B2-20080224","增值电信业务经营许可证（跨地区）： B2-20150210","浙网文（2019）1033-086号","浙江省网络食品销售第三方平台提供者备案：浙网食A33010001"];
            let arr2 = ["互联网药品信息服务资格证书（浙）-经营性-2018-0010","短消息类服务接入代码使用证书：号【2016】00154-A01","信息网络传播视听许可证：1109364号","出版物网络交易平台服务经营备案号：新出发浙备字第002号"];
            let arr3 = ["浙公网安备 33010002000078号","广播电视节目制作经营许可证（浙）字第01012号","市场名称登记证：工商网市字3301004120号","医疗器械网络交易服务第三方平台备案：（浙）网械平台备字[2018]第00004号"];
            let list1 = document.querySelector(".list1");
            let list2 = document.querySelector(".list2");
            let list3 = document.querySelector(".list3");
            insert(list1, arr1)
            insert(list2, arr2)
            insert(list3, arr3)
            function insert(el, arr){
                let str = "";
                for(let i = 0; i < arr.length; i++){
                    if(i == 0 || i == arr.length -1){
                        str += `<li><span>\${arr[i]}</span></li>`
                    }else{

                        str += `<li><span class="bord">\${arr[i]}</span></li>`
                    }
                }
                el.innerHTML = str;
            }
        </script>
    </body>
</html>