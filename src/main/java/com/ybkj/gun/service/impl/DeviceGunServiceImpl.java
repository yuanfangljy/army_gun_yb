package com.ybkj.gun.service.impl;

import com.ybkj.common.activeMq.Producer;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.common.util.ProgressiveIncreaseNumber;
import com.ybkj.common.util.RandomNumber;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceGunExample;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.DeviceGunSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    private  final  Integer gunOutStatus=0;//出库
    private  final  Integer gunIntStatus=1;//入库

    @Autowired
    DeviceGunMapper deviceGunMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    GunMapper gunMapper;
    @Autowired
    private Producer producer;
    @Autowired
    private DataTool dataTool;
    @Autowired
    private RandomNumber randomNumber;

    /**
     * 枪支入库
     * @param deviceGun
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel updategunStorage(DeviceGun deviceGun, Integer status) throws Exception {
        BaseModel baseModel=new BaseModel();
        //通过mac地址(实际上是抢号)，来获取枪支的mac地址:deviceGun.getGunMac()实际上是抢号
        Gun gun = gunMapper.selectGunByGunTag(deviceGun.getGunMac());
        if(gun!=null){
            //通过警员编号，枪支的mac地址和状态为0（已经出库）
            DeviceGun deviceGun1=deviceGunMapper.selectDeviceGunByStatus(deviceGun.getDeviceNo(), gun.getBluetoothMac(),0);

            if (deviceGun1!=null){
                deviceGun1.setUpdateTime(new Date());
                Device device = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
                //修改设备的状态
                device.setState(1);
                deviceMapper.updateByPrimaryKeySelective(device);
                //修改枪支的状态
                gun.setState(1);
                gunMapper.updateByPrimaryKeySelective(gun);
                //枪支入库
                deviceGun1.setState(1);
                deviceGun1.setInWarehouseTime(new Date());
                boolean sendMessageStorage = producer.sendMessageStorage(gun.getBluetoothMac(), randomNumber.getRandomNumber(),deviceGun.getDeviceNo());
                if (!sendMessageStorage){
                    baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
                }
                deviceGunMapper.updateByPrimaryKeySelective(deviceGun1);
                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                baseModel.setErrorMessage("入库成功!");
                //send Storage message to Netty

            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("枪号与警员号，不匹配，请重新审查");
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
            baseModel.setErrorMessage("该枪支不存在");
        }
        return baseModel;
    }

    /**
     * 枪支出库
     * @param deviceGun
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel addGunDelivery(DeviceGun deviceGun, Integer status) throws Exception {
        BaseModel baseModel=new BaseModel();
        Gun gun = gunMapper.selectGunByGunTag(deviceGun.getGunMac());
       /* System.out.println(gun.getState()==null);*/
        if(gun!=null){
            if(gun.getState()==null || gun.getState()==1){
                //通过警员编号，枪支的mac地址和状态为0（已经出库）
                DeviceGun deviceGun1=deviceGunMapper.selectDeviceGunByStatus(deviceGun.getDeviceNo(), gun.getBluetoothMac(),status);
                if (deviceGun1!=null){
                    baseModel.setErrorMessage("该警员正在使用此枪");
                }else{
                    Device device = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
                    //System.out.println(device.getPhone());
                    if(device!=null){
                        if(device.getState()==0 ){
                            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                            baseModel.setErrorMessage("该设备与枪正在连接中");
                        }else {
                            //进行出库操作
                            //修改设备的状态，根据警员编号
                            device.setState(0);
                            deviceMapper.updateByPrimaryKeySelective(device);
                            //修改枪支的状态
                            gun.setState(0);
                            gunMapper.updateByPrimaryKeySelective(gun);
                            //device_gun：修改
                            deviceGun.setCreateTime(new Date());
                            deviceGun.setOutWarehouseTime(new Date());
                            deviceGun.setState(0);
                            deviceGun.setGunMac(gun.getBluetoothMac());
                            boolean sendMessageDelivery = producer.sendMessageDelivery(gun.getBluetoothMac(), deviceGun.getGunTag(), dataTool.dateToString(), dataTool.dateToString(deviceGun.getTemperanceTime()),deviceGun.getDeviceNo());
                            if (!sendMessageDelivery){
                                baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                                baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
                            }
                            deviceGunMapper.insertSelective(deviceGun);
                            baseModel.setStatus(StatusCodeEnum.GUN_OUTPUT.getStatusCode());
                            baseModel.setErrorMessage("出库成功!");
                            //send Delivery message to Netty

                        }
                    }else{
                        baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                        baseModel.setErrorMessage("该设备不存在");
                    }
                }
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("该枪支正在使用中，不能再次出库");
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
            baseModel.setErrorMessage("该枪支不存在");
        }
        return baseModel;
    }

    /**
     * 枪支实时列表管理:根据警员编号
     * @param deviceNo
     * @return
     * @throws Exception
     */
    @Override
    public List<DeviceGun> findGunAndDeviceLocation(String deviceNo) throws Exception {
        return deviceGunMapper.selectGunAndDeviceLocation(deviceNo);
    }
    /**
     * 枪支实时列表管理:全部
     * @param deviceNo
     * @return
     * @throws Exception
     */
    @Override
    public List<DeviceGun> findGunAndDeviceLocation() throws Exception {
        return deviceGunMapper.selectGunAndDeviceLocationAll();
    }

    /**
     * 枪支离位
     * @return
     * @throws Exception
     */
    @Override
    public Integer findGunDislocation() throws Exception {
        return deviceGunMapper.selectGunDislocation();
    }

    /**
     * 设备离线
     * @return
     * @throws Exception
     */
    @Override
    public Integer findDeviceOffLine() throws Exception {
        return deviceGunMapper.selectDeviceOffLine();
    }


    @Override
    public int insertDeivceGun(DeviceGun deviceGun) throws Exception {
        return deviceGunMapper.insertSelective(deviceGun);
    }

    @Override
    public void removeDeviceGun(Integer deivceGunId) throws Exception {
        deviceGunMapper.deleteByPrimaryKey(deivceGunId);
    }

    @Override
    public void removeDeviceGun(List<String> deivceGunIds) throws Exception {
        DeviceGunExample example = new DeviceGunExample();
        DeviceGunExample.Criteria criteria = example.createCriteria();
        //delect from User where emp_id in(1,2,3);
        //在哪一个集合里面
       criteria.andDeviceNoIn(deivceGunIds);
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
