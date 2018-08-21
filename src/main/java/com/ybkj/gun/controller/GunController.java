package com.ybkj.gun.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.annotation.Token;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.DeviceGunMapper;
import com.ybkj.gun.mapper.WebUserMapper;
import com.ybkj.gun.model.DeviceGun;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.impl.GunServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@Api(value = "/",description = "枪支管理功能")
@SuppressWarnings("all")
@RestController
@RequestMapping("/gun")
public class GunController {

    @Autowired
    GunServiceImpl gunService;
    @Autowired
    WebUserMapper webUserMapper;
    @Autowired
    DeviceGunMapper deviceGunMapper;


    /**
     * 修改离位报警查找启停控制：实际不是后台去修改，是推送到sevice进行响应修改
     * @param state
     * @param gunMac
     * @return
     */
    @RequestMapping(value = "/revampGunStartAndStop",method = RequestMethod.PUT)
    public BaseModel revampGunStartAndStop(@RequestParam(value = "state",required = true) String state,@RequestParam(value = "gunMac",required = true) String gunMac) throws ParseException {
        BaseModel baseModel=new BaseModel();
        if(state=="1" || state=="0") {
            BaseModel gun = gunService.updategunStartAndStop(state, gunMac);
            if (gun.getStatus() == StatusCodeEnum.SUCCESS.getStatusCode()) {
                baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                baseModel.setErrorMessage(gun.getErrorMessage());
            } else {
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage(gun.getErrorMessage());
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力操作");
        }
        return baseModel;
    }



    /**
     * 判断枪支编号是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断枪支编号是否存在",notes = "枪支编号")
    @RequestMapping(value = "/isInquireGuntag",method = RequestMethod.GET)
    public BaseModel isInquireGunTag(@RequestParam(value = "gunTag",required = true) String gunTag) throws Exception {
        BaseModel baseModel = gunService.selectGunTag(gunTag);
        //表示枪支存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("该枪支已存在！");//表示存在
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("该枪支不存在！");
        }
        return baseModel;
    }


    @Token(remove = true)
    @ApiOperation(value = "增添枪支",notes = "增加")
    @RequestMapping(value = "/fortifyGun",method = RequestMethod.POST)
    public BaseModel fortifyGun(@Valid Gun gun , BindingResult result, HttpSession session , HttpServletRequest request, HttpServletResponse response,String token) throws Exception {
        BaseModel baseModel=new BaseModel();
        if (result.hasErrors()){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            for (FieldError fieldError : result.getFieldErrors()) {
                baseModel.getMapResults().put(fieldError.getField(),fieldError.getRejectedValue());
            }
        }else{
            BaseModel gunModel=gunService.insertGuns(gun,session);
            if (gunModel.getStatus()== StatusCodeEnum.SUCCESS.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                baseModel.setErrorMessage(gunModel.getErrorMessage());
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage(gunModel.getErrorMessage());
            }
        }
        return baseModel;
    }


    @ApiOperation(value = "修改枪支",notes = "修改")
    @RequestMapping(value = "/revampGun",method = RequestMethod.PUT)
    public BaseModel revampGun(@Validated @RequestBody Gun gun , BindingResult result) throws Exception {
        BaseModel baseModel=new BaseModel();
        if (result.hasErrors()){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            for (FieldError fieldError : result.getFieldErrors()) {
                baseModel.getMapResults().put(fieldError.getField(),fieldError.getRejectedValue());
            }
        }else{
            BaseModel gunModel=gunService.updateGuns(gun);
            if (gunModel.getStatus()== StatusCodeEnum.SUCCESS.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                baseModel.setErrorMessage("修改成功");
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("失败成功");
            }
        }
        return baseModel;
    }


    /**
     * 对枪支信息查询分页
     * @param pn
     * @param deviceNo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页查询枪支",notes = "枪支", httpMethod = "GET")
    @RequestMapping(value = "/inquireGun",method = RequestMethod.GET)
    public BaseModel inquireGun(@RequestParam(value="pn",defaultValue="1") Integer pn,@RequestParam(value="gunTag",required=false)String gunTag) throws Exception {
        BaseModel baseModel=new BaseModel();
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<Gun> guns=gunService.findGunsByDeviceNo(gunTag);
        String webNames="";
        for (Gun gun : guns) {
            WebUser webUser = webUserMapper.selectByPrimaryKey(gun.getWebId());
            webNames+=webUser.getUserName()+"@";
        }
        //用PageInfo对查询结果进行包装，只需要将pageInfo交给页面就行了
        //封装了，详细的分页信息，包括我们查询出来的数据,传入连续显示的页数
        PageInfo<Gun> page = new PageInfo<Gun>(guns,5);

        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.setErrorMessage("统计成功");
        baseModel.add("pageInfo",page).add("gunTag", gunTag);
        baseModel.add("webNames", webNames);
        return baseModel;
    }

    /**
     * 统计枪支离位信息
     * @return
     */
    @RequestMapping(value = "/inquireGunOffNormal",method = RequestMethod.GET)
    public BaseModel inquireGunOffNormal(@RequestParam(value="pn",defaultValue="1") Integer pn) throws Exception {
        BaseModel baseModel=new BaseModel();
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        String deivceNoGun="";
        List<Gun> guns=gunService.findGunOffNormal();
        for (Gun gun : guns) {
            DeviceGun deviceGun = deviceGunMapper.selectDeviceGunByMacAndState(gun.getBluetoothMac(), 0);
            deivceNoGun+=deviceGun.getDeviceNo()+"@";
        }
        PageInfo<Gun> page = new PageInfo<Gun>(guns,1);
        baseModel.setErrorMessage("统计成功");
        baseModel.add("pageInfo",page).add("deivceNoGun",deivceNoGun);
        return baseModel;
    }
}
