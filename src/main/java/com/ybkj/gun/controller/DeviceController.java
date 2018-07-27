package com.ybkj.gun.controller;

import com.ybkj.gun.model.Device;
import com.ybkj.gun.service.DeviceSerivce;
import com.ybkj.gun.service.impl.DeviceServiceImpl;
import com.ybkj.gun.service.impl.GunServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("device")
@Slf4j
@Api(value = "/",description = "设备管理功能")
public class DeviceController {

    @Autowired
    DeviceServiceImpl deviceSerivce;

    @ApiOperation(value = "添加设备信息", notes = "设备")
    @RequestMapping(value = "/errorIndex",method = RequestMethod.GET)
    public int errorIndex(Device device) throws Exception {
      //  Device device=new Device();
      /*  device.setPhone("15575938043");
        device.setPassword("123123");
        device.setDeviceNo("123123");
        device.setDeviceName("1231231");*/
        int i = deviceSerivce.insertDevice(device);
        log.info("设备信息："+i);
        return i;
    }
}
