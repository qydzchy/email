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
            properties.put("mail.smtps.ssl.protocols", "TLSv1.2");
        } else {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");
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

        String customMessageId = "<" + java.util.UUID.randomUUID().toString() + "@example.com>";
        System.out.println(customMessageId);
        msg.setHeader("Message-ID", customMessageId);
        msg.setHeader("In-Reply-To", customMessageId);
        msg.setHeader("References", customMessageId);

        String[] header = msg.getHeader("Message-ID");

        msg.setFrom(new InternetAddress(mailFrom));
        InternetAddress[] toAddresses = InternetAddress.parse("w0r1d_space@tom.com,1640878597@qq.com");
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

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
        messageBodyPart.setContent("<html><body>" + message + "<img src='https://yourserver.com/path/to/tracker.png' alt='' width='1' height='1' border='0' /></body></html>", "text/html");
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
            sendEmailWithAttachmentsAndProxy("mail.sohu.com", "465", true,"w0r1d_space@sohu.com", "QI14ATMVBC",
                    "w0r1d_space@tom.com", "13076506601@163.com", "278882047@qq.com",
                    "Test Subject 04", "Test Message", null, null, new String[] {"D:\\back\\Desktop\\upload\\1.txt", "D:\\back\\Desktop\\upload\\2.txt", "D:\\back\\Desktop\\upload\\sql.txt", "D:\\back\\Desktop\\upload\\tracker.png"});
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
