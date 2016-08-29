package com.github.kbednarz.repo;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogCmsApplication.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    
    @Test
    public void findByUsername() throws Exception {
        UserEntity expectedUser = new UserEntity("user_jack","jack_password23");
        userRepository.save(expectedUser);
        
        UserEntity actualUser = userRepository.findByUsername(expectedUser.getUsername());

        assertNotNull(actualUser);
        assertEquals(expectedUser.getUsername(),actualUser.getUsername());
        assertEquals(expectedUser.getPassword(),actualUser.getPassword());
    }

}