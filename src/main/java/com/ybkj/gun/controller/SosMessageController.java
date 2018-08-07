package com.ybkj.gun.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.baiduMap.BaiDuUtil;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.SosMessage;
import com.ybkj.gun.service.SosMessageSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *@Description:  功能描述（告警功能模块）
 *@Author:       刘家义
 *@CreateDate:   2018/8/4 16:44
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/8/4 16:44
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Api(value = "/",description = "告警消息功能")
@SuppressWarnings("all")
@RestController
@RequestMapping("/sosMission")
public class SosMessageController {

    @Autowired
    SosMessageSerivce sosMessageSerivce;
    @Autowired
    GunMapper gunMapper;


    /**
     * 对已经拿枪的警员，进行实时监测
     * @param pn
     * @param deviceNo
     * @return
     */
    @ApiOperation(value = "分页查询告警信息",notes = "告警实时", httpMethod = "GET")
    @RequestMapping(value = "/realTimeSosMassage",method = RequestMethod.GET)
    public BaseModel realTimeSosMassage(@RequestParam(value="pn",defaultValue="1",required=false) Integer pn,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize,@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        List<String> locationList=new ArrayList<>();
        String location="";
        String gunTag="";
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<SosMessage> sosMassages=sosMessageSerivce.selectSosMassageByDeviceNo(deviceNo);
        for (SosMessage sos : sosMassages) {
            location+= BaiDuUtil.getAddress(sos.getLongitude(),sos.getLatitude())+"@";
            gunTag+=gunMapper.selectGunByBluetoothMac(sos.getGunMac()).getGunTag()+"@";
        }
        PageInfo<SosMessage> page = new PageInfo<SosMessage>(sosMassages,5);
        baseModel.add("pageInfo",page).add("deviceNo", deviceNo);
        baseModel.add("location", location).add("gunTag",gunTag);
        return baseModel;
    }

}