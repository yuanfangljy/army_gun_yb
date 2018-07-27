package com.ybkj.gun.model;

import java.util.Date;

public class DeviceGun {
    private Integer id;

    private Integer deviceNo;

    private Integer gunMac;

    private Date outWarehouseTime;

    private Date inWarehouseTime;

    private String state;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    //关联属性
    private Gun guns;

    public Gun getGun() {
        return guns;
    }

    public void setGun(Gun guns) {
        this.guns = guns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Integer deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getGunMac() {
        return gunMac;
    }

    public void setGunMac(Integer gunMac) {
        this.gunMac = gunMac;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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