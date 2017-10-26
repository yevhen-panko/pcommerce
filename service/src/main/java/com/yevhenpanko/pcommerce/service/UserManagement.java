package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.entity.user.User;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRepository;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagement {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public long createUser(String email, String password, UserRole role) {
        if (!role.isPersisted()) {
            role = userRoleRepository.save(role);
        }

        final User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user).getId();
    }

    public long createUser(
            String email,
            String password,
            String firstName,
            String lastName,
            UserRole role
    ) {
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
}
