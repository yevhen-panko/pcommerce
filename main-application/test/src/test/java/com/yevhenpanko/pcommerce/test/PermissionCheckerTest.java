package com.yevhenpanko.pcommerce.test;

import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import com.yevhenpanko.pcommerce.service.PermissionChecker;
import com.yevhenpanko.pcommerce.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.yevhenpanko.pcommerce.entity.user.Permission.READ_PERMISSIONS;
import static com.yevhenpanko.pcommerce.entity.user.Permission.UPDATE_PERMISSIONS;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PermissionCheckerTest extends AbstractTest {
    private static final long WRONG_USER_ID = -1L;

    @Autowired
    private UserManagement userManagement;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PermissionChecker permissionChecker;

    private long userId;

    @BeforeClass
    private void setUp() {
        final UserRole userRole = new UserRole(getUniqueName("test"), READ_PERMISSIONS);
        userId = userManagement.create(getUniqueName("demo") + "@mail.com", "password", userRole);
    }

    @AfterClass
    private void cleanUp() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    public void testCheckIfUserHasPermissionToRead() {
        assertTrue(permissionChecker.checkIfUserHasPermission(userId, READ_PERMISSIONS));
    }

    @Test
    public void testCheckIfUserUnableToUpdate() {
        assertFalse(permissionChecker.checkIfUserHasPermission(userId, UPDATE_PERMISSIONS));
        assertFalse(permissionChecker.checkIfUserHasPermission(WRONG_USER_ID, READ_PERMISSIONS));
        assertFalse(permissionChecker.checkIfUserHasPermission(WRONG_USER_ID, READ_PERMISSIONS));
    }

    @Test
    public void testCheckIfUserHasPermissionMethodWorksWithWrongParams() {
        assertFalse(permissionChecker.checkIfUserHasPermission(WRONG_USER_ID, READ_PERMISSIONS));
        assertFalse(permissionChecker.checkIfUserHasPermission(userId, null));
        assertFalse(permissionChecker.checkIfUserHasPermission(WRONG_USER_ID, null));
    }
}