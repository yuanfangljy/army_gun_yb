package com.ybkj.common.model;

import lombok.Data;

import java.util.Date;

/**
 * 优化实时显示设备位置
 */
@SuppressWarnings("ALL")
@Data
public class OptimizeDeviceLocation {

    private String deviceNo;//设备号
    private String latitude;//纬度
    private String longitude;//经度
    private String mobile;  //电话号码
    private Integer deviceState;//设备状态
    private String gunTag;//枪号
    private String gunModel;//枪型号
    private String gunWarehouseName;//库存名
    private Integer gunState;//枪的动态状态
    private Date locationEndTime;//最后经纬度的时间
    private String jili;//距离

    private Integer gunType;//枪的类型
    private String batteryPower; //随行设备电量
    private String deviceBatteryPower; //离位报警设置电量
}
