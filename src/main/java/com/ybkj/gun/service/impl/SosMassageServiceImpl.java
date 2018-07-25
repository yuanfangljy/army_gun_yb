package com.ybkj.gun.service.impl;

import com.ybkj.gun.model.SosMassage;
import com.ybkj.gun.service.SosMassageSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Description:  功能描述（报警信息）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:42
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:42
 *@UpdateRemark: 修改内容
 *@Version:      1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class SosMassageServiceImpl implements SosMassageSerivce{
    @Override
    public int insertSosMassage(SosMassage sosMassage) throws Exception {
        return 0;
    }

    @Override
    public void removeSosMassage(Integer sosMassageId) throws Exception {

    }

    @Override
    public void removeSosMassage(List<Integer> sosMassageIds) throws Exception {

    }

    @Override
    public void updateSosMassage(SosMassage sosMassage) throws Exception {

    }

    @Override
    public List<SosMassage> findSosMassages(SosMassage sosMassages) throws Exception {
        return null;
    }

    @Override
    public SosMassage findSosMassage(Integer sosMassageId) throws Exception {
        return null;
    }
}
