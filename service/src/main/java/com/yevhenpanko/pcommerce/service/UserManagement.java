package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.entity.user.UserRole;

import java.util.Optional;

public interface UserManagement {

    Optional<UserShortDTO> readById(long id);

    long create(String email, String password, UserRole role);

    long create(String email, String password, String firstName, String lastName, UserRole role);

    void deleteById(long userId);
}
