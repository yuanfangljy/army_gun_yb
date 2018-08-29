
/*接收出入库mq显示*/
//var url = "ws://120.76.156.120:61614/WebOutQueue";
//var url = "ws://14.116.149.237:61614/WebOutQueue";
var url = "ws://192.168.0.103:61614/WebOutQueue";
var login = "admin";
var passcode = "admin";
//监听的队列
//需要和发送者的发送的队列名称一致否则无法接受到数据
destination = "WebOutQueue";
var client = Stomp.client(url);
var onconnect = function (frame) {
    client.subscribe(destination, function (message) {
        var json;
        try {
            json = JSON.parse(message.body);
            messageType=JSON.parse(message.messageType);
            alert();
        } catch (e) {
            json = eval("(" + message.body + ")");
        }
        //***************************    出库列    ************************
        if (json.messageType == "08") {
            if(json.messageBody.state=="1"){
                layer.open({
                    title: '出库消息',
                    content: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库成功',
                    offset: 'r'
                    //,shade: 0 //不显示遮罩
                });
            }else{
                layer.open({
                    title: '出库消息',
                    content: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库失败',
                    offset: 'r'
                });
            }
        }
        //***************************    入库列    ************************
        if (json.messageType == "12") {
            if(json.messageBody.state=="1"){
                layer.open({
                    title: '入库消息',
                    content: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'入库成功',
                    offset: 'r'
                });
            }else{
                layer.open({
                    title: '警告',
                    content: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'失败',
                    offset: 'r'
                });
            }
        }
        //***************************    离位警告信息    ************************

        if (json.messageType == "15") {
           // function WarningNumber() {
                $.ajax({
                    url:"../sosMission/statisticsWarningNumber",
                    type:"GET",
                    cache:false,
                    async:true,
                    success:function (result) {
                        $("#wanav li span").empty();
                        if(result.extend.warningNumber>=10){
                            $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                            //$("#waNumber").trigger("create");
                        }else if(result.extend.warningNumber>=1){
                            $("#wanav li span").append(" <img src='img/warningSmall.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+result.extend.warningNumber+"</span>");
                            $("#waNumber").trigger("create");
                        }else if(result.extend.warningNumber>=99){
                            $("#wanav li span").append(" <img src='img/warningBig.png' style='position:absolute; left:130px; top:18px; z-index:2;'><span id='warningNumber' class='text-center' style='position:absolute; left:134px; top:16px; z-index:3; color:#fff; font-size:13px;'>"+99+"</span>");
                            $("#waNumber").trigger("create");
                        }
                    }
                })
            }
       // }
    });
};
client.connect(login, passcode, onconnect);




