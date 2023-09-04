package com.ruoyi.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.util.Properties;

public class EmailSender {

    public static void sendEmailWithAttachmentsAndProxy(String host, String port, boolean useSSL, String mailFrom, String password,
                                                         String mailTo, String mailCc, String mailBcc,
                                                         String subject, String message, String proxyHost, String proxyPort,
                                                         String[] attachmentPaths) throws MessagingException {
        Properties properties = new Properties();

        if (useSSL) {
            properties.put("mail.smtps.host", host);
            properties.put("mail.smtps.port", port);
            properties.put("mail.smtps.auth", "true");
            properties.put("mail.smtps.starttls.enable", "true");
            properties.put("mail.smtps.ssl.checkserveridentity", "true");
            properties.put("mail.smtps.ssl.trust", host);
        } else {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
        }

        if (proxyHost != null && !proxyHost.isEmpty()) {
            if (useSSL) {
                properties.put("mail.smtps.proxy.host", proxyHost);
                properties.put("mail.smtps.proxy.port", proxyPort);
            } else {
                properties.put("mail.smtp.proxy.host", proxyHost);
                properties.put("mail.smtp.proxy.port", proxyPort);
            }
        }

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(mailFrom));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

        if (mailCc != null && !mailCc.isEmpty()) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailCc));
        }

        if (mailBcc != null && !mailBcc.isEmpty()) {
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailBcc));
        }

        msg.setSubject(subject);

        // 创建多部分消息
        Multipart multipart = new MimeMultipart();

        // 创建文本消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);
        multipart.addBodyPart(messageBodyPart);

        // 添加附件
        if (attachmentPaths != null && attachmentPaths.length > 0) {
            for (String attachmentPath : attachmentPaths) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(attachmentPath);
                attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                attachmentPart.setFileName(fileDataSource.getName());
                multipart.addBodyPart(attachmentPart);
            }
        }

        // 设置消息的多部分内容
        msg.setContent(multipart);

        if (useSSL) {
            Transport transport = session.getTransport("smtps");
            transport.connect(host, mailFrom, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } else {
            Transport.send(msg);
        }
    }

    public static void main(String[] args) {
        try {
            sendEmailWithAttachmentsAndProxy("mail.sohu.com", "465", false,"w0r1d_space@sohu.com", "QI14ATMVBC",
                    "w0r1d_space@sina.com", "13076506601@163.com", "278882047@qq.com",
                    "Test Subject", "Test Message", null, null, new String[] {"D:\\back\\Desktop\\5\\1.txt"});
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
