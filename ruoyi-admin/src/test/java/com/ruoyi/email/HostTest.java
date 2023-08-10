package com.ruoyi.email;

import com.ruoyi.email.domain.Host;
import com.ruoyi.email.mapper.HostMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HostTest {

    @Autowired
    private HostMapper hostMapper;

    @Test
    public void selectByDomain() {
        Host host = hostMapper.selectByDomain("sohu.com");
        System.out.println(host);

    }
}
