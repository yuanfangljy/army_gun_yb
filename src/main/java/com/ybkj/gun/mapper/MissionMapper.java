package com.ybkj.gun.mapper;

import com.ybkj.gun.model.Mission;
import com.ybkj.gun.model.MissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MissionMapper {
    long countByExample(MissionExample example);

    int deleteByExample(MissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mission record);

    int insertSelective(Mission record);

    List<Mission> selectByExample(MissionExample example);

    List<Mission> selectMission(Mission mission);

    Mission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByExample(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);
}