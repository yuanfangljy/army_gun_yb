package com.ybkj.gun.service.impl;

import com.ybkj.gun.mapper.SosMessageMapper;
import com.ybkj.gun.model.SosMessage;
import com.ybkj.gun.service.SosMessageSerivce;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SosMessageServiceImpl implements SosMessageSerivce {
    @Autowired
    SosMessageMapper sosMassageMapper;

    /**
     * 根据警员编号查询报警信息
     * @param deviceNo
     * @return
     */
    @Override
    public List<SosMessage> selectSosMassageByDeviceNo(String deviceNo) {

        return sosMassageMapper.selectSosMassageByDeviceNo(deviceNo);
    }
    @Override
    public int insertSosMassage(SosMessage sosMassage) throws Exception {
        return 0;
    }

    @Override
    public void removeSosMassage(Integer sosMassageId) throws Exception {

    }

    @Override
    public void removeSosMassage(List<Integer> sosMassageIds) throws Exception {

    }

    @Override
    public void updateSosMassage(SosMessage sosMassage) throws Exception {

    }

    @Override
    public List<SosMessage> findSosMassages(SosMessage sosMassages) throws Exception {
        return null;
    }

    @Override
    public SosMessage findSosMassage(Integer sosMassageId) throws Exception {
        return null;
    }


}
