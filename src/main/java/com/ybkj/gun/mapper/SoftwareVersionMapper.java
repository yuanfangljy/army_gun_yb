package com.ybkj.gun.mapper;

import com.ybkj.gun.model.SoftwareVersion;
import com.ybkj.gun.model.SoftwareVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SoftwareVersionMapper {
    long countByExample(SoftwareVersionExample example);

    int deleteByExample(SoftwareVersionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SoftwareVersion record);

    int insertSelective(SoftwareVersion record);

    List<SoftwareVersion> selectByExample(SoftwareVersionExample example);

    SoftwareVersion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SoftwareVersion record, @Param("example") SoftwareVersionExample example);

    int updateByExample(@Param("record") SoftwareVersion record, @Param("example") SoftwareVersionExample example);

    int updateByPrimaryKeySelective(SoftwareVersion record);

    int updateByPrimaryKey(SoftwareVersion record);

    // 根据手机类型判断该类型是否存在
    SoftwareVersion checkVersionApp(Integer type);
}