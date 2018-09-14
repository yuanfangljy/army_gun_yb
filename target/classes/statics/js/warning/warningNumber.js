/*
var g_intervalId;   // 这要定义成一个全局变量
/!*公共的警告数*!/

g_intervalId=window.setInterval(function WarningNumber() {
    $.ajax({
        url:"../sosMission/statisticsWarningNumber",
        type:"POST",
        cache:false,
        asynec:true,
        success:function (result) {
            $("#wanav li span").empty();
            if(result.extend.warningNumber>=99){
                $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                $("#waNumber").trigger("create");
            }else if(result.extend.warningNumber>=1){
                $("#wanav li span").append(" <img src='img/warningSmall.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                $("#waNumber").trigger("create");
            }
        }
    })
},900000000)

//window.clearInterval(g_intervalId);
//g_intervalId=window.setInterval(WarningNumber, 1000);   // 创建定时执行程序
//    // 在跳转之前调用，以清除定时执行程序*/
