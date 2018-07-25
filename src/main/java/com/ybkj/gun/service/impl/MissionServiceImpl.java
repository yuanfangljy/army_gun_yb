package com.ybkj.gun.service.impl;

import com.ybkj.gun.model.Mission;
import com.ybkj.gun.service.MissionSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（推送消息信息）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class MissionServiceImpl implements MissionSerivce{
    @Override
    public int insertMission(Mission mission) throws Exception {
        return 0;
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
