package com.ybkj.gun.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.baiduMap.BaiDuUtil;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.SosMessage;
import com.ybkj.gun.service.SosMessageSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
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
        Integer state=0;
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<SosMessage> sosMassages=sosMessageSerivce.selectSosMassageByDeviceNo(deviceNo);
        for (SosMessage sos : sosMassages) {
            location+= BaiDuUtil.getAddress(sos.getLongitude(),sos.getLatitude())+"@";
            gunTag+=gunMapper.selectGunByBluetoothMac(sos.getGunMac()).getGunTag()+"@";
            if(sos.getState()==1){
                state+=1;
            }
        }
        PageInfo<SosMessage> page = new PageInfo<SosMessage>(sosMassages,5);
        baseModel.add("pageInfo",page).add("deviceNo", deviceNo);
        baseModel.add("location", location).add("gunTag",gunTag).add("number",state);
        return baseModel;
    }


    /**
     * 根据设备号和状态为1，修改状态为0
     * @param DeivceNo
     * @param state
     * @return
     */
    @RequestMapping(value = "/modifierSosMessageState/{id}",method = RequestMethod.PUT)
    public BaseModel modifierSosMessageState(@PathVariable(value = "id", required = true) Integer id) throws Exception {
        BaseModel baseModel=new BaseModel();
        BaseModel sosMessage=sosMessageSerivce.updateSosMassageById(id);
        if(sosMessage.getStatus()== StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage(sosMessage.getErrorMessage());
        }else {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage(sosMessage.getErrorMessage());
        }
        return baseModel;
    }

    /**
     * 统计警告数
     * @return
     */
    @RequestMapping(value = "/statisticsWarningNumber",method = RequestMethod.POST)
    public BaseModel statisticsWarningNumber() throws Exception {
        BaseModel baseModel=new BaseModel();
        Integer  warningNumber=sosMessageSerivce.findWarningNumber(1);
        log.info("****************统计警告数****************"+warningNumber);
        baseModel.add("warningNumber",warningNumber);
        return baseModel;
    }

}
