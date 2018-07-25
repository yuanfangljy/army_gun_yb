package com.ybkj.gun.mapper;

import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.DeviceLocationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceLocationMapper {
    long countByExample(DeviceLocationExample example);

    int deleteByExample(DeviceLocationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceLocation record);

    int insertSelective(DeviceLocation record);

    List<DeviceLocation> selectByExample(DeviceLocationExample example);

    List<DeviceLocation> selectDeviceLocation(DeviceLocation deviceLocation);

    DeviceLocation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceLocation record, @Param("example") DeviceLocationExample example);

    int updateByExample(@Param("record") DeviceLocation record, @Param("example") DeviceLocationExample example);

    int updateByPrimaryKeySelective(DeviceLocation record);

    int updateByPrimaryKey(DeviceLocation record);
}