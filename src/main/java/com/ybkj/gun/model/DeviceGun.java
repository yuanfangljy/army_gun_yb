package com.ybkj.gun.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class DeviceGun {
    private Integer id;

    private String deviceNo;

    private String gunMac;

    private Date outWarehouseTime;

    private Date inWarehouseTime;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Date temperanceTime;

    private Integer version;

    //关联属性
    @Getter
    @Setter
    private Gun guns;
    @Getter
    @Setter
    private Device device;
    @Getter
    @Setter
    private DeviceLocation deviceLocation;

    //查询出来的值，重新取别名
    @Getter
    @Setter
    private Integer deviceLocationId;
    @Getter
    @Setter
    private String deviceLocationLatirude;
    @Getter
    @Setter
    private String deviceLocationLongitude;
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
    private Date locationEndTime;
    @Getter
    @Setter
    private String mobile;
    @Getter
    @Setter
    private String gunModel;


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

    public String getGunMac() {
        return gunMac;
    }

    public void setGunMac(String gunMac) {
        this.gunMac = gunMac == null ? null : gunMac.trim();
    }

    public Date getOutWarehouseTime() {
        return outWarehouseTime;
    }

    public void setOutWarehouseTime(Date outWarehouseTime) {
        this.outWarehouseTime = outWarehouseTime;
    }

    public Date getInWarehouseTime() {
        return inWarehouseTime;
    }

    public void setInWarehouseTime(Date inWarehouseTime) {
        this.inWarehouseTime = inWarehouseTime;
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

    public Date getTemperanceTime() {
        return temperanceTime;
    }

    public void setTemperanceTime(Date temperanceTime) {
        this.temperanceTime = temperanceTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "DeviceGun{" +
                "id=" + id +
                ", deviceNo='" + deviceNo + '\'' +
                ", gunMac='" + gunMac + '\'' +
                ", outWarehouseTime=" + outWarehouseTime +
                ", inWarehouseTime=" + inWarehouseTime +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", temperanceTime=" + temperanceTime +
                ", version=" + version +
                ", guns=" + guns +
                ", device=" + device +
                ", deviceLocation=" + deviceLocation +
                ", deviceLocationId=" + deviceLocationId +
                ", deviceLocationLatirude='" + deviceLocationLatirude + '\'' +
                ", deviceLocationLongitude='" + deviceLocationLongitude + '\'' +
                ", gunState=" + gunState +
                ", deviceState=" + deviceState +
                ", gunTag='" + gunTag + '\'' +
                ", gunWarehouseName='" + gunWarehouseName + '\'' +
                ", locationEndTime=" + locationEndTime +
                ", mobile='" + mobile + '\'' +
                ", gunModel='" + gunModel + '\'' +
                '}';
    }
}