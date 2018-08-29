package com.ybkj.gun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class DeviceLocation {
    private Integer id;

    private String deviceNo;

    private String latitude;

    private String longitude;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer version;


    @Getter
    @Setter
    private Integer juli;

    //关联属性
    @Getter
    @Setter
    private Gun guns;
    @Getter
    @Setter
    private Device device;
    @Getter
    @Setter
    private DeviceGun deviceGun;


    //查询出来的值，重新取别名

    @Getter
    @Setter
    private Integer gunState;
    @Getter
    @Setter
    private Integer deviceState;
    @Getter
    @Setter
    private String gunTag;
    @Getter
    @Setter
    private String  gunWarehouseName;
    @Getter
    @Setter
    private String mobile;
    @Getter
    @Setter
    private String gunModel;
  /*  @Getter
    @Setter
    private Date beginTime;
    @Getter
    @Setter
    private Data endTime;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}