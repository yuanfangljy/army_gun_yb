package com.ybkj.gun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybkj.common.activeMq.Consumer;
import com.ybkj.common.activeMq.Producer;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.error.ResultEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.mapper.GunMapper;
import com.ybkj.gun.mapper.WebUserMapper;
import com.ybkj.gun.model.Gun;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.GunSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Description: 功能描述（枪支信息）
 * @Author: 刘家义
 * @CreateDate: 2018/7/24 19:42
 * @UpdateUser: 刘家义
 * @UpdateDate: 2018/7/24 19:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@SuppressWarnings("ALL")
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GunServiceImpl implements GunSerivce {
    @Autowired
    GunMapper gunMapper;
    @Autowired
    WebUserMapper webUserMapper;
    @Autowired
    Producer producer;
    @Autowired
    Consumer consumer;

    /**
     * 添加枪支信息
     *
     * @param gun
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel insertGuns(Gun gun, HttpSession session) throws Exception {
        BaseModel baseModel = new BaseModel();
        Gun device = gunMapper.selectGunByGunTag(gun.getGunTag());
        if (device == null) {
            gun.setState(1);//默认未使用
            gun.setCreateTime(new Date());
            WebUser webUser = webUserMapper.selectWebUserByUsername((String) session.getAttribute("userName"));
            gun.setWebId(webUser.getId());
            gun.setBulletNumber(0);
            gun.setRealTimeState(1);
            final int i = gunMapper.insertSelective(gun);
            if (i != 0) {
                baseModel.setStatus(ResultEnum.SUCCESS.getCode());
                baseModel.setErrorMessage("添加成功");
            } else {
                baseModel.setStatus(ResultEnum.ERROR.getCode());
                baseModel.setErrorMessage("添加失败");
            }
        }else{
            baseModel.setStatus(ResultEnum.ERROR.getCode());
            baseModel.setErrorMessage("该枪支已存在，请重新输入！");
        }
        return baseModel;
    }

    /**
     * 修改枪支信息
     *
     * @param gun
     * @return
     * @throws Exception
     */
    @Override
    public BaseModel updateGuns(Gun gun) throws Exception {
        BaseModel baseModel = new BaseModel();
        int i = gunMapper.updateByPrimaryKeySelective(gun);
        if (i != 0) {
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        } else {
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        }
        return baseModel;
    }

    /**
     * 查询枪支信息
     *
     * @param deviceNo
     * @return
     */
    @Override
    public List<Gun> findGunsByDeviceNo(String deviceNo) throws Exception {
        return gunMapper.selectGunBydevice(deviceNo);
    }
    /**
     * 根据枪号查询枪支信息
     * @param gunTag
     * @return
     */
    @Override
    public List<Gun> findGunByGunTags(String gunTag) {
        return gunMapper.selectGunByGunTags(gunTag);
    }

    /**
     * 根据枪支编码查询
     *
     * @param gunTag
     * @return
     */
    @Override
    public BaseModel selectGunTag(String gunTag) {
        BaseModel baseModel = new BaseModel();
        Gun device = gunMapper.selectGunByGunTag(gunTag);
        if (device == null) {
            baseModel.setStatus(ResultEnum.ERROR.getCode());
        } else {
            baseModel.setStatus(ResultEnum.SUCCESS.getCode());
        }
        return baseModel;
    }

    /**
     *修改离位报警查找启停控制：实际不是后台去修改，是推送到sevice进行响应修改
     * @param state
     * @param gunMac
     * @return
     */
    @Override
    public BaseModel updategunStartAndStop(String state, String gunMac) throws ParseException {
        //send StartAndStop message to Netty
        BaseModel baseModel=new BaseModel();
        BaseModel sendMessageStartAndStop = producer.sendMessageOffNormalAlarmStartAndStop(state,gunMac);
        System.out.println("-----&&&&&------" + sendMessageStartAndStop);
        if (sendMessageStartAndStop.getStatus()!= StatusCodeEnum.Fail.getStatusCode()) {
            //mq推送成功
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
        }
        return baseModel;
    }

    /**
     * 统计枪支离位信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Gun> findGunOffNormal() throws Exception {
        return gunMapper.selectGunOffNormal();
    }

    /**
     * 射弹基数查询
     * @param baseModel
     * @param gunMac
     * @return
     */
    @Override
    public BaseModel selectGunBulletNumber(BaseModel baseModel, String gunMac,@RequestParam(value="pn",defaultValue="1") Integer pn) throws Exception{
        //1.发送射弹数生产者
        BaseModel sendMessageBullet = producer.sendMessageBulletNumberApply(gunMac);
        if (sendMessageBullet.getStatus()!=StatusCodeEnum.Fail.getStatusCode()) {
            //2.是否响应报文
            BaseModel consumerBulletNumber = consumer.manualBulletNumberApply();
            if(consumerBulletNumber.getStatus()!=StatusCodeEnum.Fail.getStatusCode()){
                //3、消费者射弹报文响应，查询数据库
                PageHelper.startPage(pn, 5);
                List<Gun> guns = gunMapper.selectGunOffNormal();
                PageInfo<Gun> page = new PageInfo<Gun>(guns,1);
                baseModel.setErrorMessage("统计成功");
                baseModel.add("pageInfo",page);
            }else{
                baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
                baseModel.setErrorMessage("查询有误!");
            }
        }else{
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("服务出现故障，暂时不能使用!");
        }

        return baseModel;
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
