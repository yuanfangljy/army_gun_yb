package com.ybkj.gun.service.impl;

import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceGunExample;
import com.ybkj.gun.service.DeviceGunSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（枪支领取信息业务表）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class DeviceGunServiceImpl implements DeviceGunSerivce{

    @Autowired
    DeviceGunMapper deviceGunMapper;

    @Override
    public int insertDeivceGun(DeviceGun deviceGun) throws Exception {
        return deviceGunMapper.insertSelective(deviceGun);
    }

    @Override
    public void removeDeviceGun(Integer deivceGunId) throws Exception {
        deviceGunMapper.deleteByPrimaryKey(deivceGunId);
    }

    @Override
    public void removeDeviceGun(List<Integer> deivceGunIds) throws Exception {
        DeviceGunExample example = new DeviceGunExample();
        DeviceGunExample.Criteria criteria = example.createCriteria();
        //delect from User where emp_id in(1,2,3);
        //在哪一个集合里面
        criteria.andDeviceNoNotIn(deivceGunIds);
    }

    @Override
    public void updateDeviceGun(DeviceGun deviceGun) throws Exception {
        deviceGunMapper.updateByPrimaryKey(deviceGun);
    }

    @Override
    public List<DeviceGun> findDeviceGuns(DeviceGun deviceGun) throws Exception {
        return deviceGunMapper.selectDeviceGun(deviceGun);
    }


    @Override
    public DeviceGun findDeviceGun(Integer deivceGunId) throws Exception {
        return deviceGunMapper.selectByPrimaryKey(deivceGunId);
    }
}
