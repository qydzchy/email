package com.ruoyi.email.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮箱服务器查询对象 mailbox_host
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class Host extends BaseEntity
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
    private Integer popPort;

    /** imap端口号 */
    @Excel(name = "imap端口号")
    private Integer imapPort;

    /** 冗余字段 */
    @Excel(name = "冗余字段")
    private Integer exchangePort;

    /** smtp端口号 */
    @Excel(name = "smtp端口号")
    private Integer smtpPort;

    /** pop ssl */
    @Excel(name = "pop ssl")
    private Boolean popSsl;

    /** imap ssl */
    @Excel(name = "imap ssl")
    private Boolean imapSsl;

    /** exchange ssl */
    @Excel(name = "exchange ssl")
    private Boolean exchangeSsl;

    /** smtp ssl */
    @Excel(name = "smtp ssl")
    private Boolean smtpSsl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPopHost() {
        return popHost;
    }

    public void setPopHost(String popHost) {
        this.popHost = popHost;
    }

    public String getImapHost() {
        return imapHost;
    }

    public void setImapHost(String imapHost) {
        this.imapHost = imapHost;
    }

    public String getExchangeHost() {
        return exchangeHost;
    }

    public void setExchangeHost(String exchangeHost) {
        this.exchangeHost = exchangeHost;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getPopPort() {
        return popPort;
    }

    public void setPopPort(Integer popPort) {
        this.popPort = popPort;
    }

    public Integer getImapPort() {
        return imapPort;
    }

    public void setImapPort(Integer imapPort) {
        this.imapPort = imapPort;
    }

    public Integer getExchangePort() {
        return exchangePort;
    }

    public void setExchangePort(Integer exchangePort) {
        this.exchangePort = exchangePort;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Boolean getPopSsl() {
        return popSsl;
    }

    public void setPopSsl(Boolean popSsl) {
        this.popSsl = popSsl;
    }

    public Boolean getImapSsl() {
        return imapSsl;
    }

    public void setImapSsl(Boolean imapSsl) {
        this.imapSsl = imapSsl;
    }

    public Boolean getExchangeSsl() {
        return exchangeSsl;
    }

    public void setExchangeSsl(Boolean exchangeSsl) {
        this.exchangeSsl = exchangeSsl;
    }

    public Boolean getSmtpSsl() {
        return smtpSsl;
    }

    public void setSmtpSsl(Boolean smtpSsl) {
        this.smtpSsl = smtpSsl;
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", popHost='" + popHost + '\'' +
                ", imapHost='" + imapHost + '\'' +
                ", exchangeHost='" + exchangeHost + '\'' +
                ", smtpHost='" + smtpHost + '\'' +
                ", popPort=" + popPort +
                ", imapPort=" + imapPort +
                ", exchangePort=" + exchangePort +
                ", smtpPort=" + smtpPort +
                ", popSsl=" + popSsl +
                ", imapSsl=" + imapSsl +
                ", exchangeSsl=" + exchangeSsl +
                ", smtpSsl=" + smtpSsl +
                '}';
    }
}
