package com.ruoyi.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailReplyTest {

    public static void main(String[] args) throws MessagingException {
        // 发送邮件
       // sendEmail("13076506601@163.com", "测试邮件01", "回复测试邮件");
        Message[] messages = receiveEmails();
        for (Message message : messages) {
            String[] messageId = message.getHeader("Message-ID");
            String[] inReplyTo = message.getHeader("In-Reply-To");
            String[] references = message.getHeader("References");

            System.out.println(messageId);
            System.out.println(inReplyTo);
            System.out.println(references);
        }
    }

    /**
     * 第一步 发送邮件
     * @param toAddress
     * @param subject
     * @param content
     */
    public static void sendEmail(String toAddress, String subject, String content) {
        Session session = getSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("w0r1d_space@tom.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            String customMessageId = "test_message_id";
            System.out.println(customMessageId);
            message.setHeader("Message-ID", customMessageId);
            message.setHeader("In-Reply-To", customMessageId);
            message.setHeader("References", customMessageId);
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Session getSession() {
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

    /**
     * 第二步 接受邮件
     * @return
     */
    public static Message[] receiveEmails() {
        Properties properties = new Properties();
        properties.put("mail.imap.host", "imap.163.com");
        properties.put("mail.imap.port", 993);
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.auth.mechanisms", "login");
        properties.put("mail.imap.ssl.trust", "*");


        Session session = Session.getDefaultInstance(properties);

        try {

            Store store = session.getStore("imap");
            store.connect("13076506601@163.com", "NMTCFCJFEFUJENJX");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            return inbox.getMessages();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 第三步 回复邮件
     */
    public static void replyEmail(Message originalMessage, String replyContent) {
        Session session = getSession();
        try {
            Message reply = new MimeMessage(session);
            reply.setFrom(new InternetAddress("yourEmail@example.com"));
            reply.setRecipients(Message.RecipientType.TO, originalMessage.getFrom());
            reply.setSubject("Re: " + originalMessage.getSubject());

            String originalMessageId = originalMessage.getHeader("Message-ID")[0];
            reply.setHeader("In-Reply-To", originalMessageId);
            reply.setHeader("References", originalMessageId);

            reply.setText(replyContent);

            Transport.send(reply);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
