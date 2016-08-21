package com.github.kbednarz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Model model){
        return "login";
    }
}
