package com.github.kbednarz.controller;

import com.github.kbednarz.model.PostsEntity;
import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;

/**
 * Created by Kamil on 2016-06-29.
 */
@Controller
public class LoggingController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String index(Model model){
        model.addAttribute("userEntity",new UserEntity());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String createPost(Model model,@ModelAttribute("userEntity") UserEntity userEntity){
        userRepository.findByName(userEntity.getName());
        model.addAttribute("isUserExists",true);
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String createNewUser(Model model, @ModelAttribute("userEntity") UserEntity userEntity){
        userRepository.save(new UserEntity(userEntity.getName(),userEntity.getPassword()));
        model.addAttribute("isRegistered",true);
        return "redirect:/index";
    }
}
