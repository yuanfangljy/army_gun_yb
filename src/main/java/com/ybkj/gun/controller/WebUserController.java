package com.ybkj.gun.controller;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.impl.WebUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *@Description:  功能描述（WebUser用户功能模块）
 *@Author:       刘家义
 *@CreateDate:   2018/7/26 9:57
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/26 9:57
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
@RestController
@RequestMapping("/webUser")
@Api(value = "/",description = "Web用户信息功能")
public class WebUserController {

    @Autowired
    private WebUserServiceImpl webUserService;

    /**
     * web用户登录
     * @return
     */
    @ApiOperation(value = "web用户列表",notes = "登录")
    @RequestMapping(value = "/webUserLogin",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel webUserLogin(@RequestParam String userName, @RequestParam String passWord, HttpServletRequest request, HttpSession session) throws Exception {
        BaseModel baseModel=new BaseModel();
        baseModel = webUserService.loginWebUser(userName, passWord, session, request);
        if(ResultEnum.SUCCESS.getCode() == baseModel.getStatus()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("用户登录成功！");
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("账号或密码错误！");
        }
        return baseModel;
    }


    /**
     * 判断手机号码是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断手机号是否存在",notes = "手机号")
    @RequestMapping(value = "/isSelectMobile",method = RequestMethod.GET)
    public BaseModel isSelectMobile(@RequestParam(value = "mobile",required = true) String mobile) throws Exception {
        BaseModel baseModel = webUserService.selectMobile(mobile);
        //表示手机号码不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("校验成功！");
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("该手机号已存在！");
        }
        return baseModel;
    }



    /**
     * 判断用户名是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断用户名是否存在",notes = "web用户名")
    @RequestMapping(value = "/isSelectUserName",method = RequestMethod.GET)
    public BaseModel isSelectUserName(@RequestParam(value = "userName",required = true) String userName) throws Exception {
        BaseModel baseModel = webUserService.selectUserName(userName);
        //表示用户名不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("校验成功！");
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("该用户名已存在！");
        }
        return baseModel;
    }

    /**
     * web用户进行注册
     * @param webUser
     * @return
     */
    @ApiOperation(value ="web用户注册",notes = "注册")
    @RequestMapping(value = "/registerWebUser",method = RequestMethod.POST)
    public BaseModel registerWebUser(@Validated @RequestBody WebUser webUser, BindingResult result) throws Exception {
        BaseModel baseModel=new BaseModel();
        //校验字段是否正确
        if(result.hasErrors()){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            List<FieldError> error = result.getFieldErrors();
            for (FieldError fieldError : error) {
                baseModel.getMapResults().put(fieldError.getField(),fieldError.getDefaultMessage());
            }
        }else{
            BaseModel webUsers = webUserService.insertWebUsers(webUser);
            if (webUsers.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                baseModel.setErrorMessage("注册成功！");
            }else{
                baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
                baseModel.setErrorMessage("注册失败！");
            }
        }
        return baseModel;
    }

    /**
     * 修改webUser密码
     * @param mobile
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation(value = "修改webUser密码",notes = "密码")
    @RequestMapping(value = "/updateWebUserPassword",method = RequestMethod.PUT)
    public BaseModel updateWebUserPassword(@RequestParam(value = "mobile",required = true) String mobile, @RequestParam(value = "password",required = true) String password, @RequestParam(value = "newPassword",required = true)String newPassword){
        BaseModel baseModel=new BaseModel();
        WebUser webUser=new WebUser();
        webUser.setPhone(mobile);
        webUser.setPassword(password);
        BaseModel resutl= webUserService.updateWebUserBy(webUser,newPassword);
        if (resutl.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("密码修改成功！");
        }else{
           /*if (result.hasErrors()){
                baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
                baseModel.getMapResults().put(result.getFieldError().getField(),result.getFieldError().getDefaultMessage());
                baseModel.setErrorMessage("旧密码格式不正确！");
            }else*/
            String regx="(^[a-zA-Z0-9_-]{6,18}$)";
            if (!newPassword.matches(regx)){
                baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
                baseModel.setErrorMessage("新密码格式不正确！");
            }else{
                baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
                baseModel.setErrorMessage("旧密码格式不正确！");
            }

        }
        return baseModel;
    }
}
