package com.example.cors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("/{name}")
    public String helloName(@PathVariable("name") String name) {
        return "Hello, " + name;
    }
}
