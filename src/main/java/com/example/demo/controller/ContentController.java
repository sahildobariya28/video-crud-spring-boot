package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    
    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/video/")
    public String getVideo() {
        return "video";
    }
}
