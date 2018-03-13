package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import com.yevhenpanko.pcommerce.service.DefaultUserManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.yevhenpanko.pcommerce.entity.user.Permission.READ_PERMISSIONS;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UserManagementTest extends AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(UserManagementTest.class);

    @Autowired
    private DefaultUserManagement userManagement;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreatingUserWithSimpleRole() {
        final UserRole userRole = new UserRole(getUniqueName("test"), READ_PERMISSIONS);
        long userId = userManagement.create(getUniqueName("demo") + "@mail.com", "password", userRole);

        final Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isPresent());

        final UserRole role = user.get().getRole();
        assertNotNull(role);

        userManagement.deleteById(userId);
    }

}
