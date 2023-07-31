package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮箱任务对象 mailbox_task
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public class MailboxTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮箱账号 */
    @Excel(name = "邮箱账号")
    private String account;

    /** 邮箱密码 */
    @Excel(name = "邮箱密码")
    private String password;

    /** 邮箱别名 */
    @Excel(name = "邮箱别名")
    private String alias;

    /** 连接状态: 1.正常 2.异常 */
    @Excel(name = "连接状态: 1.正常 2.异常")
    private Integer connStatus;

    /** 连接异常原因 */
    @Excel(name = "连接异常原因")
    private String connExceptionReason;

    /** 收件服务器 */
    @Excel(name = "收件服务器")
    private String receivingServer;

    /** 收件端口 */
    @Excel(name = "收件端口")
    private Long receivingPort;

    /** 收件SSL: 0.否 1.是 */
    @Excel(name = "收件SSL: 0.否 1.是")
    private Integer receivingSslFlag;

    /** 发件服务器 */
    @Excel(name = "发件服务器")
    private String outgoingServer;

    /** 发件端口 */
    @Excel(name = "发件端口")
    private Long outgoingPort;

    /** 发件SSL: 0.否 1.是 */
    @Excel(name = "发件SSL: 0.否 1.是")
    private Integer outgoingSslFlag;

    /** 自定义代理: 0.关闭 1.开启 */
    @Excel(name = "自定义代理: 0.关闭 1.开启")
    private Integer customProxyFlag;

    /** 代理服务器类型 */
    @Excel(name = "代理服务器类型")
    private Integer proxyServerType;

    /** 代理服务器 */
    @Excel(name = "代理服务器")
    private String proxyServer;

    /** 代理端口 */
    @Excel(name = "代理端口")
    private Long proxyPort;

    /** 代理用户名 */
    @Excel(name = "代理用户名")
    private String proxyUsername;

    /** 代理密码 */
    @Excel(name = "代理密码")
    private String proxyPassword;

    /** 同步文件夹 */
    @Excel(name = "同步文件夹")
    private Integer synchronizeFolderFlag;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    public String getAlias() 
    {
        return alias;
    }
    public void setConnStatus(Integer connStatus) 
    {
        this.connStatus = connStatus;
    }

    public Integer getConnStatus() 
    {
        return connStatus;
    }
    public void setConnExceptionReason(String connExceptionReason) 
    {
        this.connExceptionReason = connExceptionReason;
    }

    public String getConnExceptionReason() 
    {
        return connExceptionReason;
    }
    public void setReceivingServer(String receivingServer) 
    {
        this.receivingServer = receivingServer;
    }

    public String getReceivingServer() 
    {
        return receivingServer;
    }
    public void setReceivingPort(Long receivingPort) 
    {
        this.receivingPort = receivingPort;
    }

    public Long getReceivingPort() 
    {
        return receivingPort;
    }
    public void setReceivingSslFlag(Integer receivingSslFlag) 
    {
        this.receivingSslFlag = receivingSslFlag;
    }

    public Integer getReceivingSslFlag() 
    {
        return receivingSslFlag;
    }
    public void setOutgoingServer(String outgoingServer) 
    {
        this.outgoingServer = outgoingServer;
    }

    public String getOutgoingServer() 
    {
        return outgoingServer;
    }
    public void setOutgoingPort(Long outgoingPort) 
    {
        this.outgoingPort = outgoingPort;
    }

    public Long getOutgoingPort() 
    {
        return outgoingPort;
    }
    public void setOutgoingSslFlag(Integer outgoingSslFlag) 
    {
        this.outgoingSslFlag = outgoingSslFlag;
    }

    public Integer getOutgoingSslFlag() 
    {
        return outgoingSslFlag;
    }
    public void setCustomProxyFlag(Integer customProxyFlag) 
    {
        this.customProxyFlag = customProxyFlag;
    }

    public Integer getCustomProxyFlag() 
    {
        return customProxyFlag;
    }
    public void setProxyServerType(Integer proxyServerType) 
    {
        this.proxyServerType = proxyServerType;
    }

    public Integer getProxyServerType() 
    {
        return proxyServerType;
    }
    public void setProxyServer(String proxyServer) 
    {
        this.proxyServer = proxyServer;
    }

    public String getProxyServer() 
    {
        return proxyServer;
    }
    public void setProxyPort(Long proxyPort) 
    {
        this.proxyPort = proxyPort;
    }

    public Long getProxyPort() 
    {
        return proxyPort;
    }
    public void setProxyUsername(String proxyUsername) 
    {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyUsername() 
    {
        return proxyUsername;
    }
    public void setProxyPassword(String proxyPassword) 
    {
        this.proxyPassword = proxyPassword;
    }

    public String getProxyPassword() 
    {
        return proxyPassword;
    }
    public void setSynchronizeFolderFlag(Integer synchronizeFolderFlag) 
    {
        this.synchronizeFolderFlag = synchronizeFolderFlag;
    }

    public Integer getSynchronizeFolderFlag() 
    {
        return synchronizeFolderFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("alias", getAlias())
            .append("connStatus", getConnStatus())
            .append("connExceptionReason", getConnExceptionReason())
            .append("receivingServer", getReceivingServer())
            .append("receivingPort", getReceivingPort())
            .append("receivingSslFlag", getReceivingSslFlag())
            .append("outgoingServer", getOutgoingServer())
            .append("outgoingPort", getOutgoingPort())
            .append("outgoingSslFlag", getOutgoingSslFlag())
            .append("customProxyFlag", getCustomProxyFlag())
            .append("proxyServerType", getProxyServerType())
            .append("proxyServer", getProxyServer())
            .append("proxyPort", getProxyPort())
            .append("proxyUsername", getProxyUsername())
            .append("proxyPassword", getProxyPassword())
            .append("synchronizeFolderFlag", getSynchronizeFolderFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
