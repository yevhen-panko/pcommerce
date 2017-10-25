package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.entity.User;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestUserRepository extends AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(TestUserRepository.class);

    @Autowired
    private UserRepository userRepository;

    @BeforeMethod
    @AfterMethod
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        userRepository.save(new User("demo@mail.com", "password"));

        final List<User> users = userRepository.findAll();
        LOGGER.info(users.toString());
    }

}
