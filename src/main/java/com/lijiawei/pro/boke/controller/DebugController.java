package com.lijiawei.pro.boke.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Slf4j
@RequestMapping("/debug")
@RestController
@Validated
public class DebugController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/deep/hello")
    public String deepHello() {
        return "deepHello";
    }

    @GetMapping("valid")
    public String valid(@Min(1) Integer param) {
        return "test";
    }

    @PostMapping("validPost")
    public String validPost(@Min(1) Integer param) {
        return "testPass";
    }

}
