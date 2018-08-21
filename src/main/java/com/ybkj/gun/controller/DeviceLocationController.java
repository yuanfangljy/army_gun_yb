package com.ybkj.gun.controller;

import com.ybkj.common.baiduMap.BaiDuUtil;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Slf4j
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
    @RequestMapping(value = "/inquireDeviceLocationTrajectory",method= RequestMethod.GET,
            produces = "application/json")
    public BaseModel inquireDeviceLocationTrajectory(String gunTag,String startTime,String endTime) throws Exception {
        BaseModel baseModel=new BaseModel();
        Map<String, Object> map=new HashMap<String, Object>();
        Date stTime = DataTool.stringToDate(startTime);
        Date enTime = DataTool.stringToDate(endTime);
        map.put("gunTag",gunTag);
        map.put("beginTime", stTime);
        map.put("endTime", enTime);

       /* //根据枪号，查询蓝牙号
        Gun gun = gunMapper.selectGunByGunTag(gunTag);
        if(gun==null){
            map.put("gunTag",null);
        }else{
            //蓝牙号和为出库状态：查询设备号
            DeviceGun deviceGuns=deviceGunMapper.selectDeviceGunByMacAndState(gun.getBluetoothMac(),0);
            map.put("gunTag",gunTag);
        }*/
        List<DeviceLocation> deviceLocations=deviceLocationSerivce.findDeviceLocationTrajectory(map);
        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.add("deviceLocations",deviceLocations);
        return baseModel;
    }


    /**
     * 周围在线警员
     * @param deviceNo
     * @param lng
     * @param lag
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roundOnline",method = RequestMethod.GET)
    public BaseModel roundOnline(@RequestParam(value="deviceNo",required=false)String deviceNo, @RequestParam(value="lng",required=false)String lng, @RequestParam(value="lag",required=false)String lag) throws Exception {
        BaseModel baseModel=new BaseModel();
        String location="";
        String[] split = lng.split(",");
        //System.out.println("------------------"+deviceNo+",,"+split[0]+".."+split[1]);
        if(split[0]!="" && split[1]!="" && lag!="" && lng!="" && deviceNo==""){
            DeviceGun deviceGuns = deviceGunMapper.selectGunAndDeviceLocationOne(split[1]);
            DeviceLocation deviceLocation=new DeviceLocation();
            deviceLocation.setLongitude(split[0]);
            deviceLocation.setLatitude(lag);
            deviceLocation.setDeviceNo(split[1]);
            deviceLocation.setGunModel(deviceGuns.getGunModel());
            deviceLocation.setGunTag(deviceGuns.getGunTag());
            deviceLocation.setMobile(deviceGuns.getMobile());
            deviceLocation.setDeviceState(deviceGuns.getDeviceState());
            deviceLocation.setGunState(deviceGuns.getGunState());
            List<DeviceLocation> deviceLocations=deviceLocationSerivce.findRoundOnline(deviceNo,split[0],lag);
            deviceLocations.add(deviceLocation);

            for (DeviceLocation location1 : deviceLocations) {
                location+= BaiDuUtil.getAddress(location1.getLongitude(),location1.getLatitude())+"@";
            }
            baseModel.setErrorMessage("==================查询所有在线的警员和枪支==================");
            baseModel.add("onLine", deviceLocations);
            baseModel.add("number",deviceLocations.size()).add("location",location);
        }else {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力修改数据!");
        }
        return baseModel;
    }

    /**
     * 查询时间段内的枪支轨迹
     * @param deviceNo
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation(value = "查询时间段内的枪支轨迹",notes = "枪支轨迹", httpMethod = "GET")
    @RequestMapping(value = "queryTheTrajectory",method = RequestMethod.GET)
    public BaseModel queryTheTrajectory(@RequestParam(value = "deviceNo",required = false) String deviceNo,@RequestParam(value = "startTime",required = false)String startTime,@RequestParam(value = "endTime",required = false)String endTime) throws ParseException {
        BaseModel baseModel=new BaseModel();
        Map<String,Object> map=new HashMap<>();
        System.out.println("startTime-"+startTime+"endTime-"+endTime+"deviceNo-"+deviceNo);
        if(startTime.equals("") || endTime.equals("") || deviceNo.equals("")){
            map.put("gunTag",0);
            map.put("beginTime",new Date());
            map.put("endTime",new Date());
        }else{
            map.put("gunTag",deviceNo);
            map.put("beginTime",DataTool.stringToDate(startTime));
            map.put("endTime",DataTool.stringToDate(endTime));
        }
        List<DeviceLocation> deviceLocations=deviceLocationSerivce.findDeviceLocationTrajectory(map);
        log.info("*************************** 查询时间段内的枪支轨迹  ******************************");
        if(deviceLocations.size()>0){
            baseModel.add("deviceLocations",deviceLocations).add("numberLocations",deviceLocations.size());
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("暂无数据");
        }
        return baseModel;
    }
}
