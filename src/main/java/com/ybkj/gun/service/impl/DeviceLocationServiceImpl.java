package com.ybkj.gun.service.impl;

import com.ybkj.common.baiduMap.BaiDuUtil;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.model.OptimizeDeviceLocation;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.DeviceLocationMapper;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.*;
import com.ybkj.gun.service.DeviceLocationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 功能描述（设备（用户）地点）
 * @Author: 刘家义
 * @CreateDate: 2018/7/24 19:42
 * @UpdateUser: 刘家义
 * @UpdateDate: 2018/7/24 19:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeviceLocationServiceImpl implements DeviceLocationSerivce {

    @Autowired
    DeviceLocationMapper deviceLocationMapper;
    @Autowired
    DeviceGunMapper deviceGunMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    GunMapper gunMapper;

    /**
     * 查询枪支的轨迹，根据时间枪支号
     *
     * @param map
     * @return
     */
    @Override
    public List<DeviceLocation> findDeviceLocationTrajectory(Map<String, Object> map) {

        return deviceLocationMapper.selectDeviceLocationByTimeAndGunTag(map);
    }

    /**
     * 计算传入的经纬度，周围的最近的经纬度
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<DeviceLocation> findRoundOnline(String deviceNo, String lng, String lag) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceNo", deviceNo);
        map.put("lng", Double.parseDouble(lng));
        map.put("lag", Double.parseDouble(lag));
        return deviceLocationMapper.selectRoundOnline(map);
    }

    /**
     * 优化sql,显示地图上的实时数据?
     * 1、第一步：查询所有在线的警员---device
     * select a.device_no from device as a where  a.state=0      1
     * 2、第二步：根据查询在线的警员,通过device_gun查询所有已经出库的警员（state为0的），要进行去重（device_no）
     * select DISTINCT b.device_no from device_gun as b where  b.device_no=a.device_no and b.state=0
     * 3、第三步：根据以上两步，得出了最终的device_no，再去device__location得到最新的心跳记录
     * select max(c.id) from device_location as c where c.device_no =b.device_no
     * select d.device_no,d.latitude,d.longitude,d.stat from device_location as d where d.id=c.id
     * <p>
     * 4、第四步：根据3得到的device_no，查询mac，在gun表中再去得到该警员使用枪的相关信息
     * select e.mac from device_gun as e where e.device_no=d.device_no
     * <p>
     * select f.gun_tag,f.gun_model,f.bluetooth_mac,
     * f.bullet_number,f.state,f.real_time_state from gun f where f.mac=e.mac
     */
    @Override
    public BaseModel optimizeDeviceLocation(String deviceNo) throws Exception {
        System.out.println("------------------------------------------"+deviceNo);
        BaseModel baseModel = new BaseModel();
        try {
            List<String> deviceGunList = new ArrayList<>();
            Map<String, Object> optimizeMap = new HashMap<String, Object>();// 存放查询结果
            List<OptimizeDeviceLocation> optimizeList = new ArrayList<>();
            List<OptimizeDeviceLocation> optimizeOutList = new ArrayList<>();

            DeviceLocation deviceLocation = null;
            Gun gun = null;
            Device devices = null;
            DeviceGun deviceGun = null;
            String location="";

            //是否根据设备查询
            if(deviceNo==null || deviceNo.equals("")){
                // 1、第一步：查询所有在线的警员---device
                List<Device> deviceByState = deviceMapper.findDeviceByState(0);
                if (deviceByState == null) {
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("暂无在线警员");
                }
                for (Device device : deviceByState) {
                    //2、第二步：根据查询在线的警员,通过device_gun查询所有已经出库的警员（state为0的），要进行去重（device_no）
                    deviceGun = deviceGunMapper.selectDeviceGunByDeviceNoAndState(device.getDeviceNo(), 0);
                    optimizeMap = new HashMap<String, Object>();
                    if (deviceGun == null) {
                        //、直接用device_no查询device_location最新的数据:（此时用户在线，但是未出库）
                        deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(device.getDeviceNo());
                        if (deviceLocation == null) {
                            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                            baseModel.setErrorMessage("在线警员位上传位置信息，服务器出现问题");
                        }
                        OptimizeDeviceLocation optimizeDeviceLocation = new OptimizeDeviceLocation();
                        devices = deviceMapper.findDeviceByDeviceNo(device.getDeviceNo());

                        optimizeDeviceLocation.setDeviceNo(device.getDeviceNo());
                        optimizeDeviceLocation.setLongitude(deviceLocation.getLongitude());
                        optimizeDeviceLocation.setLatitude(deviceLocation.getLatitude());
                        optimizeDeviceLocation.setLocationEndTime(deviceLocation.getCreateTime());
                        optimizeDeviceLocation.setMobile(devices.getPhone());
                        optimizeDeviceLocation.setDeviceState(devices.getState());
                        optimizeDeviceLocation.setGunTag(null);
                        optimizeDeviceLocation.setGunModel(null);
                        optimizeDeviceLocation.setGunWarehouseName(null);
                        optimizeDeviceLocation.setGunState(null);
                        //optimizeMap.put(device.getDeviceNo(),optimizeDeviceLocation);
                        optimizeList.add(optimizeDeviceLocation);

                        //具体位置
                        location+= BaiDuUtil.getAddress(deviceLocation.getLongitude(),deviceLocation.getLatitude())+"@";

                    } else {
                        //3、第三步：根据以上两步，得出了最终的device_no，再去device__location得到最新的心跳
                        devices = deviceMapper.findDeviceByDeviceNo(deviceGun.getDeviceNo());
                        deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(deviceGun.getDeviceNo());
                        deviceGun = deviceGunMapper.selectDeviceGunByDeviceNoAndState(deviceGun.getDeviceNo(), 0);
                        gun = gunMapper.selectGunByBluetoothMac(deviceGun.getGunMac());
                        OptimizeDeviceLocation optimizeDeviceLocation = new OptimizeDeviceLocation();
                        optimizeDeviceLocation.setDeviceNo(device.getDeviceNo());
                        optimizeDeviceLocation.setLongitude(deviceLocation.getLongitude());
                        optimizeDeviceLocation.setLatitude(deviceLocation.getLatitude());
                        optimizeDeviceLocation.setLocationEndTime(deviceLocation.getCreateTime());
                        optimizeDeviceLocation.setMobile(devices.getPhone());
                        optimizeDeviceLocation.setDeviceState(devices.getState());
                        optimizeDeviceLocation.setGunTag(gun.getGunTag());
                        optimizeDeviceLocation.setGunModel(gun.getGunModel());
                        optimizeDeviceLocation.setGunWarehouseName(gun.getWarehouseName());
                        optimizeDeviceLocation.setGunState(gun.getRealTimeState());
                        //optimizeMap.put(device.getDeviceNo(),optimizeDeviceLocation);
                        optimizeList.add(optimizeDeviceLocation);

                        //具体位置
                        location+= BaiDuUtil.getAddress(deviceLocation.getLongitude(),deviceLocation.getLatitude())+"@";
                    }
                }
            }else{
                //根据条件查询
                //判断用户是否存在
                Device  device = deviceMapper.selectDeviceNo(deviceNo);
                if(device==null){
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("暂无此警员信息");
                }
                deviceGun = deviceGunMapper.selectDeviceGunByDeviceNoAndState(deviceNo, 0);
                if(deviceGun==null){
                    //、直接用device_no查询device_location最新的数据:（此时用户在线，但是未出库）
                    deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(deviceNo);
                    if (deviceLocation == null) {
                        baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                        baseModel.setErrorMessage("在线警员位上传位置信息，服务器出现问题");
                    }
                    OptimizeDeviceLocation optimizeDeviceLocation = new OptimizeDeviceLocation();
                    devices = deviceMapper.findDeviceByDeviceNo(deviceNo);

                    optimizeDeviceLocation.setDeviceNo(deviceNo);
                    optimizeDeviceLocation.setLongitude(deviceLocation.getLongitude());
                    optimizeDeviceLocation.setLatitude(deviceLocation.getLatitude());
                    optimizeDeviceLocation.setLocationEndTime(deviceLocation.getCreateTime());
                    optimizeDeviceLocation.setMobile(devices.getPhone());
                    optimizeDeviceLocation.setDeviceState(devices.getState());
                    optimizeDeviceLocation.setGunTag(null);
                    optimizeDeviceLocation.setGunModel(null);
                    optimizeDeviceLocation.setGunWarehouseName(null);
                    optimizeDeviceLocation.setGunState(null);
                    //optimizeMap.put(device.getDeviceNo(),optimizeDeviceLocation);
                    optimizeList.add(optimizeDeviceLocation);

                    //具体位置
                    location+= BaiDuUtil.getAddress(deviceLocation.getLongitude(),deviceLocation.getLatitude())+"@";
                }else{
                    //3、第三步：根据以上两步，得出了最终的device_no，再去device__location得到最新的心跳
                    devices = deviceMapper.findDeviceByDeviceNo(deviceNo);
                    deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(deviceNo);
                    deviceGun = deviceGunMapper.selectDeviceGunByDeviceNoAndState(deviceNo, 0);
                    gun = gunMapper.selectGunByBluetoothMac(deviceGun.getGunMac());
                    OptimizeDeviceLocation optimizeDeviceLocation = new OptimizeDeviceLocation();
                    optimizeDeviceLocation.setDeviceNo(deviceNo);
                    optimizeDeviceLocation.setLongitude(deviceLocation.getLongitude());
                    optimizeDeviceLocation.setLatitude(deviceLocation.getLatitude());
                    optimizeDeviceLocation.setLocationEndTime(deviceLocation.getCreateTime());
                    optimizeDeviceLocation.setMobile(devices.getPhone());
                    optimizeDeviceLocation.setDeviceState(devices.getState());
                    optimizeDeviceLocation.setGunTag(gun.getGunTag());
                    optimizeDeviceLocation.setGunModel(gun.getGunModel());
                    optimizeDeviceLocation.setGunWarehouseName(gun.getWarehouseName());
                    optimizeDeviceLocation.setGunState(gun.getRealTimeState());
                    //optimizeMap.put(device.getDeviceNo(),optimizeDeviceLocation);
                    optimizeList.add(optimizeDeviceLocation);

                    //具体位置
                    location+= BaiDuUtil.getAddress(deviceLocation.getLongitude(),deviceLocation.getLatitude())+"@";
                }
            }
            baseModel.add("devices", optimizeList).add("location", location);;
        } catch (Exception e) {
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("显示有误，联系管理员");
            e.printStackTrace();
        }
        return baseModel;
    }


    @Override
    public int insertDeviceLocation(DeviceLocation deviceLocation) throws Exception {
        return deviceLocationMapper.insert(deviceLocation);
    }

    @Override
    public void removeDeviceLocation(Integer deviceLocationId) throws Exception {
        deviceLocationMapper.deleteByPrimaryKey(deviceLocationId);
    }

    @Override
    public void removeDeviceLocation(List<Integer> deviceLocationIds) throws Exception {
        DeviceLocationExample deviceLocationExample = new DeviceLocationExample();
        DeviceLocationExample.Criteria criteria = deviceLocationExample.createCriteria();
        criteria.andIdIn(deviceLocationIds);
    }

    @Override
    public void updateDeviceLocation(DeviceLocation deviceLocation) throws Exception {
        deviceLocationMapper.updateByPrimaryKeySelective(deviceLocation);
    }

    @Override
    public List<DeviceLocation> findDeviceLocations(DeviceLocation deviceLocations) throws Exception {
        return deviceLocationMapper.selectDeviceLocation(deviceLocations);
    }

    @Override
    public DeviceLocation findDeviceLocation(Integer deviceLocationId) throws Exception {
        return deviceLocationMapper.selectByPrimaryKey(deviceLocationId);
    }


}
