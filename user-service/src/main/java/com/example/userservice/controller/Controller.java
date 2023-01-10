package com.example.userservice.controller;

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
}
