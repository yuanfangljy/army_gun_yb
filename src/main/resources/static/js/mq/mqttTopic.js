$(document).ready(function() {


    function doDisconnect() {
        client.disconnect();
    }

    // Web Messaging API callbacks
    var onSuccess = function(value) {
       // alert(1);
       /* $('#status').toggleClass('connected',true);
        $('#status').text('Success');*/
    }

    var onConnect = function(frame) {
       // alert(2);
       /* $('#status').toggleClass('connected',true);
        $('#status').text('Connected');*/
        client.subscribe("wuqi");

    }
    var onFailure = function(error) {
        //alert(3);
       /* $('#status').toggleClass('connected',false);
        $('#status').text("Failure");*/
    }

    function onConnectionLost(responseObject) {
       /* alert(4);
        alert(client.clientId+"\n"+responseObject.errorCode);*/
    }

    function onMessageArrived(message) {
        var userNames=localStorage.getItem("userName");//读取
        var json = JSON.parse(message.payloadString);
        //***************************   web操作出库列    ************************
        if (json.messageType == "08"&& json.messageBody.userName==userNames) {
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

        //***************************    web操作入库列    ************************
        if (json.messageType == "12" && json.messageBody.userName==userNames) {
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



    }

    var client;
    var r = Math.round(Math.random()*Math.pow(10,5));
    var d = new Date().getTime();
    var cid = r.toString() + "-" + d.toString()

    client = new Messaging.Client("192.168.0.108", 1884, cid);
    client.onConnect = onConnect;
    client.onMessageArrived = onMessageArrived;
    client.onConnectionLost = onConnectionLost;
    client.connect({onSuccess: onConnect, onFailure: onFailure});

});