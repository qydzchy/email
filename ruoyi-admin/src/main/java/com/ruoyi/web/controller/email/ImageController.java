package com.ruoyi.web.controller.email;

import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.email.service.ITaskEmailSendService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/image")
public class ImageController {

    @javax.annotation.Resource
    private ITaskEmailSendService taskEmailSendService;

    @GetMapping("/path/to/tracker.png")
    public ResponseEntity<Resource> pathTo(@RequestParam("id") String id, HttpServletRequest request) {
        if (id != null) {
            String ipAddr = IpUtils.getIpAddr();
            // todo
        }

        // 返回图片
        Resource imgFile = new ClassPathResource("static/tracker.png");
        return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_PNG).body(imgFile);
    }
}
