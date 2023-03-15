package com.letmespringyou.springbootconvertoauth2usertoentity.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
    @RequestMapping(value = "/sign-in", produces = {MediaType.TEXT_HTML_VALUE})
    public String index() {
        return "sign-in";
    }
}