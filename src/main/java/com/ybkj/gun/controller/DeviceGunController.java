package com.ybkj.gun.controller;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.impl.DeviceGunServiceImpl;
import com.ybkj.gun.service.impl.DeviceServiceImpl;
import com.ybkj.gun.service.impl.GunServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;


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
@SuppressWarnings("all")
@Api(value = "/",description = "设备和枪的状态")
@RestController
@RequestMapping("/deviceGun")
public class DeviceGunController {
    @Autowired
    DeviceGunServiceImpl deviceGunService;
    @Autowired
    GunMapper gunMapper;


    /**
     * 枪支出库
     * @param deviceGun
     * @param status
     * @return
     */
    @RequestMapping(value = "/gunDelivery/{status}",method = RequestMethod.POST)
    public BaseModel gunDelivery(DeviceGun deviceGun,@PathVariable(value = "status",required = true) Integer status) throws Exception {
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
    @RequestMapping(value = "/gunStorage/{status}",method = RequestMethod.POST)
    public BaseModel gunStorage(DeviceGun deviceGun,@PathVariable(value = "status",required = true) Integer status) throws Exception {
        BaseModel baseModel=new BaseModel();
        if(status==1) {
            BaseModel storage = deviceGunService.updategunStorage(deviceGun, status);
            if (storage.getStatus() == StatusCodeEnum.GUN_STORAGE.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage("入库成功!");
            } else if (storage.getStatus() == StatusCodeEnum.Fail.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("枪号与警员号，不匹配，请重新审查");
            } else if (storage.getStatus() == StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage("该枪支不存在");
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.VIOLENTACTION.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }
        return baseModel;
    }
















    @ApiOperation(value ="枪支出入库",notes = "枪支状态")
    @RequestMapping(value = "/amendDeviceGunStatus",method = RequestMethod.POST)
    public BaseModel amendDeviceGunStatus(@RequestParam(value="gunTag",required=false) String gunTag,@RequestParam(value="deviceNo",required=false) String deviceNo,@RequestParam(value="status",required=false) Integer status,@RequestParam(value="updateTime",required=false) String updateTime) throws Exception {
        BaseModel baseModel=new BaseModel();
        DeviceGun deviceGun=new DeviceGun();
        if(status==0){
            deviceGun.setDeviceNo(deviceNo);
            Gun gun = gunMapper.selectGunByGunTag(gunTag);
            deviceGun.setGunMac(gun.getBluetoothMac());
            deviceGun.setCreateTime(new Date());
            BaseModel deviceGunByStatus = deviceGunService.updateDeviceGunByStatus(deviceGun,status);
            if (deviceGunByStatus.getStatus()== StatusCodeEnum.GUN_OUTPUT.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.GUN_OUTPUT.getStatusCode());
                baseModel.setErrorMessage("出库成功！");
            }else if(deviceGunByStatus.getStatus()== StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage("设备不存在！");
            }
        }else if(status==1){
            deviceGun.setDeviceNo(deviceNo);
            Gun gun = gunMapper.selectGunByGunTag(gunTag);
            deviceGun.setGunMac(gun.getBluetoothMac());
            deviceGun.setUpdateTime(new Date());
            BaseModel deviceGunByStatus = deviceGunService.updateDeviceGunByStatus(deviceGun,status);
            if (deviceGunByStatus.getStatus()== StatusCodeEnum.GUN_STORAGE.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage("入库成功！");
            }else if(deviceGunByStatus.getStatus()== StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage("设备不存在！");
            }else if(deviceGunByStatus.getStatus()== StatusCodeEnum.Fail.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("库存不存在!");
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }


      /*  if(status==0 || status==1){
            BaseModel deviceGunByStatus = deviceGunService.updateDeviceGunByStatus(deviceGun,status);
            if (deviceGunByStatus.getStatus()== StatusCodeEnum.GUN_OUTPUT.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.GUN_OUTPUT.getStatusCode());
                baseModel.setErrorMessage("出库成功！");
            }else if(deviceGunByStatus.getStatus()== StatusCodeEnum.GUN_STORAGE.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage("入库成功！");
            }else if(deviceGunByStatus.getStatus()== StatusCodeEnum.DEVICE_NONENTITY.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage("设备不存在！");
            }
        } else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作！");
        }*/
        return baseModel;
    }

}
