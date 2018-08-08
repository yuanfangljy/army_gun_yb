package com.ybkj.gun.service.impl;

import com.ybkj.common.activeMq.Producer;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.DataTool;
import com.ybkj.gun.mapper.*;
import com.ybkj.gun.model.*;
import com.ybkj.gun.service.MissionSerivce;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 功能描述（协助查询推送消息信息）
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
public class MissionServiceImpl implements MissionSerivce {
    @Autowired
    MissionMapper missionMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    Producer producer;
    @Autowired
    DeviceLocationMapper deviceLocationMapper;
    @Autowired
    GunMapper gunMapper;
    @Autowired
    WebUserMapper webUserMapper;

    /**
     * 创建离线协助查找匹配人
     * @param assistDeviceNo  协助查找警员编号
     * @param lostDeviceNo    丢失警员编号
     * @param lostGunTag         丢失枪号
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel insertMission(String assistDeviceNo,String lostDeviceNo,String lostGunTag) throws Exception {
        BaseModel baseModel = new BaseModel();
        //1、根据丢失的警员编号来查找他的电话号码
        Device lostdevice = deviceMapper.selectDeviceNo(lostDeviceNo);
        if(lostdevice==null){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力更改数据");
        }
        //2、根据丢失的警员枪号，枪支的蓝牙编号
        final Gun gun = gunMapper.selectGunByGunTag(lostGunTag);
        System.out.println("--"+gun.getBluetoothMac());
        if(gun==null){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力更改数据");
        }
        //3、根据警员编号，查询出deivce_location最新的信息，获取到丢失用户的经纬度,最后时间
        DeviceLocation deviceLocation = deviceLocationMapper.selectDeviceLocationByDeviceNoNewest(lostDeviceNo);
        if(deviceLocation==null){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力更改数据");
        }
        //4、封装boby数据
        Map map = new HashMap();
        map.put("assDeviceNo", assistDeviceNo);
        map.put("bluetoothMac", gun.getBluetoothMac());
        map.put("lo", deviceLocation.getLongitude());
        map.put("la",  deviceLocation.getLatitude());
        map.put("lostDeviceNo",lostDeviceNo);
        map.put("lostphone", lostdevice.getPhone());
        map.put("lostTime", DataTool.dateToString(deviceLocation.getCreateTime()));
        map.put("lostGunTag",lostGunTag);
        JSONObject json = JSONObject.fromObject(map);
        //5、新增mission数据
        Mission mission=new Mission();
        //在创建协助任务是，判断该警员是不是在协助查找中
        Device device = deviceMapper.selectDeviceNo(assistDeviceNo);
        if (device != null) {
            if (device.getState() != 3) {
                BaseModel sendMessageStorage = producer.sendMessageMinistrantFind(json);
                //将协助查找报文消息推送到mq队列中
                if (sendMessageStorage.getStatus() != StatusCodeEnum.Fail.getStatusCode()) {
                    //创建时：默认是未处理，未读状态；等待服务器响应信息，发生状态改变
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    WebUser webUser = webUserMapper.selectWebUserByUsername((String) request.getSession().getAttribute("userName"));
                    mission.setDeviceNo(assistDeviceNo);
                    mission.setWebUserId(webUser.getId());
                    mission.setSendTime(new Date());
                    mission.setType(0);
                    mission.setState(2);
                    mission.setReadState(1);
                    mission.setCreateTime(new Date());
                    mission.setBody(json.toString());
                    final int i = missionMapper.insertSelective(mission);
                    if (i != 0) {
                        baseModel.setStatus(ResultEnum.SUCCESS.getCode());
                        baseModel.setErrorMessage("协助申请中...");
                    } else {
                        baseModel.setStatus(ResultEnum.ERROR.getCode());
                        baseModel.setErrorMessage("申请失败...s");
                    }
                } else {
                    baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                    baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
                }
            } else {
                baseModel.setStatus(ResultEnum.ERROR.getCode());
                baseModel.setErrorMessage("该警员正在协助中，请从新选择协助警员");
            }
        } else {
            baseModel.setStatus(ResultEnum.ERROR.getCode());
            baseModel.setErrorMessage("该警员不存在");
        }

        return baseModel;
    }


    @Override
    public void removeMission(Integer missionId) throws Exception {

    }

    @Override
    public void removeMission(List<Integer> missionIds) throws Exception {

    }

    @Override
    public void updateMission(Mission mission) throws Exception {

    }

    @Override
    public List<Mission> findMissions(Mission missions) throws Exception {
        return null;
    }

    @Override
    public Mission findMission(Integer missionId) throws Exception {
        return null;
    }
}
