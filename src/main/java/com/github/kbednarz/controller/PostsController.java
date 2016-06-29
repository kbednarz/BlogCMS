package com.github.kbednarz.controller;

import com.github.kbednarz.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kamil on 2016-06-28.
 */
@Controller
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping("/posts/{id}")
    public String posts(@PathVariable Long id, Model model){
//        model.addAttribute("posts", postsRepository.findOne(id));
        model.addAttribute("posts", id);
        return "posts";
    }

    @RequestMapping("/create")
    public String create(){

        return "create";
    }
}
