package com.yevhenpanko.pcommerce.dto;

import com.yevhenpanko.pcommerce.entity.user.Permission;

import java.io.Serializable;
import java.util.List;

public class UserRoleDTO implements Serializable {
    private long id;
    private String name;
    private String description;
    private List<Permission> permissions;

    public UserRoleDTO(long id, String name, String description, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public UserRoleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
