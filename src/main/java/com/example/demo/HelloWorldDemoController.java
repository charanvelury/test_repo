package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldDemoController
{
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
