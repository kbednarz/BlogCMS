package com.github.kbednarz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kamil on 2016-06-29.
 */
@Controller
public class IndexController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsRepository.findAll());
        return "index";
    }
}
