package com.fedclient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 任务参与者对象 task_client
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class TaskClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long tpId;

    /** 参与者id */
    private Long clientId;

    public void setTpId(Long tpId) 
    {
        this.tpId = tpId;
    }

    public Long getTpId() 
    {
        return tpId;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tpId", getTpId())
            .append("clientId", getClientId())
            .toString();
    }
}
