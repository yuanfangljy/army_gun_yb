$(document).ready(function() {
    var userNames=localStorage.getItem("userName");//读取

    function doDisconnect() {
        client.disconnect();//响应一次，就关闭
    }

    // Web Messaging API callbacks
    var onSuccess = function(value) {
       // alert(1);
       /* $('#status').toggleClass('connected',true);
        $('#status').text('Success');*/
    }

    var onConnect = function(frame) {
        //layer.alert("服务连接成功");
       /* $('#status').toggleClass('connected',true);
        $('#status').text('Connected');*/
        client.subscribe("WebTopic");

    }
    var onFailure = function(error) {
        //alert(3);
       /* $('#status').toggleClass('connected',false);
        $('#status').text("Failure");*/
    }
    var options = {
        keepAliveInterval:10000,
        onSuccess: onConnect,
        onFailure: onFailure
    };

    function onConnectionLost(responseObject) {
        client.connect(options);
        //layer.alert("连接服务已断开，请刷新页面！");
        //alert(client.clientId+"\n"+responseObject.errorCode);
    }



    function onMessageArrived(message) {
       // alert(message.payloadString);

       // var userNames=localStorage.getItem("userName");//读取

        var json = JSON.parse(message.payloadString);
        //***************************   web操作出库列    ************************
        if (json.messageType == "08"&& json.userName==userNames) {
            if(json.messageBody.state=="1"){
                Lobibox.notify('success', {
                    size: 'mini',
                    title: '出库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库成功'
                });

            }else{
                Lobibox.notify('error', {
                    size: 'mini',
                    title: '出库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库失败'
                });

            }
        }else if(json.messageType == "08"&& json.userName=="1"){//腕表
            if(json.messageBody.state=="1"){
                Lobibox.notify('success', {
                    size: 'mini',
                    title: '出库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库成功'
                });

            }else{
                Lobibox.notify('error', {
                    size: 'mini',
                    title: '出库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'出库失败'
                });

            }
        }

        //***************************    web操作入库列    ************************
        if (json.messageType == "12" && json.userName==userNames) {
            if(json.messageBody.state=="1"){

                Lobibox.notify('success', {
                    size: 'mini',
                    title: '入库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'入库成功'
                });

            }else{
                Lobibox.notify('error', {
                    size: 'mini',
                    title: '入库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'入库失败'
                });
            }
        }else if(json.messageType == "12" && json.userName=="1"){//腕表
            if(json.messageBody.state=="1"){

                Lobibox.notify('success', {
                    size: 'mini',
                    title: '入库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'入库成功'
                });

            }else{
                Lobibox.notify('error', {
                    size: 'mini',
                    title: '入库消息',
                    msg: '【'+json.messageBody.deviceNo+'】'+'匹配'+ '【'+json.messageBody.gunTag+'】'+'入库失败'
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
    }


    var client;
    var r = Math.round(Math.random()*Math.pow(10,5));
    var d = new Date().getTime();
    var cid = r.toString() + "-" + d.toString()

    //112.74.51.194 1884  120.76.156.120 61614
    client = new Messaging.Client("112.74.51.194", 1884, cid);
    client.onConnect = onConnect;
    client.onMessageArrived = onMessageArrived;

   // client.connectOptions=onFeconnect;
    client.onConnectionLost = onConnectionLost;
    client.connect(options);
    //client.connect({onSuccess: onConnect, onFailure: onFailure});

});