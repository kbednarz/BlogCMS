package com.github.kbednarz.service;

import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserEntityDetails;
import com.github.kbednarz.model.UserRoles;
import com.github.kbednarz.repo.UserRepository;
import com.github.kbednarz.repo.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("No postEntity with username: "+username);
        }
        List<String> userRoles=userRolesRepository.findRoleByUserName(username);
        return new UserEntityDetails(userEntity,userRoles);

    }
}
