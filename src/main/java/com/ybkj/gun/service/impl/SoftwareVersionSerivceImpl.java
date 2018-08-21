package com.ybkj.gun.service.impl;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.SoftwareVersionMapper;
import com.ybkj.gun.model.SoftwareVersion;
import com.ybkj.gun.service.SoftwareVersionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftwareVersionSerivceImpl implements SoftwareVersionSerivce{

    @Autowired
    private SoftwareVersionMapper softwareVersionMapper;
    /**
     * @Description 检查是否需要更新
     * @return
     * @author 刘家义
     * @date 2018年8月21日
     */
    @Override
    public BaseModel checkVersionApp(String versionNum, Integer type){
        BaseModel baseModel =new BaseModel();
        if (null ==type ) {
            baseModel.setStatus(10001);
            baseModel.setErrorMessage("类型不能为空");
            return baseModel;
        } else if (null == versionNum
                || "".equals(versionNum)) {
            baseModel.setStatus(10002);
            baseModel.setErrorMessage("当前版本号不能为空");
            return baseModel;
        }
        //
        SoftwareVersion versions = softwareVersionMapper.checkVersionApp(type);
        baseModel.setData(versions);
        if( null == versions ){
            baseModel.setStatus(10001);
            baseModel.setErrorMessage("查询最新版本号出错，请联系管理员");
            return baseModel;
        }

        if( !versions.getVersionNum().equals(versionNum) ){
            Integer firstNumNow = Integer.parseInt(versionNum.substring(0, 1));
            Integer firstNumOld = Integer.parseInt(versions.getVersionNum().substring(0,1));
            Float nextNumNow = Float.valueOf(versionNum.substring(2, versionNum.length()));
            Float nextNumOld = Float.valueOf(versions.getVersionNum().substring(2,versions.getVersionNum().length()));

            if(firstNumNow> firstNumOld){
                return baseModel;
            }else if(nextNumNow<nextNumOld){
                baseModel.setStatus(102);
                baseModel.setErrorMessage("您的版本号过低，请尽快升级");
                return baseModel;
            }
        }
        return baseModel;
    }
}
