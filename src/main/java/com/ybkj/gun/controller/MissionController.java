package com.ybkj.gun.controller;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Mission;
import com.ybkj.gun.service.MissionSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *1、在创建协助信息的时候，要判断该协助人员的状态是不是在线（或者不是正在执行任务中，在missionz中查找状态）
 *2、将丢枪的信息封装成json字符串，存在在boby字段中：
 *   Ministrant:协助设备号(警员编号)
 *   lost：设备号，蓝牙号，经纬度，电话，时间；（要从location_device最后一条信息中查找且状态是出库中）
 *@Description:  功能描述（离位报警协助查找相关功能）
 *@Author:       刘家义
 *@CreateDate:   2018/8/4 12:36
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/8/4 12:36
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Api(value = "/",description = "离位报警协助功能")
@SuppressWarnings("all")
@RestController
@RequestMapping("/mission")
public class MissionController {
    @Autowired
    MissionSerivce missionSerivce;

    @ApiOperation(value = "离位报警协助功能",notes = "协助", httpMethod = "PUT")
    @RequestMapping(value = "/findMinistrantAndLoseMatching",method = RequestMethod.PUT)
    public BaseModel findMinistrantAndLoseMatching(Mission mission) throws Exception {
        BaseModel baseModel=new BaseModel();
        BaseModel missions = missionSerivce.insertMission(mission);
        if(missions.getStatus()== StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage(missions.getErrorMessage());
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage(missions.getErrorMessage());
        }
        return baseModel;
    }

}
