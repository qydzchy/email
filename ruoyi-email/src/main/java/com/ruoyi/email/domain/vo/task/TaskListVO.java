package com.ruoyi.email.domain.vo.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskListVO {

    /** 主键 */
    private Long id;

    /** 邮箱账号 */
    private String account;

    /** 邮箱别名 */
    private String alias;

    /** 连接状态: 1.正常 2.异常 */
    private Integer connStatus;

    /** 连接异常原因 */
    private String connExceptionReason;

    private Integer protocolType;

    /** 收件服务器 */
    private String receivingServer;

    /** 收件端口 */
    private Integer receivingPort;

    /** 收件SSL: 0.否 1.是 */
    private Boolean receivingSslFlag;

    /** 发件服务器 */
    private String outgoingServer;

    /** 发件端口 */
    private Integer outgoingPort;

    /** 发件SSL: 0.否 1.是 */
    private Boolean outgoingSslFlag;

    /** 自定义代理: 0.关闭 1.开启 */
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
    private Boolean synchronizeFolderFlag;
}
