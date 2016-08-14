package com.github.kbednarz.controller;

import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String logInUser(Model model,@ModelAttribute("userEntity") UserEntity userEntity){
        UserEntity user = userRepository.findByUsername(userEntity.getUsername());
        if(user!= null) model.addAttribute("isUserExists",true);
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String createNewUser(Model model, @ModelAttribute("userEntity") UserEntity userEntity){
        userRepository.save(new UserEntity(userEntity.getUsername(),userEntity.getPassword()));
        model.addAttribute("isRegistered",true);
        return "login";
    }
}
