package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserRoleDTO;
import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;

public class Transformer {

    public static UserShortDTO toUserShortDTO(User user) {
        return new UserShortDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                toUserRoleDTO(user.getRole())
        );
    }

    public static UserRoleDTO toUserRoleDTO(UserRole userRole) {
        return new UserRoleDTO(
                userRole.getId(),
                userRole.getName(),
                userRole.getDescription(),
                userRole.getPermissions()
        );
    }
}
