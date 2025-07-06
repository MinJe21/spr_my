package com.example.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // templates/signup.html
    }

    @GetMapping("/create")
    public String createPostPage() {
        return "create";  // templates/create-post.html
    }

    @GetMapping("/read")
    public String readPage() {
        return "read"; // static/read.html 렌더링
    }

}
