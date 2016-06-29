package com.github.kbednarz.repo;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.PostsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by Kamil on 2016-06-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogCmsApplication.class)
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void shouldFind(){
        Date date = new Date(System.currentTimeMillis());
        PostsEntity initialEntity = new PostsEntity("Kamil","Tytul","Zawartosc",date);
        postsRepository.save(initialEntity);

        PostsEntity entity;
        entity = postsRepository.findOne(initialEntity.getId());

        assertNotNull(entity);
        assertEquals(initialEntity.getAuthor(),entity.getAuthor());

    }
}