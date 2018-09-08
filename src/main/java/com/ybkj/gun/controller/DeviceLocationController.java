package com.ybkj.gun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.baiduMap.BaiDuUtil;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.model.OptimizeDeviceLocation;
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
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

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

        //System.out.println("--------7777----------"+deviceNo+",,"+split[0]+".."+split[1]);
        if(split[0]!="" && split[1]!="" && lag!="" && lng!="" && deviceNo==""){
            DeviceGun deviceGuns = deviceGunMapper.selectGunAndDeviceLocationOne(split[1]);
            if(deviceGuns!=null) {
                System.out.println("-----------------" + deviceGuns.toString());
                DeviceLocation deviceLocation = new DeviceLocation();
                deviceLocation.setLongitude(split[0]);
                deviceLocation.setLatitude(lag);
                deviceLocation.setDeviceNo(split[1]);
                deviceLocation.setGunModel(deviceGuns.getGunModel());
                deviceLocation.setGunTag(deviceGuns.getGunTag());
                deviceLocation.setMobile(deviceGuns.getMobile());
                deviceLocation.setDeviceState(deviceGuns.getDeviceState());
                deviceLocation.setGunState(deviceGuns.getGunState());
                List<DeviceLocation> deviceLocations = deviceLocationSerivce.findRoundOnline(null, split[0], lag);
                deviceLocations.add(deviceLocation);

                for (DeviceLocation location1 : deviceLocations) {
                    location += BaiDuUtil.getAddress(location1.getLongitude(), location1.getLatitude()) + "@";
                }
                baseModel.setErrorMessage("==================查询所有在线的警员和枪支==================");
                baseModel.add("onLine", deviceLocations);
                baseModel.add("number", deviceLocations.size()).add("location", location)
                .add("sizeLocation",deviceLocations.size());
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("该设备已入库，暂无数据");
            }
        }else {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力修改数据!");
        }
        return baseModel;
    }

    /**
     * 协助查找，周围最近5个警员
     * @param deviceNo
     * @param lng
     * @param lag
     * @return
     */
    @ApiOperation(value = "协助查找，周围最近5个警员",notes = "协助查找", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceNo", value = "009886", required = false, paramType = "query"),
            @ApiImplicitParam(name = "lng", value = "114.41408", required = true, paramType = "query"),
            @ApiImplicitParam(name = "lag", value = "30.498694", required = true, paramType = "query")
            })
    @RequestMapping(value = "/optimizeAssistRoundOnline",method = RequestMethod.GET)
    public BaseModel optimizeAssistRoundOnline(@RequestParam(value="deviceNo",required=false)String deviceNo, @RequestParam(value="lng",required=false)String lng, @RequestParam(value="lag",required=false)String lag,@RequestParam(value="lostGun",required=false)String lostGun) throws Exception {
        System.out.println("---------"+deviceNo+"---"+lng+"---"+lag+"---"+lostGun);
        BaseModel baseMode=new BaseModel();
        baseMode=deviceLocationSerivce.optimizeRoundOnline(deviceNo,lng,lag,lostGun);
        return baseMode;
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
        String location="";
        //System.out.println("startTime-"+startTime+"endTime-"+endTime+"deviceNo-"+deviceNo);
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
        for (DeviceLocation deviceLocation : deviceLocations) {
            location+=BaiDuUtil.getAddress(deviceLocation.getLongitude(),deviceLocation.getLatitude())+"@";
            //System.out.println("-----000----0000-----0000---"+deviceLocation.getDeviceNo());
        }
       /* Iterator<DeviceLocation> listIt = deviceLocations.iterator();
        while (listIt.hasNext()) {
            DeviceLocation next =  listIt.next();

        }*/
        log.info("*************************** 查询时间段内的枪支轨迹  ******************************");
        if(deviceLocations.size()>0){
            baseModel.add("deviceLocations",deviceLocations).add("numberLocations",deviceLocations.size()).add("location",location);
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("暂无数据");
        }
        return baseModel;
    }

    /**
     * 优化：查询时间段内的枪支轨迹
     * @param deviceNo
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation(value = "优化：查询时间段内的枪支轨迹",notes = "优化轨迹", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceNo", value = "8651234216", required = false, paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "114.41408", required = true, paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "30.498694", required = true, paramType = "query")
    })
    @RequestMapping(value = "optimizeQueryTheTrajectory",method = RequestMethod.GET)
    public BaseModel optimizeQueryTheTrajectory(@RequestParam(value = "deviceNo",required = false) String deviceNo,@RequestParam(value = "startTime",required = false)String startTime,@RequestParam(value = "endTime",required = false)String endTime) throws Exception {
        System.out.println("----------------"+deviceNo+"====="+startTime+"-----"+endTime);
        BaseModel baseModel=new BaseModel();
        if(deviceNo.equals("")){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请输入枪号");
        }else if(startTime.equals("")&& !endTime.equals("")){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请选择开始时间");
        }else if(!startTime.equals("") && endTime.equals("")){
            //System.out.println("---------------"+DataTool.dateToString(new Date()));
            baseModel=deviceLocationSerivce.optimizeDeviceLocationTrajectory(deviceNo,startTime,DataTool.dateToStrings());
        }else if(!(startTime.equals("")&& endTime.equals(""))){
            baseModel=deviceLocationSerivce.optimizeDeviceLocationTrajectory(deviceNo,startTime,endTime);
        }else {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请选择查询时间");
        }
        return baseModel;
    }
    /**
     * 优化sql,显示地图上的实时数据
     */
    @ApiOperation(value = "优化sql,显示地图上的实时数据",notes = "map", httpMethod = "GET")
    @RequestMapping(value = "optimizeMap",method = RequestMethod.GET)
    public BaseModel optimizeMap(@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        baseModel=deviceLocationSerivce.optimizeDeviceLocation(deviceNo);
        return baseModel;
    }

    /**
     * 优化sql,枪列表的实时位置信息
     */
    @ApiOperation(value = "优化sql,枪列表的实时位置信息",notes = "枪列表的实时", httpMethod = "GET")
    @RequestMapping(value = "optimizeGunLocatins",method = RequestMethod.GET)
    public BaseModel optimizeGunLocatins(@RequestParam(value="pn",defaultValue="1",required=false) Integer pn,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize,@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        baseModel= deviceLocationSerivce.optimizeGunLocation(pn,pageSize,deviceNo);

       /* String location="";
        PageHelper.startPage(pn, 5);
        List<OptimizeDeviceLocation> optimizeDeviceLocations = deviceLocationSerivce.optimizeGunLocation(deviceNo);
        PageInfo<OptimizeDeviceLocation> page = new PageInfo<OptimizeDeviceLocation>(optimizeDeviceLocations, 5);

        for (OptimizeDeviceLocation optimizeDeviceLocation : optimizeDeviceLocations) {
            location+=BaiDuUtil.getAddress(optimizeDeviceLocation.getLongitude(),optimizeDeviceLocation.getLatitude())+"@";
        }

        System.out.println("-----------------------00----------"+optimizeDeviceLocations.size());
        System.out.println("-----------------------00----------"+page.getPages());

        baseModel.add("pageInfo", page).add("deviceNo", deviceNo).add("location", location);*/

        return baseModel;
    }


    /**
     * 实时显示当前用户的轨迹，默认是10-20分钟
     * @param deviceNo
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/realTimeDayLocus",method = RequestMethod.GET)
    public BaseModel realTimeDayLocus(@RequestParam(value = "deviceNo",required = false) String deviceNo,@RequestParam(value = "startTime",required = false)String startTime,@RequestParam(value = "endTime",required = false)String endTime,@RequestParam(value = "state",required = true)Integer state){
        BaseModel baseModel=new BaseModel();
        //1、如果state=1，默认是10分钟
        if(state==1){

        }
        return null;
    }
}
