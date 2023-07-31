package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮箱服务器查询对象 mailbox_host
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public class MailboxHost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 域名 */
    @Excel(name = "域名")
    private String domain;

    /** pop3服务器地址 */
    @Excel(name = "pop3服务器地址")
    private String popHost;

    /** imap服务器地址 */
    @Excel(name = "imap服务器地址")
    private String imapHost;

    /** exchange服务器地址,exchange有自动识别的方法,此字段作为备用字段 */
    @Excel(name = "exchange服务器地址,exchange有自动识别的方法,此字段作为备用字段")
    private String exchangeHost;

    /** smtp服务器地址 */
    @Excel(name = "smtp服务器地址")
    private String smtpHost;

    /** pop端口号 */
    @Excel(name = "pop端口号")
    private Long popPort;

    /** imap端口号 */
    @Excel(name = "imap端口号")
    private Long imapPort;

    /** 冗余字段 */
    @Excel(name = "冗余字段")
    private Long exchangePort;

    /** smtp端口号 */
    @Excel(name = "smtp端口号")
    private Long smtpPort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDomain(String domain) 
    {
        this.domain = domain;
    }

    public String getDomain() 
    {
        return domain;
    }
    public void setPopHost(String popHost) 
    {
        this.popHost = popHost;
    }

    public String getPopHost() 
    {
        return popHost;
    }
    public void setImapHost(String imapHost) 
    {
        this.imapHost = imapHost;
    }

    public String getImapHost() 
    {
        return imapHost;
    }
    public void setExchangeHost(String exchangeHost) 
    {
        this.exchangeHost = exchangeHost;
    }

    public String getExchangeHost() 
    {
        return exchangeHost;
    }
    public void setSmtpHost(String smtpHost) 
    {
        this.smtpHost = smtpHost;
    }

    public String getSmtpHost() 
    {
        return smtpHost;
    }
    public void setPopPort(Long popPort) 
    {
        this.popPort = popPort;
    }

    public Long getPopPort() 
    {
        return popPort;
    }
    public void setImapPort(Long imapPort) 
    {
        this.imapPort = imapPort;
    }

    public Long getImapPort() 
    {
        return imapPort;
    }
    public void setExchangePort(Long exchangePort) 
    {
        this.exchangePort = exchangePort;
    }

    public Long getExchangePort() 
    {
        return exchangePort;
    }
    public void setSmtpPort(Long smtpPort) 
    {
        this.smtpPort = smtpPort;
    }

    public Long getSmtpPort() 
    {
        return smtpPort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("domain", getDomain())
            .append("popHost", getPopHost())
            .append("imapHost", getImapHost())
            .append("exchangeHost", getExchangeHost())
            .append("smtpHost", getSmtpHost())
            .append("popPort", getPopPort())
            .append("imapPort", getImapPort())
            .append("exchangePort", getExchangePort())
            .append("smtpPort", getSmtpPort())
            .toString();
    }
}
