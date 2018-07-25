package com.ybkj.gun.service;

import com.ybkj.gun.model.Device;

import java.util.List;
@SuppressWarnings("all")
public interface DeviceSerivce {
    public int insertDevice(Device device) throws Exception;
    public void removeDevice(Integer deviceId) throws Exception;
    public void removeDevice(List<Integer> deviceIds) throws Exception;
    public void updateDevice(Device device) throws Exception;
    public List<Device> findDevices(Device devices) throws Exception;
    public Device findDevice(Integer deviceId) throws Exception;
}
