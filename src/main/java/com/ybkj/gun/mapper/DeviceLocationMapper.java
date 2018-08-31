package com.ybkj.gun.mapper;

import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.DeviceLocationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeviceLocationMapper {
    long countByExample(DeviceLocationExample example);

    int deleteByExample(DeviceLocationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceLocation record);

    int insertSelective(DeviceLocation record);

    List<DeviceLocation> selectByExample(DeviceLocationExample example);

    DeviceLocation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceLocation record, @Param("example") DeviceLocationExample example);

    int updateByExample(@Param("record") DeviceLocation record, @Param("example") DeviceLocationExample example);

    int updateByPrimaryKeySelective(DeviceLocation record);

    int updateByPrimaryKey(DeviceLocation record);

    List<DeviceLocation> selectDeviceLocation(DeviceLocation deviceLocation);

    //根据时间枪支查询枪支的轨迹
    List<DeviceLocation> selectDeviceLocationByTimeAndGunTag(Map<String, Object> map);
    //根据警员编号，查询出最新警员地址
    DeviceLocation selectDeviceLocationByDeviceNoNewest(String deviceNo) throws Exception;
    List<DeviceLocation> selectRoundOnline(Map<String, Object> map) throws Exception;
    //优化查询最近五个设备
    List<DeviceLocation> optimizeRoundOnline(Map<String, Object> map) throws Exception;

}