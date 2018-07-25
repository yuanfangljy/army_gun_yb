package com.ybkj.gun.service.impl;

import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.WebUserSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（管理端用户）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class WebUserServiceImpl implements WebUserSerivce{
    @Override
    public int insertWebUser(WebUser webUser) throws Exception {
        return 0;
    }

    @Override
    public void removeWebUser(Integer webUserId) throws Exception {

    }

    @Override
    public void removeWebUser(List<Integer> webUserIds) throws Exception {

    }

    @Override
    public void updateWebUser(WebUser webUser) throws Exception {

    }

    @Override
    public List<WebUser> findWebUsers(DeviceLocation webUsers) throws Exception {
        return null;
    }

    @Override
    public WebUser findWebUser(Integer webUserId) throws Exception {
        return null;
    }
}
