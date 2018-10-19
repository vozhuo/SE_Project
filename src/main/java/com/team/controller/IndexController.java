package com.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("loginPage")
    public String login_web(){
        return "login";
    }

    @RequestMapping("login_test")
    public String test(){
        return "login_test";
    }

    @RequestMapping("user_info")
    public String Info(){
        return "user_info";
    }
}
