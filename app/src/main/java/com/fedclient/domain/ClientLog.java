package com.fedclient.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户参与任务记录对象 client_log
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class ClientLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long clId;

    /** 参与者id */
    //@Excel(name = "参与者id")
    private Long clientId;

    /** 任务id */
    //@Excel(name = "任务id")
    private Long tpId;

    /** 本轮参与结果，0表示成功结束，1表示失败 */
    //@Excel(name = "本轮参与结果，0表示成功结束，1表示失败")
    private String flag;

    /** 参与设备 */
    //@Excel(name = "参与设备")
    private String device;

    /** 本地迭代次数 */
    //@Excel(name = "本地迭代次数")
    private Long localIter;

    /** 已上传更新模型的评价 */
    //@Excel(name = "已上传更新模型的评价")
    private String modelEvaluate;

    /** 开始时间 */
    //@Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    //@Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setClId(Long clId) 
    {
        this.clId = clId;
    }

    public Long getClId() 
    {
        return clId;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setTpId(Long tpId) 
    {
        this.tpId = tpId;
    }

    public Long getTpId() 
    {
        return tpId;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setDevice(String device) 
    {
        this.device = device;
    }

    public String getDevice() 
    {
        return device;
    }
    public void setLocalIter(Long localIter) 
    {
        this.localIter = localIter;
    }

    public Long getLocalIter() 
    {
        return localIter;
    }
    public void setModelEvaluate(String modelEvaluate) 
    {
        this.modelEvaluate = modelEvaluate;
    }

    public String getModelEvaluate() 
    {
        return modelEvaluate;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clId", getClId())
            .append("clientId", getClientId())
            .append("tpId", getTpId())
            .append("flag", getFlag())
            .append("device", getDevice())
            .append("localIter", getLocalIter())
            .append("modelEvaluate", getModelEvaluate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}
