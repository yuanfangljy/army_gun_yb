package com.ybkj.gun.service.impl;

import com.ybkj.gun.mapper.DeviceLocationMapper;
import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.DeviceLocationExample;
import com.ybkj.gun.service.DeviceLocationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（设备（用户）地点）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class DeviceLocationServiceImpl implements DeviceLocationSerivce{

    @Autowired
    DeviceLocationMapper deviceLocationMapper;

    @Override
    public int insertDeviceLocation(DeviceLocation deviceLocation) throws Exception {
        return deviceLocationMapper.insert(deviceLocation);
    }

    @Override
    public void removeDeviceLocation(Integer deviceLocationId) throws Exception {
        deviceLocationMapper.deleteByPrimaryKey(deviceLocationId);
    }

    @Override
    public void removeDeviceLocation(List<Integer> deviceLocationIds) throws Exception {
        DeviceLocationExample deviceLocationExample=new DeviceLocationExample();
        DeviceLocationExample.Criteria criteria=deviceLocationExample.createCriteria();
        criteria.andIdIn(deviceLocationIds);
    }

    @Override
    public void updateDeviceLocation(DeviceLocation deviceLocation) throws Exception {
        deviceLocationMapper.updateByPrimaryKeySelective(deviceLocation);
    }

    @Override
    public List<DeviceLocation> findDeviceLocations(DeviceLocation deviceLocations) throws Exception {
        return deviceLocationMapper.selectDeviceLocation(deviceLocations);
    }

    @Override
    public DeviceLocation findDeviceLocation(Integer deviceLocationId) throws Exception {
        return deviceLocationMapper.selectByPrimaryKey(deviceLocationId);
    }
}
