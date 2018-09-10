var g_intervalId;   // 这要定义成一个全局变量
/*公共的警告数*/


$.ajax({
    url: "../sosMission/statisticsWarningNumber",
    type: "GET",
    //cache: false,
    async: true,
    success: function (result) {
        $("#wanav li span").empty();
        if (result.extend.warningNumber >= 10 && result.extend.warningNumber<=99) {
            $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + result.extend.warningNumber + "</span>");
            $("#waNumber").trigger("create");
        } else if (result.extend.warningNumber >= 1 && result.extend.warningNumber < 10) {
            $("#wanav li span").append(" <img src='img/warningSmall.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + result.extend.warningNumber + "</span>");
            $("#waNumber").trigger("create");
        } else if (result.extend.warningNumber >= 99) {
            $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + 99 + "</span>");
            $("#waNumber").trigger("create");
        }
    }
})


function WarningNumber() {
    $.ajax({
        url: "../sosMission/statisticsWarningNumber",
        type: "GET",
        //cache: true,
        async: false,
        success: function (result) {
            $("#wanav li span").empty();
            if (result.extend.warningNumber >= 10 && result.extend.warningNumber<=99) {
                $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + result.extend.warningNumber + "</span>");
                $("#waNumber").trigger("create");
            } else if (result.extend.warningNumber >= 1 && result.extend.warningNumber < 10) {
                $("#wanav li span").append(" <img src='img/warningSmall.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + result.extend.warningNumber + "</span>");
                $("#waNumber").trigger("create");
            } else if (result.extend.warningNumber >= 99) {
                $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>" + 99 + "</span>");
                $("#waNumber").trigger("create");
            }
        }
    })
}

//登出
function LogOuts() {

    layer.confirm('您确定要退出登录吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        gun();
    }, function () {
        layer.msg('已取消操作', {
            time: 500, //20s后自动关闭
            // btn: ['明白了', '知道了']
        });
    })
}

function gun() {
    $.ajax({
        url: "../webUser/logOutWebUser",
        type: "PUT",
        async: true,
        success: function () {
            layer.msg('成功退出', {icon: 1,time: 500},function(index){
                window.location.href = "../login.html";
            });
            //询问框
        }
    });
}

/*g_intervalId=window.setInterval(function WarningNumber() {
    $.ajax({
        url:"../sosMission/statisticsWarningNumber",
        type:"POST",
        cache:false,
        asynec:true,
        success:function (result) {
            $("#wanav li span").empty();
            if(result.extend.warningNumber>=10){
                $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                $("#waNumber").trigger("create");
            }else if(result.extend.warningNumber>=1){
                $("#wanav li span").append(" <img src='img/warningSmall.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                $("#waNumber").trigger("create");
            }else if(result.extend.warningNumber>=99){
                $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+99+"</span>");
                $("#waNumber").trigger("create");
            }
        }
    })
},1000)*/

//window.clearInterval(g_intervalId);
//g_intervalId=window.setInterval(WarningNumber, 1000);   // 创建定时执行程序
//    // 在跳转之前调用，以清除定时执行程序
