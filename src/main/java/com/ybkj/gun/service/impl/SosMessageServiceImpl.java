package com.ybkj.gun.service.impl;

import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
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

    /**
     * 修改警告信息
     * @param id
     * @return
     */
    @Override
    public BaseModel updateSosMassageById(Integer id) {
        BaseModel baseModel=new BaseModel();
        SosMessage sosMessage = sosMassageMapper.selectByPrimaryKey(id);
        if(sosMessage!=null){
            sosMessage.setState(0);
            int i = sosMassageMapper.updateByPrimaryKeySelective(sosMessage);
            if(i!=1){
                baseModel.setStatus(ResultEnum.ERROR.getCode());
                baseModel.setErrorMessage("修改失败");
            }else {
                baseModel.setStatus(ResultEnum.SUCCESS.getCode());
                baseModel.setErrorMessage("修改成功");
            }
        }else {
            baseModel.setStatus(ResultEnum.ERROR.getCode());
            baseModel.setErrorMessage("该警告信息不存在");
        }
        return baseModel;
    }

    /**
     * 统计警告数
     * @return
     * @throws Exception
     */
    @Override
    public Integer findWarningNumber(Integer state) throws Exception {
        return sosMassageMapper.selectWarningNumber();
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
