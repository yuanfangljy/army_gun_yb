package com.ybkj.gun.mapper;

import com.ybkj.gun.model.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    List<Device> selectDevice(Device device);

    Device selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    Device selectMobile(String mobile);

    Device selectEmail(String email);

//    Device selectGunMac(String gunMac);

    Device selectDeviceName(String deviceName);

    Device selectDeviceNo(String deviceNo);
    //设备离线统计
    List<Gun> selectDeviceOffLine(Integer state);
    //统计在线设备
    Integer selectDeviceOnLine();

    List<Device> findDeviceByState(Integer state);
    List<Device> findDeviceByStates();

    Device findDeviceByDeviceNo(String deviceNo);

    Device findDeviceByDeviceNoAndState(@Param(value = "deviceNo") String deviceNo, @Param(value = "state") Integer state) throws Exception;

}