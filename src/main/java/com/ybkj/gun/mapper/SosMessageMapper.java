package com.ybkj.gun.mapper;

import com.ybkj.gun.model.SosMessage;
import com.ybkj.gun.model.SosMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SosMessageMapper {
    long countByExample(SosMessageExample example);

    int deleteByExample(SosMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SosMessage record);

    int insertSelective(SosMessage record);

    List<SosMessage> selectByExample(SosMessageExample example);

    SosMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SosMessage record, @Param("example") SosMessageExample example);

    int updateByExample(@Param("record") SosMessage record, @Param("example") SosMessageExample example);

    int updateByPrimaryKeySelective(SosMessage record);

    int updateByPrimaryKey(SosMessage record);

    List<SosMessage> selectSosMassageByDeviceNo(String deviceNo);
    //统计警告数
    Integer selectWarningNumber();
}