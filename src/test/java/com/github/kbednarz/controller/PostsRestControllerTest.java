package com.github.kbednarz.controller;

import com.github.kbednarz.BlogCmsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlogCmsApplication.class)
@WebAppConfiguration
public class PostsRestControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void showAllPosts() throws Exception {
        mockMvc.perform(get("/rest/posts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void showSpecificPost_shouldFind() throws Exception {

        mockMvc.perform(get("/rest/posts/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewPost() throws Exception {

      /*  mockMvc.perform(post("/rest/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content()
        )
                .andExpect(status().isCreated());*/
    }

    @Test
    public void updatePost() throws Exception {

    }

    @Test
    public void deletePost() throws Exception {

    }

}