package com.ybkj.gun.mapper;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.model.WebUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("all")
public interface WebUserMapper {
    long countByExample(WebUserExample example);

    int deleteByExample(WebUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    List<WebUser> selectByExample(WebUserExample example);

    List<WebUser> selectWebUser(WebUser webUser);

    WebUser selectWebUserByUsername(String userName);
    //手机号是否存在
    WebUser selectMobile(String mobile) ;
    //WebUser selectWebUserByMobile(String userMobile);

    WebUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebUser record, @Param("example") WebUserExample example);

    int updateByExample(@Param("record") WebUser record, @Param("example") WebUserExample example);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);



}