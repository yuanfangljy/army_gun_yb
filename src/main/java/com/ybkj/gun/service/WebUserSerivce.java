package com.ybkj.gun.service;

import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.WebUser;

import java.util.List;
@SuppressWarnings("all")
public interface WebUserSerivce {
    public int insertWebUser(WebUser webUser) throws Exception;
    public void removeWebUser(Integer webUserId) throws Exception;
    public void removeWebUser(List<Integer> webUserIds) throws Exception;
    public void updateWebUser(WebUser webUser) throws Exception;
    public List<WebUser> findWebUsers(DeviceLocation webUsers) throws Exception;
    public WebUser findWebUser(Integer webUserId) throws Exception;
}
