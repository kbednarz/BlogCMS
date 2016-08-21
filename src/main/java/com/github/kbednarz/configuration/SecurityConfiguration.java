package com.github.kbednarz.configuration;

import com.github.kbednarz.service.UserEntityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserEntityDetailsService userEntityDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin().loginPage("/login").and()
            .authorizeRequests()
            .antMatchers("/index", "/posts", "/login", "/").permitAll()
            .antMatchers("/user").hasRole("USER")
            .anyRequest().permitAll().and()
            .csrf().disable();  //enabling causes h2 console error

        // This makes H2 console working properly
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityDetailsService);
    }
}