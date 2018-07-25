package com.ybkj.gun.service;

import com.ybkj.gun.model.DeviceLocation;

import java.util.List;

/**
 *@Description:  功能描述（设备地点）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 20:11
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 20:11
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@SuppressWarnings("all")
public interface DeviceLocationSerivce {

    public int insertDeviceLocation(DeviceLocation deviceLocation) throws Exception;
    public void removeDeviceLocation(Integer deviceLocationId) throws Exception;
    public void removeDeviceLocation(List<Integer> deviceLocationIds) throws Exception;
    public void updateDeviceLocation(DeviceLocation deviceLocation) throws Exception;
    public List<DeviceLocation> findDeviceLocations(DeviceLocation deviceLocations) throws Exception;
    public DeviceLocation findDeviceLocation(Integer deviceLocationId) throws Exception;


}
