package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.SosMessage;

import java.util.List;
@SuppressWarnings("all")
public interface SosMessageSerivce {
    public int insertSosMassage(SosMessage sosMassage) throws Exception;
    public void removeSosMassage(Integer sosMassageId) throws Exception;
    public void removeSosMassage(List<Integer> sosMassageIds) throws Exception;
    public void updateSosMassage(SosMessage sosMassage) throws Exception;
    public List<SosMessage> findSosMassages(SosMessage sosMassages) throws Exception;
    public SosMessage findSosMassage(Integer sosMassageId) throws Exception;
    //根据警员编号查询报警信息
    List<SosMessage> selectSosMassageByDeviceNo(String deviceNo) throws Exception;
    //根据id修改信息
    BaseModel updateSosMassageById(Integer id) throws Exception;
    //统计离线数
    Integer findWarningNumber(Integer state) throws Exception;
}
