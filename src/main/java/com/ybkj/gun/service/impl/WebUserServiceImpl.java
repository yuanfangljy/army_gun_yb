package com.ybkj.gun.service.impl;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.common.util.LoginUtil;
import com.ybkj.gun.mapper.WebUserMapper;
import com.ybkj.gun.model.DeviceLocation;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.WebUserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@SuppressWarnings("all")
@Service
@Slf4j
@Transactional(propagation= Propagation.REQUIRED)
public class WebUserServiceImpl implements WebUserSerivce{

    @Autowired
    private WebUserMapper webUserMapper;

    /**
     * Web用户登录
     * @param userName
     * @param passWord
     * @param httpSession
     * @return
     */
    @Override
    public BaseModel loginWebUser(String userName, String passWord, HttpSession httpSession, HttpServletRequest httpServletRequest) throws Exception {
        BaseModel baseModel=new BaseModel();
        WebUser user= webUserMapper.selectWebUserByUsername(userName);
        String entrtyedPass = DigestUtils.md5DigestAsHex(passWord.getBytes());
        //用户存在
        if(entrtyedPass.endsWith(user.getPassword())){
            //处理登录限制处理，无论处理结果如何，对本次操作没有影响
            LoginUtil.LoginUserSessionIds(userName,httpServletRequest);
            //记录在日志中
            log.info("用户登录处理："+userName+","+httpSession.getId());
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
            baseModel.setErrorMessage("用户登录成功！");
            return baseModel;
        }
        baseModel.setStatus(ResultEnum.ERROR.getCode());
        baseModel.setErrorMessage("用户账号密码不正确！");
        return baseModel;
    }


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
