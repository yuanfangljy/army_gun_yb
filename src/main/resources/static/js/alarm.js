
$(function () {
    to_page(1, null);
});
var likePhone = "";
//定义全局变量，保存总记录数
var totalRecord, currentPage;

//请求数据
function to_page(pn, deviceNo) {
    $.ajax({
        url: "../sosMission/realTimeSosMassage",
        data: {"pn": pn, "deviceNo": deviceNo},
        type: "GET",
        dataType: "json",
        success: function (result) {
            if(result.status==200){
                layer.msg(result.errorMessage);
                return false;
            }
            //  alert("111");
            var ii = layer.load(1, {
                shade: [0.2, '#ffc659'] //0.1透明度的白色背景
            });
            //此处用setTimeout演示ajax的回调
            setTimeout(function () {
                layer.close(ii);
                if (result.extend.pageInfo.pages >= "2") {
                    //1、解析屏显示枪支地点数据、
                    build_location_table(result);
                    //2、解析并显示分页信息
                    buid_page_info(result);
                    //3、解析显示分页条
                    build_page_nac(result);
                    //4.伸长选择页码跳转div
                    $(".pagination").width($(".pagination").width() + 500);
                } else {
                    build_location_table(result);
                }

            }, 200);

        }, error: function (result) {
            if (result.responseText == "IsAjax") {
                window.location.href = "login.html";
                return false;
            }
        }
    });
}


//遍历列表
function build_location_table(result) {
    $("#sosMassage_table tbody").empty();
    var emps = result.extend.pageInfo.list;
    var sosLocatoin = result.extend.location.split("@");
    var sosGunTag = result.extend.gunTag.split("@");
    $.each(emps, function (index, item) {
            // var id=$("<td></td>").append(item.id);
            var gunTag = $("<td></td>").append(sosGunTag[index]).addClass("text-center");
            var deviceNo = $("<td></td>").append(item.deviceNo).addClass("text-center");
            var location = $("<td></td>").append(sosLocatoin[index]).addClass("text-center");
            var sosTime = $("<td></td>").append(new Date(item.sosTime).format("yyyy-MM-dd hh:mm:ss")).addClass("text-center");
            var state = $("<td></td>").append((item.state) == 1 ? "<a href='javascript:void()' onclick=FindAssist('" +item.id+ "','" + item.deviceNo + "','" + sosGunTag[index] + "','" + item.longitude + "','" + item.latitude + "')  style='color: #ff2217'>未处理</a>" : (item.state) == 0 ? "<a href='javascript:void()' onclick=FindAssist('" +item.id+ "','" + item.deviceNo + "','" + sosGunTag[index] + "','" + item.longitude + "','" + item.latitude + "') style='color: #6b8aff'>已处理</a>" : "离位").addClass("text-center findAssist");

            //edit_btn添加上的class标识
            var selectBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil  ")
                    .append("查询"));
            //查询按钮添加最新枪支轨道的Id
            selectBtn.attr("device-location-id", item.deviceLocationId);
            //将两个按钮追加到一个按钮中
            var btnTd = $("<td></td>").append(selectBtn).addClass("text-center");

            //append（追加）方法执行完成以后还是放回原来的元素
            if (item.state == 1) {
                $("<tr></tr>")
                //.append(id)
                    .append(gunTag)
                    .append(deviceNo)
                    .append(location)
                    .append(sosTime)
                    .append(state).addClass("warning")
                    .appendTo("#sosMassage_table tbody");

            } else {
                $("<tr></tr>")
                //.append(id)
                    .append(gunTag)
                    .append(deviceNo)
                    .append(location)
                    .append(sosTime)
                    .append(state).addClass("success")
                    .appendTo("#sosMassage_table tbody");
            }
        }
    );

}

//2、解析并显示分页信息,点击分页要进到下一页。。。。。。
function buid_page_info(result) {
//清空分页信息
    $("#page_info_area").empty();
    /**
     当前  页,总  页,总条  记录,
     */
    $("#page_info_area").append("当前 " + result.extend.pageInfo.pageNum + " 页",
        "总" + result.extend.pageInfo.pages + " 页",
        "总条 " + result.extend.pageInfo.total + " 记录");
    //保存总记录数
    totalRecord = result.extend.pageInfo.total;
    currentPage = result.extend.pageInfo.pageNum;
}

//3、解析显示分页条
function build_page_nac(result) {
//清空分页条信息
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

//构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
//为元素，添加点击翻页的事件
//第一页
        firstPageLi.click(function () {
            to_page(1, null);
        });
//前一页
        prePageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum - 1, result.extend.deviceNo);
        });
    }


    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    var goPage = $("<li></li>").append("<div class='input-group' style='width:100px'>"
        + "<input type='text' id='toPage' style='width: 83px' class='form-control'"
        + "placeholder='选择页码'> <span class='input-group-btn'  >"
        + "<button id='goPageButton' class='btn btn-default' type='button'>搜索</button></span></div>")

    if (result.extend.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        //下一页
        nextPageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum + 1, result.extend.deviceNo);
        });
        //末页
        lastPageLi.click(function () {
            to_page(result.extend.pageInfo.pages, result.extend.deviceNo);
        });
    }


//添加首页和前一页的提示
    ul.append(firstPageLi).append(prePageLi).append(nextPageLi).append(lastPageLi);
//遍历给ul中添加页码提示,12345
    $.each(result.extend.pageInfo.navigatepageNums,
        function (index, item) {

            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            //绑定单击事件
            numLi.click(function () {
                likePhone = result.extend.deviceNo;
                to_page(item, result.extend.deviceNo);

            });
            ul.append(numLi);
        });
//添加下一页和末页的提示
    ul.append(nextPageLi).append(lastPageLi).append(goPage);
//ul加入到nav元素中
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

//清空表单内容和样式
function reset_from(ele) {
//重置表单内容
    $(ele)[0].reset();
//重置表单样式
    $(ele).find("*").removeClass("has-error has-success");
//通过传选择器，找到.help-block，然后将样式清空
    $(ele).find(".help-block").text("");

}

//跳到指定页码
$(document).on('click', '#goPageButton', function () {
    to_page($("#toPage").val(), likePhone);
});

//回车查询事件
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        to_page(1, $("#deviceNos").val());
    }
});
function Openljy() {
    //alert("");
}
//====================================   地图模态框    =========================================
function FindAssist(sosId,deiceNos, lostGun, lng, lag) {
    ModifierSosMessage(sosId);
    //to_page(1, null);
    WarningNumber();
    //iframe窗
    layer.open({
        type: 2,
        title: '离位协助查找',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: 'assist.html?deviceNos='+deiceNos+"&"+'lostGun='+lostGun+"&"+'lng='+lng+"&"+'lag='+lag
    });


    /*  deviceLocation(null, lng + "," + deiceNos, lag);
      $("#findGun").modal({
          backdrop: "static"
      });
      // 百度地图API功能
      var map = new BMap.Map("allmap");
      var point = new BMap.Point(114.034656, 22.616798);
      map.centerAndZoom(point, 5);
      //1、设置地图的样式
      //map.setMapStyle({style:'midnight'});
      //2、设置地图默认的鼠标指针样式
      map.setDefaultCursor("url('bird.cur')");
      //3、启用滚轮放大缩小，默认禁用
      map.enableScrollWheelZoom();
      //4、启用地图惯性拖拽，默认禁用
      map.enableContinuousZoom();
      //5、进行拖拽
      map.enableScrollWheelZoom(true);
      map.disableDragging();     //禁止拖拽

      setTimeout(function () {
          map.enableDragging();   //两秒后开启拖拽
          map.enableInertialDragging();   //两秒后开启惯性拖拽
      }, 2000);
      //设置地图
      map.addControl(new BMap.MapTypeControl({
          mapTypes: BMAP_HYBRID_MAP
          /!* [
           BMAP_HYBRID_MAP,
           BMAP_NORMAL_MAP
           //BMAP_SATELLITE_MAP//卫星地图
       ]*!/
      }));
      //设置地图：使用混合地图
      map.setMapType(BMAP_SATELLITE_MAP);
      //6、 添加带有定位的导航控件
      var navigationControl = new BMap.NavigationControl({
          // 靠左上角位置
          anchor: BMAP_ANCHOR_TOP_LEFT,
          // LARGE类型
          type: BMAP_NAVIGATION_CONTROL_LARGE,
          // 启用显示定位
          enableGeolocation: true
      });
      map.addControl(navigationControl);

      // 添加定位控件
      var geolocationControl = new BMap.GeolocationControl();
      geolocationControl.addEventListener("locationSuccess", function (e) {
          // 定位成功事件
          var address = '';
          address += e.addressComponent.province;
          address += e.addressComponent.city;
          address += e.addressComponent.district;
          address += e.addressComponent.street;
          address += e.addressComponent.streetNumber;
          layer.alert("当前定位地址为：" + address);
      });
      geolocationControl.addEventListener("locationError", function (e) {
          // 定位失败事件
          layer.alert(e.message);
      });
      map.addControl(geolocationControl);
      //6、左下角地图查看空间
      var overviewControl = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: true});
      map.addControl(overviewControl);

//7、添加标注
      function deviceLocation(deviceNos, lng, lag) {
          var label = [];//保存日期
          var value = [];//数量
          var markers = [];//点聚合参数
          var markerClusterer = null;//点聚合参数
          var recentlyDeivce;//最近设备
          var location = "";
          //设备实时状态显示
          $.ajax({
              url: "../deviceLocation/roundOnline",
              type: "POST",
              data: {"deviceNo": deviceNos, "lng": lng, "lag": lag},
              dataType: "json",
              asynec: false,
              success: function (result) {
                  if (result.status == 200) {
                      layer.alert(result.errorMessage);
                      return false;
                  }
                  location = result.extend.location.split("@");
                  $.each(result.extend.onLine, function (i, p) {
                      label[i] = p.longitude;
                      value[i] = p.latitude;
                      // 7.1:创建点
                      var myIcon;
                      if (p.gunState == 0 && p.deviceState == 0) {
                          myIcon = new BMap.Icon('img/normalGun.png', new BMap.Size(68, 75));//这里先不用第三个参数IconOptions
                      } else {
                          myIcon = new BMap.Icon('img/offNormalGun.png', new BMap.Size(68, 75));//这里先不用第三个参数IconOptions
                      }

                      //这就是对象的字面量形式
                      var marker = new BMap.Marker(new BMap.Point(label[i], value[i]), {icon: myIcon});
                      //将标注添加到地图中
                      map.addOverlay(marker);
                      addClickHandler(marker);

                      //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                      function addClickHandler(marker) {
                          marker.addEventListener("click", function (e) {
                                  layer.alert(location[i]);
                                  //alert(p.deviceNo + "--" + p.longitude + "--" + p.latitude);
                                  //deviceLocation(p.deviceNo, p.longitude, p.latitude);
                              }
                          );
                      }

                      <!--框-->
                      var gunOffNormal = '<span style=" background:url(img/red.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">离位</span>';
                      var unused = '<span style=" background:url(img/red.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">未使用</span>';
                      var accompany = '<span style=" background:url(img/green.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">随行</span>';
                      var onLine = '<span style=" background:url(img/green.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">在线</span>';
                      var offLine = '<span style=" background:url(img/red.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">离线</span>';
                      var assist = '<span style=" background:url(img/orange.png); background-repeat:no-repeat; background-position:left; padding-left: 16px; margin-right: 22px">协助</span>';

                      var tag;//浮框
                      if (result.extend.number - 2 == i) {
                          recentlyDeivce = p.deviceNo;
                          tag = "<div class='tag' style='padding-left:10px;padding-top:6px;line-height:20px;width:150px; height:173px; border:1px solid #ccc; position:relative; border-radius:8px; background-color:#FFF;'>"
                              + "警员编号：" + recentlyDeivce + "</br>"
                              + "枪号：" + p.gunTag + "</br>"
                              + "枪支：" + p.gunModel + "</br>"
                              + "电话：" + p.mobile + "</br>"
                              + "<span style='color: #ff2217'>最近距离：" + p.juli + "," + (result.extend.number - i - 1) + "</br></span>"
                              + ((p.gunState) == 0 ? accompany : (p.gunState) == 1 ? unused : gunOffNormal)
                              + ((p.deviceState) == 0 ? onLine : (p.deviceState) == 1 ? unused : (p.deviceState) == 2 ? offLine : assist) + "</br>" +
                              "<button type='button' onclick=IsRequestFind('" + deiceNos + "','" + p.deviceNo + "','" + lostGun + "') id='findAssist' class='btn btn-primary btn-sm' style='margin-top: 6px; margin-left: 25px;font-size: 14px;padding-top: 6px;'>前去查找</button></div>"
                          ;
                      } else {
                          if (result.extend.number - 1 == i) {
                              tag = "<div class='tag2' style='padding-left:10px;padding-top:6px;line-height:20px;width:150px; height:150px; border:1px solid #f8b400; position:relative; border-radius:8px; background-color:#f8b400;'>"
                                  + "警员编号：" + p.deviceNo + "</br>"
                                  + "枪号：" + p.gunTag + "</br>"
                                  + "枪支：" + p.gunModel + "</br>"
                                  + "电话：" + p.mobile + "</br>"
                                  + "<span style='color: #ff2589'>最近警员：" + recentlyDeivce + "</br></span>"
                                  + ((p.gunState) == 0 ? accompany : (p.gunState) == 1 ? unused : gunOffNormal)
                                  + ((p.deviceState) == 0 ? onLine : (p.deviceState) == 1 ? unused : (p.deviceState) == 2 ? offLine : assist) + "</br>" +
                                  "</div>"
                              ;
                          } else {
                              tag = "<div class='tag' style='padding-left:10px;padding-top:6px;line-height:20px;width:150px; height:173px; border:1px solid #ccc; position:relative; border-radius:8px; background-color:#FFF;'>"
                                  + "警员编号：" + p.deviceNo + "</br>"
                                  + "枪号：" + p.gunTag + "</br>"
                                  + "枪支：" + p.gunModel + "</br>"
                                  + "电话：" + p.mobile + "</br>"
                                  + "<span style='color: #ff2217'>距离：" + p.juli + "," + (result.extend.number - 1 - i) + "</br></span>"
                                  + ((p.gunState) == 0 ? accompany : (p.gunState) == 1 ? unused : gunOffNormal)
                                  + ((p.deviceState) == 0 ? onLine : (p.deviceState) == 1 ? unused : (p.deviceState) == 2 ? offLine : assist) + "</br>" +
                                  "<button type='button' onclick=IsRequestFind('" + deiceNos + "','" + p.deviceNo + "','" + lostGun + "') id='findAssist' class='btn btn-primary btn-sm' style='margin-top: 6px; margin-left: 25px;font-size: 14px;padding-top: 6px;'>前去查找</button></div>"
                              ;
                          }

                      }


                      /!* if (result.extend.number - 2 == i) {
                           //7.2 添加字体
                           var labela = new BMap.Label(tag, {offset: new BMap.Size(-50, -185)});
                           labela.setStyle({
                               color: "#302e18",
                               width: "100%",
                               height: "100%",
                               fontSize: "14px",
                           });
                           marker.setLabel(labela);
                       }*!/

                      //创建标注的hover事件
                      marker.addEventListener("mouseover", function (e) {
                          //7.2 添加字体
                          var labela;
                          if (result.extend.number - 1 == i) {
                              labela = new BMap.Label(tag, {offset: new BMap.Size(-50, -160)});
                          } else {
                              labela = new BMap.Label(tag, {offset: new BMap.Size(-50, -185)});
                          }
                          labela.setStyle({
                              color: "#302e18",
                              width: "100%",
                              height: "100%",
                              fontSize: "14px",
                          });
                          marker.setLabel(labela);
                      });
                      marker.addEventListener("mouseout", function (e) {
                          var label = this.getLabel();
                          setTimeout(function () {
                              label.setContent("");//设置标签内容为空
                              label.setStyle({border: "none", width: "0px", padding: "0px"});//设置标签边框宽度为0
                          }, 2000);
                      });

                      //7.4 将经纬坐标划入地图
                      label[i] = new BMap.Point(label[i], value[i]);

                      //7.3 将坐标放入到点聚合中
                      markers.push(marker);
                      //marker.addEventListener("click", attribute);

                      //7.5 让所有点在视野范围内
                      map.setViewport(label);

                  });
                  //7.3 最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
                  markerClusterer = new BMapLib.MarkerClusterer(map, {markers: markers});


                  //获取覆盖物位置
                  function attribute(e) {
                      var p = e.target;
                      //layer.alert("marker的位置是" + p.getPosition().lng + "," + p.getPosition().lat);
                  }
              }, error: function (result) {
                  if (result.responseText == "IsAjax") {
                      window.location.href = "login.html";
                      return false;
                  }
              }
          });
      }*/
}

//================================   请求协助查询   ==============================
function IsRequestFind(lostDeviceNo, assistDeviceNo, lostGun) {
    /*alert(lostDeviceNo+"--"+assistDeviceNo+"--"+lostGun);*/
//询问框
    layer.confirm('确定要该【' + assistDeviceNo + '】去协助寻找？', {
        btn: ['确定', '取消'] //按钮
    }, function (index) {
        RequestFind(lostDeviceNo, assistDeviceNo, lostGun);
    }, function () {
        layer.msg('已取消操作', {
            time: 500, //20s后自动关闭
            // btn: ['明白了', '知道了']
        });
    });
}

//发送消息
function RequestFind(lostDeviceNo, assistDeviceNo, lostGun) {
    $.ajax({
        url: "../mission/findMinistrantAndLoseMatching/" + assistDeviceNo + "/" + lostDeviceNo + "/" + lostGun,
        type: "POST",
        dataType: "JSON",
        async: 'true',
        success: function (result) {
            if (result.status == 1) {
                layer.msg('消息发送中...', {
                    icon: 1
                });
            } else {
                layer.msg('消息发送失败', {
                    icon: 1
                });
            }
        },error: function (result) {
            if (result.responseText == "IsAjax") {
                window.location.href = "login.html";
                return false;
            }
        }
    })
}
//修改
function ModifierSosMessage(id) {
    $.ajax({
        url:"../sosMission/modifierSosMessageState/"+id,
        type:"PUT",
        async: 'true',
        success:function (result) {
        }
    })
}
//登出
function LogOut(){
    LogOuts();
}





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
//***************************    离位警告信息    ************************
        var json = JSON.parse(message.payloadString);
        if (json.messageType == "15") {
            to_page(1, null);
        }
    }



    var client;
    var r = Math.round(Math.random()*Math.pow(10,5));
    var d = new Date().getTime();
    var cid = r.toString() + "-" + d.toString()

    //112.74.51.194 1884  120.76.156.120 61614
    client = new Messaging.Client("120.76.156.120", 61614, cid);
    client.onConnect = onConnect;
    client.onMessageArrived = onMessageArrived;

    // client.connectOptions=onFeconnect;
    client.onConnectionLost = onConnectionLost;
    client.connect(options);
    //client.connect({onSuccess: onConnect, onFailure: onFailure});

});