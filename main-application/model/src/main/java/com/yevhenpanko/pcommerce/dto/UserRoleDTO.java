package com.yevhenpanko.pcommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yevhenpanko.pcommerce.entity.user.Permission;

import java.io.Serializable;
import java.util.List;

public class UserRoleDTO implements Serializable {
    private long id;
    private String name;
    private String description;

    /**
     * Keeps short name to be compatible with Spring API.
     * ROLE_ADMIN will be shortened to ADMIN
     */
    @JsonIgnore
    private transient String shortName;

    /**
     * Confidential information. Must be hidden by default.
     */
    @JsonIgnore
    private transient List<Permission> permissions;

    public UserRoleDTO(long id, String name, String description, List<Permission> permissions) {
        this.id = id;
        this.name = name;

        final int underscoreIndex = name.indexOf("_");
        if (underscoreIndex > 0) {
            shortName = name.substring(underscoreIndex + 1);
        } else {
            shortName = name;
        }

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
                ", shortName='" + shortName + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
