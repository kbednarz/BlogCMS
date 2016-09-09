package com.github.kbednarz.repo;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserRoles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogCmsApplication.class)
public class UserRolesRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
    private  UserEntity user;

    @Before
    public void setup() {
        user = new UserEntity("user467","somepass");
        userRepository.save(user);
    }

    @Test
    public void findRoleByUserName() throws Exception {
        user = userRepository.findByUsername(user.getUsername());
        UserRoles role = new UserRoles(user.getId(),"ROLE_USER");
        userRolesRepository.save(role);

        List<String> actualRole;
        actualRole = userRolesRepository.findRoleByUserName(user.getUsername());

        List<String> expectedRole = new ArrayList<>();
        expectedRole.add(role.getRole());

        assertNotNull(actualRole);
        assertEquals(expectedRole,actualRole);
    }



}