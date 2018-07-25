package com.ybkj.gun.service.impl;

import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.service.DeviceSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（设备信息）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class DeviceServiceImpl implements DeviceSerivce{

    @Autowired
    DeviceMapper deviceMapper;
    @Override
    public int insertDevice(Device device) throws Exception {

        System.out.println(device.toString());
        return deviceMapper.insert(device);
    }

    @Override
    public void removeDevice(Integer deviceId) throws Exception {

    }

    @Override
    public void removeDevice(List<Integer> deviceIds) throws Exception {

    }

    @Override
    public void updateDevice(Device device) throws Exception {

    }

    @Override
    public List<Device> findDevices(Device devices) throws Exception {
        return null;
    }

    @Override
    public Device findDevice(Integer deviceId) throws Exception {
        return null;
    }
}
