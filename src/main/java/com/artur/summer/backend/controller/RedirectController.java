package com.artur.summer.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RedirectController {
    @GetMapping(value = "/client/**")
    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
