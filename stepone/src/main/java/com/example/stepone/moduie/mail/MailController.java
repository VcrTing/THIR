package com.example.stepone.moduie.mail;

import com.example.stepone.tools.MailTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    MailTool mailTool;

    String to = "vcrting@163.com";
    String subject = "这里是简单的 SUBJECT";

    @GetMapping("/mail")
    public Object send() {
        mailTool.sendMail(to, subject, subject);
        return "OK";
    }

    @GetMapping("/mail/html")
    public Object sendHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<body><h2>标题</h2></body>");

        mailTool.sendHtml(to, subject, sb.toString());
        return "OK";
    }
}
