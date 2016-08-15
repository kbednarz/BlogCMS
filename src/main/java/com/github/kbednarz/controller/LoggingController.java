package com.github.kbednarz.controller;

import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserRoles;
import com.github.kbednarz.repo.UserRepository;
import com.github.kbednarz.repo.UserRolesRepository;
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
    @Autowired
    UserRolesRepository userRolesRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String index(Model model){
        model.addAttribute("userEntity",new UserEntity());
        return "login";
    }



    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String createNewUser(Model model, @ModelAttribute("userEntity") UserEntity userEntity){
        userRepository.save(new UserEntity(userEntity.getUsername(),userEntity.getPassword()));
        long userId = userRepository.findByUsername(userEntity.getUsername()).getId();
        UserRoles userRoles = new UserRoles(userId,"ROLE_USER");
        userRolesRepository.save(userRoles);
        return "login";
    }
}
