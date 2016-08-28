package com.github.kbednarz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @RequestMapping("/posts")
    public String showPosts(){
        return "posts";
    }

    @RequestMapping("/post/**")
    public String showPost(){
        return "post";
    }

    @RequestMapping("/create")
    public String createPosts(){
        return "create";
    }

    @RequestMapping("/manage-posts")
    public String managePosts(){
        return "manage-posts";
    }

}
