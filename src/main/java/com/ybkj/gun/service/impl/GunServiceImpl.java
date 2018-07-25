package com.ybkj.gun.service.impl;

import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.GunSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（枪支信息）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class GunServiceImpl implements GunSerivce{
    @Override
    public int insertGun(Gun gun) throws Exception {
        return 0;
    }

    @Override
    public void removeGun(Integer gunId) throws Exception {

    }

    @Override
    public void removeGun(List<Integer> gunIds) throws Exception {

    }

    @Override
    public void updateGun(Gun gun) throws Exception {

    }

    @Override
    public List<Gun> findGuns(Gun guns) throws Exception {
        return null;
    }

    @Override
    public Gun findGun(Integer gunId) throws Exception {
        return null;
    }
}
