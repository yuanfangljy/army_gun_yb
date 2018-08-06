package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Mission;

import java.util.List;
@SuppressWarnings("all")
public interface MissionSerivce {
    public void removeMission(Integer missionId) throws Exception;
    public void removeMission(List<Integer> missionIds) throws Exception;
    public void updateMission(Mission mission) throws Exception;
    public List<Mission> findMissions(Mission missions) throws Exception;
    public Mission findMission(Integer missionId) throws Exception;
    public BaseModel insertMission(Mission mission) throws Exception;
}
