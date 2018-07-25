package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.WebUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@SuppressWarnings("all")
public interface WebUserSerivce {
    public int insertWebUser(WebUser webUser) throws Exception;
    public void removeWebUser(Integer webUserId) throws Exception;
    public void removeWebUser(List<Integer> webUserIds) throws Exception;
    public void updateWebUser(WebUser webUser) throws Exception;
    public List<WebUser> findWebUsers(DeviceLocation webUsers) throws Exception;
    public WebUser findWebUser(Integer webUserId) throws Exception;
    public BaseModel loginWebUser(String userName, String passWord, HttpSession httpSession,HttpServletRequest httpServletRequest) throws Exception;
}
