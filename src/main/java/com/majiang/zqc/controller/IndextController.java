package com.majiang.zqc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndextController {
    @GetMapping("/")
    public  String index(){
        System.out.println("asdasd");
        return "index";
    }
}
