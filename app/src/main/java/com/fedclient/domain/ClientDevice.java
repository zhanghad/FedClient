package com.fedclient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 参与者设备对象 client_device
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class ClientDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备标识 */
    private Long deviceId;


    private String deviceCode;

    /** 用户 */
//    @Excel(name = "用户")
    private Long clientId;

    /** 设备（特指智能手机）型号 */
//    @Excel(name = "设备", readConverterExp = "特=指智能手机")
    private String deviceType;

    /** 处理器型号 */
//    @Excel(name = "处理器型号")
    private String proc;

    /** 电池容量，单位为mAh */
//    @Excel(name = "电池容量，单位为mAh")
    private Double batteryCapacity;

    /** 内存容量，单位为GB */
//    @Excel(name = "内存容量，单位为GB")
    private Double ramCapacity;

    /** Android版本 */
//    @Excel(name = "Android版本")
    private String androidVersion;

    /** 操作系统版本 */
//    @Excel(name = "操作系统版本")
    private String os;

    /** 机身存储容量，单位GB */
//    @Excel(name = "机身存储容量，单位GB")
    private Double storage;

    /** 是否连接电源，0表示没有，1表示已连接 */
//    @Excel(name = "是否连接电源，0表示没有，1表示已连接")
    private String powered;

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }
    public void setProc(String proc) 
    {
        this.proc = proc;
    }

    public String getProc() 
    {
        return proc;
    }
    public void setBatteryCapacity(Double batteryCapacity)
    {
        this.batteryCapacity = batteryCapacity;
    }

    public Double getBatteryCapacity()
    {
        return batteryCapacity;
    }
    public void setRamCapacity(Double ramCapacity)
    {
        this.ramCapacity = ramCapacity;
    }

    public Double getRamCapacity()
    {
        return ramCapacity;
    }
    public void setAndroidVersion(String androidVersion) 
    {
        this.androidVersion = androidVersion;
    }

    public String getAndroidVersion() 
    {
        return androidVersion;
    }
    public void setOs(String os) 
    {
        this.os = os;
    }

    public String getOs() 
    {
        return os;
    }
    public void setStorage(Double storage)
    {
        this.storage = storage;
    }

    public Double getStorage()
    {
        return storage;
    }
    public void setPowered(String powered) 
    {
        this.powered = powered;
    }

    public String getPowered() 
    {
        return powered;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clientId", getClientId())
            .append("deviceCode",getDeviceCode())
            .append("deviceType", getDeviceType())
            .append("proc", getProc())
            .append("batteryCapacity", getBatteryCapacity())
            .append("ramCapacity", getRamCapacity())
            .append("androidVersion", getAndroidVersion())
            .append("os", getOs())
            .append("storage", getStorage())
            .append("powered", getPowered())
            .toString();
    }
}
