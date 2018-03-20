package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserRoleDTO;
import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserManagement {

    Optional<UserShortDTO> readById(long id);

    Optional<UserShortDTO> readByEmail(String email);

    @Transactional
    long create(String email, String password, UserRole role);

    @Transactional
    long create(String email, String password, String firstName, String lastName, UserRole role);

    /**
     * Deletes user if user has permissions to delete users.
     *
     * @param actorId - id of user who tries to do action
     * @param userId  - id of user to delete
     */
    @Transactional
    void deleteById(long actorId, long userId);

    default UserShortDTO toUserShortDTO(User user) {
        return new UserShortDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                toUserRoleDTO(user.getRole())
        );
    }

    default UserRoleDTO toUserRoleDTO(UserRole userRole) {
        return new UserRoleDTO(
                userRole.getId(),
                userRole.getName(),
                userRole.getDescription(),
                userRole.getPermissions()
        );
    }
}
