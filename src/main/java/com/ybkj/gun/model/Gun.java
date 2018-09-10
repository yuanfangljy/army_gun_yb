package com.ybkj.gun.model;

import java.util.Date;

public class Gun {
    private Integer id;

    private Integer webId;

    private String gunTag;

    private String gunModel;

    private Integer gunType;

    private String bluetoothMac;

    private String warehouseName;

    private Integer warehouseId;

    private Integer bulletNumber;

    private Integer totalBulletNumber;

    private String deviceBatteryPower;

    private Integer state;

    private Integer realTimeState;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public String getGunTag() {
        return gunTag;
    }

    public void setGunTag(String gunTag) {
        this.gunTag = gunTag == null ? null : gunTag.trim();
    }

    public String getGunModel() {
        return gunModel;
    }

    public void setGunModel(String gunModel) {
        this.gunModel = gunModel == null ? null : gunModel.trim();
    }

    public Integer getGunType() {
        return gunType;
    }

    public void setGunType(Integer gunType) {
        this.gunType = gunType;
    }

    public String getBluetoothMac() {
        return bluetoothMac;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac == null ? null : bluetoothMac.trim();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getBulletNumber() {
        return bulletNumber;
    }

    public void setBulletNumber(Integer bulletNumber) {
        this.bulletNumber = bulletNumber;
    }

    public Integer getTotalBulletNumber() {
        return totalBulletNumber;
    }

    public void setTotalBulletNumber(Integer totalBulletNumber) {
        this.totalBulletNumber = totalBulletNumber;
    }

    public String getDeviceBatteryPower() {
        return deviceBatteryPower;
    }

    public void setDeviceBatteryPower(String deviceBatteryPower) {
        this.deviceBatteryPower = deviceBatteryPower == null ? null : deviceBatteryPower.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRealTimeState() {
        return realTimeState;
    }

    public void setRealTimeState(Integer realTimeState) {
        this.realTimeState = realTimeState;
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


    @Override
    public String toString() {
        return "Gun{" +
                "id=" + id +
                ", webId=" + webId +
                ", gunTag='" + gunTag + '\'' +
                ", gunModel='" + gunModel + '\'' +
                ", gunType=" + gunType +
                ", bluetoothMac='" + bluetoothMac + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseId=" + warehouseId +
                ", bulletNumber=" + bulletNumber +
                ", totalBulletNumber=" + totalBulletNumber +
                ", deviceBatteryPower='" + deviceBatteryPower + '\'' +
                ", state=" + state +
                ", realTimeState=" + realTimeState +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                '}';
    }
}