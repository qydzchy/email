package com.ruoyi.email.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditTaskDTO {

    /** 主键 */
    @NotNull(message = "主键不能为空")
    private Long id;

    /** 邮箱账号 */
    @NotBlank(message = "邮箱账号不能为空")
    private String account;

    /** 邮箱密码 */
    @NotBlank(message = "邮箱密码不能为空")
    private String password;

    @NotNull(message = "协议类型不能为空")
    private Integer protocolType;

    /** 收件服务器 */
    @NotBlank(message = "收件服务器不能为空")
    private String receivingServer;

    /** 收件端口 */
    @NotNull(message = "收件端口不能为空")
    private Integer receivingPort;

    /** 收件SSL: 0.否 1.是 */
    @NotNull(message = "收件SSL不能为空")
    private Boolean receivingSslFlag;

    /** 发件服务器 */
    @NotBlank(message = "发件服务器不能为空")
    private String outgoingServer;

    /** 发件端口 */
    @NotNull(message = "发件端口不能为空")
    private Integer outgoingPort;

    /** 发件SSL: 0.否 1.是 */
    @NotNull(message = "发件SSL不能为空")
    private Boolean outgoingSslFlag;

    /** 自定义代理: 0.关闭 1.开启 */
    @NotNull(message = "自定义代理不能为空")
    private Boolean customProxyFlag;

    /** 代理服务器类型 */
    private Integer proxyServerType;

    /** 代理服务器 */
    private String proxyServer;

    /** 代理端口 */
    private Integer proxyPort;

    /** 代理用户名 */
    private String proxyUsername;

    /** 代理密码 */
    private String proxyPassword;

    /** 同步文件夹 */
    @NotNull(message = "同步文件夹不能为空")
    private Boolean synchronizeFolderFlag;
}
