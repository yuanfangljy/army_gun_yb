package com.ybkj.gun.service;

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
    List<SosMessage> selectSosMassageByDeviceNo(String deviceNo);
}
