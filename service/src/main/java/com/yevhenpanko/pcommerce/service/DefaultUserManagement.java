package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserRoleDTO;
import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.yevhenpanko.pcommerce.entity.user.Permission.DELETE_USER;

@Service
public class DefaultUserManagement implements UserManagement {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PermissionChecker permissionChecker;

    @Override
    public Optional<UserShortDTO> readById(long id) {
        final Optional<User> user = userRepository.findById(id);

        return user.map(u -> {
            final UserRole role = u.getRole();

            return new UserShortDTO(
                    u.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    new UserRoleDTO(
                            role.getId(),
                            role.getName(),
                            role.getDescription(),
                            role.getPermissions()));
        });
    }

    @Override
    public long create(String email, String password, UserRole role) {
        if (!role.isPersisted()) {
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
        if (!role.isPersisted()) {
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
