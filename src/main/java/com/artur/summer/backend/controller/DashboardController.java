package com.artur.summer.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class DashboardController {
    @PostMapping(value = "/main")
    public String test() {
        return "ttt";
    }
}
