package com.ruoyi.email.service.handler.email;

import com.ruoyi.common.enums.ProxyTypeEnum;
import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Message;
import com.sun.mail.pop3.POP3Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class Pop3Service implements IMailService {

    public Pop3Service() {
    }

    public UniversalMail parseEmail(MailItem mailItem, String localSavePath) throws MailPlusException {
        return MailItemParser.parseMail(mailItem, localSavePath);
    }

    public List<MailItem> listAll(MailConn mailConn, String dateFormat, List<String> existUids) throws MailPlusException {
        int numEmailsToFetch = 300;
        POP3Store pop3Store = mailConn.getPop3Store();
        List<MailItem> mList = Collections.synchronizedList(new ArrayList<>());

        try {
            POP3Folder pop3Folder = (POP3Folder)pop3Store.getFolder("INBOX");
            pop3Folder.open(Folder.READ_WRITE);

            int totalMessages = pop3Folder.getMessageCount();
            int end = totalMessages;
            int fetched = 0;

            while (fetched < numEmailsToFetch && end > 0) {
                int start = Math.max(end - numEmailsToFetch + fetched + 1, 1);
                Message[] messages = pop3Folder.getMessages(start, end);

                for (Message message : messages) {
                    if (fetched >= numEmailsToFetch) break;

                    try {
                        String uid = pop3Folder.getUID(message);
                        if (uid == null || uid.isEmpty()) {
                            uid = getUniqueHash(message);
                        }

                        if (existUids != null && existUids.contains(uid)) {
                            continue;  // 跳过已经存在的邮件
                        }

                        if (!message.getFolder().isOpen()) {
                            message.getFolder().open(Folder.READ_WRITE);
                        }

                        mList.add(MailItem.builder().pop3Message((POP3Message) message).uid(uid).build());
                        fetched++;

                    } catch (Exception e) {
                        log.error("pop3 - 获取邮件异常，异常原因：" + "\t" + e.getMessage());
                        e.printStackTrace();
                    }
                }
                end = start - 1;  // 准备拉取更早的邮件
            }

            return mList;
        } catch (MessagingException var10) {
            var10.printStackTrace();
            throw new MailPlusException(String.format("【POP3服务】打开文件夹/获取邮件列表失败，错误信息【{}】"));
        }
    }

    /**
     * uid若是为空，则生成uid
     * @param message
     * @return
     * @throws MessagingException
     */
    private String getUniqueHash(Message message) throws MessagingException {
        String data = message.getSentDate().toString() + message.getFrom()[0].toString() + message.getSubject();
        return String.valueOf(data.hashCode());
    }


    public MailConn createConn(MailConnCfg mailConnCfg, boolean proxy) throws MailPlusException {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", mailConnCfg.getHost());
        properties.put("mail.pop3.port", mailConnCfg.getPort());
        properties.put("mail.pop3.ssl.enable", mailConnCfg.isSsl());
        properties.put("mail.pop3.auth", true);
        if (proxy && mailConnCfg.getProxyType() != null) {
            ProxyTypeEnum proxyType = mailConnCfg.getProxyType();
            if (proxyType.equals(ProxyTypeEnum.HTTP)) {
                properties.put("mail.pop3.proxy.host", mailConnCfg.getProxyHost());
                properties.put("mail.pop3.proxy.port", mailConnCfg.getProxyPort());
            } else if (proxyType.equals(ProxyTypeEnum.SOCKS)) {
                properties.put("mail.pop3.socks.host", mailConnCfg.getSocksProxyHost());
                properties.put("mail.pop3.socks.port", mailConnCfg.getSocksProxyPort());
            }
        }

        Session session = Session.getInstance(properties);

        try {
            Store store = session.getStore("pop3");
            store.connect(mailConnCfg.getEmail(), mailConnCfg.getPassword());
            return MailConn.builder().pop3Store((POP3Store)store).build();
        } catch (NoSuchProviderException var6) {
            var6.printStackTrace();
            throw new MailPlusException(var6.getMessage());
        } catch (MessagingException var7) {
            var7.printStackTrace();
            throw new MailPlusException(var7.getMessage());
        }
    }

    @Override
    public ProtocolTypeEnum getProtocolTypeEnum() {
        return ProtocolTypeEnum.POP3;
    }
}
