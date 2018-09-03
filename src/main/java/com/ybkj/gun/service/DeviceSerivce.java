package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.Gun;

import java.util.List;
@SuppressWarnings("all")
public interface DeviceSerivce {
    public int insertDevice(Device device) throws Exception;
    public void removeDevice(Integer deviceId) throws Exception;
    public void removeDevice(List<Integer> deviceIds) throws Exception;
    public void updateDevice(Device device) throws Exception;
    public List<Device> findDevices(Device devices) throws Exception;
    public Device findDevice(Integer deviceId) throws Exception;
    //添加设备信息
    BaseModel insertDevices(Device device) throws Exception;
    //查询手机号
    BaseModel selectMobile(String mobile) throws Exception;
    //查询邮箱
    BaseModel selectEmail(String email) throws Exception;
    //查询设备名
    BaseModel selectDeviceName(String deviceName) throws Exception ;
    //查询设备编码
    BaseModel selectDeviceNo(String deviceNo)throws Exception;
    //设备离线统计
    List<Gun> findDeviceOffLine(Integer state) throws Exception;
    public BaseModel findDeviceByStates() throws Exception;
    //统计在线设备
    Integer findDeviceOnLine() throws Exception ;
    //根据设备状态查询设备信息
    BaseModel selectDeviceByState(Integer state)throws Exception ;
}
