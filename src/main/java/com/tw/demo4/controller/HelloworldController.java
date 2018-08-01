package com.tw.demo4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloworldController {
    @GetMapping
    public ResponseEntity<String> helloWorld() {
        String testConnection = "hello world";
        return ResponseEntity.ok(testConnection);
    }
}
