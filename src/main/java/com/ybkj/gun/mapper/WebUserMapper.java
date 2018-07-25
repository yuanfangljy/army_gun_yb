package com.ybkj.gun.mapper;

import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.model.WebUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebUserMapper {
    long countByExample(WebUserExample example);

    int deleteByExample(WebUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    List<WebUser> selectByExample(WebUserExample example);

    List<WebUser> selectWebUser(WebUser webUser);

    WebUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebUser record, @Param("example") WebUserExample example);

    int updateByExample(@Param("record") WebUser record, @Param("example") WebUserExample example);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);
}