package org.derryfield.isp2025.mlswebsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheck {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
