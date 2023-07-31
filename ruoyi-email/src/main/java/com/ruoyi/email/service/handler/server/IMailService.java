package com.ruoyi.email.service.handler.server;

import com.ruoyi.common.exception.mailbox.MailPlusException;

import java.util.List;

/**
 * 邮件服务接口
 * @author tangJM.
 * @date 2021/10/13
 * @description
 */
public interface IMailService {
    UniversalMail parseEmail(MailItem mailItem, String localSavePath) throws MailPlusException;

    List<MailItem> listAll(MailConn mailConn, String var2, List<String> var3) throws MailPlusException;

    MailConn createConn(MailConnCfg mailConnCfg, boolean proxyFlag) throws MailPlusException;
}

