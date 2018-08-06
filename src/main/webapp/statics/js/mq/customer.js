
/*接收出入库mq显示*/

var url = "ws://112.74.51.194:61616/WebInQueue";
var login = "admin";
var passcode = "admin";
//监听的队列
//需要和发送者的发送的队列名称一致否则无法接受到数据
destination = "WebInQueue";
var client = Stomp.client(url);
var onconnect = function (frame) {
    client.subscribe(destination, function (message) {
        var json;
        try {
            json = JSON.parse(message.body);
        } catch (e) {
            json = eval("(" + message.body + ")");
        }
        if (json.state == "0") {
            layer.open({
                title: '出库消息',
                content: '【'+json.deviceNo+'】'+'匹配'+ '【'+json.gunTag+'】'+'出库成功',
                offset: 'r'
            });
        }else if (json.state == "1") {
            layer.open({
                title: '入库消息',
                content: '【'+json.deviceNo+'】'+'匹配'+ '【'+json.gunTag+'】'+'入库成功',
                offset: 'r'
            });
        }else{
            layer.open({
                title: '警告',
                content: '【'+json.deviceNo+'】'+'匹配'+ '【'+json.gunTag+'】'+'失败',
                offset: 'r'
            });
        }
    });
};
client.connect(login, passcode, onconnect);
