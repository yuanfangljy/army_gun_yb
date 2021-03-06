package com.ybkj.gun.mapper;

import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceGunExample;
import java.util.List;
import java.util.Map;

import com.ybkj.gun.model.DeviceLocation;
import org.apache.ibatis.annotations.Param;

public interface DeviceGunMapper {
    long countByExample(DeviceGunExample example);

    int deleteByExample(DeviceGunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceGun record);

    int insertSelective(DeviceGun record);

    List<DeviceGun> selectByExample(DeviceGunExample example);

    DeviceGun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceGun record, @Param("example") DeviceGunExample example);

    int updateByExample(@Param("record") DeviceGun record, @Param("example") DeviceGunExample example);

    int updateByPrimaryKeySelective(DeviceGun record);

    int updateByPrimaryKey(DeviceGun record);

    //判断是否，有出库的，枪支
    DeviceGun selectDeviceGunByStatus(@Param(value = "deviceNo") String deviceNo,@Param(value = "gunMac")String gunMac,@Param(value = "state") Integer state) throws Exception;
    // 枪支实时列表管理:根据警员编号
    List<DeviceGun> selectGunAndDeviceLocation(String deviceNo) throws Exception ;
    DeviceGun selectGunAndDeviceLocationOne(String deviceNo) throws Exception ;
    // 枪支实时列表管理:根据警员编号
    List<DeviceGun> selectGunAndDeviceLocationAll() throws Exception ;
    List<DeviceGun> selectDeviceGun(DeviceGun deviceGun) throws Exception;
    //统计设备离线
    Integer selectDeviceOffLine() throws Exception;
    //统计（枪支）蓝牙离位
    Integer selectGunDislocation() throws Exception;
    //通过蓝牙mac和状态查询设备号
     DeviceGun selectDeviceGunByMacAndState(@Param(value="bluetoothMac")String bluetoothMac, @Param(value = "state") Integer state) throws Exception;
    //通过警员编号和状态查询警员有没有出库
    DeviceGun selectDeviceGunByDeviceNoAndState(@Param(value="deviceNo")String deviceNo, @Param(value = "state") Integer state) throws Exception;
    List<DeviceGun> selectDeviceGunByDeviceNoAndStates(@Param(value="deviceNo")String deviceNo, @Param(value = "state") Integer state) throws Exception;
    List<DeviceGun> selectDeviceGunByDeviceNoAndStatesInventoryList(@Param(value="deviceNo")String deviceNo, @Param(value = "state") Integer state) throws Exception;

    //查询所有在线警员和枪支
    List<DeviceGun> selectGunAndDeviceLocationAllOnLine(String deviceNo) throws Exception;
    //根据蓝牙号，查询到所有的设备号，进行去重
    List<DeviceGun> selectDeviceGunByMac(String gunMac) throws Exception;

}