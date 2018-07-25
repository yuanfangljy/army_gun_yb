package com.ybkj.gun.mapper;

import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceGunExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceGunMapper {
    long countByExample(DeviceGunExample example);

    int deleteByExample(DeviceGunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceGun record);

    int insertSelective(DeviceGun record);

    List<DeviceGun> selectByExample(DeviceGunExample example);

    DeviceGun selectByPrimaryKey(Integer id);

    List<DeviceGun> selectDeviceGun(DeviceGun deviceGun);

    int updateByExampleSelective(@Param("record") DeviceGun record, @Param("example") DeviceGunExample example);

    int updateByExample(@Param("record") DeviceGun record, @Param("example") DeviceGunExample example);

    int updateByPrimaryKeySelective(DeviceGun record);

    int updateByPrimaryKey(DeviceGun record);
}