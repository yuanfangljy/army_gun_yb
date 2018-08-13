
/*接收出入库mq显示*/
//var url = "ws://120.76.156.120:61614/WebOutQueue";
var url = "ws://127.0.0.1:61614/liujiayi";
var login = "admin";
var passcode = "admin";
//监听的队列
//需要和发送者的发送的队列名称一致否则无法接受到数据
destination = "liujiayi";
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
    });
};
client.connect(login, passcode, onconnect);
