package com.yevhenpanko.pcommerce.service.impl;

import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import com.yevhenpanko.pcommerce.service.PermissionChecker;
import com.yevhenpanko.pcommerce.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.yevhenpanko.pcommerce.entity.user.Permission.DELETE_USER;

@Service
public class DefaultUserManagement implements UserManagement {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PermissionChecker permissionChecker;

    @Autowired
    public DefaultUserManagement(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PermissionChecker permissionChecker
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.permissionChecker = permissionChecker;
    }

    @Override
    public Optional<UserShortDTO> readById(long id) {
        return userRepository.findById(id).map(this::toUserShortDTO);
    }

    @Override
    public Optional<UserShortDTO> readByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toUserShortDTO);
    }

    @Override
    public long create(String email, String password, UserRole role) {
        if (role.isNotPersisted()) {
            role = userRoleRepository.save(role);
        }

        final User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user).getId();
    }

    @Override
    public long create(String email, String password, String firstName, String lastName, UserRole role) {
        if (role.isNotPersisted()) {
            role = userRoleRepository.save(role);
        }

        final User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        return userRepository.save(user).getId();
    }

    @Override
    public void deleteById(long actorId, long userId) {
        if (permissionChecker.checkIfUserHasPermission(actorId, DELETE_USER)) {
            final Optional<UserShortDTO> user = readById(userId);
            user.ifPresent(u -> userRepository.deleteById(u.getId()));
        }
    }
}
