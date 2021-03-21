package com.fedclient.domain;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 参与者信息对象 client
 * 
 * @author zhanghad
 * @date 2021-02-19
 */
public class Client extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参与者id */
    private Long clientId;

    /** 用户名 */
    //@Excel(name = "用户名")
    private String clientName;

    /** 登录名 */
    //@Excel(name = "登录名")
    private String loginName;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 邮箱 */
    //@Excel(name = "邮箱")
    private String email;

    /** 手机号 */
    //@Excel(name = "手机号")
    private String phonenumber;

    /** 用户状态 */
    //@Excel(name = "用户状态")
    private String status;

    /** 头像路径 */
    private String avator;

    /** 性别 */
    //@Excel(name = "性别")
    private String sex;

    /** 删除标志 */
    private String delFlag;

    /** 创建时间 */
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建者 */
    private String creatBy;

    /** 用户参与任务记录信息 */
    private List<ClientLog> clientLogList;

    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAvator(String avator) 
    {
        this.avator = avator;
    }

    public String getAvator() 
    {
        return avator;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }
    public void setCreatBy(String creatBy) 
    {
        this.creatBy = creatBy;
    }

    public String getCreatBy() 
    {
        return creatBy;
    }

    public List<ClientLog> getClientLogList()
    {
        return clientLogList;
    }

    public void setClientLogList(List<ClientLog> clientLogList)
    {
        this.clientLogList = clientLogList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clientId", getClientId())
            .append("clientName", getClientName())
            .append("loginName", getLoginName())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("status", getStatus())
            .append("avator", getAvator())
            .append("sex", getSex())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("creatTime", getCreatTime())
            .append("creatBy", getCreatBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("clientLogList", getClientLogList())
            .toString();
    }


    private byte[] avatarImage;

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }



}
