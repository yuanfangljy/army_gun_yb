package com.ybkj.gun.service;


import com.ybkj.common.model.BaseModel;
@SuppressWarnings("all")
public interface SoftwareVersionSerivce {
    //是否需要更新版本号
    public BaseModel checkVersionApp(String versionNum, Integer type) throws Exception;
}
