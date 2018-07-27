package com.ybkj.gun.controller;

import com.ybkj.gun.service.impl.DeviceServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "设备和枪的状态")
@RestController
@RequestMapping("/deviceGun")
public class DeviceGunController {
    @Autowired
    DeviceServiceImpl deviceService;


}
