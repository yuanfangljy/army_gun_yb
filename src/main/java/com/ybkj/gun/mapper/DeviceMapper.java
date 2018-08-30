package com.ybkj.gun.mapper;

import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.DeviceExample;
import java.util.List;
import java.util.Map;

import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.Gun;
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

    Device selectGunMac(String gunMac);

    Device selectDeviceName(String deviceName);

    Device selectDeviceNo(String deviceNo);
    //设备离线统计
    List<Gun> selectDeviceOffLine(Integer state);
    //统计在线设备
    Integer selectDeviceOnLine();

    List<Device> findDeviceByState(Integer state);

    Device findDeviceByDeviceNo(String deviceNo);

}