package com.yevhenpanko.pcommerce.service;

import com.yevhenpanko.pcommerce.dto.UserRoleDTO;
import com.yevhenpanko.pcommerce.entity.user.Permission;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.yevhenpanko.pcommerce.service.Transformer.toUserRoleDTO;

@Service
public class UserRoleManagement {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleManagement(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDTO> readUserRolesByPermission(Permission permission) {
        final List<UserRole> userRoles = userRoleRepository.findAllByPermissionsIsContaining(permission);

        final List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            userRoleDTOList.add(toUserRoleDTO(userRole));
        }

        return userRoleDTOList;
    }
}
