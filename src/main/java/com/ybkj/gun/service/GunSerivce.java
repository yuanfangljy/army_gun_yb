package com.ybkj.gun.service;

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
}
