package com.yevhenpanko.pcommerce.springconfig;

import com.yevhenpanko.pcommerce.dto.UserRoleDTO;
import com.yevhenpanko.pcommerce.entity.user.Permission;
import com.yevhenpanko.pcommerce.service.UserRoleManagement;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.List;

public class SpringURLAccessConfigurer {
    private final HttpSecurity http;
    private final UserRoleManagement userRoleManagement;

    public SpringURLAccessConfigurer(HttpSecurity http, UserRoleManagement userRoleManagement) {
        this.http = http;
        this.userRoleManagement = userRoleManagement;
    }

    public SpringURLAccessConfigurer anyRoleWithPermission(String url, Permission permission) throws Exception {
        final List<UserRoleDTO> userRoles = userRoleManagement.readUserRolesByPermission(permission);

        final String[] roles = new String[userRoles.size()];
        for (int i = 0; i < userRoles.size(); i++) {
            roles[i] = userRoles.get(i).getShortName();
        }

        http.authorizeRequests().antMatchers(url).hasAnyRole(roles);

        return this;
    }

    public SpringURLAccessConfigurer permitAll(String url) throws Exception {
        http.authorizeRequests().antMatchers(url).permitAll();

        return this;
    }
}
