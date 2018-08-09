package com.ybkj.gun.service.impl;

import com.ybkj.gun.mapper.DeviceLocationMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.DeviceLocationExample;
import com.ybkj.gun.service.DeviceLocationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询枪支的轨迹，根据时间
     * @param map
     * @return
     */
    @Override
    public List<DeviceLocation> selectDeviceLocationTrajectory(Map<String, Object> map) {

        return deviceLocationMapper.selectDeviceLocationByTimeAndGunTag(map);
    }

    /**
     *  计算传入的经纬度，周围的最近的经纬度
     * @return
     * @throws Exception
     */
    @Override
    public List<DeviceLocation> findRoundOnline(String deviceNo,String lng,String lag) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("deviceNo",deviceNo);
        map.put("lng",Double.parseDouble(lng));
        map.put("lag",Double.parseDouble(lag));
        return deviceLocationMapper.selectRoundOnline(map);
    }


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
