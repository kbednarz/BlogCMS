package com.github.kbednarz.controller;

import com.github.kbednarz.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kamil on 2016-06-29.
 */
@Controller
public class IndexController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("posts", postsRepository.findAll());
        return "index";
    }
}
