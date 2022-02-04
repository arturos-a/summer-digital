package com.artur.summer.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AppController {
    @GetMapping(value = "/client/**")
    String resolveApp() throws IOException {
        return "index";
    }

    @GetMapping(value = "/")
    String resolveMainPage() throws IOException {
        return "index";
    }
}
