package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.entity.user.Permission;
import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Utility class to check user permissions
 *
 * @author Yevhen Panko
 */
@Service
public class PermissionChecker {

    private final UserRepository userRepository;

    @Autowired
    public PermissionChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * <p>
     * Checks if user has wanted permission.
     * Returns false if there is no user with @userId or @permission is null
     * or user does not have wanted permission.
     * Returns true if user has wanted permission.
     * </p>
     *
     * @param userId     - id of the user for whom permission should be checked.
     * @param permission - wanted permission.
     * @return true if user has wanted permission and false otherwise.
     */
    public boolean checkIfUserHasPermission(long userId, Permission permission) {
        final Optional<User> user = userRepository.findById(userId);

        return user.isPresent()
                && permission != null
                && user.get().getRole().getPermissions().contains(permission);

    }
}
