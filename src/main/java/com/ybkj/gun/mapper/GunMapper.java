package com.ybkj.gun.mapper;

import com.ybkj.gun.model.Gun;
import com.ybkj.gun.model.GunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GunMapper {
    long countByExample(GunExample example);

    int deleteByExample(GunExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Gun record);

    int insertSelective(Gun record);

    List<Gun> selectByExample(GunExample example);

    Gun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Gun record, @Param("example") GunExample example);

    int updateByExample(@Param("record") Gun record, @Param("example") GunExample example);

    int updateByPrimaryKeySelective(Gun record);

    int updateByPrimaryKey(Gun record);

    //根据警员编号
    List<Gun> selectGunBydevice(String gunTag);
    List<Gun> selectGunByGunTags(String gunTag);
    //根据警员编号查询
    Gun selectGunByGunTag(String gunTag);

    Gun selectGunByBluetoothMac(String bluetoothMac);
    //统计枪支离位信息
    List<Gun> selectGunOffNormal();

    List<Gun> findSelectByState(Integer state);

    List<Gun> findSelectByStates();


    List<Gun> findSelectAllByState(Integer state);
}