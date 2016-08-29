package com.github.kbednarz.repo;

import com.github.kbednarz.BlogCmsApplication;
import com.github.kbednarz.model.UserEntity;
import com.github.kbednarz.model.UserRoles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogCmsApplication.class)
public class UserRolesRepositoryTest {
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void findRoleByUserName() throws Exception {
        UserEntity user = new UserEntity("useruser","somepass");
        userRepository.save(user);

        UserRoles role = new UserRoles(23,"ROLE_USER");
        userRolesRepository.save(role);

        List<String> actualRole;
        actualRole = userRolesRepository.findRoleByUserName(user.getUsername());

        List<String> expectedRole = new ArrayList<>();
        expectedRole.add(role.getRole());

        assertNotNull(actualRole);
        assertEquals(expectedRole,actualRole);
    }

}