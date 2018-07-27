package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Gun;

import java.util.List;

@SuppressWarnings("all")
public interface GunSerivce {
    public int insertGun(Gun gun) throws Exception;
    public void removeGun(Integer gunId) throws Exception;
    public void removeGun(List<Integer> gunIds) throws Exception;
    public void updateGun(Gun gun) throws Exception;
    public List<Gun> findGuns(Gun guns) throws Exception;
    public Gun findGun(Integer gunId) throws Exception;
    //添加枪支
    BaseModel insertGuns(Gun gun) throws Exception;
    //修改枪支
    BaseModel updateGuns(Gun gun) throws Exception;
    //分页查询枪支信息，根据警员编号
    List<Gun> findGunsByDeviceNo(String deviceNo) throws Exception;
}
