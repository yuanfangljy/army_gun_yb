package com.ybkj.gun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.baiduMap.BaiDuUtil;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.service.DeviceLocationSerivce;
import com.ybkj.gun.service.impl.DeviceGunServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 1、先有入库才有出库
 * 2、入库的时候，是从出库表中查数据（只能查到出库状态的数据，和出库和入库时间没有都存在的时候），进行入库；如果没有就不能进行入库
 * 3、出库的时候，要拿到枪支（gun）的信息和警员编号（device）的信息
 * 4、还要进行处理：如果同一把枪被同一个警员领取的多少，怎么样？
 *    1、我才用出库入库时间来判断，如果都存在，就进行再次添加
 *    2、在出库的时候要看枪支是否被当前人使用都，
 *@Description:  功能描述（枪支出入库管理）
 *@Author:       刘家义
 *@CreateDate:   2018/7/27 18:43
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/27 18:43
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Slf4j
@SuppressWarnings("all")
@Api(value = "/",description = "设备和枪的状态")
@RestController
@RequestMapping("/deviceGun")
public class DeviceGunController {
    @Autowired
    DeviceGunServiceImpl deviceGunService;
    @Autowired
    GunMapper gunMapper;
    @Autowired
    DeviceGunMapper deviceGunMapper;

    /**
     * 统计设备离线数
     * @return
     */
    @ApiOperation(value = "统计设备离线数",notes = "设备线位", httpMethod = "GET")
    @RequestMapping(value = "/statisticsDeviceOffLine",method = RequestMethod.GET)
    public BaseModel statisticsDeviceOffLine() throws Exception {
        BaseModel baseModel=new BaseModel();
        Integer deviceDislocation=deviceGunService.findDeviceOffLine();
        log.info("==================统计设备离线数==================");
        baseModel.add("deviceDislocation",deviceDislocation);
        return baseModel;
    }

    /**
     * 统计：枪支离位数
     * @return
     */
    @ApiOperation(value = "统计枪支离位数",notes = "枪支离位", httpMethod = "GET")
    @RequestMapping(value = "/statisticsGunDislocation",method = RequestMethod.GET)
    public BaseModel statisticsGunDislocation() throws Exception {
        BaseModel baseModel=new BaseModel();
        Integer gunDislocation=deviceGunService.findGunDislocation();
        log.info("==================统计枪支离位数==================");
        baseModel.add("gunDislocation",gunDislocation);
        return baseModel;
    }


    /**
     * 全部已领装备的士兵实时状态
     * @param pn
     * @param deviceNo
     * @return
     */
    @RequestMapping(value = "/realTimeDeviceAndGun",method = RequestMethod.GET)
    public BaseModel realTimeDeviceAndGun(@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        String location="";
        List<DeviceGun> devices=deviceGunService.findGunAndDeviceLocation(deviceNo);
        for (DeviceGun device : devices) {
            location+=BaiDuUtil.getAddress(device.getDeviceLocationLongitude(),device.getDeviceLocationLatirude())+"@";
        }
        log.info("==================全部以领装备的士兵实时状态==================");
        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.add("devices",devices);
        baseModel.add("location", location);
        return baseModel;
    }

    /**
     * 对已经拿枪的警员，进行实时监测
     * @param pn
     * @param deviceNo
     * @return
     */
    @ApiOperation(value = "分页查询枪支实时位置信息",notes = "枪支实时", httpMethod = "GET")
    @RequestMapping(value = "/realTimeDispalyDeviceGun",method = RequestMethod.GET)
    public BaseModel realTimeDispalyDeviceGun(@RequestParam(value="pn",defaultValue="1",required=false) Integer pn,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize,@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        List<String> locationList=new ArrayList<>();
        String location="";
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<DeviceGun> guns=deviceGunService.findGunAndDeviceLocation(deviceNo);

        for (DeviceGun gun : guns) {
            location+=BaiDuUtil.getAddress(gun.getDeviceLocationLongitude(),gun.getDeviceLocationLatirude())+"@";
        }
        //用PageInfo对查询结果进行包装，只需要将pageInfo交给页面就行了
        //封装了，详细的分页信息，包括我们查询出来的数据,传入连续显示的页数
        PageInfo<DeviceGun> page = new PageInfo<DeviceGun>(guns,5);
        baseModel.setErrorMessage("==================分页查询枪支实时位置信息==================");
        baseModel.add("pageInfo",page).add("deviceNo", deviceNo);
        baseModel.add("location", location);
        return baseModel;
    }

    /**
     * 查询所有在线的警员和枪支
     * @return
     */
    @RequestMapping(value = "/inquireDeviceAndGunOnline",method = RequestMethod.GET)
    public BaseModel inquireDeviceAndGunOnline(@RequestParam(value="deviceNo",required=false)String deviceNo,@RequestParam(value="lng",required=false)String lng,@RequestParam(value="lag",required=false)String lag) throws Exception {
        //System.out.println("========="+deviceNo+"--"+lng+"--"+lag);
        BaseModel baseModel=new BaseModel();
        List<DeviceGun> onLine = deviceGunService.findGunAndDeviceLocationAllOnLine(deviceNo);
        for (DeviceGun deviceGun : onLine) {
            System.out.println("-------"+deviceGun);
        }
        log.info("==================查询所有在线的警员和枪支==================");
        baseModel.add("onLine", onLine);
        return baseModel;
    }

    /**
     * 枪支出库
     * @param deviceGun
     * @param status
     * @return
     */
    @RequestMapping(value = "/gunDelivery/{status}/{endtime}",method = RequestMethod.POST)
    public BaseModel gunDelivery(DeviceGun deviceGun,@PathVariable(value = "status",required = true) Integer status,@PathVariable(value = "endtime",required = true) String endtime) throws Exception {
        deviceGun.setTemperanceTime(new DataTool().stringToDate(endtime));
        BaseModel baseModel=new BaseModel();
        if(status==0) {
            BaseModel storage = deviceGunService.addGunDelivery(deviceGun, status);
            if (storage.getStatus() == StatusCodeEnum.GUN_OUTPUT.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.GUN_OUTPUT.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.Fail.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.VIOLENTACTION.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }
        return baseModel;
    }

    /**
     * 枪支入库
     * @param deviceGun
     * @param status
     * @return
     */
    @RequestMapping(value = "/gunStorage/{status}",method = RequestMethod.PUT)
    public BaseModel gunStorage(DeviceGun deviceGun,@PathVariable(value = "status",required = true) Integer status) throws Exception {
        BaseModel baseModel=new BaseModel();
        if(status==1) {
            BaseModel storage = deviceGunService.updategunStorage(deviceGun, status);
            if (storage.getStatus() == StatusCodeEnum.GUN_STORAGE.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.Fail.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.VIOLENTACTION.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }
        return baseModel;
    }
    /**
     * 枪支入库:列表形式
     * @param deviceGun
     * @param status
     * @return
     */
    @RequestMapping(value = "/gunStorageList/{id}",method = RequestMethod.PUT)
    public BaseModel gunStorageList(@PathVariable(value = "id",required = true) Integer id) throws Exception {
       // System.out.println("000000000000:---"+id);
        BaseModel baseModel=new BaseModel();
        DeviceGun deviceGun = deviceGunMapper.selectByPrimaryKey(id);
        if(deviceGun==null){
            baseModel.setStatus(StatusCodeEnum.VIOLENTACTION.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
            return baseModel;
        }
        if(id!=null) {
            BaseModel storage = deviceGunService.updategunStorage(deviceGun, 1);
            if (storage.getStatus() == StatusCodeEnum.GUN_STORAGE.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.Fail.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            } else if (storage.getStatus() == StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage(storage.getErrorMessage());
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.VIOLENTACTION.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }
        return baseModel;
    }

    /**
     * 枪支已经出库的列表显示
     * @return
     */
    @ApiOperation(value = "枪支已经出库的列表显示",notes = "出库的列表", httpMethod = "GET")
    @RequestMapping(value = "/inventoryList",method = RequestMethod.GET)
    public BaseModel inventoryList(@RequestParam(value="pn",defaultValue="1",required=false) Integer pn,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize,@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        baseModel=deviceGunService.findInventoryList(pn,pageSize,deviceNo);
        return baseModel;
    }

}
