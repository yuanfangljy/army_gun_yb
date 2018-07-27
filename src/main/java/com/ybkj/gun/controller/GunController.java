package com.ybkj.gun.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.impl.GunServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "/",description = "枪支管理功能")
@SuppressWarnings("all")
@RestController
@RequestMapping("/gun")
public class GunController {

    @Autowired
    GunServiceImpl gunService;

    @ApiOperation(value = "增添枪支",notes = "增加")
    @RequestMapping(value = "/fortifyGun",method = RequestMethod.PUT)
    public BaseModel fortifyGun(@Validated @RequestBody Gun gun , BindingResult result) throws Exception {
        BaseModel baseModel=new BaseModel();
        if (result.hasErrors()){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            for (FieldError fieldError : result.getFieldErrors()) {
                baseModel.getMapResults().put(fieldError.getField(),fieldError.getRejectedValue());
            }
        }else{
            BaseModel gunModel=gunService.insertGuns(gun);
            if (gunModel.getStatus()== StatusCodeEnum.SUCCESS.getStatusCode()){
                baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                baseModel.setErrorMessage("添加成功");
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("添加成功");
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



    @ApiOperation(value = "分页查询枪支",notes = "枪支", httpMethod = "POST")
    @RequestMapping(value = "/inquireGun",method = RequestMethod.POST)
    public BaseModel inquireGun(@RequestParam(value="pn",defaultValue="1") Integer pn,@RequestParam(value="deviceNo",required=false)String deviceNo) throws Exception {
        BaseModel baseModel=new BaseModel();
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<Gun> guns=gunService.findGunsByDeviceNo(deviceNo);
        //用PageInfo对查询结果进行包装，只需要将pageInfo交给页面就行了
        //封装了，详细的分页信息，包括我们查询出来的数据,传入连续显示的页数
        PageInfo<Gun> page = new PageInfo<Gun>(guns,5);

        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.setErrorMessage("统计成功");
        baseModel.add("adverPageInfo",page).add("deviceNo", deviceNo);
        baseModel.add("guns", guns);
        return baseModel;
    }


}
