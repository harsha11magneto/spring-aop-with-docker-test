package com.jira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {

    @RequestMapping("/")
    public String greeting(){
        return "<h1> kill bill.... <h/1>";
    }
}
