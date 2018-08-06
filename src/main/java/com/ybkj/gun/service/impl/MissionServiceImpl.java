package com.ybkj.gun.service.impl;

import com.ybkj.common.activeMq.Producer;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.DeviceMapper;
import com.ybkj.gun.mapper.MissionMapper;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.Mission;
import com.ybkj.gun.service.MissionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 创建离线协助查找匹配人
     *
     * @param mission
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel insertMission(Mission mission) throws Exception {
        BaseModel baseModel = new BaseModel();
        //在创建协助任务是，判断该警员是不是在协助查找中
        Device device = deviceMapper.selectDeviceNo(mission.getDeviceNo());
        if (device != null) {
            if (device.getState() != 3) {
                BaseModel sendMessageStorage = producer.sendMessageMinistrantFind(mission.getBody());
                //将协助查找报文消息推送到mq队列中
                if (sendMessageStorage.getStatus() != StatusCodeEnum.Fail.getStatusCode()) {
                    //创建时：默认是未处理，未读状态；等待服务器响应信息，发生状态改变
                    mission.setState(2);
                    mission.setReadState(1);
                    final int i = missionMapper.insertSelective(mission);
                    if (i != 0) {
                        baseModel.setStatus(ResultEnum.SUCCESS.getCode());
                        baseModel.setErrorMessage("添加成功");
                    } else {
                        baseModel.setStatus(ResultEnum.ERROR.getCode());
                        baseModel.setErrorMessage("添加失败");

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
