package com.ybkj.gun.service;

import com.ybkj.gun.model.SosMassage;

import java.util.List;
@SuppressWarnings("all")
public interface SosMassageSerivce {
    public int insertSosMassage(SosMassage sosMassage) throws Exception;
    public void removeSosMassage(Integer sosMassageId) throws Exception;
    public void removeSosMassage(List<Integer> sosMassageIds) throws Exception;
    public void updateSosMassage(SosMassage sosMassage) throws Exception;
    public List<SosMassage> findSosMassages(SosMassage sosMassages) throws Exception;
    public SosMassage findSosMassage(Integer sosMassageId) throws Exception;
}
