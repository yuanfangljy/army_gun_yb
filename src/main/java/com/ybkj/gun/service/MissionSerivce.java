package com.ybkj.gun.service;

import com.ybkj.gun.model.Mission;

import java.util.List;
@SuppressWarnings("all")
public interface MissionSerivce {
    public int insertMission(Mission mission) throws Exception;
    public void removeMission(Integer missionId) throws Exception;
    public void removeMission(List<Integer> missionIds) throws Exception;
    public void updateMission(Mission mission) throws Exception;
    public List<Mission> findMissions(Mission missions) throws Exception;
    public Mission findMission(Integer missionId) throws Exception;
}
