package com.letmespringyou.springbootconvertoauth2usertoentity.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInController {
    @RequestMapping("/login/oauth2")
    public String index() {
        return "login";
    }
}
