package com.ybkj.gun.controller;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.DeviceLocationSerivce;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description:  功能描述（设备实时地点：轨迹查询）
 *@Author:       刘家义
 *@CreateDate:   2018/8/3 16:28
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/8/3 16:28
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
@Api(value = "/",description = "设备地点")
@RestController
@RequestMapping("/deviceLocation")
public class DeviceLocationController {

    @Autowired
    DeviceLocationSerivce deviceLocationSerivce;
    @Autowired
    GunMapper gunMapper;
    @Autowired
    DeviceGunMapper deviceGunMapper;
    /**
     * 轨迹查询
     * @param gunTag
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/inquireDeviceLocationTrajectory",method= RequestMethod.POST,
            produces = "application/json")
    public BaseModel inquireDeviceLocationTrajectory(String gunTag,String startTime,String endTime) throws Exception {
        BaseModel baseModel=new BaseModel();
        Map<String, Object> map=new HashMap<String, Object>();
        Date stTime = DataTool.stringToDate(startTime);
        Date enTime = DataTool.stringToDate(endTime);
        map.put("beginTime", stTime);
        map.put("endTime", enTime);
        //根据枪号，查询蓝牙号
        Gun gun = gunMapper.selectGunByGunTag(gunTag);
        if(gun==null){
            map.put("deviceNo",null);
        }else{
            //蓝牙号和为出库状态：查询设备号
            DeviceGun deviceGuns=deviceGunMapper.selectDeviceGunByMacAndState(gun.getBluetoothMac(),0);
            map.put("deviceNo",deviceGuns.getDeviceNo());
        }
        List<DeviceLocation> deviceLocations=deviceLocationSerivce.selectDeviceLocationTrajectory(map);
        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.add("deviceLocations",deviceLocations);
        return baseModel;
    }
}
