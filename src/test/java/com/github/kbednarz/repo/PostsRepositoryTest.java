package com.github.kbednarz.repo;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogCmsApplication.class)
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void shouldFindPost(){
        Date date = new Date(System.currentTimeMillis());
        PostEntity initialEntity = new PostEntity("Kamil","Tiiiittttle","Content ipsum",date);
        postsRepository.save(initialEntity);

        PostEntity entity;
        Iterator<PostEntity> iter;
        iter = postsRepository.findAll().iterator();
        do{
            entity = iter.next();
        } while (iter.hasNext());

        assertNotNull(entity);
        assertEquals(initialEntity.getAuthor(),entity.getAuthor());
        assertEquals(initialEntity.getContent(),entity.getContent());
        assertEquals(initialEntity.getTitle(),entity.getTitle());
        assertEquals(initialEntity.getDate().toString().trim(),entity.getDate().toString().trim());

    }
}