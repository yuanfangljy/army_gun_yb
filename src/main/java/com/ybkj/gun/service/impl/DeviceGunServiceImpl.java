package com.ybkj.gun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.activeMq.Producer;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.common.util.RandomNumber;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.DeviceLocationMapper;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.*;
import com.ybkj.gun.service.DeviceGunSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 功能描述（枪支领取信息业务表）
 * @Author: 刘家义
 * @CreateDate: 2018/7/24 19:42
 * @UpdateUser: 刘家义
 * @UpdateDate: 2018/7/24 19:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@SuppressWarnings("all")
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeviceGunServiceImpl implements DeviceGunSerivce {

    private final Integer gunOutStatus = 0;//出库
    private final Integer gunIntStatus = 1;//入库

    @Autowired
    DeviceGunMapper deviceGunMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceLocationMapper deviceLocationMapper;
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
     *
     * @param deviceGun
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel updategunStorage(DeviceGun deviceGun, Integer status) throws Exception {
        BaseModel baseModel = new BaseModel();
        Device device1 = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
        if(device1.getState()!=0){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("警员【" + deviceGun.getDeviceNo() + "】尚未登录，请重新登录(可能被下线)");
            return baseModel;
        }
        //通过mac地址(实际上是抢号)，来获取枪支的mac地址:deviceGun.getGunMac()实际上是抢号
        //Gun gun = gunMapper.selectGunByGunTag(deviceGun.getGunMac());
        Gun gun = gunMapper.selectGunByBluetoothMac(deviceGun.getGunMac());
        if (gun != null) {
            //在入库的时候，还要判断设备有没有离位，如果离位则不能进行入库
            if (gun.getTotalBulletNumber() == 2) {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("该枪已离位，需重新连接成功，才能入库!");
            } else {
                //通过警员编号，枪支的mac地址和状态为0（已经出库）
                DeviceGun deviceGun1 = deviceGunMapper.selectDeviceGunByStatus(deviceGun.getDeviceNo(), gun.getBluetoothMac(), 0);
                if (deviceGun1 != null) {
                    //send Storage message to Netty
                    BaseModel sendMessageStorage = producer.sendMessageStorage(gun.getBluetoothMac(), randomNumber.getRandomNumber(), deviceGun.getDeviceNo());
                    if (sendMessageStorage.getStatus() != StatusCodeEnum.Fail.getStatusCode()) {

                        Device device = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
                        //修改设备的状态
                        device.setState(1);
                        //deviceMapper.updateByPrimaryKeySelective(device);
                        //修改枪支的状态
                        gun.setState(1);
                        gun.setRealTimeState(1);
                        //gunMapper.updateByPrimaryKeySelective(gun);
                        //枪支入库
                        //deviceGun1.setState(1);
                        //deviceGun1.setUpdateTime(new Date());
                        //deviceGun1.setInWarehouseTime(new Date());

                        //deviceGunMapper.updateByPrimaryKeySelective(deviceGun1);
                        baseModel.setStatus(StatusCodeEnum.GUN_STORAGE.getStatusCode());
                        baseModel.setErrorMessage("正在入库中...");

                    } else {
                        baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                        baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
                    }
                } else {
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("枪号与警员号，不匹配，请重新审查");
                }
            }
        } else {
            baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
            baseModel.setErrorMessage("该枪支不存在");
        }
        return baseModel;
    }

    /**
     * 枪支出库
     *
     * @param deviceGun
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel addGunDelivery(DeviceGun deviceGun, Integer status) throws Exception {
        BaseModel baseModel = new BaseModel();
        //出库时判断设备有没有在线
        Device device1 = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
        if(device1.getState()!=0){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("警员【" + deviceGun.getDeviceNo() + "】尚未登录，请重新登录(可能被下线)");
            return baseModel;
        }
        //实际是：枪号deviceGun.getGunMac()
        Gun gun = gunMapper.selectGunByGunTag(deviceGun.getGunMac());
        /**
         * 2018-09-04:出库bug修改
         * 1、根据传入的设备查询，state为0的，如果存在，就说改警员正在出库中
         * 2、根据传入的枪支编号，查询对应的mac，再在device__gun中查询state为0的，
         如果存在，就报该枪支正在使用中
         */
        DeviceGun deviceGun3 = deviceGunMapper.selectDeviceGunByMacAndState(gun.getBluetoothMac(), 0);
        DeviceGun deviceGun4 = deviceGunMapper.selectDeviceGunByDeviceNoAndState(deviceGun.getDeviceNo(), 0);
        if (deviceGun3 != null) {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("枪支【" + deviceGun.getGunMac() + "】正在使用中...");
        }else if(deviceGun4 != null) {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("警员【" + deviceGun.getDeviceNo() + "】已出库...");
        }else{
       /* System.out.println(gun.getState()==null);*/
            if (gun != null) {
                if (gun.getState() == null || gun.getState() == 1) {
                    //通过警员编号，枪支的mac地址和状态为0（已经出库）
                    System.out.println("=========" + deviceGun.getDeviceNo() + "----" + gun.getBluetoothMac() + "----" + status);
                    DeviceGun deviceGun1 = deviceGunMapper.selectDeviceGunByStatus(deviceGun.getDeviceNo(), gun.getBluetoothMac(), status);
                    if (deviceGun1 != null) {
                        baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                        baseModel.setErrorMessage("该警员正在使用此枪");
                    } else {
                        //判断设备是否在在使用，通过设备号和状态是否为O
                        DeviceGun deviceGun2 = deviceGunMapper.selectDeviceGunByDeviceNoAndState(deviceGun.getDeviceNo(), status);
                        Device device = deviceMapper.selectDeviceNo(deviceGun.getDeviceNo());
                    /*System.out.println(device.getPhone()+"--"+device);*/
                        if (device != null && device.getState() != null) {
                            if (deviceGun2 != null) {
                                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                                baseModel.setErrorMessage("该警员与枪正在连接中");
                            } else {
                                //send Delivery message to Netty
                                BaseModel sendMessageDelivery = producer.sendMessageDelivery(gun.getBluetoothMac(), gun.getGunTag(), dataTool.dateToString(), dataTool.dateToString(deviceGun.getTemperanceTime()), deviceGun.getDeviceNo());
                               // System.out.println("-----&&&&&------" + sendMessageDelivery);
                                if (sendMessageDelivery.getStatus() != StatusCodeEnum.Fail.getStatusCode()) {
                                   /* //进行出库操作
                                    //修改设备的状态，根据警员编号
                                    device.setState(1);
                                    //deviceMapper.updateByPrimaryKeySelective(device);
                                    //修改枪支的状态
                                    gun.setState(1);
                                    gun.setRealTimeState(1);
                                    //gunMapper.updateByPrimaryKeySelective(gun);
                                    //device_gun：修改
                                    deviceGun.setCreateTime(new Date());
                                    deviceGun.setOutWarehouseTime(new Date());
                                    deviceGun.setState(0);
                                    deviceGun.setGunMac(gun.getBluetoothMac());
                                    //deviceGunMapper.insertSelective(deviceGun);*/
                                    baseModel.setStatus(StatusCodeEnum.GUN_OUTPUT.getStatusCode());
                                    baseModel.setErrorMessage("正在出库中...");
                                } else {
                                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                                    baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
                                }
                            }
                        } else {
                            baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                            baseModel.setErrorMessage("该警员不存在");
                        }
                    }
                } else {
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("该枪支正在使用中，不能再次出库");
                }
            } else {
                baseModel.setStatus(StatusCodeEnum.DEVICE_NONENTITY.getStatusCode());
                baseModel.setErrorMessage("该枪支不存在");
            }
        }
        return baseModel;
    }

    /**
     * 枪支实时列表管理:根据警员编号
     *
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
     *
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
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer findGunDislocation() throws Exception {
        return deviceGunMapper.selectGunDislocation();
    }

    /**
     * 设备离线
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer findDeviceOffLine() throws Exception {
        return deviceGunMapper.selectDeviceOffLine();
    }

    /**
     * 查询所有在线警员和枪支
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<DeviceGun> findGunAndDeviceLocationAllOnLine(String deviceNo) throws Exception {
       /* DeviceLocation deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(deviceNo);
        System.out.println("deviceLocation---------"+deviceNo+"="+deviceLocation.getLongitude()+"--"+deviceLocation.getLatitude());*/
        /*Map<String,Object> map=new HashMap<String,Object>();
        map.put("deviceNo",deviceNo);
        map.put("lng",Double.parseDouble(lng));
        map.put("lag",Double.parseDouble(lag));*/
        return deviceGunMapper.selectGunAndDeviceLocationAllOnLine(deviceNo);
    }

    /**
     * 查询已经出库的枪支列表
     * @param pn
     * @param pageSize
     * @param deviceNo
     * @return
     */
    @Override
    public BaseModel findInventoryList(Integer pn, Integer pageSize, String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        PageHelper.startPage(pn, 5);
        List<DeviceGun> deviceGun = deviceGunMapper.selectDeviceGunByDeviceNoAndStatesInventoryList(deviceNo, 0);
        PageInfo<DeviceGun> page = new PageInfo<DeviceGun>(deviceGun,5);
        baseModel.add("pageInfo",page).add("deviceNo", deviceNo);
        return baseModel;
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
