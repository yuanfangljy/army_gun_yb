package com.ybkj.gun.mapper;

import com.ybkj.gun.model.SosMassage;
import com.ybkj.gun.model.SosMassageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SosMassageMapper {
    long countByExample(SosMassageExample example);

    int deleteByExample(SosMassageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SosMassage record);

    int insertSelective(SosMassage record);

    List<SosMassage> selectByExample(SosMassageExample example);

    List<SosMassage> selectSosMassage(SosMassage sosMassage);

    SosMassage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SosMassage record, @Param("example") SosMassageExample example);

    int updateByExample(@Param("record") SosMassage record, @Param("example") SosMassageExample example);

    int updateByPrimaryKeySelective(SosMassage record);

    int updateByPrimaryKey(SosMassage record);
}