package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.DeviceGun;

import java.util.List;


/**
 *@Description:  功能描述（枪支领取表）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:32
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:32
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@SuppressWarnings("all")
public  interface DeviceGunSerivce {

    /**
     * 枪支出库添加
     * @param deviceGun
     * @return
     * @throws Exception
     */
    public int insertDeivceGun(DeviceGun deviceGun) throws Exception;

    /**
     * 删除枪支领取
     * @param deivceGunId
     * @throws Exception
     */
    public void removeDeviceGun(Integer deivceGunId) throws Exception;


    /**
     * 批量删除枪支领取
     * @param deivceGunIds
     * @throws Exception
     */
    public void removeDeviceGun(List<String> deivceGunIds) throws Exception;

    /**
     * 入库表：修改枪支领取
     * @param deviceGun
     * @throws Exception
     */
    public void updateDeviceGun(DeviceGun deviceGun) throws Exception;

    /**
     * 查询多条信息
     * @param deviceGun
     * @return
     * @throws Exception
     */
    public List<DeviceGun> findDeviceGuns(DeviceGun deviceGun) throws Exception;

    /**
     * 根据id查询
     * @param deivceGunId
     * @return
     * @throws Exception
     */
    public DeviceGun findDeviceGun(Integer deivceGunId) throws Exception;


    //枪支入库
    BaseModel updategunStorage(DeviceGun deviceGun, Integer status) throws Exception;

    //枪支出库
    BaseModel addGunDelivery(DeviceGun deviceGun, Integer status) throws Exception;

    //枪支实时列表管理:可根据设备号
    List<DeviceGun> findGunAndDeviceLocation(String deviceNo) throws Exception;
    //枪支实时列表管理:全部
    List<DeviceGun> findGunAndDeviceLocation() throws Exception;
    //统计设备离位
    Integer findGunDislocation() throws Exception;
    //统计设备离线
    Integer findDeviceOffLine() throws Exception;
    //查询所有在线警员和枪支:根据设备号
    List<DeviceGun> findGunAndDeviceLocationAllOnLine(String deviceNo) throws Exception;
    //枪支已经出库的列表
    BaseModel findInventoryList(Integer pn, Integer pageSize, String deviceNo) throws Exception;
}