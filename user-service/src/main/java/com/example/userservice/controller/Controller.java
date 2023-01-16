package com.example.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Controller {
    @GetMapping("/info")
    public String info() {
        return "User 서비스 동작합니다";
    }

    @GetMapping("/config")
    public String configPage(@Value("${message.owner}") String messageOwner,
                             @Value("${message.content}") String messageContent){
        return "Configuration File's Message Owner: " + messageOwner + "\n"
                + "Configuration File's Message Content: " + messageContent;
    }
}
