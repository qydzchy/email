package com.ruoyi.email.service.handler.email;

import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MailContext {

    private Map<ProtocolTypeEnum, IMailService> protocolTypeMap = new HashMap<>();

    @Autowired
    public void setProtocolTypeMap(List<IMailService> mailServiceList){
        mailServiceList.forEach((mailService) -> {
            protocolTypeMap.put(mailService.getProtocolTypeEnum(), mailService);
        });
    }

    /**
     * 创建连接
     */
    public MailConn createConn(ProtocolTypeEnum protocolTypeEnum, MailConnCfg mailConnCfg, boolean proxyFlag) throws MailPlusException {
        IMailService mailService = protocolTypeMap.get(protocolTypeEnum);
        return mailService.createConn(mailConnCfg, proxyFlag);
    }

    /**
     * 解析文件
     */
    public UniversalMail parseEmail(ProtocolTypeEnum protocolTypeEnum, MailItem mailItem, String localSavePath) throws MailPlusException {
        IMailService mailService = protocolTypeMap.get(protocolTypeEnum);
        return mailService.parseEmail(mailItem, localSavePath);
    }

    /**
     * 获取邮件列表
     */
    public List<MailItem> listAll(ProtocolTypeEnum protocolTypeEnum, MailConn mailConn, List<String> existUidList) throws MailPlusException {
        IMailService mailService = protocolTypeMap.get(protocolTypeEnum);
        return mailService.listAll(mailConn, existUidList);
    }
}
