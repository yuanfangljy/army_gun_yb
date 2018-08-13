package com.ybkj.gun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Device;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.impl.DeviceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("device")
@Slf4j
@Api(value = "/",description = "设备管理功能")
public class DeviceController {

    @Autowired
    DeviceServiceImpl deviceSerivce;


    /**
     * 判断手机号码是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断手机号是否存在",notes = "手机号")
    @RequestMapping(value = "/isInquireMobile",method = RequestMethod.POST)
    public BaseModel isInquireMobile(@RequestParam(value = "mobile",required = true) String mobile) throws Exception {
        BaseModel baseModel = deviceSerivce.selectMobile(mobile);
        //表示手机号码不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("手机号存在！");
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("手机号不存在，可用！");//表示不存在
        }
        return baseModel;
    }


    /**
     * 判断邮箱是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断邮箱是否存在",notes = "邮箱")
    @RequestMapping(value = "/isInquireEmail",method = RequestMethod.POST)
    public BaseModel isInquireEmail(@RequestParam(value = "email",required = true) String email) throws Exception {
        BaseModel baseModel = deviceSerivce.selectEmail(email);
        //表示邮箱不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("邮箱存在！");//表示不存在
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("邮箱不存在，可用！");
        }
        return baseModel;
    }


    /**
     * 判断设备名是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断设备名是否存在",notes = "设备名称")
    @RequestMapping(value = "/isInquireDeviceName",method = RequestMethod.POST)
    public BaseModel isInquireUserName(@RequestParam(value = "deviceName",required = true) String deviceName) throws Exception {
        BaseModel baseModel = deviceSerivce.selectDeviceName(deviceName);
        //表示设备名称不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("设备名存在！");//表示不存在
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("设备名不存在，可用！");
        }
        return baseModel;
    }


    /**
     * 判断设备编码是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value ="判断设备编码是否存在",notes = "设备编码")
    @RequestMapping(value = "/isInquireDeviceNo",method = RequestMethod.POST)
    public BaseModel isInquireDeviceNo(@RequestParam(value = "deviceNo",required = true) String deviceNo) throws Exception {
        BaseModel baseModel = deviceSerivce.selectDeviceNo(deviceNo);
        //表示设备名称不存在
        if (baseModel.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
            baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
            baseModel.setErrorMessage("设备（警员编码）编码存在！");//表示不存在
        }else{
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            baseModel.setErrorMessage("设备（警员编码）名不存在，可用！");
        }
        return baseModel;
    }


    /**
     * 添加设备信息
     * @param device
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "添加设备信息", notes = "设备")
    @RequestMapping(value = "/fortifyDevice",method = RequestMethod.PUT)
    public BaseModel fortifyDevice(@Validated @RequestBody Device device, BindingResult result) throws Exception {
        BaseModel baseModel=new BaseModel();
        //校验字段是否正确
        if(result.hasErrors()){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            List<FieldError> error = result.getFieldErrors();
            for (FieldError fieldError : error) {
                baseModel.getMapResults().put(fieldError.getField(),fieldError.getDefaultMessage());
            }
        }else{
            BaseModel device1 = deviceSerivce.insertDevices(device);
            if (device1.getStatus()==StatusCodeEnum.SUCCESS.getStatusCode()){
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
     * 统计枪支离位信息
     * @return
     */
    @RequestMapping(value = "/inquireDeviceOffLine",method = RequestMethod.POST)
    public BaseModel inquireDeviceOffLine(@RequestParam(value="pn",defaultValue="1") Integer pn,@RequestParam(value = "state",required = true)Integer state) throws Exception {
        BaseModel baseModel=new BaseModel();
        PageHelper.startPage(pn,4);
        //startPage后面紧跟着的这个查询就是一个分页查询
        List<Gun> guns=null;
        if(state==0){
             guns=deviceSerivce.findDeviceOffLine(0);
        }else if(state==2){
             guns=deviceSerivce.findDeviceOffLine(2);
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("请不要暴力篡改数据");
        }
        PageInfo<Gun> page = new PageInfo<Gun>(guns,1);
        baseModel.setErrorMessage("统计成功");
        baseModel.add("pageInfo",page);
        return baseModel;
    }

    /**
     * 统计在线设备
     * @return
     */
    @ApiOperation(value = "设备在线人数", notes = "在线数")
    @RequestMapping(value = "/statisticsOnlineDeivce",method = RequestMethod.POST)
    public BaseModel statisticsOnlineDeivce() throws Exception {
        BaseModel baseModel=new BaseModel();
        Integer number=deviceSerivce.findDeviceOnLine();
        baseModel.add("number",number);
        return baseModel;
    }


    public static void main(String[] args) {
        int a=0;
        String  b=(a>89)?"A":(a>60)?"B":"C";//三目运算符里的嵌套
        System.out.println(b);
    }

}
