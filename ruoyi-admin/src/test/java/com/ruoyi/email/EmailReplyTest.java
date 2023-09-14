package com.ruoyi.email;

import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.imap.protocol.IMAPProtocol;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class EmailReplyTest {

    public static void main(String[] args) throws MessagingException {
        // 发送邮件
        // sendEmail("13076506601@163.com", "测试邮件10000", "回复测试邮件");
        Message[] messages = new Message[0];
        try {
            messages = receiveEmails();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Message message : messages) {
            replyEmail(message, "163邮箱，代码回复测试。");
        }

    }

    /**
     * 第一步 发送邮件
     * @param toAddress
     * @param subject
     * @param content
     */
    public static void sendEmail(String toAddress, String subject, String content) {
        Session session = getTomSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("w0r1d_space@tom.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            /*String customMessageId = "<"+ UUID.randomUUID() +"@example.com>";
            System.out.println(customMessageId);
            message.setHeader("Message-ID", customMessageId);*/
            /*message.setHeader("In-Reply-To", customMessageId);
            message.setHeader("References", customMessageId);*/

            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            String[] header = message.getHeader("Message-ID");
            System.out.println(header != null && header.length > 0 ? header[0] : null);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Session getTomSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.tom.com");
        properties.put("mail.smtp.port", "25");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("w0r1d_space@tom.com", "6yhn7UJM");
            }
        });
        return session;
    }

    private static Session get163Session() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.163.com");  // 使用 163 的 SMTP 服务器
        properties.put("mail.smtp.port", "465");  // 163 的 SSL 端口
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("13076506601@163.com", "NMTCFCJFEFUJENJX");  // 使用 163 邮箱的凭证
            }
        });
        return session;
    }

    /**
     * 163 邮箱获取邮件的方式
     * 第二步 接受邮件
     * @return
     */
    public static Message[] receiveEmails() throws Exception {
        String user = "13076506601@163.com";// 邮箱的用户名
        String password = "NMTCFCJFEFUJENJX"; // 邮箱的密码

        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", "imap.163.com");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop);

        // 使用imap会话机制，连接服务器
        int total = 0;
        IMAPStore store = (IMAPStore) session.getStore("imap");
        store.connect(user, password);

        final Map<String, String> clientParams = new HashMap<String, String>();
        clientParams.put("name", "my-imap");
        clientParams.put("version", "1.0");

        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX");
        folder.doOptionalCommand("ID not supported",
                new IMAPFolder.ProtocolCommand() {
                    @Override
                    public Object doCommand(IMAPProtocol p)
                            throws ProtocolException {
                        return p.id(clientParams);
                    }
                });
        folder.open(Folder.READ_ONLY);

        // 获取总邮件数
        total = folder.getMessageCount();
       // System.out.println("-----------------共有邮件：" + total + " 封--------------");
        // 得到收件箱文件夹信息，获取邮件列表
       // System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
        Message[] messages = folder.getMessages();
        int messageNumber = 0;
        for (Message message : messages) {
         //   System.out.println("发送时间：" + message.getSentDate());
          //  System.out.println("主题：" + message.getSubject());
           // System.out.println("内容：" + message.getContent());
            Flags flags = message.getFlags();
            if (flags.contains(Flags.Flag.SEEN)) {
           //     System.out.println("这是一封已读邮件");
            } else {
            //    System.out.println("未读邮件");
            }

            //每封邮件都有一个MessageNumber，可以通过邮件的MessageNumber在收件箱里面取得该邮件
            messageNumber = message.getMessageNumber();

            String[] messageId = message.getHeader("Message-ID");
            String[] inReplyTo = message.getHeader("In-Reply-To");
            String[] references = message.getHeader("References");
            System.out.println("-------------------------------");
        }
        Message message = folder.getMessage(messageNumber);
     //   System.out.println(message.getContent() + message.getContentType());
        // 释放资源
        /*if (folder != null) {
            folder.close(true);
        }

        if (store != null) {
            store.close();
        }*/
        return messages;
    }


    /**
     * 第三步 回复邮件
     */
    public static void replyEmail(Message originalMessage, String replyContent) {
        Session session = get163Session();
        try {
            Message reply = new MimeMessage(session);
            reply.setFrom(new InternetAddress("13076506601@163.com"));
            reply.setRecipients(Message.RecipientType.TO, originalMessage.getFrom());
            reply.setSubject("Re: " + originalMessage.getSubject());

            String originalMessageId = originalMessage.getHeader("Message-ID")[0];
            System.out.println("originalMessageId = " + originalMessageId);
            reply.setHeader("In-Reply-To", originalMessageId);
            reply.setHeader("References", originalMessageId);

            reply.setText(replyContent);

            Transport.send(reply);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
