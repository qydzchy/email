package com.ruoyi.email;

import com.ruoyi.email.domain.Task;
import com.ruoyi.email.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmailTest {

    @Autowired
    private ITaskService taskService;

    @Test
    public void getEmailTest() {

    }


    private static final String USER_NAME = "w0r1d_space@sohu.com";  // 你的Sohu邮箱用户名
    private static final String PASSWORD = "RCKVZGTBSMY2F";   // 你的Sohu邮箱密码

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", "pop3.sohu.com");
        properties.put("mail.pop3.port", 110);
        properties.put("mail.pop3.ssl.enable", false);
        properties.put("mail.pop3.auth", true);

        Session emailSession = Session.getDefaultInstance(properties);
        try {
            Store store = emailSession.getStore("pop3");
            store.connect(USER_NAME, PASSWORD);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("邮件总数: " + messages.length);

            // 仅显示最新的10封邮件
            for (int i = 0; i < Math.min(10, messages.length); i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("邮件主题: " + message.getSubject());
                System.out.println("发件人: " + message.getFrom()[0]);
                System.out.println("邮件内容: " + message.getContent().toString());
            }

            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void taskList() {
        List<Task> tasks = taskService.selectTaskList(new Task());
        log.info("{}", tasks);
    }
}
