package com.github.kbednarz.service;

import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserEntityDetails;
import com.github.kbednarz.model.UserRoles;
import com.github.kbednarz.repo.UserRepository;
import com.github.kbednarz.repo.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserEntityDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("No user with username "+username);
        }
        List<String> userRoles=userRolesRepository.findRoleByUserName(username);
        return new UserEntityDetails(userEntity,userRoles);
    }
}
