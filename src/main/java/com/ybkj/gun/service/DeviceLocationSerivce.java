package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.DeviceLocation;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@Description:  功能描述（设备地点）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 20:11
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 20:11
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@SuppressWarnings("all")
public interface DeviceLocationSerivce {

    public int insertDeviceLocation(DeviceLocation deviceLocation) throws Exception;
    public void removeDeviceLocation(Integer deviceLocationId) throws Exception;
    public void removeDeviceLocation(List<Integer> deviceLocationIds) throws Exception;
    public void updateDeviceLocation(DeviceLocation deviceLocation) throws Exception;
    public List<DeviceLocation> findDeviceLocations(DeviceLocation deviceLocations) throws Exception;
    public DeviceLocation findDeviceLocation(Integer deviceLocationId) throws Exception;

    //根据时间查询枪支的轨迹
    List<DeviceLocation> findDeviceLocationTrajectory(Map<String, Object> map);

    List<DeviceLocation> findRoundOnline(String deviceNo,String lng,String lag) throws Exception;



    /**
     * 优化sql,显示地图上的实时数据?
     * 1、第一步：查询所有在线的警员---device
     *    select a.device_no from device as a where  a.state=0      1
     * 2、第二步：根据查询在线的警员,通过device_gun查询所有已经出库的警员（state为0的），要进行去重（device_no）
     *    select DISTINCT b.device_no from device_gun as b where  b.device_no=a.device_no and b.state=0
     * 3、第三步：根据以上两步，得出了最终的device_no，再去device__location得到最新的心跳记录
     *    select max(c.id) from device_location as c where c.device_no =b.device_no
     *    select d.device_no,d.latitude,d.longitude,d.stat from device_location as d where d.id=c.id
     *
     * 4、第四步：根据3得到的device_no，查询mac，在gun表中再去得到该警员使用枪的相关信息
     *    select e.mac from device_gun as e where e.device_no=d.device_no
     *
     *    select f.gun_tag,f.gun_model,f.bluetooth_mac,
     *    f.bullet_number,f.state,f.real_time_state from gun f where f.mac=e.mac
     *
     */
    public BaseModel optimizeDeviceLocation(String deviceNo) throws Exception;

    /**
     * 优化协助查找最近的5个在线的警员信息？
     * 1、查询最近的五个经纬度和设备号（device_location），得到设备号，进行出重
     *    select
     * 2、根据得到的最近五个设备号，去(device去筛选状态为0)的device_no
     * 3、根据2中得到的最终device_no，去判断用户有没有出库（根据state=0）；
     *    如果为空，就执行4；
     *    否则：就执行5
     * 4、根据2中得到的最终device_no,去device_location中得到最新的数据（这个是用户在线，未出库）
     * 5、根据deive_no在device_gun中的到mac，再根据mac在gun中得到相关信息；
     *    还要根据device_no在device_location和device中的到相关信息
     */
    BaseModel optimizeRoundOnline(String deviceNo,String lng,String lag,String lostGun) throws Exception;


    /**
     * 优化轨迹查询？
     * 1、根据传入的枪号，在gun表中获取到对应的mac
     * 2、再根据mac，在device_gun中获取到所有的设备号，进行去重
     * 3、根据设备号，在device_location中，根据state=0，时间，查询到最终的轨迹
     * @param map
     * @return
     */
    BaseModel optimizeDeviceLocationTrajectory(String gunTag,String startTime,String endTime) throws Exception;


    /**
     * 优化枪列表的实时位置信息？
     * 1、既然是枪，就在gun表中，查询所以state=0的枪支信息
     * 2、根据查询出来枪的mac，在device_gun,根据mac和状态为state=0得到在线的设备号device_no
     * 3、再根据deviceNo在device_location中的到最新的设备记录
     * @return
     * @throws Exception
     */
    BaseModel optimizeGunLocation(Integer pn,Integer pageSize,String deviceNo) throws Exception;

    /**
     * 实时显示列表相关信息
     * @param deviceNo
     * @return
     * @throws Exception
     */
    public List<DeviceLocation> selectOnLineGun(String deviceNo) throws Exception;
}
