package com.fedclient.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 联邦任务日志对象 task_log
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class TaskLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务日志id */
    private Long tlId;

    /** 任务id */
//    @Excel(name = "任务id")
    private Long tpId;

    /** 模型训练的全局迭代次序 */
//    @Excel(name = "模型训练的全局迭代次序")
    private String trainEpoch;

    /** 模型评价 */
//    @Excel(name = "模型评价")
    private String modelEvaluate;

    /** 参与本轮训练的用户 */
//    @Excel(name = "参与本轮训练的用户")
    private String clientStart;

    /** 成功结束本轮的用户 */
//    @Excel(name = "成功结束本轮的用户")
    private String clientSuccess;

    /** 0表示成功结束，1表示未达到结束目标，2表示中途被暂停，3表示被中止 */
//    @Excel(name = "0表示成功结束，1表示未达到结束目标，2表示中途被暂停，3表示被中止")
    private String flag;

    /** 开始时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**  */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setTlId(Long tlId) 
    {
        this.tlId = tlId;
    }

    public Long getTlId() 
    {
        return tlId;
    }
    public void setTpId(Long tpId) 
    {
        this.tpId = tpId;
    }

    public Long getTpId() 
    {
        return tpId;
    }
    public void setTrainEpoch(String trainEpoch) 
    {
        this.trainEpoch = trainEpoch;
    }

    public String getTrainEpoch() 
    {
        return trainEpoch;
    }
    public void setModelEvaluate(String modelEvaluate) 
    {
        this.modelEvaluate = modelEvaluate;
    }

    public String getModelEvaluate() 
    {
        return modelEvaluate;
    }
    public void setClientStart(String clientStart) 
    {
        this.clientStart = clientStart;
    }

    public String getClientStart() 
    {
        return clientStart;
    }
    public void setClientSuccess(String clientSuccess) 
    {
        this.clientSuccess = clientSuccess;
    }

    public String getClientSuccess() 
    {
        return clientSuccess;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
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
            .append("tlId", getTlId())
            .append("tpId", getTpId())
            .append("trainEpoch", getTrainEpoch())
            .append("modelEvaluate", getModelEvaluate())
            .append("clientStart", getClientStart())
            .append("clientSuccess", getClientSuccess())
            .append("flag", getFlag())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}
