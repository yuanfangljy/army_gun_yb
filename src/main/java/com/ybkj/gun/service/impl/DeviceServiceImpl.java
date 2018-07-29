package com.ybkj.gun.service.impl;

import com.ybkj.common.encryption.MD5;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.DeviceSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（设备信息）
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
public class DeviceServiceImpl implements DeviceSerivce{

    @Autowired
    DeviceMapper deviceMapper;

    /**
     * 添加设备信息
     * @param device
     * @return
     */
    @Override
    public BaseModel insertDevices(Device device) {
        BaseModel baseModel=new BaseModel();
        Object passWord= MD5.Md5(device.getPhone(), device.getPassword());
        device.setPassword(passWord.toString());
        device.setState(1);//新增时，默认未使用
        final int i = deviceMapper.insertSelective(device);
        if(i!=0){
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
        return baseModel;
    }

    /**
     * 查询手机号是否存在
     * @param mobile
     * @return
     */
    @Override
    public BaseModel selectMobile(String mobile) {
        BaseModel baseModel=new BaseModel();
        Device device = deviceMapper.selectMobile(mobile);
        if(device==null){
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }else{
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    @Override
    public BaseModel selectEmail(String email) {
        BaseModel baseModel=new BaseModel();
        Device  device = deviceMapper.selectEmail(email);
        if(device==null){
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }else{
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }

    /**
     * 设备名
     * @param deviceName
     * @return
     */
    @Override
    public BaseModel selectDeviceName(String deviceName) {
        BaseModel baseModel=new BaseModel();
        Device  device = deviceMapper.selectDeviceName(deviceName);
        if(device==null){
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }else{
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }
    /**
     * 设备编码
     * @param deviceName
     * @return
     */
    @Override
    public BaseModel selectDeviceNo(String deviceNo) {
        BaseModel baseModel=new BaseModel();
        Device  device = deviceMapper.selectDeviceNo(deviceNo);
        if(device==null){
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }else{
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }


    @Override
    public int insertDevice(Device device) throws Exception {

        System.out.println(device.toString());
        return deviceMapper.insert(device);
    }

    @Override
    public void removeDevice(Integer deviceId) throws Exception {

    }

    @Override
    public void removeDevice(List<Integer> deviceIds) throws Exception {

    }

    @Override
    public void updateDevice(Device device) throws Exception {

    }

    @Override
    public List<Device> findDevices(Device devices) throws Exception {
        return null;
    }

    @Override
    public Device findDevice(Integer deviceId) throws Exception {
        return null;
    }


}
