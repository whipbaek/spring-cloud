package com.example.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class Controller {
    @GetMapping("/info")
    public String info() {
        return "Order 서비스 동작합니다";
    }
}
