package com.github.kbednarz.controller;

import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserRoles;
import com.github.kbednarz.repo.UserRepository;
import com.github.kbednarz.repo.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class LoginRestController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value="/rest/user", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUser() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<UserEntity>(user,HttpStatus.OK);

    }

    @RequestMapping(value="/rest/user", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(UserEntity user) {
        if ( userRepository.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        long idFromDatabase = userRepository.findByUsername(user.getUsername()).getId();
        UserRoles role = new UserRoles(idFromDatabase,"ROLE_USER");
        userRolesRepository.save(role);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
