package com.ruoyi.email.service.handler.email;

import com.ruoyi.common.enums.ProxyTypeEnum;
import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.email.domain.bo.EmailOperateParamBO;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.util.*;


@Slf4j
@Component
public class ImapService implements IMailService {

    public ImapService() {
    }

    public UniversalMail parseEmail(MailItem mailItem, EmailOperateParamBO emailOperateParamBO, String localSavePath, String attachmentPath) throws MailPlusException {
        return MailItemParser.parseMail(mailItem, localSavePath, attachmentPath);
    }

    public List<MailItem> listAll(MailConn mailConn, EmailOperateParamBO emailOperateParamBO, List<String> existUidList) throws MailPlusException {
        int numEmailsToFetch = emailOperateParamBO.getMaxPerPage();
        IMAPStore imapStore = mailConn.getImapStore();
        List<MailItem> mList = Collections.synchronizedList(new ArrayList<>());

        try {
            Folder defaultFolder = imapStore.getDefaultFolder();
            Folder[] list = defaultFolder.list();

            for (Folder folder : list) {
                IMAPFolder imapFolder = (IMAPFolder) folder;
                if (!imapFolder.isOpen()) {
                    imapFolder.open(Folder.READ_WRITE);
                }

                boolean flag = imapFolder.getName().equalsIgnoreCase("[gmail]") ?
                        listGmailMessageFolder(mList, imapFolder, existUidList, numEmailsToFetch) :
                        listFolderMessage(mList, imapFolder, existUidList, numEmailsToFetch);

                if (flag) break;
            }

            return mList;
        } catch (MessagingException ex) {
            String content = String.format("【IMAP服务】打开文件夹/获取邮件列表失败，错误信息【{%s}】", ex.getMessage());
            log.error(content);
            throw new MailPlusException(content);
        }
    }

    private boolean listGmailMessageFolder(List<MailItem> mList, IMAPFolder imapFolder, List<String> existUids, int numEmailsToFetch) throws MessagingException {
        Folder[] list = imapFolder.list();
        boolean flag = false;

        for (Folder folder : list) {
            IMAPFolder subFolder = (IMAPFolder) folder;
            flag = listFolderMessage(mList, subFolder, existUids, numEmailsToFetch);
            if (flag) break;
        }

        return flag;
    }

    private boolean listFolderMessage(List<MailItem> mList, IMAPFolder imapFolder, List<String> existUidList, int numEmailsToFetch) throws MessagingException {
        boolean flag = false;
        if (!imapFolder.isOpen()) {
            imapFolder.open(Folder.READ_WRITE);
        }

        FetchProfile profile = new FetchProfile();
        profile.add(UIDFolder.FetchProfileItem.UID);

        // 从邮件夹的末尾获取最新的邮件
        int startMessage = Math.max(1, imapFolder.getMessageCount() - numEmailsToFetch + 1);
        int endMessage = imapFolder.getMessageCount();

        Message[] messages = imapFolder.getMessages(startMessage, endMessage);
        imapFolder.fetch(messages, profile);

        for (Message message : messages) {
            // 拉取的邮件数
            if (mList.size() == numEmailsToFetch) break;

            try {
                String uid = imapFolder.getFullName() + imapFolder.getUID((IMAPMessage) message);
                if (uid == null || uid.isEmpty()) {
                    uid = getUniqueHash(message);
                }

                // Skip already processed mails
                if (existUidList != null && existUidList.contains(uid)) {
                    continue;
                }

                if (!message.getFolder().isOpen()) {
                    message.getFolder().open(Folder.READ_WRITE);
                }

                mList.add(MailItem.builder().imapMessage((IMAPMessage) message).uid(uid).build());

            } catch (Exception e) {
                log.error("imap - 获取邮件异常，异常原因：", e);
            }
        }

        return flag;
    }

    private String getUniqueHash(Message message) throws MessagingException {
        String data = message.getSentDate().toString() + Arrays.toString(message.getFrom()) + message.getSubject();
        return String.valueOf(data.hashCode());
    }

    public MailConn createConn(MailConnCfg mailConnCfg, boolean proxyFlag) throws MailPlusException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", mailConnCfg.getHost());
        properties.put("mail.imap.port", mailConnCfg.getPort());
        properties.put("mail.imap.ssl.enable", mailConnCfg.isSsl());
        properties.put("mail.imap.partialfetch", false);
        properties.put("mail.imap.starttls.enable", false);
        properties.put("mail.imap.auth", true);

        if (proxyFlag && mailConnCfg.getProxyType() != null) {
            ProxyTypeEnum proxyType = mailConnCfg.getProxyType();
            if (proxyType.equals(ProxyTypeEnum.SOCKS)) {
                properties.put("mail.imap.socks.host", mailConnCfg.getSocksProxyHost());
                properties.put("mail.imap.socks.port", mailConnCfg.getSocksProxyPort());
            } else if (proxyType.equals(ProxyTypeEnum.HTTP)) {
                properties.put("mail.imap.proxy.host", mailConnCfg.getProxyHost());
                properties.put("mail.imap.proxy.port", mailConnCfg.getProxyPort());
            }
        }

        Session session = Session.getInstance(properties);

        try {
            Store store = session.getStore("imap");
            store.connect(mailConnCfg.getEmail(), mailConnCfg.getPassword());
            return MailConn.builder().imapStore((IMAPStore)store).build();
        } catch (NoSuchProviderException var6) {
            log.error("var6-------" + var6.getMessage() + "----------");
            throw new MailPlusException(var6.getMessage());
        } catch (MessagingException var7) {
            log.error("var7-------" + var7.getMessage() + "----------");
            throw new MailPlusException(var7.getMessage());
        }
    }

    @Override
    public ProtocolTypeEnum getProtocolTypeEnum() {
        return ProtocolTypeEnum.IMAP;
    }
}
