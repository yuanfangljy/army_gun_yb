package com.ybkj.gun.service.impl;

import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.service.GunSerivce;
import org.springframework.beans.factory.annotation.Autowired;
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
@SuppressWarnings("ALL")
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class GunServiceImpl implements GunSerivce{
    @Autowired
    GunMapper gunMapper;

    /**
     * 添加枪支信息
     * @param gun
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel insertGuns(Gun gun) throws Exception{
        BaseModel baseModel=new BaseModel();
        final int i = gunMapper.insertSelective(gun);
        if (i!=0){
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
        return baseModel;
    }

    /**
     * 修改枪支信息
     * @param gun
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel updateGuns(Gun gun) throws Exception{
        BaseModel baseModel=new BaseModel();
        int i = gunMapper.updateByPrimaryKeySelective(gun);
        if (i!=0){
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
        return baseModel;
    }

    /**
     * 查询枪支信息
     * @param deviceNo
     * @return
     */
    @Override
    public List<Gun> findGunsByDeviceNo(String deviceNo) throws Exception{
        return  gunMapper.selectGunBydevice(deviceNo);
    }

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
