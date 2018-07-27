package com.ybkj.gun.service.impl;

import com.ybkj.common.encryption.MD5;
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
    public BaseModel loginWebUser(String userMobile, String passWord, HttpSession httpSession, HttpServletRequest httpServletRequest) throws Exception {
        BaseModel baseModel=new BaseModel();
        WebUser user= webUserMapper.selectMobile(userMobile);
        System.out.println(user.toString());
        String password =(MD5.Md5(userMobile, passWord)).toString();
        //用户存在
        if(password.endsWith(user.getPassword())){
            //处理登录限制处理，无论处理结果如何，对本次操作没有影响
            LoginUtil.LoginUserSessionIds(userMobile,httpServletRequest);
            //记录在日志中
            log.info("用户登录处理："+userMobile+","+httpSession.getId());
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
            baseModel.setErrorMessage("用户登录成功！");
            return baseModel;
        }
        baseModel.setStatus(ResultEnum.ERROR.getCode());
        baseModel.setErrorMessage("用户账号密码不正确！");
        return baseModel;
    }

    /**
     * 判断手机号码是否存在
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel selectMobile(String mobile) throws Exception {
        BaseModel baseModel=new BaseModel();
        WebUser user = webUserMapper.selectMobile(mobile);
        if(user!=null){
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }else{
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }

    /**
     * 添加web用户
     * @param webUser
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel insertWebUsers(WebUser webUser) throws Exception {
        BaseModel baseModel=new BaseModel();
        Object passWord= MD5.Md5(webUser.getPhone(), webUser.getPassword());
        webUser.setPassword(passWord.toString());
        final int i = webUserMapper.insertSelective(webUser);
        if(i!=0){
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
        return baseModel;
    }

    /**
     * 修改用户密码
     * @param mobile
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public BaseModel updateWebUserBy(WebUser webUser,String newPassword) {
        BaseModel baseModel=new BaseModel();
        WebUser user = webUserMapper.selectMobile(webUser.getPhone());
        if(MD5.Md5(webUser.getPhone(), webUser.getPassword()).toString().equals(user.getPassword())){
            user.setPassword(MD5.Md5(webUser.getPhone(), newPassword).toString());
            int i = webUserMapper.updateByPrimaryKeySelective(user);
            if(i!=0){
                baseModel.setStatus(ResultEnum.SUCCESS.getCode());
            }else{
                baseModel.setStatus(ResultEnum.ERROR.getCode());
            }
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
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
