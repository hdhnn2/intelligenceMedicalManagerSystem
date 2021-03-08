package com.hd.imms.performance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class DictionaryController {
    @GetMapping("/hello")
    public String hello(){
        return "hello Spring Boot 2.0";
    }
}
