package com.github.kbednarz.controller;

import com.github.kbednarz.model.PostsEntity;
import com.github.kbednarz.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;

/**
 * Created by Kamil on 2016-06-28.
 */
@Controller
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping("/posts/{id}")
    public String posts(@PathVariable Long id, Model model){
        model.addAttribute("posts", postsRepository.findOne(id));
        return "posts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createPage(Model model){
        model.addAttribute("newPost", new PostsEntity());
        return "create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createPost(@ModelAttribute("newPost") PostsEntity entity){
        entity.setDate(new Date(System.currentTimeMillis()));
        postsRepository.save(entity);
        return "redirect:/index";
    }
}
