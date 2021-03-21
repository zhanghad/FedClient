package com.fedclient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务配置信息对象 task_config
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class TaskConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务配置标识 */
    private Long tcId;

    /** 配置名称 */
//    @Excel(name = "配置名称")
    private String tcName;

    /** 任务描述 */
//    @Excel(name = "任务描述")
    private String tcDescription;

    /** 任务需要的数据格式描述 */
//    @Excel(name = "任务需要的数据格式描述")
    private String dataFormat;

    /** 硬件设备要求 */
//    @Excel(name = "硬件设备要求")
    private String deviceRequire;

    /** 预训练模型路径 */
//    @Excel(name = "预训练模型路径")
    private String modelPath;

    /** 服务端测试数据路径 */
//    @Excel(name = "服务端测试数据路径")
    private String testData;

    /** 任务运行所需最少参与者数量 */
//    @Excel(name = "任务运行所需最少参与者数量")
    private Long minClient;

    /** 任务运行可支持的最大用户数量 */
//    @Excel(name = "任务运行可支持的最大用户数量")
    private Long maxClient;

    /** 每次全局迭代的本地迭代次数 */
//    @Excel(name = "每次全局迭代的本地迭代次数")
    private String localIterations;

    /** 全局迭代次数 */
//    @Excel(name = "全局迭代次数")
    private String globalEpoch;

    /** 目标精度，单位为% */
//    @Excel(name = "目标精度，单位为%")
    private String targetAccuracy;

    /** 0表示以固定迭代次数作为结束标志，1表示以达到目标精度为结束标志 */
//    @Excel(name = "0表示以固定迭代次数作为结束标志，1表示以达到目标精度为结束标志")
    private String finishFlag;

    /** 0表示正常存在，2表示已被删除 */
    private String delFlag;

    /** 0表示正常，1表示隐藏 */
//    @Excel(name = "0表示正常，1表示隐藏")
    private String status;

    public void setTcId(Long tcId) 
    {
        this.tcId = tcId;
    }

    public Long getTcId() 
    {
        return tcId;
    }
    public void setTcName(String tcName) 
    {
        this.tcName = tcName;
    }

    public String getTcName() 
    {
        return tcName;
    }
    public void setTcDescription(String tcDescription) 
    {
        this.tcDescription = tcDescription;
    }

    public String getTcDescription() 
    {
        return tcDescription;
    }
    public void setDataFormat(String dataFormat) 
    {
        this.dataFormat = dataFormat;
    }

    public String getDataFormat() 
    {
        return dataFormat;
    }
    public void setDeviceRequire(String deviceRequire) 
    {
        this.deviceRequire = deviceRequire;
    }

    public String getDeviceRequire() 
    {
        return deviceRequire;
    }
    public void setModelPath(String modelPath) 
    {
        this.modelPath = modelPath;
    }

    public String getModelPath() 
    {
        return modelPath;
    }
    public void setTestData(String testData) 
    {
        this.testData = testData;
    }

    public String getTestData() 
    {
        return testData;
    }
    public void setMinClient(Long minClient) 
    {
        this.minClient = minClient;
    }

    public Long getMinClient() 
    {
        return minClient;
    }
    public void setMaxClient(Long maxClient) 
    {
        this.maxClient = maxClient;
    }

    public Long getMaxClient() 
    {
        return maxClient;
    }
    public void setLocalIterations(String localIterations) 
    {
        this.localIterations = localIterations;
    }

    public String getLocalIterations() 
    {
        return localIterations;
    }
    public void setGlobalEpoch(String globalEpoch) 
    {
        this.globalEpoch = globalEpoch;
    }

    public String getGlobalEpoch() 
    {
        return globalEpoch;
    }
    public void setTargetAccuracy(String targetAccuracy) 
    {
        this.targetAccuracy = targetAccuracy;
    }

    public String getTargetAccuracy() 
    {
        return targetAccuracy;
    }
    public void setFinishFlag(String finishFlag) 
    {
        this.finishFlag = finishFlag;
    }

    public String getFinishFlag() 
    {
        return finishFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tcId", getTcId())
            .append("tcName", getTcName())
            .append("tcDescription", getTcDescription())
            .append("dataFormat", getDataFormat())
            .append("deviceRequire", getDeviceRequire())
            .append("modelPath", getModelPath())
            .append("testData", getTestData())
            .append("minClient", getMinClient())
            .append("maxClient", getMaxClient())
            .append("localIterations", getLocalIterations())
            .append("globalEpoch", getGlobalEpoch())
            .append("targetAccuracy", getTargetAccuracy())
            .append("finishFlag", getFinishFlag())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
