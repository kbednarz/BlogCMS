package com.github.kbednarz.controller;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlogCmsApplication.class)
@WebAppConfiguration
public class LoginRestControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void getUser_shouldNotPass() throws Exception {
//        mockMvc.perform(get("/rest/user"));
//
//    }

    @Test
    public void registerUser() throws Exception {
        UserEntity user = new UserEntity("user45","pass2435");

        mockMvc.perform(post("/rest/user")
        .param("username",user.getUsername())
        .param("password",user.getPassword()))
            .andExpect(status().isOk());

    }

}