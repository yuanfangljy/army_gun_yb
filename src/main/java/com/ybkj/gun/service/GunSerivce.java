package com.ybkj.gun.service;

import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.model.Gun;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@SuppressWarnings("all")
public interface GunSerivce {
    public int insertGun(Gun gun) throws Exception;
    public void removeGun(Integer gunId) throws Exception;
    public void removeGun(List<Integer> gunIds) throws Exception;
    public void updateGun(Gun gun) throws Exception;
    public List<Gun> findGuns(Gun guns) throws Exception;
    public Gun findGun(Integer gunId) throws Exception;
    //添加枪支
    BaseModel insertGuns(Gun gun, HttpSession session) throws Exception;
    //修改枪支
    BaseModel updateGuns(Gun gun) throws Exception;
    //分页查询枪支信息，根据警员编号
    List<Gun> findGunsByDeviceNo(String deviceNo) throws Exception;
    //查询枪支编码
    BaseModel selectGunTag(String gunTag);
    //推送mq,gun的启动状态
    BaseModel updategunStartAndStop(String state, String gunMac) throws ParseException;
    //统计枪支离位信息
    List<Gun> findGunOffNormal() throws  Exception;
    //查询枪支射弹基数
    BaseModel selectGunBulletNumber(BaseModel baseModel, String gunMac,@RequestParam(value="pn",defaultValue="1") Integer pn) throws  Exception;
    //根据枪号查询gun
    List<Gun> findGunByGunTags(String gunTag);
    //根据状态查询gun
    BaseModel selectGunByState(Integer state);
}
