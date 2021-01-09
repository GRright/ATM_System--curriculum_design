package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/index")
    public String login(){
        return "/font/login.html";
    }
    @RequestMapping("/draw")
    public String draw(){
        return "/font/draw.html";
    }
    @RequestMapping("/input")
    public String input(){
        return "/font/input.html";
    }
    @RequestMapping("/transfer")
    public String transfer(){
        return "/font/transfer.html";
    }
}
