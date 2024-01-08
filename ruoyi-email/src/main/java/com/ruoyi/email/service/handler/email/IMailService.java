package com.ruoyi.email.service.handler.email;

import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.email.domain.bo.EmailOperateParamBO;

import java.util.List;

/**
 * 邮件服务接口
 * @author tangJM.
 * @date 2021/10/13
 * @description
 */
public interface IMailService {

    /**
     * 创建连接
     * @param mailConnCfg
     * @param proxyFlag
     * @return
     * @throws MailPlusException
     */
    MailConn createConn(MailConnCfg mailConnCfg, boolean proxyFlag) throws MailPlusException;

    /**
     * 解析邮件
     * @param mailItem
     * @param localSavePath 邮件路径
     * @param attachmentPath 附件路径
     * @return 获取邮件内容
     * @throws MailPlusException
     */
    UniversalMail parseEmail(MailItem mailItem, String localSavePath, String attachmentPath) throws MailPlusException;

    /**
     * 获取邮件列表
     * @param mailConn
     * @param existUidList
     * @return
     * @throws MailPlusException
     */
    List<MailItem> listAll(MailConn mailConn, List<String> existUidList) throws MailPlusException;


    ProtocolTypeEnum getProtocolTypeEnum();
}

