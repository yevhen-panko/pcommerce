package com.yevhenpanko.pcommerce.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

@Table(name = "USER_ROLE")
public class UserRole extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @ElementCollection(targetClass = Permission.class, fetch = EAGER)
    @Enumerated(STRING)
    @CollectionTable(name = "USER_ROLE_PERMISSION")
    @Column(name = "PERMISSION")
    private List<Permission> permissions;

    public UserRole() {
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
}
