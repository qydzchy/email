package com.ruoyi.email;

import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.email.service.handler.email.MailItemParser;
import com.ruoyi.email.service.handler.email.UniversalAttachment;
import com.ruoyi.email.service.handler.email.UniversalMail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmlParserWithoutAccount {

    public static void main(String[] args) {
        String emlFilePath = "D:\\back\\Desktop\\mailbox\\w0r1d_space@sohu.com\\A16Z合伙人：5G来了，我们能干啥？-2019-10-11.eml";

        try {
            // Get the Session object
            Session session = Session.getDefaultInstance(new Properties());

            // Create a MimeMessage object from the EML file
            MimeMessage mimeMessage = new MimeMessage(session, new FileInputStream(new File(emlFilePath)));

            try {
                UniversalMail universalMail = MailItemParser.parseMimeMessage(mimeMessage);
                String emlPath = MailItemParser.saveMimiMessageAsLocalEml(mimeMessage, "D:\\back\\Desktop\\mailbox\\w0r1d_space@sohu.com");
                List<UniversalAttachment> universalAttachments = MailItemParser.parseAttachment(mimeMessage, "D:\\back\\Desktop\\attachment\\w0r1d_space@sohu.com");
                universalMail.setAttachments(universalAttachments);
                universalMail.setEmlPath(emlPath);
                MailItemParser.setMessageHeaderParam(mimeMessage, universalMail);

                System.out.println(universalMail);

            } catch (MailPlusException e) {

            }

        } catch(Exception e) {
          e.printStackTrace();
        }
    }
}
