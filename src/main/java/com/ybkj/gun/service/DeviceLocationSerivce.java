package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Device;
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

}
